package com.codesquale.file;

import java.io.File;

public class FileElement extends AbstractElement 
{
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FileElement.class);
	
	private int type = -1;
	File file = null;
	
	public FileElement(File physicalFile)
	{
		super(physicalFile);
		file = physicalFile;
	}

	public int getType(){
		if (type == -1){
			String extension = getExtension();
			if( extension.equals("java") ){
				return FileFilter.JAVA_SOURCEFILE;
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
