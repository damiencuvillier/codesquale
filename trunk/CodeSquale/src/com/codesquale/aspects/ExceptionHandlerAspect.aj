package com.codesquale.aspects;

import org.apache.log4j.Logger;

public aspect ExceptionHandlerAspect {
	Logger _logger;
	
	/**
	 * Define a logger for the poincuted class
	 */
	pointcut loggerPoint() :execution(* *.*(..)) && !within(TraceAspect);
	after():loggerPoint()
	{
			_logger = Logger.getLogger(thisJoinPoint.getSignature().getDeclaringType());
		
	}

	/**
	 * Catching compilation unit exception
	 */
	declare soft : java.lang.Exception : call(void com.codesquale.parser.java.JavaRecognizer.compilationUnit());
	void around() : call(* com.codesquale.parser.java.JavaRecognizer.compilationUnit()){
		try
		{
			proceed();
		}catch(Exception e)
		{
			_logger.fatal("Abstract syntaxic tree errors were found: " + e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 * Antlr Process analysis exception
	 */
	declare soft: java.lang.Exception : call(void com.codesquale.parser.AntlrParsingProcess.processAnalysis());
	void around() : call(* com.codesquale.parser.AntlrParsingProcess.processAnalysis())
	{
		try
		{
			proceed();
		}catch(Exception e)
		{
			_logger.fatal("Errors were found during parsing: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Antlr Process description exception
	 */
	declare soft: java.lang.Exception : call(void com.codesquale.parser.AntlrParsingProcess.processDescription());
	void around() : call(* com.codesquale.parser.AntlrParsingProcess.processDescription())
	{
		try
		{
			proceed();
		}catch(Exception e)
		{
			_logger.fatal("Errors were found during parsing: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
}
