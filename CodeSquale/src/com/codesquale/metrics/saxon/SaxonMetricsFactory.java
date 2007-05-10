package com.codesquale.metrics.saxon;

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
import com.codesquale.metrics.*;


public class SaxonMetricsFactory implements IMetricsFactory {
	
	
	private static Logger logger = Logger.getLogger(SaxonMetricsFactory.class);

	@SuppressWarnings("deprecation")
	public void generateResultFile(String fullPathSourceFile, String fullPathResultFile)
	{
		logger.debug("Processing Xquery from the file... " + fullPathSourceFile);
		
		// Preparing the single file to analyze
		File inputFile = null;
		StreamSource inputStreamSource = null;
		// Loading the file to analyze
		inputFile = new File(fullPathSourceFile);
		inputStreamSource = new StreamSource(inputFile);
		
		SaxonProcessor.getInstance().setXMLSourceDocument(inputStreamSource);
		SaxonProcessor.getInstance().ExecuteSingleFileCountingQuery(fullPathResultFile);
		
		logger.debug("Xquery process finished... "+ fullPathResultFile + " generated ...");
	}

}