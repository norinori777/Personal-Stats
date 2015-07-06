package com.gmail.norinori6791.personalstats;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BasketBallAdapter extends BaseAdapter{
	
	private Context context;
	private List<BasketBall> list;
	private BasketBall basketball;
	private SQLiteDatabase mdb;
	private ScoreBookDBHelper db;
	private int view_text_date;
	private int view_text_stats;
	private LayoutInflater inflater;

	public BasketBallAdapter(Context context, String from, String to, int view_item, int view_text_date_, int view_text_stats_ ){
		super();
		String sql;
		Cursor c;
		this.context = context;
		
		view_text_date = view_text_date_;
		view_text_stats = view_text_stats_;
		
		list = new ArrayList<BasketBall>();
		
		// SQLで検索
		db = new ScoreBookDBHelper(context);
		mdb = db.getWritableDatabase();
		basketball = new BasketBall();
		
		// アベレージセット
		sql = "select count(*) as num," +
				"SUM(" + ScoreBookDBHelper.TWO_OK + ") as s_two," +
				"SUM(" + ScoreBookDBHelper.TWO_ATTEMPT + ") as s_two_a," +
				"SUM(" + ScoreBookDBHelper.THREE_OK +  ") as s_three," +
				"SUM(" + ScoreBookDBHelper.THREE_ATTEMPT + ") as s_three_a," +
				"SUM(" + ScoreBookDBHelper.FT_OK +  ") as s_ft," +
				"SUM(" + ScoreBookDBHelper.FT_ATTEMPT + ") as s_ft_a," +
				"SUM(" + ScoreBookDBHelper.AST + ") as s_ast," +
				"SUM(" + ScoreBookDBHelper.OREB + ") as s_oreb," +
				"SUM(" + ScoreBookDBHelper.STL + ") as s_stl," +
				"SUM(" + ScoreBookDBHelper.BLK + ") as s_blk," +
				"SUM(" + ScoreBookDBHelper.DREB + ") as s_dreb," +
				"SUM(" + ScoreBookDBHelper.TO + ") as s_to " +
				"	FROM	ScoreBook" +
				"	WHERE	" + ScoreBookDBHelper.DATE + ">=" + from +
				"	AND	" + ScoreBookDBHelper.DATE + "<=" + to;
				c = mdb.rawQuery(sql, null);
		c.moveToFirst();	
		if(c.getInt(c.getColumnIndex("num")) != 0){
			basketball.setDate("検索期間平均");
			basketball.setGame(c.getInt(c.getColumnIndex("num")));
			basketball.setSuccessGoal2(c.getInt(c.getColumnIndex("s_two")));
			basketball.setAttemptGoal2(c.getInt(c.getColumnIndex("s_two_a")));
			basketball.setSuccessGoal3(c.getInt(c.getColumnIndex("s_three")));
			basketball.setAttemptGoal3(c.getInt(c.getColumnIndex("s_three_a")));
			basketball.setSuccessFT(c.getInt(c.getColumnIndex("s_ft")));
			basketball.setAttemptFT(c.getInt(c.getColumnIndex("s_ft_a")));
			basketball.setAsist(c.getInt(c.getColumnIndex("s_ast"))/c.getInt(c.getColumnIndex("num")));
			basketball.setOrebound(c.getInt(c.getColumnIndex("s_oreb"))/c.getInt(c.getColumnIndex("num")));
			basketball.setSteal(c.getInt(c.getColumnIndex("s_stl"))/c.getInt(c.getColumnIndex("num")));
			basketball.setBlock(c.getInt(c.getColumnIndex("s_blk"))/c.getInt(c.getColumnIndex("num")));
			basketball.setDrebound(c.getInt(c.getColumnIndex("s_dreb"))/c.getInt(c.getColumnIndex("num")));
			basketball.setTurnover(c.getInt(c.getColumnIndex("s_to"))/c.getInt(c.getColumnIndex("num")));

			list.add(basketball);

		}
		sql = "SELECT	" + ScoreBookDBHelper.DATE + ","+
				ScoreBookDBHelper.TWO_OK + "," +
				ScoreBookDBHelper.TWO_ATTEMPT + "," +
				ScoreBookDBHelper.THREE_OK + "," +
				ScoreBookDBHelper.THREE_ATTEMPT + "," +
				ScoreBookDBHelper.FT_OK + "," +
				ScoreBookDBHelper.FT_ATTEMPT + "," +
				ScoreBookDBHelper.AST + "," +
				ScoreBookDBHelper.OREB + "," +
				ScoreBookDBHelper.STL + "," +
				ScoreBookDBHelper.BLK + "," +
				ScoreBookDBHelper.DREB + "," +
				ScoreBookDBHelper.TO +
				"	FROM	ScoreBook" +
				"	WHERE	" + ScoreBookDBHelper.DATE + ">=" + from +
				"	AND	" + ScoreBookDBHelper.DATE + "<=" + to +
				"	ORDER	BY	" + ScoreBookDBHelper.DATE + "	DESC";
 		c = mdb.rawQuery(sql, null);
			
		c.moveToFirst();
		
		for( int i = 0; i < c.getCount(); i++ ){
			basketball = new BasketBall();
			
			basketball.setDate(c.getString(c.getColumnIndex(ScoreBookDBHelper.DATE)));
			basketball.setSuccessGoal2(c.getInt(c.getColumnIndex(ScoreBookDBHelper.TWO_OK)));
			basketball.setAttemptGoal2(c.getInt(c.getColumnIndex(ScoreBookDBHelper.TWO_ATTEMPT)));
			basketball.setSuccessGoal3(c.getInt(c.getColumnIndex(ScoreBookDBHelper.THREE_OK)));
			basketball.setAttemptGoal3(c.getInt(c.getColumnIndex(ScoreBookDBHelper.THREE_ATTEMPT)));
			basketball.setSuccessFT(c.getInt(c.getColumnIndex(ScoreBookDBHelper.FT_OK)));
			basketball.setAttemptFT(c.getInt(c.getColumnIndex(ScoreBookDBHelper.FT_ATTEMPT)));
			basketball.setAsist(c.getInt(c.getColumnIndex(ScoreBookDBHelper.AST)));
			basketball.setOrebound(c.getInt(c.getColumnIndex(ScoreBookDBHelper.OREB)));
			basketball.setSteal(c.getInt(c.getColumnIndex(ScoreBookDBHelper.STL)));
			basketball.setBlock(c.getInt(c.getColumnIndex(ScoreBookDBHelper.BLK)));
			basketball.setDrebound(c.getInt(c.getColumnIndex(ScoreBookDBHelper.DREB)));
			basketball.setTurnover(c.getInt(c.getColumnIndex(ScoreBookDBHelper.TO)));
			
			list.add(basketball);
			
			c.moveToNext();
		}
		c.close();
	}
	public BasketBallAdapter(Context context){
		super();
		String sql;
		Cursor c;
		this.context = context;
		
		list = new ArrayList<BasketBall>();
		
		// SQLで検索
		db = new ScoreBookDBHelper(context);
		mdb = db.getWritableDatabase();
		basketball = new BasketBall();
		
		sql = "SELECT	" + ScoreBookDBHelper.DATE + ","+
				ScoreBookDBHelper.TWO_OK + "," +
				ScoreBookDBHelper.TWO_ATTEMPT + "," +
				ScoreBookDBHelper.THREE_OK + "," +
				ScoreBookDBHelper.THREE_ATTEMPT + "," +
				ScoreBookDBHelper.FT_OK + "," +
				ScoreBookDBHelper.FT_ATTEMPT + "," +
				ScoreBookDBHelper.AST + "," +
				ScoreBookDBHelper.OREB + "," +
				ScoreBookDBHelper.STL + "," +
				ScoreBookDBHelper.BLK + "," +
				ScoreBookDBHelper.DREB + "," +
				ScoreBookDBHelper.TO +
				"	FROM	ScoreBook" +
				"	ORDER	BY	_ID ASC";
 		c = mdb.rawQuery(sql, null);
			
		c.moveToFirst();
		
		for( int i = 0; i < c.getCount(); i++ ){
			basketball = new BasketBall();
			
			basketball.setDate(c.getString(c.getColumnIndex(ScoreBookDBHelper.DATE)));
			basketball.setSuccessGoal2(c.getInt(c.getColumnIndex(ScoreBookDBHelper.TWO_OK)));
			basketball.setAttemptGoal2(c.getInt(c.getColumnIndex(ScoreBookDBHelper.TWO_ATTEMPT)));
			basketball.setSuccessGoal3(c.getInt(c.getColumnIndex(ScoreBookDBHelper.THREE_OK)));
			basketball.setAttemptGoal3(c.getInt(c.getColumnIndex(ScoreBookDBHelper.THREE_ATTEMPT)));
			basketball.setSuccessFT(c.getInt(c.getColumnIndex(ScoreBookDBHelper.FT_OK)));
			basketball.setAttemptFT(c.getInt(c.getColumnIndex(ScoreBookDBHelper.FT_ATTEMPT)));
			basketball.setAsist(c.getInt(c.getColumnIndex(ScoreBookDBHelper.AST)));
			basketball.setOrebound(c.getInt(c.getColumnIndex(ScoreBookDBHelper.OREB)));
			basketball.setSteal(c.getInt(c.getColumnIndex(ScoreBookDBHelper.STL)));
			basketball.setBlock(c.getInt(c.getColumnIndex(ScoreBookDBHelper.BLK)));
			basketball.setDrebound(c.getInt(c.getColumnIndex(ScoreBookDBHelper.DREB)));
			basketball.setTurnover(c.getInt(c.getColumnIndex(ScoreBookDBHelper.TO)));
			
			list.add(basketball);
			
			c.moveToNext();
		}
		c.close();
	}

	public List<BasketBall> getList(){
		return list;
	}
	@Override
	public int getCount() {
		// TODO 自動生成されたメソッド・スタブ
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO 自動生成されたメソッド・スタブ
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO 自動生成されたメソッド・スタブ
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO 自動生成されたメソッド・スタブ
		StringBuilder sb = new StringBuilder();
		View row = arg1;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if( row == null ){
			row = inflater.inflate(R.layout.listitem_basketball, arg2, false);
		}
		BasketBall item = (BasketBall) getItem(arg0);
		TextView date = (TextView)row.findViewById(view_text_date);
		TextView stats = (TextView)row.findViewById(view_text_stats);
		
		String strSysdate;
		if(item.getDate().equals("検索期間平均")){
			strSysdate = item.getDate();
			
			// STATSの文字列を作成
			sb.append("Point:");
			sb.append(item.getPointAverage());
			sb.append("     FG:");
			sb.append(item.getFieldGoal());
			sb.append("     2pt:");
			sb.append(item.GetFieldGoal2());
			sb.append("     3pt:");
			sb.append(item.GetFieldGoal3());
			sb.append("     FT:");
			sb.append(item.GetFTP());
			sb.append("\n");
			sb.append("ASIST:");
			sb.append(item.getAsist());
			sb.append("     ORB:");
			sb.append(item.getOrebound());
			sb.append("     STEAL:");
			sb.append(item.getSteal());
			sb.append("\n");
			sb.append("BLOCK:");
			sb.append(item.getBlock());
			sb.append("     DRB:");
			sb.append(item.getDrebound());
			sb.append("     TO:");
			sb.append(item.getTurnover());
		}else{
			strSysdate = item.getDateFormat();
			
			// STATSの文字列を作成
			sb.append("Point:");
			sb.append(item.getPoint());
			sb.append("     FG:");
			sb.append(item.getFieldGoal());
			sb.append("     2pt:");
			sb.append(item.getGoal2());
			sb.append("/");
			sb.append(item.getAttemptGoal2());
			sb.append("     3pt:");
			sb.append(item.getGoal3());
			sb.append("/");
			sb.append(item.getAttemptGoal3());
			sb.append("     FT:");
			sb.append(item.getFT());
			sb.append("/");
			sb.append(item.getAttemptFT());
			sb.append("\n");
			sb.append("ASIST:");
			sb.append(item.getAsist());
			sb.append("     ORB:");
			sb.append(item.getOrebound());
			sb.append("     STEAL:");
			sb.append(item.getSteal());
			sb.append("\n");
			sb.append("BLOCK:");
			sb.append(item.getBlock());
			sb.append("     DRB:");
			sb.append(item.getDrebound());
			sb.append("     TO:");
			sb.append(item.getTurnover());
		}
		date.setText(strSysdate);
		stats.setText(sb.toString());
		
		return row;
	}
}
