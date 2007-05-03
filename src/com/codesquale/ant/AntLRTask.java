package com.codesquale.ant;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import com.codesquale.file.FileFilter;
import com.codesquale.file.NotDirectoryException;
import com.codesquale.file.ProjectBrowser;


public class AntLRTask extends Task {
	private static Logger logger = Logger.getLogger(AntLRTask.class);
	private String source, target;
	
	public void execute() throws BuildException {
		logger.info("File Filter init");
		FileFilter filter = new FileFilter();
		filter.addFileType(FileFilter.JAVA_SOURCEFILE);
		logger.info("Browsing File...");
		ProjectBrowser browser;
		try {
			browser = new ProjectBrowser(new File(source),new File(target), new File(target+"AntlrProjectOutput.xml"), filter);
			browser.ProcessAnalysis();
			browser.ProcessDescription();
		} catch (NotDirectoryException e) {
			logger.error("File is not valid");
		}
		
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	
}
