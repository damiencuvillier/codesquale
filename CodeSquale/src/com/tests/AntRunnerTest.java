package com.tests;

import static org.junit.Assert.*;

import java.io.*;
import java.util.HashMap;

import junit.framework.JUnit4TestAdapter;
import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.codesquale.ant.AntRunner;
import com.codesquale.parser.AntlrParsingProcess;
import com.codesquale.file.NotDirectoryException;
import com.codesquale.file.FileFilter;
import com.codesquale.file.ProjectBrowser;
import com.codesquale.utils.Messages;

/**
 * 
 * @author mbourguignon
 * Test Case for AntRunner Singleton
 */
public class AntRunnerTest extends TestCase{
	
	private static AntRunner runner=null; 
	private static HashMap myHash = null;
	
	private static Logger logger = Logger.getLogger(AntRunnerTest.class);
	
	public AntRunnerTest(String name)
	{
		super(name);
	}
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(ParsingUnitTest.class);
	}
	@Before public void setUp()
	{
		myHash = new HashMap();
		runner = AntRunner.getInstance();
	}
	
	@Test 
	public static void testAntResults()
	{
		try
		{
			runner.init("AntScript.xml", "xml");
			
			myHash.put("event", "parseOneFile");
			myHash.put("subject", "Results Target");
			myHash.put("message", "Testing Results ant target");
			
			runner.setProperties(myHash, false);
					
			boolean result = runner.runTarget("parseOneFile");
			logger.info(result);
			assertEquals(result, false);
			
		}catch(Exception e){
			logger.fatal(e.getMessage());
		}
		
		
	}
	
	public static void main(String[] args)
	{
		
		
		logger.info(Messages.getString("LIB_FILTER_INIT")); //$NON-NLS-1$
		FileFilter filter = new FileFilter();
		filter.addFileType(FileFilter.JAVA_SOURCEFILE);
		logger.info(Messages.getString("LIB_BROWSING_DIR")); //$NON-NLS-1$
		ProjectBrowser browser;
		AntlrParsingProcess parsing;
			
		try {
			ProjectBrowser.getInstance().init(new File("Z:\\Livrables\\alpha.V0.1\\V0.1\\jedit"), new File("results"),new File("output.xml"),filter);
			
			AntlrParsingProcess.getInstance().execute();
			
		} catch (NotDirectoryException e) {
			logger.error("File is not valid");
		}
			
		
		testAntResults();
	}
	
	

}
