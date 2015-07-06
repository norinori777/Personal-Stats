package com.gmail.norinori6791.personalstats;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ExportActivity extends Activity implements OnItemClickListener,
								OnItemLongClickListener, 
								OnClickListener, 
								android.content.DialogInterface.OnClickListener{
	public final int REQUEST_ACTIVITY_TEST = 1;
	
	static String[] menuItems = { "全期間平均", "記録入力", "ライブ入力", "検索・編集・共有・削除", "インポート"};
	static ArrayAdapter<String> adapter;
	
	DrawerLayout drawerLayout;
	ListView drawerListView;
	ActionBarDrawerToggle drawerToggle;

	private Button btn;
	private ListView lv;
	private FileDirectory fd;
	private ArrayAdapter<String> adp;
	private AlertDialog mAction;
	private List<BasketBall> lb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	 
		
		// AlertDialogをビルド
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("エクスポートをしますか？");
		builder.setPositiveButton("OK", this);	
		builder.setNegativeButton("Cancel", null);
		mAction = builder.create();
		
		setContentView(R.layout.activity_export);
	  	  
		lv = (ListView)findViewById(R.id.list_dir);
		
		fd = new FileDirectory();
		lv.setItemsCanFocus(false);
		lv.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

		adp = new ArrayAdapter<String>(this, R.layout.list_item_single_choice, fd.getDirectoriesList());
		lv.setAdapter(adp);
		lv.setOnItemLongClickListener(this);
			lv.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				
				fd.setSelectedDirectory(fd.getParentDirectory() + fd.getSelectedDir(position));
			}
		}); 
	  
		// エクスポートボタン
		btn = (Button) findViewById(R.id.button_choice);
		btn.setOnClickListener(this);
		
		findViews();
		setListView();  
		setDrawer();
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
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
		drawerLayout.closeDrawers();		
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		fd.setParentDirectory(fd.getSelectedDir(arg2));
		adp.clear();
		adp.addAll(fd.getDirectoriesList());
		lv.setAdapter(adp);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		// ダイアログ表示
		mAction.show();
	}

	@Override
	public void onClick(DialogInterface arg0, int arg1) {
		// TODO Auto-generated method stub
		// DBから情報取得
		StringBuilder sb = new StringBuilder();
		BasketBall bb;
		BasketBallAdapter bba = new BasketBallAdapter(this);
		
		// エクスポートファイル名作成
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String strSysdate = sdf.format(calendar.getTime());
		String file_name = getString(R.string.export_file_name) + strSysdate + ".csv";
		String export_path = fd.getSelectedDirectory() + file_name;

		try{
			
			FileWriter fw = new FileWriter(export_path, true);
		
			lb = bba.getList();
		
			for(int i = 0; i < lb.size(); i++){
				if( i == 0 ){
					fw.write(getString(R.string.export_file_header));
				}
				bb = lb.get(i);
			
				sb.append(bb.getDate() + ",");
				sb.append(bb.getGoal2() + ",");
				sb.append(bb.getAttemptGoal2() + ",");
				sb.append(bb.getGoal3() + ",");
				sb.append(bb.getAttemptGoal3() + ",");
				sb.append(bb.getFT() + ",");
				sb.append(bb.getAttemptFT() + ",");
				sb.append(bb.getAsist() + ",");
				sb.append(bb.getOrebound() + ",");
				sb.append(bb.getDrebound() + ",");
				sb.append(bb.getSteal() + ",");
				sb.append(bb.getBlock() + ",");
				sb.append(bb.getTurnover() + "\n");
				
				fw.write(sb.toString());
				sb.setLength(0);
			}
			fw.flush();
			fw.close();
			// 完了通知
			Toast.makeText(this, export_path + getString(R.string.export_success),Toast.LENGTH_LONG).show();
		} catch(IOException e){
			Toast.makeText(this, getString(R.string.export_error),Toast.LENGTH_SHORT).show();  
		} catch(NullPointerException e){
			Toast.makeText(this, getString(R.string.export_error),Toast.LENGTH_SHORT).show();  			
		}
	}
	protected void findViews(){
		drawerLayout =(DrawerLayout) findViewById(R.id.drawer_layout_export);
		drawerListView =(ListView) findViewById(R.id.drawer_listview_export);
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
