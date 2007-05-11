package com.codesquale.metrics.saxon;

import java.io.IOException;

import net.sf.saxon.expr.StaticContext;
import net.sf.saxon.query.StaticQueryContext;
import net.sf.saxon.query.XQueryExpression;
import net.sf.saxon.trans.XPathException;

import org.apache.log4j.Logger;

import com.codesquale.utils.Utilities;

public class SaxonQueryProvider {
	
	private static Logger logger = Logger.getLogger(SaxonProcessor.class);
	
	private final String SINGLE_FILE_COUNTING_QUERY_PATH = "xml\\XQuery\\saxon\\DirectoryFileCounters.xquery";

	private XQueryExpression singleFileCountingQuery = null;
	private XQueryExpression numberOfClasses = null;
	private XQueryExpression numberOfPrivateClasses = null;
	private XQueryExpression numberOfPublicClasses = null;
	
	private static SaxonQueryProvider _instance = null;
	
	public static SaxonQueryProvider getInstance()
	{
		if(_instance==null) _instance = new SaxonQueryProvider();
		
		return _instance;
	}
	
	private SaxonQueryProvider()
	{
		compileQuery();
	}
	
	private void compileQuery()
	{
		StaticQueryContext context = SaxonProcessor.getInstance().getStaticQueryContext(); 
		
		try 
		{
			 singleFileCountingQuery = context.compileQuery(getSingleFileCountingQuery());
			 numberOfClasses = context.compileQuery(getNumberOfClassesQuery());
			 numberOfPrivateClasses = context.compileQuery(getNumberOfPublicClassesQuery()); 
			 numberOfPublicClasses = context.compileQuery(getNumberOfPrivateClassesQuery()); 
		} 
		catch (XPathException ex) 
		{
			logger.fatal(ex.getMessage());
		} 
	}
	
	/*
	 *  XQuery object
	 */
	
	
	public XQueryExpression getNumberOfClassesQueryObject()
	{
		return numberOfClasses ;
	}
	
	public XQueryExpression getNumberOfPrivateClassesQueryObject()
	{
		return numberOfPrivateClasses ;
	}
	
	public XQueryExpression getNumberOfPublicClassesQueryObject()
	{
		return numberOfPublicClasses ;
	}
	
	public XQueryExpression getSingleFileCountingQueryObject()
	{
		return singleFileCountingQuery ;
	}
	
	
	/*
	 *  Query litterals
	 */
	
	private String getSingleFileCountingQuery()
	{
		try 
		{
			return Utilities.readFileAsString(SINGLE_FILE_COUNTING_QUERY_PATH);
		} catch (IOException e) 
		{
			logger.fatal("The XQuery file seems to be unavailable.");
		}
		
		return null;
	}
	
	private  String getNumberOfClassesQuery()
	{
		return "//directoryResult/counters/classes/all";
	}
	
	private  String getNumberOfPrivateClassesQuery()
	{
		return "//directoryResult/counters/classes/private";
	}
	
	private  String getNumberOfPublicClassesQuery()
	{
		return "//directoryResult/counters/classes/public";
	}
	
}
