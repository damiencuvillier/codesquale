package com.codesquale.ant;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import com.codesquale.parser.AntlrParsingProcess;
import com.codesquale.file.FileFilter;
import com.codesquale.file.NotDirectoryException;
import com.codesquale.file.ProjectBrowser;


public class AntLRTask extends Task {
	private static Logger logger = Logger.getLogger(AntLRTask.class);
	private String source, target;
	private boolean success = false;
	
	public void execute() throws BuildException {
		logger.info("File Filter init");
		
		FileFilter filter = new FileFilter();
		filter.addFileType(FileFilter.JAVA_SOURCEFILE);
		
		logger.info("Browsing project files...");
		
		try {
			
			// Need to init the project browser
			ProjectBrowser.getInstance().init(new File(source),new File(target), new File(target+"AntlrProjectOutput.xml"), filter);
			
			// Now we can run the AntlrProcess
			AntlrParsingProcess.getInstance().execute();
	
			success = true;
			
		} catch (NotDirectoryException e) {
			logger.error("File is not valid");
			success = false;
		} catch (Exception e)
		{
			logger.error(e.getMessage());
			success = false;
		}
		
	}
	public boolean isSuccessfull() {
		return success;
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
