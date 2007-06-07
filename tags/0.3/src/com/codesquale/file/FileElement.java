package com.codesquale.file;

import java.io.File;

public class FileElement extends AbstractElement 
{
	//private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FileElement.class);
	
	private int type = -1;
	private String extension;
	private String xmlDesc;
	File file = null;
	
	public FileElement(File physicalFile)
	{
		super(physicalFile);
		file = physicalFile;
	}
	
	/*
	 * Returns the extension of the file
	 */
	public String getExtension(){
		if(extension == null){
			String name = file.getName();
			extension = name.substring(name.lastIndexOf(".") + 1);
		}
		return extension;
	}
	
	public int getType(){
		if(type == -1){
			type = FileFilter.getType(getExtension());
		}
		return type;
	}
	public String toString() {
		// FIXME � g�n�riser car ici on part du principe qu'il s'agit de fichiers java uniquement
		String message = "<FILE name=\""+getName()+"\" xmlDesc=\""+ getXmlDesc() +"\" />";
		return message;
	}

	public String getXmlDesc() {
		return xmlDesc;
	}


	public void setXmlDesc(String xmlDesc) {
		this.xmlDesc = xmlDesc;
	}
}