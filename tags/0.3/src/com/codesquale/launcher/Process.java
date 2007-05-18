package com.codesquale.launcher;

import java.io.File;
import java.util.HashMap;

import com.codesquale.ant.AntRunner;

/**
 * 
 * This class contain the main process 
 * 
 * @author RBITTEL
 * @param source Contain the source folder
 * @param target Contain the destination XML file 
 *
 */


public class Process extends Thread{
	
	private File source, target, XMLFile;
	
	private  org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Process.class);

	
	
	public Process(File source, File target){
		setSource(source);
		setTarget(target);	
	}
	
	public Process(File source, File target, File XMLFile){
		setSource(source);
		setTarget(target);
		setXMLFile(XMLFile);
		
	}
	
	@SuppressWarnings("unchecked")
	public void run(){
		
		try
		{
			HashMap myHash = new HashMap();
			AntRunner.getInstance().init("AntScript.xml");
			
			myHash.put("OutputDir", target.getAbsolutePath());
			myHash.put("SourceDir",source.getAbsolutePath());
			
			AntRunner.getInstance().setProperties(myHash, true);

			AntRunner.getInstance().runTarget("CodeSqualeMetricsProcess");
			
		}catch(Exception e){
			
			logger.fatal(e.getMessage());
		}
		
	}

	public File getSource() {
		return source;
	}

	public void setSource(File source) {
		this.source = source;
	}

	public File getTarget() {
		return target;
	}

	public void setTarget(File target) {
		this.target = target;
	}

	public File getXMLFile() {
		return XMLFile;
	}

	public void setXMLFile(File file) {
		XMLFile = file;
	}
	
	
}
