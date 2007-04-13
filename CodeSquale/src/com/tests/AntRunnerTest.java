package com.tests;

import static org.junit.Assert.*;

import java.util.HashMap;

import junit.framework.JUnit4TestAdapter;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.codesquale.ant.AntRunner;

public class AntRunnerTest {
	
	private AntRunner myAntRunner;
	private HashMap myHash = null;
	
	private static Logger logger = Logger.getLogger(AntRunnerTest.class);
	
	public AntRunnerTest()
	{
		
	}
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(AntRunner.class);
	}
	
	@Before public void setUp()
	{
		myHash = new HashMap();
		myAntRunner = AntRunner.getInstance();
	}
	
	@Test public void testAntResults()
	{
		try
		{
			myAntRunner.init("testsrc/AntScript.xml", "testsrc");
			myHash.put("event", "results");
			myHash.put("subject", "Results Target");
			myHash.put("message", "Testing Results ant target");
			myAntRunner.setProperties(myHash, false);
			
			boolean result = myAntRunner.runTarget("results");
			
			assertEquals(result, true);
			
		}catch(Exception e){
			logger.fatal("Error in testAntResults");
		}
		
		
	}
	
	

}
