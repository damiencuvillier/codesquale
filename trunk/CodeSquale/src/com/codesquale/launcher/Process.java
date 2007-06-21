package com.codesquale.launcher;

import java.io.File;
import java.util.HashMap;

import com.codesquale.ant.AntRunner;
import com.codesquale.utils.ExceptionLevel;
import com.codesquale.utils.ExceptionManager;

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
	
	private File source, target;
	

	
	
	public Process(File source, File target){
		setSource(source);
		setTarget(target);	
	}
	
	
	@SuppressWarnings("unchecked")
	public void run(){
		
		try
		{
			HashMap myHash = new HashMap();
			AntRunner.getInstance().init("xml/launch.xml");
			
			myHash.put("OutputDir", target.getAbsolutePath());
			myHash.put("SourceDir",source.getAbsolutePath());
			
			AntRunner.getInstance().setProperties(myHash, true);

			AntRunner.getInstance().runTarget("launch");
			
		}catch(Exception e){
			ExceptionManager.aspectManagedException(e, ExceptionLevel.FATAL);
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

	
	
}
