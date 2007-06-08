package com.codesquale.ant;

import java.io.File;
import org.apache.log4j.Logger;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import com.codesquale.file.FileFilter;
import com.codesquale.file.NotDirectoryException;
import com.codesquale.file.ProjectBrowser;
import com.codesquale.parser.AntlrParsingProcess;

/** Ant Task for AntLR processing.<br />
 * It is the first step in CodeSquale<br />
 * This task use in particuler the project browser
 * @author Damien Cuvillier
 * @see com.codesquale.file
 */
public class AntLRTask extends Task {
	/** Log4J Logger.
	 */
	private static Logger logger = Logger.getLogger(AntLRTask.class);
	
	/** Path where is located the source files. */
	private String source;
	/** Path where will be stored the xml ast provided by AntLR. */
	private String target;
	
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
			
		} catch (NotDirectoryException e) {
			logger.error("File is not valid");
		} catch (Exception e)
		{
			logger.error(e.getMessage());
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
