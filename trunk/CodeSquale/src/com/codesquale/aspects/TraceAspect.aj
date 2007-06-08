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
	pointcut loggerPoint() :execution(* *.*(..)) && !within(TraceAspect);
	after():loggerPoint()
	{
			_logger = Logger.getLogger(thisJoinPoint.getSignature().getDeclaringType());
		
	}

	/**
	 * ANTLR Parsing process logging
	 * 
	 */
	pointcut traceParsingProcess(com.codesquale.parser.AntlrParsingProcess p): 
		target(p) && call(private void processAnalysis()) && !within(TraceAspect);

	/**
	 * ANTLR XML Description process 
	 */
	pointcut traceDescriptionProcess(com.codesquale.parser.AntlrParsingProcess p): 
		target(p) && call(private void processDescription()) && !within(TraceAspect);

	
	/**
	 * Pointcut for Parsing unit factory unit instanciation
	 */
	pointcut debugParsingUnitFactory(com.codesquale.parser.ParsingUnitFactory f):
		target(f) && call(public IParsingUnit createInstance()) && !within(TraceAspect);

	/**
	 * Pointcuts for ParsingUnit
	 */
	pointcut traceParseFile(com.codesquale.parser.ParsingUnit u)
		: target(u) && call(public void DoParse(java.io.File) ) && !within(TraceAspect);
	
	pointcut traceASTTransform(com.codesquale.parser.ParsingUnit u)
	: target(u) && call(public FileOutputStream ASTToXML(String)) && !within(TraceAspect);
	


	/**
	 * Before Intercepting AntlrParsingProcess
	 */
	before(com.codesquale.parser.AntlrParsingProcess p) : traceParsingProcess(p)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();
		_logger.trace("[Process] " +  sig.getDeclaringTypeName() + "." + sig.getName() + " start files analysis");
	}
	/**
	 * After Intercepting AntlrParsingProcess
	 */
	after(com.codesquale.parser.AntlrParsingProcess p) : traceParsingProcess(p)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();
		_logger.trace("[Process] " +  sig.getDeclaringTypeName() + "." + sig.getName() + " finished");
	}
	
	
	/**
	 * Before Intercepting AntlrParsingProcess
	 */
	before(com.codesquale.parser.AntlrParsingProcess p) : traceDescriptionProcess(p)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();
		_logger.trace("[Process] " +  sig.getDeclaringTypeName() + "." + sig.getName() + " writing files description in "+ ProjectBrowser.getInstance().getProjectOutputFileName());
	}
	/**
	 * After Intercepting AntlrParsingProcess
	 */
	after(com.codesquale.parser.AntlrParsingProcess p) : traceDescriptionProcess(p)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();
		_logger.trace("[Process] " +  sig.getDeclaringTypeName() + "." + sig.getName() + " finished");
	}
	
	
	/**
	 * Before parsing unit by factory instanciation  
	 */
	before(com.codesquale.parser.ParsingUnitFactory f) : debugParsingUnitFactory(f)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();
		_logger.debug("[Factory] "+ sig.getDeclaringTypeName() + "." + sig.getName() + 
				 "  call");
	}
	
	/**
	 * After parsing file 
	 */
	after(com.codesquale.parser.ParsingUnit u) : traceParseFile(u)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();

		_logger.debug("[Parsing] " +  sig.getDeclaringTypeName() + "."+ sig.getName()+ " parse: "
				+ u.getFileName());
		_logger.trace("[Parsing] file: "
				+ u.getFileName());
	}

	/**
	 * After writing XML AST Transformation 
	 */
	after(com.codesquale.parser.ParsingUnit u) : traceASTTransform(u)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();
		
		_logger.debug("[Parsing] " + sig.getDeclaringTypeName() + "."+ sig.getName() + " transform: "
				+ u.getXmlFileName());
		_logger.trace("[Parsing] writing description: "
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
