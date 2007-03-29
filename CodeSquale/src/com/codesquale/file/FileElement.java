package com.codesquale.file;



public class FileElement extends AbstractElement {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FileElement.class);
	
	

	
	private int linesCount = 0;
	private int type = -1;
	
	
	
	java.io.File file = null;
	
	public FileElement(java.io.File file){
		super(file);
		this.file = file;
		
	}
	/**
	 * Initialize the counters
	 */

	
	public int getType(){
		if (type == -1){
			String extension = getExtension();
			if( extension.equals("java") ){
				return JAVA_SOURCEFILE;
			}
		}
		return type;
	}
	
	/*
	 * Returns the extension of the file
	 */
	private String getExtension(){
		String name = file.getName();
		String extension = name.substring(name.lastIndexOf(".") + 1);
		return extension;
	}
	
}
