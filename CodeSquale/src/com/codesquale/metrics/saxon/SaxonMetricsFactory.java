package com.codesquale.metrics.saxon;

import org.apache.log4j.Logger;
import com.codesquale.metrics.*;


public class SaxonMetricsFactory implements IMetricsFactory {
	
	
	private static Logger logger = Logger.getLogger(SaxonMetricsFactory.class);

	@SuppressWarnings("deprecation")
	public void CalculateCountersFromSourceFile(String fullPathSourceFile, String fullPathResultFile)
	{
		logger.debug("Processing Xquery counting from the file " + fullPathSourceFile+" ...");
		
		SaxonProcessor.getInstance().setXMLSourceDocument(fullPathSourceFile);
		SaxonProcessor.getInstance().ExecuteSingleFileCountingQuery(fullPathResultFile);
		

		// Getting info about file which has been processed
		SaxonProcessor.getInstance().setXMLSourceDocument(fullPathResultFile);
		
		//System.out.println(SaxonProcessor.getInstance().GetNumberOfClasses() + "classes");
		

		logger.debug("Xquery counting process finished "+ fullPathResultFile + " generated ...");
	}

}