package com.gmail.norinori6791.personalstats;

import java.util.ArrayList;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FileAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<String> al;
	private TextView tv;
	private LayoutInflater lf;
	
	public FileAdapter(Context context, ArrayList<String> data){
		super();
		al = data;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		// TODO 自動生成されたメソッド・スタブ
		return al.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO 自動生成されたメソッド・スタブ
		return al.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO 自動生成されたメソッド・スタブ
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO 自動生成されたメソッド・スタブ
		View row = arg1;
		if( row == null ){
			lf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = lf.inflate(R.layout.listitem_file, arg2, false);
		}
		String item = (String) getItem(arg0);
		tv = (TextView)row.findViewById(R.id.textView_file);
		
		tv.setText(item);
		
		return row;
	}
}

