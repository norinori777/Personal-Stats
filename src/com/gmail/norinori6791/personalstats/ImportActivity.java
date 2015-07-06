package com.gmail.norinori6791.personalstats;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
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

public class ImportActivity extends Activity implements OnItemClickListener, 
																OnItemLongClickListener, 
																OnClickListener, 
																android.content.DialogInterface.OnClickListener{
	public final int REQUEST_ACTIVITY_TEST = 1;
	
	static String[] menuItems = { "全期間平均", "記録入力", "ライブ入力", "検索・編集・共有・削除", "エクスポート"};
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
	private ScoreBookDBHelper db;
	private SQLiteDatabase mdb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	 
		
		// AlertDialogをビルド
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("インポートをしますか？");
		builder.setPositiveButton("OK", this);	
		builder.setNegativeButton("Cancel", null);
		mAction = builder.create();
		
		setContentView(R.layout.activity_import);
	  	  
		lv = (ListView)findViewById(R.id.list_files_directories);
		
		fd = new FileDirectory();
		lv.setItemsCanFocus(false);
		lv.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

		adp = new ArrayAdapter<String>(this, R.layout.list_item_single_choice, fd.getFilesDirectriesList());
		lv.setAdapter(adp);
		lv.setOnItemLongClickListener(this);
			lv.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				
				fd.setSelectedFile(fd.getParentDirectory() + fd.getSelectedFileDir(position));
			}
		}); 
	  
		// インポートボタン
		btn = (Button) findViewById(R.id.button_choice);
		btn.setOnClickListener(this);
		
		db = new ScoreBookDBHelper(this);
		  
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
			Intent intent = new Intent(getApplicationContext(), ExportActivity.class);
			startActivityForResult(intent, REQUEST_ACTIVITY_TEST);
		}
		drawerLayout.closeDrawers();		
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub		
		if(fd.isDirectory(fd.getParentDirectory() + fd.getSelectedFileDir(arg2))){
			fd.setParentDirectory(fd.getSelectedFileDir(arg2));
			adp.clear();
			adp.addAll(fd.getFilesDirectriesList());
			lv.setAdapter(adp);
			return true;
		}
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
		// インポートファイルパス取得
		String import_path = fd.getSelectedFile();
		
		// ファイルオープン
		try{
			FileReader fd = new FileReader(import_path);
			BufferedReader br = new BufferedReader(fd);
			br.mark(1024);
			mdb = db.getWritableDatabase();			
			ContentValues val = new ContentValues();


			// チェック処理
			String line = br.readLine();
			String datas[];
			int title = 0;
			while(line != null){
				// タイトルチェック
				if(title == 0){
					if(! line.equals(getString(R.string.export_file_header).trim())){
						// エラー
						throw new IOException("ファイルのタイトルが正しくありません。");
					}
					title++;
				}
				// カラム数チェック
				datas = line.split(",");
				if(datas.length != 13){
					// エラー
					throw new IOException("レコードのカラム数が正しくありません。");
				}
				line = br.readLine();
			}
			
			br.reset();
			
			// INSERT処理
			line = br.readLine();
			title = 0;
			while(line != null){
				if(title == 0){
					title++;
					line = br.readLine();
					continue;
				}
				datas = line.split(",");
				
				val.put(ScoreBookDBHelper.DATE, datas[0]);
				val.put(ScoreBookDBHelper.TWO_OK, datas[1]);
				val.put(ScoreBookDBHelper.TWO_ATTEMPT, datas[2]);
				val.put(ScoreBookDBHelper.THREE_OK, datas[3]);
				val.put(ScoreBookDBHelper.THREE_ATTEMPT, datas[4]);
				val.put(ScoreBookDBHelper.FT_OK, datas[5]);
				val.put(ScoreBookDBHelper.FT_ATTEMPT, datas[6]);
				val.put(ScoreBookDBHelper.AST, datas[7]);
				val.put(ScoreBookDBHelper.OREB, datas[8]);
				val.put(ScoreBookDBHelper.DREB, datas[9]);
				val.put(ScoreBookDBHelper.STL, datas[10]);
				val.put(ScoreBookDBHelper.BLK, datas[11]);
				val.put(ScoreBookDBHelper.TO, datas[12]);
				
				mdb.insert(ScoreBookDBHelper.TABLE_NAME, null, val);	
				line = br.readLine();
			}
			Toast.makeText(this, "インポートが完了しました", Toast.LENGTH_SHORT).show();	

		}catch(IOException e){
			Toast.makeText(this, getString(R.string.import_error),Toast.LENGTH_SHORT).show();
		}catch(Exception e){
			Toast.makeText(this, getString(R.string.import_error),Toast.LENGTH_SHORT).show();			
		}	
	}
	
	protected void findViews(){
		drawerLayout =(DrawerLayout) findViewById(R.id.drawer_layout_import);
		drawerListView =(ListView) findViewById(R.id.drawer_listview_import);
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
