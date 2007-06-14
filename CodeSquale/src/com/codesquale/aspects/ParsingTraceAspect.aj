package com.codesquale.aspects;


import java.io.FileOutputStream;

import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.SourceLocation;

import org.apache.log4j.Logger;


import com.codesquale.file.ProjectBrowser;
import com.codesquale.parser.IParsingUnit;

public aspect ParsingTraceAspect {
	
	static Logger _logger;
	
	ParsingTraceAspect() {
		
	}

	/**
	 * Define a logger for the poincuted class
	 */
	pointcut loggerPoint() :
		execution(* *.*(..)) 
		//&& !execution(* *.set*(..)) 
		//&& !execution(* *.get*(..)) 
		&& !within(TraceAspect);
	
	after():loggerPoint()
	{
			_logger = Logger.getLogger(thisJoinPoint.getSignature().getDeclaringType());
		
	}


	
	/**
	 * Pointcut for Parsing unit factory unit instanciation
	 */
	pointcut debugParsingUnitFactory(com.codesquale.parser.ParsingUnitFactory f):
		target(f) 
		&& call(public IParsingUnit createInstance()) 
		&& !within(TraceAspect);

	/**
	 * Pointcuts for ParsingUnit
	 */
	pointcut traceParseFile(com.codesquale.parser.ParsingUnit u)
		: target(u) 
		&& call(public void DoParse(java.io.File) ) 
		&& !within(TraceAspect);
	
	pointcut traceASTTransform(com.codesquale.parser.ParsingUnit u)
		: target(u) 
		&& call(public FileOutputStream ASTToXML(String)) 
		&& !within(TraceAspect);
	
	/**
	 * FileFilter init pointcut
	 */
	pointcut traceFileFilterInit(com.codesquale.file.FileFilter f)
		: target(f)
		&& call (public void addFileType(int))
		&& !within(TraceAspect);
	/**
	 * ProjectBrowser Initialisation pointcut
	 */
	pointcut traceProjectBrowser(com.codesquale.file.ProjectBrowser p)
		: target(p)
		&& call (public void init(java.io.File,java.io.File,java.io.File,com.codesquale.file.FileFilter))
		&& !within(TraceAspect);
	
	
	/**
	 * Tracing File Filter initialisation
	 */
	before(com.codesquale.file.FileFilter f) : traceFileFilterInit(f)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();
		ParsingTraceAspect._logger.trace(sig.getDeclaringTypeName() + "." + sig.getName() + " Filter initialisation" );
	}
	/**
	 * ProjectBrowser Initialisation ptracing 
	 */
	before(com.codesquale.file.ProjectBrowser p) : traceProjectBrowser(p)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();
		ParsingTraceAspect._logger.trace(sig.getDeclaringTypeName() + "." + sig.getName() + " Browsing project files" );
	}
	/**
	 * Before parsing unit by factory instanciation  
	 */
	before(com.codesquale.parser.ParsingUnitFactory f) : debugParsingUnitFactory(f)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();
		ParsingTraceAspect._logger.debug(sig.getDeclaringTypeName() + "." + sig.getName() + 
				 "  call");
	}
	
	/**
	 * before parsing file 
	 */
	before(com.codesquale.parser.ParsingUnit u) : traceParseFile(u)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();

		ParsingTraceAspect._logger.debug( sig.getDeclaringTypeName() + "."+ sig.getName()+ " parse: "
				+ u.getFileName());
		ParsingTraceAspect._logger.trace("parsing file: "
				+ u.getFileName());
	}

	/**
	 * before writing XML AST Transformation 
	 */
	before(com.codesquale.parser.ParsingUnit u) : traceASTTransform(u)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();
		
		ParsingTraceAspect._logger.debug(sig.getDeclaringTypeName() + "."+ sig.getName() + " transform: "
				+ u.getXmlFileName());
		ParsingTraceAspect._logger.trace("writing description: "
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
