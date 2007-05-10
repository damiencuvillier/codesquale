package com.tests;

import static org.junit.Assert.fail;

import java.io.File;

import com.codesquale.metrics.*;
import com.codesquale.metrics.saxon.SaxonMetricsFactory;

import junit.framework.JUnit4TestAdapter;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;



public class SaxonXqueryProcessingTest {
	

	private static Logger logger = Logger.getLogger(SaxonXqueryProcessingTest.class);
	
	private MetricsFactoryProvider.MetricsFactoryType desiredFactory  = null;
	private IMetricsFactory saxonFactory = null;
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(SaxonXqueryProcessingTest.class);
	}
	
	
	@Before public void setUp()
	{
		desiredFactory = MetricsFactoryProvider.MetricsFactoryType.SAXON_FACTORY;
		
		saxonFactory = MetricsFactoryProvider.getInstance().GetMetricsFactory(desiredFactory);
	}
	
	@Test
	public void ProcessXquery()
	{
		saxonFactory.CalculateCountersFromSourceFile("test\\classpath.xml","U:\\out\\test.xml");
	}
}
