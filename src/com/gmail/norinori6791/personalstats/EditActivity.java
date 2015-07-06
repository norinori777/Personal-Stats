package com.gmail.norinori6791.personalstats;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class EditActivity extends Activity implements OnItemClickListener, OnClickListener {
	
	public final int REQUEST_ACTIVITY_TEST = 1;
	
	static String[] menuItems = { "全期間平均", "記録入力", "ライブ入力","検索・編集・共有・削除","インポート", "エクスポート"  };
	static ArrayAdapter<String> adapter;
	
	DrawerLayout drawerLayout;
	ListView drawerListView;
	ActionBarDrawerToggle drawerToggle;
	
	private Button btn;
	private BasketBall bb;
	private ScoreBookDBHelper db;
	private SQLiteDatabase mdb;
	private Intent intent;
	
	private EditText edit_3pt;
	private EditText edit_3pt_attemp;
	private EditText edit_2pt;
	private EditText edit_2pt_attemp;
	private EditText edit_ft;
	private EditText edit_ft_attemp;
	private EditText edit_ast;
	private EditText edit_oreb;
	private EditText edit_stl;
	private EditText edit_blk;
	private EditText edit_dreb;
	private EditText edit_to;
	private String in_date;
	private String re_date_from;
	private String re_date_to;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
	  
		findViews();
		setListView();
		setDrawer();
	  
		btn = (Button) findViewById(R.id.edit_button);
		btn.setOnClickListener(this);
	  
		edit_3pt = (EditText)findViewById(R.id.EditText_3pt);
		edit_3pt_attemp = (EditText)findViewById(R.id.EditText_3pt_attempt);
		edit_2pt = (EditText)findViewById(R.id.EditText_2pt);
		edit_2pt_attemp = (EditText)findViewById(R.id.EditText_2pt_attempt);
		edit_ft = (EditText)findViewById(R.id.EditText_ft);
		edit_ft_attemp = (EditText)findViewById(R.id.EditText_ft_attempt);
		edit_ast = (EditText)findViewById(R.id.EditText_ast);
		edit_oreb = (EditText)findViewById(R.id.EditText_oreb);
		edit_stl = (EditText)findViewById(R.id.EditText_stl);
		edit_blk = (EditText)findViewById(R.id.EditText_blk);
		edit_dreb = (EditText)findViewById(R.id.EditText_dreb);
		edit_to = (EditText)findViewById(R.id.EditText_to);
		
		intent = getIntent();
		if(intent != null){
			edit_3pt.setText(String.valueOf(intent.getIntExtra("3pt", 0)));
			edit_3pt_attemp.setText(String.valueOf(intent.getIntExtra("3pt_attempt", 0)));
			edit_2pt.setText(String.valueOf(intent.getIntExtra("2pt", 0)));
			edit_2pt_attemp.setText(String.valueOf(intent.getIntExtra("2pt_attempt", 0)));
			edit_ft.setText(String.valueOf(intent.getIntExtra("FT", 0)));
			edit_ft_attemp.setText(String.valueOf(intent.getIntExtra("FT_attempt", 0)));
			edit_ast.setText(String.valueOf(intent.getIntExtra("ast",0)));
			edit_oreb.setText(String.valueOf(intent.getIntExtra("oreb",0)));
			edit_stl.setText(String.valueOf(intent.getIntExtra("stl", 0)));
			edit_blk.setText(String.valueOf(intent.getIntExtra("blk", 0)));
			edit_dreb.setText(String.valueOf(intent.getIntExtra("dreb", 0)));
			edit_to.setText(String.valueOf(intent.getIntExtra("to", 0)));
			in_date = intent.getStringExtra("date");
			re_date_from = intent.getStringExtra("from");
			re_date_to = intent.getStringExtra("to");
		}
		bb = new BasketBall();
		db = new ScoreBookDBHelper(this);
	}
	@SuppressLint("SimpleDateFormat")
	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		bb.setSuccessGoal3(Integer.parseInt(edit_3pt.getText().toString()));
		bb.setAttemptGoal3(Integer.parseInt(edit_3pt_attemp.getText().toString()));
		bb.setSuccessGoal2(Integer.parseInt(edit_2pt.getText().toString()));
		bb.setAttemptGoal2(Integer.parseInt(edit_2pt_attemp.getText().toString()));
		bb.setSuccessFT(Integer.parseInt(edit_ft.getText().toString()));
		bb.setAttemptFT(Integer.parseInt(edit_ft_attemp.getText().toString()));
		bb.setAsist(Integer.parseInt(edit_ast.getText().toString()));
		bb.setOrebound(Integer.parseInt(edit_oreb.getText().toString()));
		bb.setSteal(Integer.parseInt(edit_stl.getText().toString()));
		bb.setBlock(Integer.parseInt(edit_blk.getText().toString()));
		bb.setDrebound(Integer.parseInt(edit_dreb.getText().toString()));
		bb.setTurnover(Integer.parseInt(edit_to.getText().toString()));
		
		mdb = db.getWritableDatabase();
		
		ContentValues val = new ContentValues();
		
		val.put(ScoreBookDBHelper.THREE_OK, bb.getGoal3());
		val.put(ScoreBookDBHelper.THREE_ATTEMPT, bb.getAttemptGoal3());
		val.put(ScoreBookDBHelper.TWO_OK, bb.getGoal2());
		val.put(ScoreBookDBHelper.TWO_ATTEMPT, bb.getAttemptGoal2());
		val.put(ScoreBookDBHelper.FT_OK, bb.getFT());
		val.put(ScoreBookDBHelper.FT_ATTEMPT, bb.getAttemptFT());
		val.put(ScoreBookDBHelper.AST, bb.getAsist());
		val.put(ScoreBookDBHelper.OREB, bb.getOrebound());
		val.put(ScoreBookDBHelper.STL, bb.getSteal());
		val.put(ScoreBookDBHelper.BLK, bb.getBlock());
		val.put(ScoreBookDBHelper.DREB, bb.getDrebound());
		val.put(ScoreBookDBHelper.TO, bb.getTurnover());
		
		mdb.update(ScoreBookDBHelper.TABLE_NAME, val, ScoreBookDBHelper.DATE + "=" + in_date, null);
		
		Toast.makeText(this, "編集が完了しました", Toast.LENGTH_SHORT).show();
		
		// クリアする
		edit_3pt.getEditableText().clear();
		edit_3pt_attemp.getEditableText().clear();
		edit_2pt.getEditableText().clear();
		edit_2pt_attemp.getEditableText().clear();
		edit_ft.getEditableText().clear();
		edit_ft_attemp.getEditableText().clear();
		edit_ast.getEditableText().clear();
		edit_oreb.getEditableText().clear();
		edit_stl.getEditableText().clear();
		edit_blk.getEditableText().clear();
		edit_dreb.getEditableText().clear();
		edit_to.getEditableText().clear();
		edit_3pt.requestFocus();
		
		//ページ遷移する
		Intent re = new Intent(getApplicationContext(), SearchActivity.class);
		re.putExtra("re_from", re_date_from);
		re.putExtra("re_to", re_date_to);
		startActivityForResult(re, REQUEST_ACTIVITY_TEST);		
	}
	
	protected void findViews(){
		  drawerLayout =
		    (DrawerLayout) findViewById(R.id.drawer_layout_input);
		  drawerListView =
		    (ListView) findViewById(R.id.drawer_listview_input);
		}
		
		protected void setListView(){
		  // データアダプタ
		  adapter = new ArrayAdapter<String>(
		  this,
		  R.layout.listitem,
		  menuItems);
		  drawerListView.setAdapter(adapter);
		  
		  // アイテムの選択
		  drawerListView.setOnItemClickListener(this);
		}
		
		@Override
		public void onItemClick(
		  AdapterView<?> parent, View view, int position, long id) {
			if(position==0){
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				startActivityForResult(intent, REQUEST_ACTIVITY_TEST);
			}
			if(position==1){
				Intent intent = new Intent(getApplicationContext(), InputActivity.class);
				startActivityForResult(intent, REQUEST_ACTIVITY_TEST);
			}
			if(position==2){
				Intent intent = new Intent(getApplicationContext(), EasyInputActivity.class);
				startActivityForResult(intent, REQUEST_ACTIVITY_TEST);
			}
			if(position==3){
				Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
				startActivityForResult(intent, REQUEST_ACTIVITY_TEST);
			}
			if(position==4){
				Intent intent = new Intent(getApplicationContext(), ImportActivity.class);
				startActivityForResult(intent, REQUEST_ACTIVITY_TEST);
			}
			if(position==5){
				Intent intent = new Intent(getApplicationContext(), ExportActivity.class);
				startActivityForResult(intent, REQUEST_ACTIVITY_TEST);
			}
		 	drawerLayout.closeDrawers();
		}
		
		protected void setDrawer(){
		  getActionBar().setDisplayHomeAsUpEnabled(true);
		  getActionBar().setHomeButtonEnabled(true);
		
		  drawerToggle = new ActionBarDrawerToggle(
		    this,
		    drawerLayout,
		    R.drawable.ic_drawer,
		    R.string.drawer_open,
		    R.string.drawer_close);
		  drawerLayout.setDrawerListener(drawerToggle);
		}
		
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
		  if (drawerToggle.onOptionsItemSelected(item)) {
		    return true;
		  }
		  return super.onOptionsItemSelected(item);
		}
		
		@Override
		protected void onPostCreate(Bundle savedInstanceState) {
		  super.onPostCreate(savedInstanceState);
		  drawerToggle.syncState();
		}

		@Override
		public void onConfigurationChanged(Configuration newConfig) {
		  super.onConfigurationChanged(newConfig);
		  drawerToggle.onConfigurationChanged(newConfig);
		}
}
