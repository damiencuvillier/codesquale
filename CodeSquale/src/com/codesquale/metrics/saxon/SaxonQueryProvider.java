package com.codesquale.metrics.saxon;

import java.io.IOException;

import net.sf.saxon.query.StaticQueryContext;
import net.sf.saxon.query.XQueryExpression;
import net.sf.saxon.trans.XPathException;

import org.apache.log4j.Logger;

import com.codesquale.utils.Utilities;

/**
 * Provides the specific xquery request needed to calculates counters and
 * metrics.
 * 
 * @author dwillier
 * 
 */
public class SaxonQueryProvider {

	private static Logger logger = Logger.getLogger(SaxonProcessor.class);

	private String numberOfClassesQuery = "//directoryResults/packageAnalysis/packageGlobalMetrics/classes/all";

	private String numberOfOthersClassesQuery = "//directoryResults/packageAnalysis/packageGlobalMetrics/classes/others";

	private String numberOfPublicClassesQuery = "//directoryResults/packageAnalysis/packageGlobalMetrics/classes/public";

	private String numberOfFilesQuery = "//directoryResults/packageAnalysis/packageGlobalMetrics/files";

	private String numberOfToliQuery = "//directoryResults/packageAnalysis/packageGlobalMetrics/linesOfCode/toli";

	private String numberOfPlocQuery = "//directoryResults/packageAnalysis/packageGlobalMetrics/linesOfCode/ploc";

	private String numberOfBlliQuery = "//directoryResults/packageAnalysis/packageGlobalMetrics/linesOfCode/blli";

	private XQueryExpression singleFileCountingQuery = null;

	private XQueryExpression numberOfClasses = null;

	private XQueryExpression numberOfPrivateClasses = null;

	private XQueryExpression numberOfPublicClasses = null;

	private XQueryExpression numberOfFile = null;

	private XQueryExpression numberOfToli;

	private XQueryExpression numberOfPloc;

	private XQueryExpression numberOfBlli;

	/**
	 * Private instance of the class itselft needed by the singleton mechanism.
	 */
	private static SaxonQueryProvider _instance = null;

	/**
	 * Methods that returns the singleton instance of the class.
	 * 
	 * @return A unique SaxonQueryProvider reference.
	 */
	public static SaxonQueryProvider getInstance(String queryFile) {
		if (_instance == null)
			_instance = new SaxonQueryProvider(queryFile);

		return _instance;
	}

	private SaxonQueryProvider(String queryFile) {
		compileQuery(queryFile);
	}

	/**
	 * Compile all the query contained in the SaxonQueryProvider class.
	 * 
	 */
	private void compileQuery(String queryFile) {
		StaticQueryContext context = SaxonProcessor.getInstance()
				.getStaticQueryContext();

		try {
			singleFileCountingQuery = context
					.compileQuery(getSingleFileCountingQuery(queryFile));
			numberOfClasses = context.compileQuery(numberOfClassesQuery);
			numberOfPrivateClasses = context
					.compileQuery(numberOfOthersClassesQuery);
			numberOfPublicClasses = context
					.compileQuery(numberOfPublicClassesQuery);
			numberOfFile = context.compileQuery(numberOfFilesQuery);

			numberOfToli = context.compileQuery(numberOfToliQuery);

			numberOfPloc = context.compileQuery(numberOfPlocQuery);

			numberOfBlli = context.compileQuery(numberOfBlliQuery);

		} catch (XPathException ex) {
			logger.fatal(ex.getMessage());
		}
	}

	/*
	 * XQuery object
	 */

	public XQueryExpression getNumberOfFilesObject() {
		return numberOfFile;
	}

	public XQueryExpression getNumberOfClassesQueryObject() {
		return numberOfClasses;
	}

	public XQueryExpression getNumberOfPrivateClassesQueryObject() {
		return numberOfPrivateClasses;
	}

	public XQueryExpression getNumberOfPublicClassesQueryObject() {
		return numberOfPublicClasses;
	}

	public XQueryExpression getSingleFileCountingQueryObject() {
		return singleFileCountingQuery;
	}
	
	public XQueryExpression getNumberOfToliQueryObject()
	{
		return numberOfToli;
	}
	
	public XQueryExpression getNumberOfPlocQueryObject()
	{
		return numberOfPloc;
	}
	
	public XQueryExpression getNumberOfBlliQueryObject()
	{
		return numberOfBlli;
	}
	

	private String getSingleFileCountingQuery(String queryFile) {
		try {
			return Utilities.readFileAsString(queryFile);
		} catch (IOException e) {
			logger.fatal("The XQuery file seems to be unavailable.");
		}

		return null;
	}
	
}
