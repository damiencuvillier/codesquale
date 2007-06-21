package com.codesquale.metrics;

import java.io.File;
import java.util.Properties;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.stream.StreamSource;

import net.sf.saxon.Configuration;
import net.sf.saxon.query.DynamicQueryContext;
import net.sf.saxon.query.StaticQueryContext;
import net.sf.saxon.query.XQueryExpression;
import net.sf.saxon.trans.XPathException;

import org.apache.log4j.Logger;

/**
 * 
 * This class is the encapsulation of the saxon implementation. It provides the
 * few essential functionnalities of Saxon such as "setting the source xml
 * document to proceed xquery on", "providing the execution context", etc.
 * 
 * @author dwillier
 * 
 */
public final class SaxonProcessor {

	/**
	 * It is the unique logger of the class.
	 */
	private static Logger logger = Logger.getLogger(SaxonProcessor.class);

	/**
	 * It is the singleton attribute of the class.
	 */
	private static SaxonProcessor uniqueInstance = null;

	/**
	 * Represents the SAXONB XQuery Configuration. Contains some informations
	 * about the XQuery configuration.
	 */
	private Configuration config = null;

	/**
	 * Contains the informations about the Query context such as the
	 * DefaultNamespace or the BaseURI.
	 */
	private StaticQueryContext staticContext = null;

	/**
	 * Provides the dynamic context to process the execution of the XQuery.
	 */
	private DynamicQueryContext dynamicContext = null;

	/**
	 * Represents the properties of the produced XML output.
	 */
	private Properties properties;

	/**
	 * Gives the unique instance of the SaxonProcessor.
	 * 
	 * @return An unique instance of SaxonProcessor
	 */
	public static SaxonProcessor getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new SaxonProcessor();
		}

		return uniqueInstance;
	}

	/**
	 * Compile a XqueryExpression from a literal query string.
	 * 
	 * @param expr
	 *            the literal XQuery string to compile
	 * @return the compiled expression
	 * @throws XPathException
	 *             Throw XPathException if the query cannot be compiled.
	 */
	public XQueryExpression compileXquery(final String expr)
			throws XPathException {
		return staticContext.compileQuery(expr);
	}

	/**
	 * Sets the XML file which the xquery have to be processed on.
	 * 
	 * @param inputFilePath
	 *            Full path of the XML file.
	 */
	@SuppressWarnings("deprecation")
	public void setXMLSourceDocument(final String inputFilePath) {
		// Preparing the single file to analyze
		File inputFile = null;
		StreamSource inputStreamSource = null;

		// Loading the file to analyze
		inputFile = new File(inputFilePath);
		inputStreamSource = new StreamSource(inputFile);

		try {
			dynamicContext.setContextNode(config
					.buildDocument(inputStreamSource));
		} catch (XPathException e) {
			logger.fatal(e.getMessage());
		}
	}

	/**
	 * Provides the static query context of Saxon.
	 * 
	 * @return The StaticQueryContext object initialized.
	 */
	public StaticQueryContext getStaticQueryContext() {
		return staticContext;
	}

	/**
	 * Provides the dynamic query context built upon the source document to
	 * proceed.
	 * 
	 * @return The DynamicQueryContext object initialized.
	 */
	public DynamicQueryContext getDynamicQueryContext() {
		return dynamicContext;
	}

	/**
	 * Default constructor. Private because the class is singleton. It
	 * initializes the XQueryEngine.
	 * 
	 */
	private SaxonProcessor() {
		initProcessor();
	}

	/**
	 * Initializes the saxon processor.
	 * 
	 */
	private void initProcessor() {
		// Creating configuration
		config = new Configuration();
		// Creating static context : used for compile xquery
		staticContext = new StaticQueryContext(config);
		// Creating dynamic context : used for process xquery
		dynamicContext = new DynamicQueryContext(config);

		properties = new Properties();
		properties.setProperty(OutputKeys.METHOD, "xml");
		properties.setProperty(OutputKeys.INDENT, "yes");
	}

	/**
	 * Get the XQuery configuration.
	 * @return Xquery Configuration
	 */
	public Configuration getConfig() {
		return config;
	}

	/**
	 * Get the XML formatting properties.
	 * @return Properties describing indent and other output formating
	 */
	public Properties getProperties() {
		return properties;
	}

}
