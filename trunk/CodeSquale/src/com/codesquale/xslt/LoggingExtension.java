package com.codesquale.xslt;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.xalan.extensions.XSLProcessorContext;
import org.apache.xalan.templates.ElemExtensionCall;
import org.apache.xalan.templates.ElemTemplateElement;

/** Xalan Logging Extension.
 * add features in xalan xslt processor to log events with log4j
 * 
 * To declare this extension in XSLT StyleSheet Header, use this syntax :
 *  
 * 	<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
 *		xmlns:xsi="http://www.w3.org/2000/10/XMLSchema-instance"
 *		xmlns:xalan="http://xml.apache.org/xslt" 
 *		version="1.0"
 * 		xmlns:log4j="xalan://com.codesquale.xslt.LoggingExtension"
 *  	extension-element-prefixes="log4j">
 *  
 *  <xalan:component prefix="log4j" elements="log">
		<xalan:script lang="javaclass" 
			src="xalan://com.codesquale.xslt.LoggingExtension" />
	</xalan:component>
 * @author DCUVILLIER
 */
public final class LoggingExtension extends ElemTemplateElement {
	/**
	 * Serialization ID
	 */
	private static final long serialVersionUID = 1929912350303337416L;
	
	private static final String[] levelTab = new String[]{"debug", "info", "warn", "error", "fatal"};
	/** Private Constructor.
	 *  Util Class
	 *  Don't need to be instanciated
	 */
	private LoggingExtension() {
	
	}
	/**
	 * Xalan Extension 
	 * Implements xalan syntax
	 * 
	 * Checks parameters and call log method
	 * @param xslContext
	 * auto param
	 * @param extensionCall
	 * autoparam 
	 */
	public static void log(final XSLProcessorContext xslContext, 
		final ElemExtensionCall extensionCall) {
		String level, message, loggerName;
		// Getting Attributes 
		level = extensionCall.getAttribute("level");
		message = extensionCall.getAttribute("message");
		loggerName = extensionCall.getAttribute("loggerName");
		// Debug is default value
		if (level.equals("")) {
			level = "debug";
		}
		if (loggerName.equals("")){
			loggerName = "TechnicalCodeSquale";
		}
		if (message.equals("")) {
			log("warn", "LOG4J Message is not setted ", "TechnicalCodeSquale");
		}else {
			log(level, message, loggerName);
		}
	}
	
	private static void log(String level, String message, String loggerName){
	// Log4J Logger
	Logger logger;
	
	// Checks the logging level
	if (!isLevel(level)) {
		logger = Logger.getLogger("GlobalCodeSqualeProcess");
		logger.error("LoggingExtension has tried to log a message +"
				+ "with a bad level :" + level);
	}
	
	// If level is right, log the setted message
	logger = Logger.getLogger(loggerName);
	logger.log(Level.toLevel(level), message);
	
}
	/**
	 * Tell if param is a right LOG4J level
	 * @param level 
	 * LOG4J level
	 * @return true if parameter is a right level
	 */
	private static boolean isLevel(final String level) {
		for (int i = 0; i < levelTab.length; i++) {
			if (level.equals(levelTab[i])) {
				return true;
			}
		}
		return false;
	}

}
