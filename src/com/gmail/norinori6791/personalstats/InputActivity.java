package com.gmail.norinori6791.personalstats;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

public class InputActivity extends Activity implements OnItemClickListener, OnClickListener {
	
	public final int REQUEST_ACTIVITY_TEST = 1;
	
	static String[] menuItems = { "全期間平均", "ライブ入力", "検索・編集・共有・削除", "インポート", "エクスポート"};
	static ArrayAdapter<String> adapter;
	
	DrawerLayout drawerLayout;
	ListView drawerListView;
	ActionBarDrawerToggle drawerToggle;
	
	private Button btn;
	private BasketBall bb;
	private ScoreBookDBHelper db;
	private SQLiteDatabase mdb;
	
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_input);
	  
	  findViews();
	  setListView();
	  setDrawer();
	  
	  btn = (Button) findViewById(R.id.button1);
	  btn.setOnClickListener(this);
	  
		edit_3pt = (EditText)findViewById(R.id.ET_3pt);
		edit_3pt_attemp = (EditText)findViewById(R.id.ET_3pt_A);
		edit_2pt = (EditText)findViewById(R.id.ET_2pt);
		edit_2pt_attemp = (EditText)findViewById(R.id.ET_2pt_A);
		edit_ft = (EditText)findViewById(R.id.ET_ft);
		edit_ft_attemp = (EditText)findViewById(R.id.ET_ft_A);
		edit_ast = (EditText)findViewById(R.id.ET_ast);
		edit_oreb = (EditText)findViewById(R.id.ET_oreb);
		edit_stl = (EditText)findViewById(R.id.ET_stl);
		edit_blk = (EditText)findViewById(R.id.ET_blk);
		edit_dreb = (EditText)findViewById(R.id.ET_dreb);
		edit_to = (EditText)findViewById(R.id.ET_to);
	  
	  bb = new BasketBall();
	  db = new ScoreBookDBHelper(this);
	}
	@SuppressLint("SimpleDateFormat")
	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		
		Calendar calendar = Calendar.getInstance();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String strSysdate = sdf.format(calendar.getTime());
		
		// 空の場合
		if(edit_3pt.getText().length() == 0){
			edit_3pt.setText("0");
		}
		if(edit_3pt_attemp.getText().length() == 0){
			edit_3pt_attemp.setText("0");
		}
		if(edit_2pt.getText().length() == 0){
			edit_2pt.setText("0");
		}
		if(edit_2pt_attemp.getText().length() == 0){
			edit_2pt_attemp.setText("0");
		}
		if(edit_2pt.getText().length() == 0){
			edit_2pt.setText("0");
		}
		if(edit_2pt_attemp.getText().length() == 0){
			edit_2pt_attemp.setText("0");
		}
		if(edit_ft.getText().length() == 0){
			edit_ft.setText("0");
		}
		if(edit_ft_attemp.getText().length() == 0){
			edit_ft_attemp.setText("0");
		}
		if(edit_ast.getText().length() == 0){
			edit_ast.setText("0");
		}
		if(edit_oreb.getText().length() == 0){
			edit_oreb.setText("0");
		}
		if(edit_stl.getText().length() == 0){
			edit_stl.setText("0");
		}
		if(edit_blk.getText().length() == 0){
			edit_blk.setText("0");
		}
		if(edit_dreb.getText().length() == 0){
			edit_dreb.setText("0");
		}
		if(edit_to.getText().length() == 0){
			edit_to.setText("0");
		}
		
		if(edit_3pt_attemp.getText().length() == 0){
			edit_3pt_attemp.setText("0");
		}
		if(edit_2pt.getText().length() == 0){
			edit_2pt.setText("0");
		}
		if(edit_2pt_attemp.getText().length() == 0){
			edit_2pt_attemp.setText("0");
		}
		if(edit_2pt.getText().length() == 0){
			edit_2pt.setText("0");
		}
		if(edit_2pt_attemp.getText().length() == 0){
			edit_2pt_attemp.setText("0");
		}
		if(edit_ft.getText().length() == 0){
			edit_ft.setText("0");
		}
		if(edit_ft_attemp.getText().length() == 0){
			edit_ft_attemp.setText("0");
		}
		if(edit_ast.getText().length() == 0){
			edit_ast.setText("0");
		}
		if(edit_oreb.getText().length() == 0){
			edit_oreb.setText("0");
		}
		if(edit_stl.getText().length() == 0){
			edit_stl.setText("0");
		}
		if(edit_blk.getText().length() == 0){
			edit_blk.setText("0");
		}
		if(edit_dreb.getText().length() == 0){
			edit_dreb.setText("0");
		}
		if(edit_to.getText().length() == 0){
			edit_to.setText("0");
		}
		
		
		// セット
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
		
		val.put(ScoreBookDBHelper.DATE, strSysdate);
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
		
		mdb.insert(ScoreBookDBHelper.TABLE_NAME, null, val);
		
		Toast.makeText(this, "記録が完了しました", Toast.LENGTH_SHORT).show();
		
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
		edit_2pt.requestFocus();
		
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
				Intent intent = new Intent(getApplicationContext(), EasyInputActivity.class);
				startActivityForResult(intent, REQUEST_ACTIVITY_TEST);
			}
			if(position==2){
				Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
				startActivityForResult(intent, REQUEST_ACTIVITY_TEST);
			}
			if(position==3){
				Intent intent = new Intent(getApplicationContext(), ImportActivity.class);
				startActivityForResult(intent, REQUEST_ACTIVITY_TEST);
			}
			if(position==4){
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
