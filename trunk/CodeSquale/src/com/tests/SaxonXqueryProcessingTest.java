package com.tests;


import com.codesquale.metrics.*;

import junit.framework.JUnit4TestAdapter;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;



public class SaxonXqueryProcessingTest {
	

	private static Logger logger = Logger.getLogger(SaxonXqueryProcessingTest.class);
	
	private MetricsFactoryType desiredFactory  = null;
	private IMetricsFactory saxonFactory = null;
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(SaxonXqueryProcessingTest.class);
	}
	
	
	@Before public void setUp()
	{
		desiredFactory = MetricsFactoryType.SAXON_FACTORY;
		
		saxonFactory = MetricsFactoryProvider.getInstance().GetMetricsFactory(desiredFactory);
	}
	
	@Test
	public void ProcessXquery()
	{
		String sampleTestFile = "test\\classpath.xml";
		String otherSampleTestFile = "xml\\models\\DirectoryFile_Sample.xml";
		saxonFactory.CalculateCountersFromSourceFile(otherSampleTestFile,"testoutput\\MetricsTest.xml");
	}
}