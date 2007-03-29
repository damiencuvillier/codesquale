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
	public static final int XML_FILE = 2;
	public static final int JAVA_BINARYFILE = 3; 
	
	
	
	/**
	 * List of the enabled filetypes
	 */
	private Vector<Integer> fileTypes = null;
	
	/**
	 * Constructors
	 * @param fileExtensions
	 */
	public FileFilter(){
		fileTypes = new Vector<Integer>();
	}
	public FileFilter(Vector<String> fileExtensions){
		init(fileExtensions);
	}
	public FileFilter(String[] fileExtensions){
		this();
		Vector<String> liste =  new Vector<String>();
		for(int i=0;i<fileExtensions.length;i++){
			liste.add(fileExtensions[i]);
		}
		init(liste);
	}
	
	
	/**
	 * Initialization
	 * 
	 * Convert extensions to types
	 * 
	 * @param fileExtensions
	 */
	public void init(Vector<String> fileExtensions){
		logger.debug("Initialisation du Filter");
		
		// Extensions iterator
		for(String extension:fileExtensions){
			// File type assignment
			fileTypes.add(getType(extension));
		}
	}
	
	public static int getType(String extension){
		if( extension.equals("java")) return FileFilter.JAVA_SOURCEFILE;
		if( extension.equals("class")) return FileFilter.JAVA_BINARYFILE;
		if( extension.equals("xml")) return FileFilter.XML_FILE;
		return -1;
	}
	
	/**
	 * Return true if the parameter is includes in
	 * the allowed type file list
	 * @param extension
	 * @return
	 */
	public boolean isAllowed(String extension){
		return fileTypes.contains(getType(extension));
	}
	/**
	 * Getters
	 * @return
	 */
	public Vector<Integer> getFileTypes() {
		return fileTypes;
	}
	public void addFileType(int type){
		fileTypes.add(type);
	}
}
