package com.codesquale.launcher;


import java.io.File;

import com.codesquale.file.FileFilter;
import com.codesquale.file.NotDirectoryException;
import com.codesquale.file.ProjectBrowser;
import com.codesquale.utils.Messages;

/**
 * Test Class for project
 * 
 * Launch Repository Browser Which calls the parsing process
 * @author DCUVILLIER
 *
 */
public class Test 
{
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Test.class);
	
public static void main(String[] args) 
{
		logger.info(Messages.getString("LIB_BEGIN_TEST")); //$NON-NLS-1$
	
		if(args.length < 2){
			System.err.println(Messages.getString("ERR_ARGS_SYNTAX")); //$NON-NLS-1$
			return;
		}
		try 
		{
			logger.info(Messages.getString("LIB_FILTER_INIT")); //$NON-NLS-1$
			FileFilter filter = new FileFilter();
			filter.addFileType(FileFilter.JAVA_SOURCEFILE);
			logger.info(Messages.getString("LIB_BROWSING_DIR")); //$NON-NLS-1$
			ProjectBrowser browser = new ProjectBrowser(new File(args[0]),new File(args[1]),new File(args[2]),filter);
			browser.ProcessAnalysis();
			browser.ProcessDescription();
			
		} catch (NotDirectoryException e) {
			logger.fatal(Messages.getString("ERR_INVALID_DIR")); //$NON-NLS-1$
		}
		logger.info(Messages.getString("LIB_JOB_DONE")); //$NON-NLS-1$
		logger.info(Messages.getString("LIB_RESULTS_OUTPUT_PATH")+args[1]); //$NON-NLS-1$
	}
}
