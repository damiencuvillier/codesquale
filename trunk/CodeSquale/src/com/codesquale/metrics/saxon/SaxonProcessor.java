package com.codesquale.metrics.saxon;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import net.sf.saxon.Configuration;
import net.sf.saxon.query.DynamicQueryContext;
import net.sf.saxon.query.StaticQueryContext;
import net.sf.saxon.query.XQueryExpression;
import net.sf.saxon.trans.XPathException;

import org.apache.log4j.Logger;

import com.codesquale.utils.Utilities;

public class SaxonProcessor 
{
	private static Logger logger = Logger.getLogger(SaxonProcessor.class);
	
	private static SaxonProcessor _instance= null;
	
	private Configuration config = null;
	private StaticQueryContext staticContext = null;
	private DynamicQueryContext dynamicContext = null;
	
	
	private XQueryExpression singleFileCountingQuery = null;
	
	public static SaxonProcessor getInstance()
	{
		if(_instance == null) _instance = new SaxonProcessor();
		
		return _instance;
	}
	
	public void ExecuteSingleFileCountingQuery(String outFileFullPath)
	{
		Properties props = new Properties();
		props.setProperty(OutputKeys.METHOD, "xml");
		props.setProperty(OutputKeys.INDENT, "yes");
		
		try {
			singleFileCountingQuery.run(dynamicContext, new StreamResult(new File(outFileFullPath)), props);
		} catch (XPathException e) {
			logger.fatal(e.getMessage());
		}
	}
	
	@SuppressWarnings("deprecation")
	public void setXMLSourceDocument(StreamSource inputFile)
	{
		try {
			dynamicContext.setContextNode(config.buildDocument(inputFile));
		} catch (XPathException e) {
			logger.fatal(e.getMessage());
		}
	}
	
	private SaxonProcessor()
	{
		initProcessor();
		compileQuery();
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
	
	private void compileQuery()
	{
		String singleFileCountingQueryString;
		
		try {
			 singleFileCountingQueryString = Utilities.readFileAsString(SaxonQueryProvider.SINGLE_FILE_COUNTING);
			 singleFileCountingQuery = staticContext.compileQuery(singleFileCountingQueryString);
		} catch (IOException e) 
		{
			logger.fatal(e.getMessage());
		}
		catch (XPathException ex) 
		{
			logger.fatal(ex.getMessage());
		} 
	}
	
	
}
