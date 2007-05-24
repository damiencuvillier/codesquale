package com.codesquale.metrics.saxon;

import java.io.File;

import javax.xml.transform.stream.StreamSource;

import net.sf.saxon.Configuration;
import net.sf.saxon.query.DynamicQueryContext;
import net.sf.saxon.query.StaticQueryContext;
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
public class SaxonProcessor {
	private static Logger logger = Logger.getLogger(SaxonProcessor.class);

	private static SaxonProcessor _instance = null;

	private Configuration config = null;

	private StaticQueryContext staticContext = null;

	private DynamicQueryContext dynamicContext = null;

	public static SaxonProcessor getInstance() {
		if (_instance == null)
			_instance = new SaxonProcessor();

		return _instance;
	}

	/**
	 * Sets the XML file which the xquery have to be processed on.
	 * 
	 * @param inputFilePath
	 *            Full path of the XML file.
	 */
	@SuppressWarnings("deprecation")
	public void setXMLSourceDocument(String inputFilePath) {
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
	 * Provides the dynamic query context built upon the source document to proceed.
	 * 
	 * @return The DynamicQueryContext object initialized.
	 */
	public DynamicQueryContext getDynamicQueryContext() {
		return dynamicContext;
	}

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
	}

}
