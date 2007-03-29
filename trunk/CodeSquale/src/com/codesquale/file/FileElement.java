package com.codesquale.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.codesquale.metrics.RawMetricsData;
import com.codesquale.parser.ParsingUnit;

public class FileElement extends AbstractElement 
{
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FileElement.class);
	
	private int type = -1;
	private String extension;
	File file = null;
	
	
	/*
	 * Metrics parameters
	 */
	RawMetricsData metricsData = null;
	
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
		// FIXME à génériser car ici on part du principe qu'il s'agit de fichiers java uniquement
		String message = "FILE "+getName()+"\tMétriques : Classe(s) :"+metricsData.GetClassCount();
		message += " Lines : " + metricsData.GetLineCount()+ " Methods : "+metricsData.GetMethodCount();
		return message;
	}


	public RawMetricsData getMetricsData() {
		return metricsData;
	}


	public void setMetricsData(RawMetricsData metricsData) {
		this.metricsData = metricsData;
	}
}
