package com.codesquale.metrics.saxon;

import java.io.File;
import java.util.Properties;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.stream.StreamResult;

import net.sf.saxon.om.NodeInfo;
import net.sf.saxon.om.SequenceIterator;
import net.sf.saxon.trans.XPathException;

import org.apache.log4j.Logger;

import com.codesquale.metrics.IMetricsFactory;
import com.codesquale.metrics.ProjectGlobalCounters;

/**
 * 
 * @author dwillier
 * 
 * Class in charge of executing XQuery Request based on Saxon implementation and
 * returning results of request.
 * 
 */
public class SaxonMetricsFactory implements IMetricsFactory {

	/**
	 * Log object for the SaxonMetricsFactory
	 */
	private static Logger logger = Logger.getLogger(SaxonMetricsFactory.class);

	/**
	 * This method executes the main xquery (calculates counters and ratios) on
	 * a single xml result file generated by the XSLT transformation from the
	 * XML ANTLR tree. Moreover, it increments the project global counter each
	 * time a input file is processed.
	 * 
	 * @param fullPathSourceFile
	 *            It is the full path of the input results file.
	 * @param fullPathResultFile
	 *            It is the full path of the output file generated by the
	 *            method. This file contains counters and metrics calculated
	 *            from the input file.
	 */
	@SuppressWarnings("deprecation")
	public void CalculateCountersFromSourceFile(String fullPathSourceFile,
			String fullPathResultFile) {
		logger.debug("Processing Xquery counting from the file "
				+ fullPathSourceFile + " ...");
		// Setting to SaxonProcessor the file to analyze
		SaxonProcessor.getInstance().setXMLSourceDocument(fullPathSourceFile);
		// Call the private method that executes the query and serializes the
		// result
		ExecuteSingleFileCountingQuery(fullPathResultFile);

		// Setting to SaxonProcessor the result file just being generated
		SaxonProcessor.getInstance().setXMLSourceDocument(fullPathResultFile);
		// Retrieving the package counters calculated from our result file
		int numberOfClasses = GetNumberOfClasses();
		int numberOfPrivateClasses = GetNumberOfPrivateClasses();
		int numberOfPublicClasses = GetNumberOfPublicClasses();

		// Incrementing the global project counters
		if (numberOfClasses != -1)
			ProjectGlobalCounters.getInstance().incrementNumberOfClasses(
					numberOfClasses);
		if (numberOfPrivateClasses != -1)
			ProjectGlobalCounters.getInstance()
					.incrementNumberOfPrivateClasses(numberOfPrivateClasses);
		if (numberOfPublicClasses != -1)
			ProjectGlobalCounters.getInstance().incrementNumberOfPublicClasses(
					numberOfPublicClasses);

		logger.debug("Xquery counting process finished " + fullPathResultFile
				+ " generated ...");
	}

	/**
	 * The method executes the counting xquery on a Tansformed XSLT results file.
	 * 
	 * @param outFileFullPath
	 *            Represents the full path of the counters file to be created.
	 */
	public void ExecuteSingleFileCountingQuery(String outFileFullPath) {
		Properties props = new Properties();
		props.setProperty(OutputKeys.METHOD, "xml");
		props.setProperty(OutputKeys.INDENT, "yes");

		try {
			SaxonQueryProvider.getInstance().getSingleFileCountingQueryObject()
					.run(SaxonProcessor.getInstance().getDynamicQueryContext(),
							new StreamResult(new File(outFileFullPath)), props);
		} catch (XPathException e) {
			logger.fatal(e.getMessage());
		}
	}

	/**
	 * This method executes a simple xquery request to obtain number of private classes.
	 * @return Nnumber of private classes got by the request
	 */
	private int GetNumberOfClasses() {
		try {
			SequenceIterator classesIterator = SaxonQueryProvider.getInstance()
					.getNumberOfClassesQueryObject().iterator(
							SaxonProcessor.getInstance()
									.getDynamicQueryContext());

			while (true && classesIterator != null) {
				NodeInfo classesValue = (NodeInfo) classesIterator.next();
				if (classesValue == null)
					break;
				return Integer.parseInt(classesValue.getStringValue());
			}

		} catch (XPathException e) {
			logger.fatal(e.getMessage());
		}
		return -1;
	}

	/**
	 * This method executes a simple xquery request to obtain total number of classes.
	 * @return Total number of classes got by the request
	 */
	private int GetNumberOfPrivateClasses() {
		try {
			SequenceIterator classesIterator = SaxonQueryProvider.getInstance()
					.getNumberOfPrivateClassesQueryObject().iterator(
							SaxonProcessor.getInstance()
									.getDynamicQueryContext());

			while (true && classesIterator != null) {
				NodeInfo classesValue = (NodeInfo) classesIterator.next();
				if (classesValue == null)
					break;
				return Integer.parseInt(classesValue.getStringValue());
			}

		} catch (XPathException e) {
			logger.fatal(e.getMessage());
		}
		return -1;
	}

	/**
	 * This method executes a simple xquery request to obtain number of public classes.
	 * @return Total number of public classes got by the request
	 */
	private int GetNumberOfPublicClasses() {
		try {
			SequenceIterator classesIterator = SaxonQueryProvider.getInstance()
					.getNumberOfPublicClassesQueryObject().iterator(
							SaxonProcessor.getInstance()
									.getDynamicQueryContext());

			while (true && classesIterator != null) {
				NodeInfo classesValue = (NodeInfo) classesIterator.next();
				if (classesValue == null)
					break;
				return Integer.parseInt(classesValue.getStringValue());
			}

		} catch (XPathException e) {
			logger.fatal(e.getMessage());
		}
		return -1;
	}

}