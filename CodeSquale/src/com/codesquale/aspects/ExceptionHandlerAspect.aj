package com.codesquale.aspects;

import java.io.File;

import com.codesquale.file.FileFilter;
import com.codesquale.utils.ExceptionLevel;
/**
 * Simple exceptions handler aspect
 * 
 * TODO: Implement softened exceptions
 */
public aspect ExceptionHandlerAspect {
	
	/***************************************************************************/
	/***************** Browsing & Parsing Exception Logging ******************************/
	/***************************************************************************/
	
	/**
	 * Catching JavaRecognizer.compilationUnit() exception
	 */
	void around() : call(* com.codesquale.parser.java.JavaRecognizer.compilationUnit()){
		try
		{
			proceed();
		}catch(Exception e)
		{
			ParsingTraceAspect._logger.fatal("Abstract syntaxic tree errors were found: " + e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 * Catching AntlrProcess.analysis() exception
	 */
	void around() : call(* com.codesquale.parser.AntlrParsingProcess.processAnalysis())
	{
		try
		{
			proceed();
		}catch(Exception e)
		{
			ParsingTraceAspect._logger.fatal("Errors were found during parsing: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Catching AntlrProcess.description() exception
	 */
	void around() : call(* com.codesquale.parser.AntlrParsingProcess.processDescription())
	{
		try
		{
			proceed();
		}catch(Exception e)
		{
			ParsingTraceAspect._logger.fatal("Errors were found while retreiving abstract syntaxic tree: " + e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 * Catching AntlrProcess.execute() exception
	 */
	void around() : call(void com.codesquale.parser.AntlrParsingProcess.execute())
	{
		try
		{
			proceed();
		}catch(Exception e)
		{
			ParsingTraceAspect._logger.fatal(e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 * ProjectBrowser Initialisation exception checking
	 */
	void around() : call(void com.codesquale.file.ProjectBrowser.init(java.io.File,java.io.File,java.io.File,com.codesquale.file.FileFilter ))
	{
		try
		{
			proceed();
		}catch(Exception e)
		{
			ParsingTraceAspect._logger.fatal(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Catching DirectoryElement.browseFiles()
	 */
	void around() : call( void com.codesquale.file.DirectoryElement.browseFiles(java.io.File)){
		try
		{
			proceed();
		}catch(Exception e)
		{
			ParsingTraceAspect._logger.fatal(e.getMessage());
			e.printStackTrace();
		}
	}
	/***************************************************************************/
	/***************** /Browsing & Parsing Exception Logging ******************************/
	/***************************************************************************/
	
	
	
	/***************************************************************************/
	/***************** Ant Task Exception Logging ******************************/
	/***************************************************************************/
	
	/**
	 * MetricsTask exception catching
	 */
	void around() : call( void com.codesquale.ant.MetricsTask.validate()){
		try
		{
			proceed();
		}catch(Exception e)
		{
			ParsingTraceAspect._logger.fatal(e.getMessage());
			e.printStackTrace();
		}
	}

	/***************************************************************************/
	/***************** /Ant Task Exception Logging *****************************/
	/***************************************************************************/
	

	/***************************************************************************/
	/***************** Managed Exception Logging *******************************/
	/***************************************************************************/
	
	pointcut traceManagedException(Exception e,ExceptionLevel level) 
		: execution(public static void com.codesquale.utils.Utilities.ManageException(Exception,ExceptionLevel))
		&& args(e,level);
	
	void around(Exception e, ExceptionLevel level): traceManagedException(e,level) {
		switch(level)
		{
		case INFO: 
			ParsingTraceAspect._logger.info(e.getMessage());
			break;
		case DEBUG:
			ParsingTraceAspect._logger.debug(e.getMessage());
			break;
		case TRACE:
			ParsingTraceAspect._logger.trace(e.getMessage());
			break;
		case FATAL:
			ParsingTraceAspect._logger.fatal(e.getMessage());
			break;
		case WARN:
			ParsingTraceAspect._logger.warn(e.getMessage());
			break;
		default:
			ParsingTraceAspect._logger.fatal(e.getMessage());
			break;
		}
		e.printStackTrace();
	}
	

	/***************************************************************************/
	/***************** /Managed Exception Logging ******************************/
	/***************************************************************************/
	
}
