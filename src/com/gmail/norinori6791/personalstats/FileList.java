package com.gmail.norinori6791.personalstats;

import java.io.File;
import java.util.List;

import android.content.Context;

public class FileList {
	private Context context;
	private File[] files;
	private boolean isDirectorySelect;
	private String _parent;
	private List<String> filesList;
	private List<String> directoriesList;
	private List<String> filesDirectoriesList;
	
	public FileList(Context context){
		super();
		this.context = context;
		this._parent = "/";
	}
	public FileList(String _parent, Context context){
		super();
		this.context = context;
		this._parent = _parent;
	}
	public void setDirectorySelect(boolean is){
		isDirectorySelect = is;
	}
	public boolean getDirectorySelect(){
		return isDirectorySelect;
	}
	public void setParentDirectory(String _parent){
		this._parent = _parent;
	}
	public String getParentDirectory(){
		return this._parent;
	}
	public File[] getFiles(){
		files = new File(_parent).listFiles();
		return files;
	}
	public List<String> getFilesList(){
		files = getFiles();
		for(int i = 0; i < files.length; i++){
			if(files[i].isFile()){
				filesList.add(files[i].getName());
			}
		}
		return filesList;
	}
	public List<String> getDirectoriesList(){
		files = getFiles();
		for(int i = 0; i < files.length; i++){
			if(files[i].isDirectory()){
				directoriesList.add(files[i].getName()+"/");
			}
		}
		return directoriesList;
	}
	public List<String> getFilesDirectriesList(){
		files = getFiles();
		for(int i = 0; i < files.length; i++){
			if(files[i].isFile()){
				filesDirectoriesList.add(files[i].getName());
			}	
			if(files[i].isDirectory()){
				filesDirectoriesList.add(files[i].getName()+"/");
			}
		}
		return filesDirectoriesList;
	}
}
