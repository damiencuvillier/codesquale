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

	// ////////////////////
	// QUERY LITERRALS //
	// ////////////////////

	// Number of classes query
	private String numberOfClassesQuery = "//directoryResults/packageAnalysis/packageGlobalMetrics/classes/all";

	private String numberOfOthersClassesQuery = "//directoryResults/packageAnalysis/packageGlobalMetrics/classes/others";

	private String numberOfPublicClassesQuery = "//directoryResults/packageAnalysis/packageGlobalMetrics/classes/public";

	// Methods counting query

	private String numberOfMethodsQuery = "//directoryResults/packageAnalysis/packageGlobalMetrics/methods/all";

	private String numberOfOthersMethodsQuery = "//directoryResults/packageAnalysis/packageGlobalMetrics/methods/others";

	private String numberOfPublicMethodsQuery = "//directoryResults/packageAnalysis/packageGlobalMetrics/methods/public";
	
	//	 Attributes counting query

	private String numberOfAttributesQuery = "//directoryResults/packageAnalysis/packageGlobalMetrics/attributes/all";

	private String numberOfOthersAttributesQuery = "//directoryResults/packageAnalysis/packageGlobalMetrics/attributes/others";

	private String numberOfPublicAttributesQuery = "//directoryResults/packageAnalysis/packageGlobalMetrics/attributes/public";

	// Files and attributes counting
	private String numberOfFilesQuery = "//directoryResults/packageAnalysis/packageGlobalMetrics/files";

	private String packageSizeQuery = "//directoryResults/packageAnalysis/packageGlobalMetrics/totalSize";

	// LOC query
	private String numberOfToliQuery = "//directoryResults/packageAnalysis/packageGlobalMetrics/linesOfCode/toli";

	private String numberOfPlocQuery = "//directoryResults/packageAnalysis/packageGlobalMetrics/linesOfCode/ploc";

	private String numberOfBlliQuery = "//directoryResults/packageAnalysis/packageGlobalMetrics/linesOfCode/blli";

	// ////////////////////////////
	// QUERY EXPRESSION OBJECT //
	// ////////////////////////////

	private XQueryExpression singleFileCountingQuery = null;

	private XQueryExpression numberOfClasses = null;

	private XQueryExpression numberOfOtherClasses = null;

	private XQueryExpression numberOfPublicClasses = null;

	private XQueryExpression numberOfMethods = null;

	private XQueryExpression numberOfOtherMethods = null;

	private XQueryExpression numberOfPublicMethods = null;

	private XQueryExpression numberOfAttributes = null;

	private XQueryExpression numberOfOtherAttributes = null;

	private XQueryExpression numberOfPublicAttributes = null;
	
	private XQueryExpression numberOfFile = null;

	private XQueryExpression numberOfToli;

	private XQueryExpression numberOfPloc;

	private XQueryExpression numberOfBlli;

	private XQueryExpression packageSize;

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
			numberOfOtherClasses = context
					.compileQuery(numberOfOthersClassesQuery);
			numberOfPublicClasses = context
					.compileQuery(numberOfPublicClassesQuery);

			numberOfMethods = context.compileQuery(numberOfMethodsQuery);
			numberOfOtherMethods = context
					.compileQuery(numberOfOthersMethodsQuery);
			numberOfPublicMethods = context
					.compileQuery(numberOfPublicMethodsQuery);
			
			numberOfAttributes = context.compileQuery(numberOfAttributesQuery);
			numberOfOtherAttributes = context
					.compileQuery(numberOfOthersAttributesQuery);
			numberOfPublicAttributes = context
					.compileQuery(numberOfPublicAttributesQuery);

			numberOfToli = context.compileQuery(numberOfToliQuery);

			numberOfPloc = context.compileQuery(numberOfPlocQuery);

			numberOfBlli = context.compileQuery(numberOfBlliQuery);

			numberOfFile = context.compileQuery(numberOfFilesQuery);
			packageSize = context.compileQuery(packageSizeQuery);

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

	public XQueryExpression getNumberOfOtherClassesQueryObject() {
		return numberOfOtherClasses;
	}

	public XQueryExpression getNumberOfPublicClassesQueryObject() {
		return numberOfPublicClasses;
	}
	
	public XQueryExpression getNumberOfMethodsQueryObject() {
		return numberOfMethods;
	}

	public XQueryExpression getNumberOfOtherMethodsQueryObject() {
		return numberOfOtherMethods;
	}

	public XQueryExpression getNumberOfPublicMethodsQueryObject() {
		return numberOfPublicMethods;
	}
	
	public XQueryExpression getNumberOfAttributesQueryObject() {
		return numberOfAttributes;
	}

	public XQueryExpression getNumberOfOtherAttributesQueryObject() {
		return numberOfOtherAttributes;
	}

	public XQueryExpression getNumberOfPublicAttributesQueryObject() {
		return numberOfPublicAttributes;
	}
	

	public XQueryExpression getSingleFileCountingQueryObject() {
		return singleFileCountingQuery;
	}

	public XQueryExpression getNumberOfToliQueryObject() {
		return numberOfToli;
	}

	public XQueryExpression getNumberOfPlocQueryObject() {
		return numberOfPloc;
	}

	public XQueryExpression getNumberOfBlliQueryObject() {
		return numberOfBlli;
	}

	public XQueryExpression getPackageSizeQueryObject() {
		return packageSize;
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
