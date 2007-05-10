package com.codesquale.metrics.saxon;

import java.io.File;
import javax.xml.transform.stream.StreamSource;
import org.apache.log4j.Logger;
import com.codesquale.metrics.*;


public class SaxonMetricsFactory implements IMetricsFactory {
	
	
	private static Logger logger = Logger.getLogger(SaxonMetricsFactory.class);

	@SuppressWarnings("deprecation")
	public void CalculateCountersFromSourceFile(String fullPathSourceFile, String fullPathResultFile)
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