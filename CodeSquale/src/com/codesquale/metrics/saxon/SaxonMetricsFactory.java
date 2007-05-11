package com.codesquale.metrics.saxon;

import java.io.File;
import java.util.Properties;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.stream.StreamResult;

import net.sf.saxon.om.NodeInfo;
import net.sf.saxon.om.SequenceIterator;
import net.sf.saxon.trans.XPathException;

import org.apache.log4j.Logger;
import com.codesquale.metrics.*;


public class SaxonMetricsFactory implements IMetricsFactory {
	
	
	private static Logger logger = Logger.getLogger(SaxonMetricsFactory.class);

	@SuppressWarnings("deprecation")
	public void CalculateCountersFromSourceFile(String fullPathSourceFile, String fullPathResultFile)
	{
		logger.debug("Processing Xquery counting from the file " + fullPathSourceFile+" ...");
		// Setting to SaxonProcessor the file to analyze
		SaxonProcessor.getInstance().setXMLSourceDocument(fullPathSourceFile);
		ExecuteSingleFileCountingQuery(fullPathResultFile);
		

		// Getting info about file which has been processed
		SaxonProcessor.getInstance().setXMLSourceDocument(fullPathResultFile);
		// Updating the project counters
		ProjectGlobalCounters.getInstance().incrementNumberOfClasses(GetNumberOfClasses());
		ProjectGlobalCounters.getInstance().incrementNumberOfPrivateClasses(GetNumberOfPrivateClasses());
		ProjectGlobalCounters.getInstance().incrementNumberOfPublicClasses(GetNumberOfPublicClasses());
		
		logger.debug("Xquery counting process finished "+ fullPathResultFile + " generated ...");
	}
	
	
	public void ExecuteSingleFileCountingQuery(String outFileFullPath)
	{
		Properties props = new Properties();
		props.setProperty(OutputKeys.METHOD, "xml");
		props.setProperty(OutputKeys.INDENT, "yes");
		
		try {
			SaxonQueryProvider.getInstance().getSingleFileCountingQueryObject().run
				(SaxonProcessor.getInstance().getDynamicQueryContext(), new StreamResult(new File(outFileFullPath)), props);
		} catch (XPathException e) {
			logger.fatal(e.getMessage());
		}
	}
	
	private int GetNumberOfClasses()
	{
		try {
			SequenceIterator classesIterator = 
				SaxonQueryProvider.getInstance().getNumberOfClassesQueryObject().iterator
				(SaxonProcessor.getInstance().getDynamicQueryContext());

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
	
	private int GetNumberOfPrivateClasses()
	{
		try {
			SequenceIterator classesIterator = 
				SaxonQueryProvider.getInstance().getNumberOfPrivateClassesQueryObject().iterator
				(SaxonProcessor.getInstance().getDynamicQueryContext());

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
	
	private int GetNumberOfPublicClasses()
	{
		try {
			SequenceIterator classesIterator = 
				SaxonQueryProvider.getInstance().getNumberOfPublicClassesQueryObject().iterator
				(SaxonProcessor.getInstance().getDynamicQueryContext());

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

}