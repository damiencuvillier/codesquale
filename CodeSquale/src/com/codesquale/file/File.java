package com.codesquale.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
/**
 * Representes a file
 * @author DCUVILLIER
 *
 */

public class File {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(File.class);
	
	
	public static final int JAVA_SOURCEFILE = 1; 
	
	private int linesCount = 0;
	private int type = -1;
	
	
	
	java.io.File _file = null;
	
	public File(java.io.File file){
		_file = file;
		init();
	}
	/**
	 * Initialize the counters
	 */
	private void init(){
		/**
		 * Count the number of lines in the file
		 */
		try {
			FileInputStream inputStream = new FileInputStream(_file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public int getType(){
		if (type == -1){
			String extension = getExtension();
			if( extension.equals("java") ){
				
			}
		}
		return type;
	}
	
	/*
	 * Returns the extension of the file
	 */
	private String getExtension(){
		String name = _file.getName();
		String extension = name.substring(name.lastIndexOf(".") + 1);
		return extension;
	}
	
}
