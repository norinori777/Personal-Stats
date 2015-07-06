package com.gmail.norinori6791.personalstats;

import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ScoreBookDBHelper extends SQLiteOpenHelper{
	
	public static final String DB_NAME="mydb";
	public static final String TABLE_NAME="ScoreBook";
	public static final String FT_OK="freeSuccessPoint";
	public static final String FT_ATTEMPT="freeAttempt";
	public static final String TWO_OK="twoSuccessPoint";
	public static final String TWO_ATTEMPT="twoAttempt";
	public static final String THREE_OK="threeSuccessPoint";
	public static final String THREE_ATTEMPT="threeAttempt";
	public static final String AST="asist";
	public static final String DREB="drebound";
	public static final String OREB="orebound";
	public static final String BLK="block";
	public static final String STL="steal";
	public static final String TO="turnover";
	public static final String DATE="in_date";
	public static final String GAME="game";
	
	private BasketBall bb;
	private ArrayList al;
	
	public static final String STRING_CREATE = 
			"CREATE TABLE " +TABLE_NAME+ 
				" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+DATE+" TEXT, "
				+TWO_OK+ "	INTEGER, "
				+TWO_ATTEMPT+ "	INTEGER, "
				+THREE_OK+ " INTEGER, "
				+THREE_ATTEMPT+ " INTEGER, "
				+FT_OK+ " INTEGER, "
				+FT_ATTEMPT+ " INTEGER, "				
				+AST+" INTEGER, "
				+DREB+" INTEGER, "
				+OREB+" INTEGER, "
				+BLK+" INTEGER, "
				+STL+" INTEGER, "
				+TO+" INTEGER);";
	
	public ScoreBookDBHelper(Context context){
		super(context, DB_NAME, null, 1);
	}
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 自動生成されたメソッド・スタブ
		db.execSQL(STRING_CREATE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	
	public ArrayList selectScoreBookDB(){
		return al;
	}
}
