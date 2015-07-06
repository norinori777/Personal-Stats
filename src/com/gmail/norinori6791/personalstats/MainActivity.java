package com.gmail.norinori6791.personalstats;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity
implements ListView.OnItemClickListener, OnItemSelectedListener {
	public final int REQUEST_ACTIVITY_TEST = 1;

	static String[] menuItems = { "記録入力", "ライブ入力", "検索・編集・共有・削除", "エクスポート", "インポート"};
	static ArrayAdapter<String> adapter;
	
	DrawerLayout drawerLayout;
	ListView drawerListView;
	ActionBarDrawerToggle drawerToggle;
	TextView textView;
	
	private SQLiteDatabase mdb;
	private ScoreBookDBHelper db;
	private BasketBall bb;
	
	private ArrayList<String> al;
	private AlertDialog ad;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_main);

	  findViews();
	  setListView();
	  setDrawer();
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		
		bb = new BasketBall();
		db = new ScoreBookDBHelper(this);
		
		mdb = db.getWritableDatabase();

		String sql = "select count(*) as num," +
				"MAX(_ID) as max_id," +
				"SUM(" + ScoreBookDBHelper.TWO_OK + ") as s_two," +
				"SUM(" + ScoreBookDBHelper.TWO_ATTEMPT + ") as s_two_a," +
				"SUM(" + ScoreBookDBHelper.THREE_OK +  ") as s_three," +
				"SUM(" + ScoreBookDBHelper.THREE_ATTEMPT + ") as s_three_a," +
				"SUM(" + ScoreBookDBHelper.FT_OK + ") as s_ft," +
				"SUM(" + ScoreBookDBHelper.FT_ATTEMPT + ") as s_ft_a," +
				"SUM(" + ScoreBookDBHelper.AST + ") as s_ast," +
				"SUM(" + ScoreBookDBHelper.OREB + ") as s_oreb," +
				"SUM(" + ScoreBookDBHelper.STL + ") as s_stl," +
				"SUM(" + ScoreBookDBHelper.BLK + ") as s_blk," +
				"SUM(" + ScoreBookDBHelper.DREB + ") as s_dreb," +
				"SUM(" + ScoreBookDBHelper.TO + ") as s_to " +
				"from ScoreBook";
		Cursor c = mdb.rawQuery(sql, null);
		c.moveToFirst();
		bb.setGame(c.getInt(c.getColumnIndex("num")));
		bb.setSuccessGoal2(c.getInt(c.getColumnIndex("s_two")));
		bb.setAttemptGoal2(c.getInt(c.getColumnIndex("s_two_a")));
		bb.setSuccessGoal3(c.getInt(c.getColumnIndex("s_three")));
		bb.setAttemptGoal3(c.getInt(c.getColumnIndex("s_three_a")));
		bb.setSuccessFT(c.getInt(c.getColumnIndex("s_ft")));
		bb.setAttemptFT(c.getInt(c.getColumnIndex("s_ft_a")));
		bb.setAsist(c.getInt(c.getColumnIndex("s_ast")));
		bb.setOrebound(c.getInt(c.getColumnIndex("s_oreb")));
		bb.setSteal(c.getInt(c.getColumnIndex("s_stl")));
		bb.setBlock(c.getInt(c.getColumnIndex("s_blk")));
		bb.setDrebound(c.getInt(c.getColumnIndex("s_dreb")));
		bb.setTurnover(c.getInt(c.getColumnIndex("s_to")));

		textView = (TextView)findViewById(R.id.text_point_value);
		textView.setText(Integer.toString(bb.getPointAverage()));
		textView = (TextView)findViewById(R.id.textView3);
		textView.setText(bb.getFieldGoal());
		textView = (TextView)findViewById(R.id.textView5);
		textView.setText(bb.GetFieldGoal2());
		textView = (TextView)findViewById(R.id.textView7);
		textView.setText(bb.GetFieldGoal3());
		textView = (TextView)findViewById(R.id.text_ft_value);
		textView.setText(bb.GetFTP());
		textView = (TextView)findViewById(R.id.TextView9);
		textView.setText(Integer.toString(bb.getAverageAsist()));
		textView = (TextView)findViewById(R.id.TextView02);
		textView.setText(Integer.toString(bb.getAverageOrebound()));
		textView = (TextView)findViewById(R.id.TextView13);
		textView.setText(Integer.toString(bb.getAverageSteal()));
		textView = (TextView)findViewById(R.id.TextView04);
		textView.setText(Integer.toString(bb.getAverageBlock()));
		textView = (TextView)findViewById(R.id.TextView17);
		textView.setText(Integer.toString(bb.getAverageDrebound()));
		textView = (TextView)findViewById(R.id.TextView19);
		textView.setText(Integer.toString(bb.getAverageTurnover()));
		c.close();
	}
	
	protected void findViews(){
	  drawerLayout =
	    (DrawerLayout) findViewById(R.id.drawer_layout);
	  drawerListView =
	    (ListView) findViewById(R.id.drawer_listview);
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
			Intent intent = new Intent(getApplicationContext(), InputActivity.class);
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
			Intent intent = new Intent(getApplicationContext(), ExportActivity.class);
			startActivityForResult(intent, REQUEST_ACTIVITY_TEST);
		}
		if(position==4){
			//インポート処理
			Intent intent = new Intent(getApplicationContext(), ImportActivity.class);
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

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO 自動生成されたメソッド・スタブ
		Toast.makeText(this, "編集が完了しました", Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}
