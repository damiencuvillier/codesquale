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

import com.codesquale.utils.Utilities;

public class SaxonProcessor 
{
	private static Logger logger = Logger.getLogger(SaxonProcessor.class);
	
	private static SaxonProcessor _instance= null;
	
	private Configuration config = null;
	private StaticQueryContext staticContext = null;
	private DynamicQueryContext dynamicContext = null;
	
	
	private XQueryExpression singleFileCountingQuery = null;
	private XQueryExpression numberOfClasses = null;
	
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
		
		File outputFile = new File(outFileFullPath);
		
		StreamResult serializedFile =  new StreamResult(outputFile);
		
		
		try {
			System.out.println(outFileFullPath);
			System.out.println(dynamicContext);
			singleFileCountingQuery.run(dynamicContext,serializedFile,props);
			System.out.println("Test2");
		} catch (XPathException e) {
			logger.fatal(e.getMessage());
		}
	}
	
	public int GetNumberOfClasses()
	{
		try {
			SequenceIterator classesIterator = numberOfClasses.iterator(dynamicContext);

			while (true && classesIterator != null) 
			{
			    NodeInfo classesValue = (NodeInfo)classesIterator.next();
			    if (classesValue==null) break;
			    return Integer.parseInt(classesValue.getStringValue());
			}   
			
		} catch (XPathException e) {
			logger.fatal(e.getMessage());
		}
		return -1;
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
			  
			 numberOfClasses = staticContext.compileQuery("//directoryResult/counters/classes/all");
			 
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
