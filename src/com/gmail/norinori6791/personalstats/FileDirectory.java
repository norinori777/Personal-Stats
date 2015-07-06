package com.gmail.norinori6791.personalstats;

import java.io.File;
import java.util.ArrayList;

import android.content.Context;

public class FileDirectory {
	private File[] files;
	private boolean isDirectorySelect;
	private String _parent;
	private String _current;
	private String _selected;
	private ArrayList<String> filesList;
	private ArrayList<String> directoriesList;
	private ArrayList<String> filesDirectoriesList;
	
	public FileDirectory(){
		super();
		this._parent = "/storage/sdcard0/";
		this._selected = "/storage/sdcard0/";
	}
	public FileDirectory(String _parent){
		super();
		this._parent = _parent;
		this._selected = _parent;
	}
	public void setDirectorySelect(boolean is){
		isDirectorySelect = is;
	}
	public boolean getDirectorySelect(){
		return isDirectorySelect;
	}
	public void setSelectedFile(String _selected){
		this._selected = _selected;
	}
	public String getSelectedFile(){
		return this._selected;
	}	
	public void setSelectedDirectory(String _selected){
		this._selected = _selected;
	}
	public String getSelectedDirectory(){
		return this._selected;
	}
	public void setParentDirectory(String _parent){
		if(_parent.matches("\\.\\./")){
			if(this._parent.matches("^/storage/sdcard0/$") == false){
				this._parent = this._parent.substring(0, this._parent.lastIndexOf('/'));
				this._parent = this._parent.substring(0, this._parent.lastIndexOf('/'));
				this._parent = this._parent + "/";
			}
		} else {
			this._parent = this._parent + _parent;
		}
	}
	public void setCurrentDirectory(String _current){
		this._current = _current;
	}
	public String getCurrentDirectory(){
		return this._current;
	}
	public String getParentDirectory(){
		return this._parent;
	}
	public File[] getFiles(){
		files = new File(_parent).listFiles();
		return files;
	}
	public ArrayList<String> getFilesList(){
		filesList = new ArrayList<String>();
		files = getFiles();
		for(int i = 0; i < files.length; i++){
			if(files[i].isFile()){
				filesList.add(files[i].getName());
			}
		}
		return filesList;
	}
	public ArrayList<String> getDirectoriesList(){
		directoriesList = new ArrayList<String>();
		files = getFiles();
		
		if( _parent.equals("/storage/sdcard0/") == false ){
			directoriesList.add("../");
		}
		
		for(int i = 0; i < files.length; i++){
			if(files[i].isDirectory()){
				directoriesList.add(files[i].getName()+"/");
			}
		}
		return directoriesList;
	}
	public ArrayList<String> getFilesDirectriesList(){
		filesDirectoriesList = new ArrayList<String>();
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
	public String getSelectedDir(int i){
		return directoriesList.get(i);
	}
	public String getSelectedFileDir(int i){
		return filesDirectoriesList.get(i);
	}
	public boolean isDirectory(String path){
		File f = new File(path);
		if(f.isDirectory()){
			return true;
		}
		return false;
	}
	public boolean isFile(String path){
		File f = new File(path);
		if(f.isFile()){
			return true;
		}
		return false;
	}
}
