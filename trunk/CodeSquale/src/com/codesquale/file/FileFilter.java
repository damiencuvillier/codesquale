package com.codesquale.file;

import java.util.Vector;

public class FileFilter {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(FileFilter.class);
	
	
	/**
	 * Constants
	 * 
	 * File Types
	 */
	public static final int JAVA_SOURCEFILE = 1; 
	
	
	private Vector<Integer> fileTypes = null;
	public FileFilter(Vector<String> fileExtensions){
		init(fileExtensions);
	}
	public FileFilter(Vector<Integer> fileTypes){
		
	
	}
	public FileFilter(String[] fileExtensions){
		Vector<String> liste =  new Vector<String>();
		for(int i=0;i<fileExtensions.length;i++){
			liste.add(fileExtensions[i]);
		}
		init(liste);
	}
	public void init(Vector<String> fileExtensions){
		Vector<Integer> fileTypes = new Vector<Integer>();
		for(String extension:fileExtensions){
			if( extension.equals("java"))fileTypes.add(JAVA_SOURCEFILE);
		
		}
	}
}
