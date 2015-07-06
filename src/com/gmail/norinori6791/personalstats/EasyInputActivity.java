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
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class EasyInputActivity extends Activity implements OnItemClickListener, OnClickListener, OnLongClickListener {
	
	public final int REQUEST_ACTIVITY_TEST = 1;
	
	static String[] menuItems = { "全期間平均", "記録入力", "検索・編集・共有・削除", "インポート", "エクスポート"};
	static ArrayAdapter<String> adapter;
	
	DrawerLayout drawerLayout;
	ListView drawerListView;
	ActionBarDrawerToggle drawerToggle;
	
	private Button btn;
	private BasketBall bb;
	private ScoreBookDBHelper db;
	private SQLiteDatabase mdb;
	
	private Button btn_3pt_s;
	private Button btn_3pt_f;
	private Button btn_2pt_s;
	private Button btn_2pt_f;
	private Button btn_ft_s;
	private Button btn_ft_f;
	private Button btn_ast;
	private Button btn_oreb;
	private Button btn_stl;
	private Button btn_blk;
	private Button btn_dreb;
	private Button btn_to;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_easy_input);
	  
	  findViews();
	  setListView();
	  setDrawer();
	  
	  btn = (Button) findViewById(R.id.button1);
	  btn.setOnClickListener(this);
	  	  
	  bb = new BasketBall();
	  db = new ScoreBookDBHelper(this);
	}
	@SuppressLint("SimpleDateFormat")
	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch(v.getId()){
		case R.id.btn_2pt_s:
			bb.setSuccessGoal2();
			bb.setAttemptGoal2();
			this.btn_2pt_s.setText(Integer.toString(bb.getGoal2()));
			break;
		case R.id.btn_2pt_f:
			bb.setAttemptGoal2();
			this.btn_2pt_f.setText(Integer.toString(bb.getAttemptGoal2()-bb.getGoal2()));
			break;
		case R.id.btn_3pt_s:
			bb.setSuccessGoal3();
			bb.setAttemptGoal3();
			this.btn_3pt_s.setText(Integer.toString(bb.getGoal3()));
			break;
		case R.id.btn_3pt_f:
			bb.setAttemptGoal3();
			this.btn_3pt_f.setText(Integer.toString(bb.getAttemptGoal3()-bb.getGoal3()));
			break;
		case R.id.ft_s_btn:
			bb.setSuccessFT();
			bb.setAttemptFT();
			this.btn_ft_s.setText(Integer.toString(bb.getFT()));
			break;
		case R.id.ft_f_btn:
			bb.setAttemptFT();
			this.btn_ft_f.setText(Integer.toString(bb.getAttemptFT()-bb.getFT()));
			break;
		case R.id.ast_btn:
			bb.setAsist();
			this.btn_ast.setText(Integer.toString(bb.getAsist()));
			break;
		case R.id.oreb_btn:
			bb.setOrebound();
			this.btn_oreb.setText(Integer.toString(bb.getOrebound()));
			break;
		case R.id.dreb_btn:
			bb.setDrebound();
			this.btn_dreb.setText(Integer.toString(bb.getDrebound()));
			break;
		case R.id.stl_btn:
			bb.setSteal();
			this.btn_stl.setText(Integer.toString(bb.getSteal()));
			break;
		case R.id.blk_btn:
			bb.setBlock();
			this.btn_blk.setText(Integer.toString(bb.getBlock()));
			break;
		case R.id.to_btn:
			bb.setTurnover();
			this.btn_to.setText(Integer.toString(bb.getTurnover()));
			break;
		case R.id.button1:
			Calendar calendar = Calendar.getInstance();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String strSysdate = sdf.format(calendar.getTime());
			
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
			this.btn_3pt_s.setText("SUCCESS");
			this.btn_3pt_f.setText("FAIL");
			this.btn_2pt_s.setText("SUCCESS");
			this.btn_2pt_f.setText("FAIL");
			this.btn_ft_s.setText("SUCCESS");
			this.btn_ft_f.setText("FAIL");
			this.btn_ast.setText("SUCCESS");
			this.btn_oreb.setText("SUCCESS");
			this.btn_dreb.setText("SUCCESS");
			this.btn_stl.setText("SUCCESS");
			this.btn_blk.setText("SUCCESS");
			this.btn_to.setText("SUCCESS");	
			
			bb.clear();
		}
		
	}
	@Override
	public boolean onLongClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch(v.getId()){
		case R.id.btn_2pt_s:
			bb.setSuccessGoal2(bb.getGoal2()-1);
			bb.setAttemptGoal2(bb.getAttemptGoal2()-1);
			this.btn_2pt_s.setText(Integer.toString(bb.getGoal2()));
			break;
		case R.id.btn_2pt_f:
			bb.setAttemptGoal2(bb.getAttemptGoal2()-1);
			this.btn_2pt_f.setText(Integer.toString(bb.getAttemptGoal2()-bb.getGoal2()));
			break;
		case R.id.btn_3pt_s:
			bb.setSuccessGoal3(bb.getGoal3()-1);
			bb.setAttemptGoal3(bb.getAttemptGoal3()-1);
			this.btn_3pt_s.setText(Integer.toString(bb.getGoal3()));
			break;
		case R.id.btn_3pt_f:
			bb.setAttemptGoal3(bb.getAttemptGoal3()-1);
			this.btn_3pt_f.setText(Integer.toString(bb.getAttemptGoal3()-bb.getGoal3()));
			break;
		case R.id.ft_s_btn:
			bb.setSuccessFT(bb.getFT()-1);
			bb.setAttemptFT(bb.getAttemptFT()-1);
			this.btn_ft_s.setText(Integer.toString(bb.getFT()));
			break;
		case R.id.ft_f_btn:
			bb.setAttemptFT(bb.getAttemptFT()-1);
			this.btn_ft_f.setText(Integer.toString(bb.getAttemptFT()-bb.getFT()));
			break;
		case R.id.ast_btn:
			bb.setAsist(bb.getAsist()-1);
			this.btn_ast.setText(Integer.toString(bb.getAsist()));
			break;
		case R.id.oreb_btn:
			bb.setOrebound(bb.getOrebound()-1);
			this.btn_oreb.setText(Integer.toString(bb.getOrebound()));
			break;
		case R.id.dreb_btn:
			bb.setDrebound(bb.getDrebound()-1);
			this.btn_dreb.setText(Integer.toString(bb.getDrebound()));
			break;
		case R.id.stl_btn:
			bb.setSteal(bb.getSteal()-1);
			this.btn_stl.setText(Integer.toString(bb.getSteal()));
			break;
		case R.id.blk_btn:
			bb.setBlock(bb.getBlock()-1);
			this.btn_blk.setText(Integer.toString(bb.getBlock()));
			break;
		case R.id.to_btn:
			bb.setTurnover(bb.getTurnover()-1);
			this.btn_to.setText(Integer.toString(bb.getTurnover()));
			break;
		}
		return true;
	}
	protected void findViews(){
		  drawerLayout =
		    (DrawerLayout) findViewById(R.id.drawer_layout_input);
		  drawerListView =
		    (ListView) findViewById(R.id.drawer_listview_input);
		  this.btn_2pt_s = (Button)findViewById(R.id.btn_2pt_s);
		  this.btn_2pt_f = (Button)findViewById(R.id.btn_2pt_f);
		  this.btn_3pt_s = (Button)findViewById(R.id.btn_3pt_s);
		  this.btn_3pt_f = (Button)findViewById(R.id.btn_3pt_f);
		  this.btn_ft_s = (Button)findViewById(R.id.ft_s_btn);
		  this.btn_ft_f = (Button)findViewById(R.id.ft_f_btn);
		  this.btn_ast = (Button)findViewById(R.id.ast_btn);
		  this.btn_dreb = (Button)findViewById(R.id.dreb_btn);
		  this.btn_oreb = (Button)findViewById(R.id.oreb_btn);
		  this.btn_stl = (Button)findViewById(R.id.stl_btn);
		  this.btn_blk = (Button)findViewById(R.id.blk_btn);
		  this.btn_to = (Button)findViewById(R.id.to_btn);
		  
		  this.btn_2pt_s.setOnClickListener(this);
		  this.btn_2pt_f.setOnClickListener(this);
		  this.btn_3pt_s.setOnClickListener(this);
		  this.btn_3pt_f.setOnClickListener(this);
		  this.btn_ft_s.setOnClickListener(this);
		  this.btn_ft_f.setOnClickListener(this);
		  this.btn_ast.setOnClickListener(this);
		  this.btn_dreb.setOnClickListener(this);
		  this.btn_oreb.setOnClickListener(this);
		  this.btn_stl.setOnClickListener(this);
		  this.btn_blk.setOnClickListener(this);
		  this.btn_to.setOnClickListener(this);
		  
		  this.btn_2pt_s.setOnLongClickListener(this);
		  this.btn_2pt_f.setOnLongClickListener(this);
		  this.btn_3pt_s.setOnLongClickListener(this);
		  this.btn_3pt_f.setOnLongClickListener(this);
		  this.btn_ft_s.setOnLongClickListener(this);
		  this.btn_ft_f.setOnLongClickListener(this);
		  this.btn_ast.setOnLongClickListener(this);
		  this.btn_dreb.setOnLongClickListener(this);
		  this.btn_oreb.setOnLongClickListener(this);
		  this.btn_stl.setOnLongClickListener(this);
		  this.btn_blk.setOnLongClickListener(this);
		  this.btn_to.setOnLongClickListener(this);
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
