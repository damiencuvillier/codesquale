package com.codesquale.aspects;


import java.io.FileOutputStream;

import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.SourceLocation;

import org.apache.log4j.Logger;


import com.codesquale.file.ProjectBrowser;
import com.codesquale.parser.IParsingUnit;

public aspect TraceAspect {
	
	Logger _logger;
	
	TraceAspect() {
		
	}
	
	/**
	 * Define a logger for the poincuted class
	 */
	pointcut loggerPoint() :execution(* *.*(..)) && !within(TraceAspect);;
	after():loggerPoint()
	{
		_logger = Logger.getLogger(thisJoinPoint.getSignature().getDeclaringType());
	}
	
	/**
	 * Pointcuts
	 */
	pointcut infoParseFile(com.codesquale.parser.ParsingUnit u)
		: target(u) && call(public void DoParse(java.io.File) ) && !within(TraceAspect);
	
	pointcut infoASTTransform(com.codesquale.parser.ParsingUnit u)
	: target(u) && call(public FileOutputStream ASTToXML(String)) && !within(TraceAspect);
	
	
	 pointcut exceptionLoggerMethods()
	 : call (java.lang.Exception +.new(..)) && !within(TraceAspect);
		
	 after() throwing : exceptionLoggerMethods() {
	 SourceLocation src = thisJoinPointStaticPart.getSourceLocation();
	 	_logger.fatal("[Exception] - "+src.getWithinType()+ "." +
	 thisJoinPointStaticPart.getSignature().getName());
	 }

	/**
	 * ANTLR Parsing process logging
	 * 
	 */
	pointcut infoParsingProcess(com.codesquale.parser.AntlrParsingProcess p): 
		target(p) && call(private void processAnalysis()) && !within(TraceAspect);

	before(com.codesquale.parser.AntlrParsingProcess p) : infoParsingProcess(p)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();
		_logger.info("[Process] " +  sig.getDeclaringTypeName() + "." + sig.getName() + " start files analysis");
	}

	after(com.codesquale.parser.AntlrParsingProcess p) : infoParsingProcess(p)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();
		_logger.info("[Process] " +  sig.getDeclaringTypeName() + "." + sig.getName() + " finished");
	}
	
	
	/**
	 * ANTLR Description process 
	 */
	pointcut infoDescriptionProcess(com.codesquale.parser.AntlrParsingProcess p): 
		target(p) && call(private void processDescription()) && !within(TraceAspect);

	before(com.codesquale.parser.AntlrParsingProcess p) : infoDescriptionProcess(p)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();
		_logger.info("[Process] " +  sig.getDeclaringTypeName() + "." + sig.getName() + " writing files description in "+ ProjectBrowser.getInstance().getProjectOutputFileName());
	}

	after(com.codesquale.parser.AntlrParsingProcess p) : infoDescriptionProcess(p)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();
		_logger.info("[Process] " +  sig.getDeclaringTypeName() + "." + sig.getName() + " finished");
	}
	
	
	/**
	 * DEBUG Parsing unit factory unit instanciation
	 */
	pointcut debugParsingUnitFactory(com.codesquale.parser.ParsingUnitFactory f):
		target(f) && call(public IParsingUnit createInstance()) && !within(TraceAspect);

	before(com.codesquale.parser.ParsingUnitFactory f) : debugParsingUnitFactory(f)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();
		_logger.debug("[Factory] "+ sig.getDeclaringTypeName() + "." + sig.getName() + 
				 "  call");
	}

	pointcut logAntTarget()
	 : execution (org.apache.tools.ant.taskdefs.optional.TraXLiaison +.new(..)) && !within(TraceAspect);

	before(): logAntTarget(){
		Signature sig = thisJoinPointStaticPart.getSignature();
		_logger.debug("[Target] "+ sig.getDeclaringTypeName() + "." + sig.getName() + 
				 "  call");
	}
//	pointcut infoAntlrTask(org.apache.tools.ant.Task t)
//	: target(t) && call(public void execute() ) && !within(TraceAspect);
//	
//	before(org.apache.tools.ant.Task t) : infoAntlrTask(t)
//	{
//		Signature sig = thisJoinPointStaticPart.getSignature();
//		
//		_logger.info("[Process] execute task " + sig.getName());
//	}
	
	
	
	
	
	/**
	 * Parsing file
	 */
	after(com.codesquale.parser.ParsingUnit u) : infoParseFile(u)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();

		_logger.debug("[Parsing] " +  sig.getDeclaringTypeName() + "."+ sig.getName()+ " parse: "
				+ u.getFileName());
		_logger.info("[Parsing] file: "
				+ u.getFileName());
	}

	/**
	 * ASTTransform
	 */
	after(com.codesquale.parser.ParsingUnit u) : infoASTTransform(u)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();
		
		_logger.debug("[Parsing] " + sig.getDeclaringTypeName() + "."+ sig.getName() + " transform: "
				+ u.getXmlFileName());
		_logger.info("[Parsing] writing description: "
				+ u.getXmlFileName());
	}

//	 pointcut totalTrace()
//	 : execution(* *.*(..)) && !within(TraceAspect);
//		
//	 before() : totalTrace() {
//	 Signature sig = thisJoinPointStaticPart.getSignature();
//	 _logger.log(Level.TRACE, "[Entering - "
//	 + sig.getDeclaringType().getName() + "." + sig.getName() + "]");
//	 }
//	 after() : totalTrace() {
//	 Signature sig = thisJoinPointStaticPart.getSignature();
//	 _logger.log(Level.TRACE, "[Exiting - "
//	 + sig.getDeclaringType().getName() + "." + sig.getName() + "]");
//	 }
//		

	// before(com.codesquale.parser.AntlrParsingProcess p) : parsers(p) {
	// Signature sig = thisJoinPointStaticPart.getSignature();
	// _logger.log(Level.TRACE, "[Entering] - "
	// + sig.getDeclaringType().getName() + "." + sig.getName() + "]");
	// }
	//
	// after(com.codesquale.parser.AntlrParsingProcess p) :parsers(p){
	// SourceLocation src = thisJoinPointStaticPart.getSourceLocation();
	// Logger logger = Logger.getLogger(src.getWithinType());
	// logger.log(Level.TRACE, "[Exiting] - " + src.getWithinType() + "."
	// + thisJoinPointStaticPart.getSignature().getName());
	// }
	//
	// after(com.codesquale.parser.AntlrParsingProcess p) returning :
	// parsers(p){
	// SourceLocation src = thisJoinPointStaticPart.getSourceLocation();
	// Logger logger = Logger.getLogger(src.getWithinType());
	// logger.log(Level.DEBUG, "[Returning] - " + src.getWithinType() + "."
	// + thisJoinPointStaticPart.getSignature().getName());
	// }
	//
	// after(com.codesquale.parser.AntlrParsingProcess p) throwing(Exception e)
	// : parsers(p) {
	// SourceLocation src = thisJoinPointStaticPart.getSourceLocation();
	// Logger logger = Logger.getLogger(src.getWithinType());
	// logger.log(Level.FATAL, "[Exception] - " + src.getWithinType() + "."
	// + thisJoinPointStaticPart.getSignature().getName());
	// }

	// after(): handler(Exception){
	// SourceLocation src = thisJoinPointStaticPart.getSourceLocation();
	// Logger logger = Logger.getLogger(src.getWithinType());
	// logger.log(Level.FATAL, "[HandleException] - "+src.getWithinType()+
	// thisJoinPointStaticPart.getSignature().getName());
	// }
}