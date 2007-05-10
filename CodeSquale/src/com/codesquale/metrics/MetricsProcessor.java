package com.codesquale.metrics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;

import net.sf.saxon.Configuration;
import net.sf.saxon.query.DynamicQueryContext;
import net.sf.saxon.query.StaticQueryContext;
import net.sf.saxon.query.XQueryExpression;
import net.sf.saxon.trans.XPathException;

import com.codesquale.utils.*;


public class MetricsProcessor {
	
	
	private static Logger logger = Logger.getLogger(MetricsProcessor.class);
	private Configuration config = null;
	private StaticQueryContext staticContext = null;
	private DynamicQueryContext dynamicContext = null;
	
	
	public MetricsProcessor()
	{
		// Creating configuration
		config = new Configuration();
		// Creating static context  : used for compile xquery
		staticContext = new StaticQueryContext(config);
		// Creating dynamic context : used for process xquery
		dynamicContext = new DynamicQueryContext(config);
	}
	
	@SuppressWarnings("deprecation")
	public void generateResultFile(String in, String out)
	{
		logger.debug("Processing Xquery..." + out);
		
		// Preparing the single file to analyze
		File inputFile = null;
		StreamSource inputStreamSource = null;
		// Loading the file to analyze
		inputFile = new File(in);
		inputStreamSource = new StreamSource(inputFile);
		
		XQueryExpression exp = null;
	

		try 
		{
			// Retrieving the query
			String queryString = Utilities.readFileAsString("U:\\xquery\\saxon\\DirectoryFileCounters.xquery");
			exp = staticContext.compileQuery(queryString);
	
		} 
		catch (XPathException e) 
		{
			logger.fatal(e.getMessage());
		}
		catch(IOException ex)
		{
			logger.fatal(ex.getMessage());
		}
		
		
		try 
		{
			// Paramètrage du fichier d'entrée
			dynamicContext.setContextNode(
					config.buildDocument(inputStreamSource));
			
			Properties props = new Properties();
			props.setProperty(OutputKeys.METHOD, "xml");
			props.setProperty(OutputKeys.INDENT, "yes");
			
			exp.run(dynamicContext, new StreamResult(new File(out)), null);
			
		} 
		catch (XPathException e) 
		{
			logger.fatal(e.getMessage());
		}
		
		logger.debug("Xquery process finished..."+out);
	}

}