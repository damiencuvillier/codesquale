package com.codesquale.metrics.saxon;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import net.sf.saxon.Configuration;
import net.sf.saxon.om.NodeInfo;
import net.sf.saxon.om.SequenceIterator;
import net.sf.saxon.query.DynamicQueryContext;
import net.sf.saxon.query.StaticQueryContext;
import net.sf.saxon.query.XQueryExpression;
import net.sf.saxon.trans.XPathException;

import org.apache.log4j.Logger;



public class SaxonProcessor 
{
	private static Logger logger = Logger.getLogger(SaxonProcessor.class);
	
	private static SaxonProcessor _instance= null;
	
	private Configuration config = null;
	private StaticQueryContext staticContext = null;
	private DynamicQueryContext dynamicContext = null;
	

	public static SaxonProcessor getInstance()
	{
		if(_instance == null) _instance = new SaxonProcessor();
		
		return _instance;
	}
	
	
	@SuppressWarnings("deprecation")
	public void setXMLSourceDocument(String inputFilePath)
	{
		// Preparing the single file to analyze
		File inputFile = null;
		StreamSource inputStreamSource = null;
		
		// Loading the file to analyze
		inputFile = new File(inputFilePath);
		inputStreamSource = new StreamSource(inputFile);
		
		try {
			dynamicContext.setContextNode(config.buildDocument(inputStreamSource));
		} catch (XPathException e) {
			logger.fatal(e.getMessage());
		}
	}
	
	public StaticQueryContext getStaticQueryContext()
	{
		return staticContext;
	}
	
	
	public DynamicQueryContext getDynamicQueryContext()
	{
		return dynamicContext;
	}
	
	
	private SaxonProcessor()
	{
		initProcessor();
	}
	
	private void initProcessor()
	{
		// Creating configuration
		config = new Configuration();
		// Creating static context  : used for compile xquery
		staticContext = new StaticQueryContext(config);
		// Creating dynamic context : used for process xquery
		dynamicContext = new DynamicQueryContext(config);
	}
	
	
	
}
