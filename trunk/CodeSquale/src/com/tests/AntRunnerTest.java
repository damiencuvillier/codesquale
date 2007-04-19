package com.tests;

import static org.junit.Assert.*;

import java.util.HashMap;

import junit.framework.JUnit4TestAdapter;
import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.codesquale.ant.AntRunner;

/**
 * 
 * @author mbourguignon
 * Test Case for AntRunner Singleton
 */
public class AntRunnerTest extends TestCase{
	
	private AntRunner runner=null; 
	private HashMap myHash = null;
	
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
	public void testAntResults()
	{
		try
		{
			runner.init("AntScript.xml", "xml");
			System.out.println("Init");
			
			myHash.put("event", "results");
			myHash.put("subject", "Results Target");
			myHash.put("message", "Testing Results ant target");
			
			runner.setProperties(myHash, false);
			System.out.println("Properties");
			
			
			boolean result = runner.runTarget("results");
			System.out.println("Run");
			
			assertEquals(result, true);

			
		}catch(Exception e){
			logger.fatal(e.getMessage());
		}
		
		
	}
	
//	public static void main(String[] args)
//	{
//		
//		
//		logger.info(Messages.getString("LIB_FILTER_INIT")); //$NON-NLS-1$
//		FileFilter filter = new FileFilter();
//		filter.addFileType(FileFilter.JAVA_SOURCEFILE);
//		logger.info(Messages.getString("LIB_BROWSING_DIR")); //$NON-NLS-1$
//		ProjectBrowser browser;
//		try {
//			browser = new ProjectBrowser(new File("Z:\\Livrables\\alpha.V0.1\\V0.1\\jedit"),new File("output.xml"),filter);
//			
//			browser.ProcessAnalysis();
//			browser.ProcessDescription();
//			
//		} catch (NotDirectoryException e) {
//			// TODO Bloc catch auto-généré
//			e.printStackTrace();
//		}
//		
//		
//		testAntResults();
//	}
	
	

}
