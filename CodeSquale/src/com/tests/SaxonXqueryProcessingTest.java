package com.tests;

import static org.junit.Assert.fail;

import java.io.File;

import com.codesquale.metrics.*;
import junit.framework.JUnit4TestAdapter;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;



public class SaxonXqueryProcessingTest {
	

	private static Logger logger = Logger.getLogger(SaxonXqueryProcessingTest.class);

	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(SaxonXqueryProcessingTest.class);
	}
	
	
	@Before public void setUp()
	{
	
	}
	
	@Test
	public void ProcessXquery()
	{
		MetricsProcessor metrics = new MetricsProcessor();
		
		metrics.generateResultFile("test\\classpath.xml","U:\\out\\test.xml");
	}
}
