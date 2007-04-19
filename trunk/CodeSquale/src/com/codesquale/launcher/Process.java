package com.codesquale.launcher;

import java.io.File;

import com.codesquale.exceptions.NotDirectoryException;
import com.codesquale.file.FileFilter;
import com.codesquale.file.ProjectBrowser;

/**
 * 
 * This class contain the main process 
 * 
 * @author RBITTEL
 * @param source Contain the source folder
 * @param target Contain the destination XML file 
 *
 */


public class Process {
	
	private File source, target;
	
	private  org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Process.class);

	public Process(File source, File target){

		try {

			logger.info("File Filter init");
			FileFilter filter = new FileFilter();
			filter.addFileType(FileFilter.JAVA_SOURCEFILE);
			logger.info("Browsing File...");
			ProjectBrowser browser = new ProjectBrowser(source, target, filter);
			browser.ProcessAnalysis();
			browser.ProcessDescription();

		} catch (NotDirectoryException e) {
			logger.info("Param is not a valid directory");
		}
		logger.info("Done...");
		logger.info("Results written in " + target.getAbsolutePath());
		
		
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
