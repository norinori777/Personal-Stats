package com.gmail.norinori6791.personalstats;

import java.util.Calendar;
import java.util.List;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends Activity implements OnItemClickListener, android.view.View.OnClickListener {
	public final int REQUEST_ACTIVITY_TEST = 1;

	private static String[] menuItems = { "全期間平均", "記録入力", "ライブ入力", "エクスポート", "インポート"};
	static ArrayAdapter<String> adapter;
	
	DrawerLayout drawerLayout;
	ListView drawerListView;
	ActionBarDrawerToggle drawerToggle;
	Calendar calendar;
	private int year;
	private int monthOfYear;
	private int dayOfMonth;
	private SQLiteDatabase mdb;
	private ScoreBookDBHelper db;
	private Button btn;
	private TextView text_from;
	private TextView text_to;
	private StringBuffer date_from;
	private StringBuffer date_to;
	private DatePickerDialog datePickerDialog_from;
	private DatePickerDialog datePickerDialog_to;
	private LinearLayout ll;
	private LayoutInflater li;
	private List<BasketBall> lb;
	private ListView lv;
	private StringBuilder sb;
	private boolean judge = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_search);
	  
	  findViews();
	  setListView();
	  setDrawer();
	  text_from.setOnClickListener(this);
	  text_to.setOnClickListener(this);
	  btn.setOnClickListener(this);
	  calendar = Calendar.getInstance();
	    
	  year = calendar.get(calendar.YEAR);
	  monthOfYear = calendar.get(calendar.MONTH);
	  dayOfMonth = calendar.get(calendar.DATE);
	  
	  monthOfYear = monthOfYear + 1;
	  	  
	  text_from.setText(year+"/"+monthOfYear+"/"+dayOfMonth);
	  text_to.setText(year+"/"+monthOfYear+"/"+dayOfMonth);
	  
	  if( date_from == null && date_to == null){
		date_from = new StringBuffer();  
		date_to = new StringBuffer();
		
		date_from.append(year);
		date_from.append(String.format("%1$02d", monthOfYear));
		date_from.append(String.format("%1$02d", dayOfMonth));
		date_from.append("000000");
		date_to.append(year);
		date_to.append(String.format("%1$02d", monthOfYear));
		date_to.append(String.format("%1$02d", dayOfMonth));
		date_to.append("235959");
	  } 
	  
	  Intent re = getIntent();
	  if(re.getStringExtra("re_from") != null && re.getStringExtra("re_to") != null){			
		  TextView text_result = (TextView)this.findViewById(R.id.text_result);
		  lv = (ListView)this.findViewById(R.id.list_result);
		  text_result.setVisibility(View.INVISIBLE);
			
		  BasketBallAdapter adp = new BasketBallAdapter(this, re.getStringExtra("re_from"),  re.getStringExtra("re_to"), 
													R.layout.listitem_basketball,R.id.textView_date, R.id.textView_stats);
		  lb = adp.getList();
		  if(lb.size()>0){
			  text_result.setVisibility(View.VISIBLE);
		  }
		  lv.setAdapter(adp);
		  registerForContextMenu(lv);
	  }
	}
	
	@Override
	protected void onResume(){
		super.onResume();
	}
	
	protected void findViews(){
		  drawerLayout =
		    (DrawerLayout) findViewById(R.id.drawer_layout_search);
		  drawerListView =
		    (ListView) findViewById(R.id.drawer_listview_search);
		  btn = (Button)findViewById(R.id.button_search);
		  text_from = (TextView)findViewById(R.id.textView_from);
		  text_to = (TextView)findViewById(R.id.textView_to);
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
			Intent intent = new Intent(getApplicationContext(), ExportActivity.class);
			startActivityForResult(intent, REQUEST_ACTIVITY_TEST);
		}
		if(position==4){
			Intent intent = new Intent(getApplicationContext(), ImportActivity.class);
			startActivityForResult(intent, REQUEST_ACTIVITY_TEST);
		}
		drawerLayout.closeDrawers();
	}
	DatePickerDialog.OnDateSetListener DateSetListener_from = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO 自動生成されたメソッド・スタブ
			// calendar.set(year, monthOfYear, dayOfMonth);
			// date_from = calendar.getTime();

			monthOfYear = monthOfYear + 1;
			text_from.setText(year+"/"+monthOfYear+"/"+dayOfMonth);
			date_from.delete(0, date_to.length());
			date_from.append(year);
			date_from.append(String.format("%1$02d", monthOfYear));
			date_from.append(String.format("%1$02d", dayOfMonth));
			date_from.append("000000");
		}
	};

	DatePickerDialog.OnDateSetListener DateSetListener_to = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO 自動生成されたメソッド・スタブ
			// calendar.set(year, monthOfYear, dayOfMonth);
			// date_to = calendar.getTime();

			monthOfYear = monthOfYear + 1;
			text_to.setText(year+"/"+monthOfYear+"/"+dayOfMonth);
			date_to.delete(0, date_to.length());
			date_to.append(year);
			date_to.append(String.format("%1$02d", monthOfYear));
			date_to.append(String.format("%1$02d", dayOfMonth));
			date_to.append("235959");
		}
	};

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・ス
		year = calendar.get(calendar.YEAR);
		monthOfYear = calendar.get(calendar.MONTH);
		dayOfMonth = calendar.get(calendar.DATE);
		
		switch(v.getId()){
		case R.id.textView_from:	
			datePickerDialog_from = new DatePickerDialog( this, DateSetListener_from, year, monthOfYear, dayOfMonth);
			datePickerDialog_from.show();
			break;
		case R.id.textView_to:
			datePickerDialog_to = new DatePickerDialog( this, DateSetListener_to, year, monthOfYear, dayOfMonth);			
			datePickerDialog_to.show();
			break;
		case R.id.button_search:
			//日付のチェック
			//if(date_to.before(date_from)){
				//検索
			
				//検索結果リストクリア
				TextView text_result = (TextView)this.findViewById(R.id.text_result);
				lv = (ListView)this.findViewById(R.id.list_result);
				text_result.setVisibility(View.INVISIBLE);
				
				BasketBallAdapter adp = new BasketBallAdapter(this, date_from.toString(),  date_to.toString(), 
											R.layout.listitem_basketball,R.id.textView_date, R.id.textView_stats);
				lb = adp.getList();
				if(lb.size()>0){
					text_result.setVisibility(View.VISIBLE);
				}
				lv.setAdapter(adp);
				registerForContextMenu(lv);
				
				judge = true;

			break;
		}
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.menu, menu);
		menu.setHeaderTitle("操作を選択してください");
	}
	@Override
	public boolean onContextItemSelected(MenuItem item){
		AdapterContextMenuInfo info1 = (AdapterContextMenuInfo)item.getMenuInfo();
		int listPosition = info1.position;
				
		switch(item.getItemId()){
		case R.id.menu_edit:
			//期間平均の場合
			if(listPosition == 0){
				break;
			}
			
			//編集画面へ遷移する処理
			Intent intent = new Intent(getApplicationContext(), EditActivity.class);
			intent.putExtra("3pt", lb.get(listPosition).getGoal3());
			intent.putExtra("3pt_attempt", lb.get(listPosition).getAttemptGoal3());
			intent.putExtra("2pt", lb.get(listPosition).getGoal2());
			intent.putExtra("2pt_attempt", lb.get(listPosition).getAttemptGoal2());
			intent.putExtra("FT", lb.get(listPosition).getFT());
			intent.putExtra("FT_attempt", lb.get(listPosition).getAttemptFT());
			intent.putExtra("ast", lb.get(listPosition).getAsist());
			intent.putExtra("stl", lb.get(listPosition).getSteal());
			intent.putExtra("dreb", lb.get(listPosition).getDrebound());
			intent.putExtra("oreb", lb.get(listPosition).getOrebound());
			intent.putExtra("blk", lb.get(listPosition).getBlock());
			intent.putExtra("to", lb.get(listPosition).getTurnover());
			intent.putExtra("date", lb.get(listPosition).getDate());
			
			//戻ってくる時の検索条件
			intent.putExtra("from", date_from.toString());
			intent.putExtra("to", date_to.toString());
			startActivityForResult(intent, REQUEST_ACTIVITY_TEST);
			
			//編集画面へ行く際に選択したアイテムの項目を渡す
			break;
		case R.id.menu_share:
			//共有する処理
			//共有する際に選択したアイテムの項目を渡す	
			if(listPosition == 0){
				sb = new StringBuilder();
				sb.append("Point");
				sb.append(lb.get(listPosition).getPointAverage());
				sb.append("     FG:");
				sb.append(lb.get(listPosition).getFieldGoal());
				sb.append("     2pt:");
				sb.append(lb.get(listPosition).GetFieldGoal2());
				sb.append("     3pt:");
				sb.append(lb.get(listPosition).GetFieldGoal3());
				sb.append("     FT:");
				sb.append(lb.get(listPosition).GetFTP());
				sb.append("\n");
				sb.append("ASIST:");
				sb.append(lb.get(listPosition).getAverageAsist());
				sb.append("     ORB:");
				sb.append(lb.get(listPosition).getAverageOrebound());
				sb.append("     STEAL:");
				sb.append(lb.get(listPosition).getAverageSteal());
				sb.append("\n");
				sb.append("BLOCK:");
				sb.append(lb.get(listPosition).getAverageBlock());
				sb.append("     DRB:");
				sb.append(lb.get(listPosition).getAverageDrebound());
				sb.append("     TO:");
				sb.append(lb.get(listPosition).getAverageTurnover());				
			}else{			
				sb = new StringBuilder();
				sb.append("Point");
				sb.append(lb.get(listPosition).getPoint());
				sb.append("     FG:");
				sb.append(lb.get(listPosition).getFieldGoal());
				sb.append("     2pt:");
				sb.append(lb.get(listPosition).getGoal2());
				sb.append("/");
				sb.append(lb.get(listPosition).getAttemptGoal2());
				sb.append("     3pt:");
				sb.append(lb.get(listPosition).getGoal3());
				sb.append("/");
				sb.append(lb.get(listPosition).getAttemptGoal3());
				sb.append("     FT:");
				sb.append(lb.get(listPosition).getFT());
				sb.append("/");
				sb.append(lb.get(listPosition).getAttemptFT());
				sb.append("\n");
				sb.append("ASIST:");
				sb.append(lb.get(listPosition).getAsist());
				sb.append("     ORB:");
				sb.append(lb.get(listPosition).getOrebound());
				sb.append("     STEAL:");
				sb.append(lb.get(listPosition).getSteal());
				sb.append("\n");
				sb.append("BLOCK:");
				sb.append(lb.get(listPosition).getBlock());
				sb.append("     DRB:");
				sb.append(lb.get(listPosition).getDrebound());
				sb.append("     TO:");
				sb.append(lb.get(listPosition).getTurnover());
			}
			
			Intent share_intent = getIntent();
			share_intent.setAction(share_intent.ACTION_SEND);
			share_intent.setType("text/plain");
			share_intent.putExtra(share_intent.EXTRA_TEXT, sb.toString());
			
			//日付をセットするようにする
			if(listPosition == 0){
				if(text_from.getText().equals(text_to.getText())){
					share_intent.putExtra(share_intent.EXTRA_TITLE, text_from.getText());
				}else{
					share_intent.putExtra(share_intent.EXTRA_TITLE, text_from.getText()+"～"+text_to.getText());					
				}
			}else{
				share_intent.putExtra(share_intent.EXTRA_TITLE, lb.get(listPosition).getDateFormat());
			}
			startActivity(share_intent.createChooser(share_intent, "アプリケーションを選択"));
			
			
			break;
		case R.id.menu_delete:
			//期間平均の場合
			if(listPosition == 0){
				break;
			}
			
			//消す処理　選択した項目のみを削除　DB処理
			//再表示する
			deleteItem(lb.get(listPosition).getDate());
			
			Toast.makeText(this, "削除が完了しました", Toast.LENGTH_SHORT).show();
			
			break;
		case R.id.menu_cancel:
			break;
		}
		return true;		
	}
	@Override
	protected void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		
		outState.putString("FROM", date_from.toString());
		outState.putString("FROM_TEXT", text_from.getText().toString());
		outState.putString("TO", date_to.toString());
		outState.putString("TO_TEXT", text_to.getText().toString());
		outState.putBoolean("JUDGE", judge);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState){
		super.onRestoreInstanceState(savedInstanceState);
		
		date_from = new StringBuffer(savedInstanceState.getString("FROM"));
		date_to = new StringBuffer(savedInstanceState.getString("TO"));
		text_from.setText(savedInstanceState.getString("FROM_TEXT"));
		text_to.setText(savedInstanceState.getString("TO_TEXT"));
		judge = savedInstanceState.getBoolean("JUDGE");
		
		if( judge == true){
			TextView text_result = (TextView)this.findViewById(R.id.text_result);
			lv = (ListView)this.findViewById(R.id.list_result);
			text_result.setVisibility(View.INVISIBLE);
		
			BasketBallAdapter adp = new BasketBallAdapter(this, date_from.toString(), date_to.toString(), 
												R.layout.listitem_basketball,R.id.textView_date, R.id.textView_stats);
			lb = adp.getList();
			if(lb.size()>0){
				text_result.setVisibility(View.VISIBLE);
			}
			lv.setAdapter(adp);
			registerForContextMenu(lv);
		}
	}
	
	public ListView deleteItem(String date){
		db = new ScoreBookDBHelper(this);
		mdb = db.getWritableDatabase();

		//削除処理
		mdb.delete(ScoreBookDBHelper.TABLE_NAME, ScoreBookDBHelper.DATE + " = " + date, null);
		
		// 削除後の再表示処理
		TextView text_result = (TextView)this.findViewById(R.id.text_result);
		lv = (ListView)this.findViewById(R.id.list_result);
		text_result.setVisibility(View.INVISIBLE);
		
		BasketBallAdapter adp = new BasketBallAdapter(this, date_from.toString(),  date_to.toString(), R.layout.listitem_basketball,R.id.textView_date, R.id.textView_stats);
		lb = adp.getList();
		if(lb.size()>0){
			text_result.setVisibility(View.VISIBLE);
		}
		lv.setAdapter(adp);
		registerForContextMenu(lv);
			
		return lv;
	}
}
