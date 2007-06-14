package com.codesquale.aspects;

import java.io.File;

import com.codesquale.file.FileFilter;

public aspect ExceptionHandlerAspect {

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
	
}
