package com.codesquale.aspects;


import java.io.FileOutputStream;
import org.aspectj.lang.Signature;
import org.apache.log4j.Logger;
import com.codesquale.parser.IParsingUnit;

/**
 * Aspect that log the parsing execution
 * Define the weaving logger
 * @author mbourguignon
 */
public aspect ParsingTraceAspect {
	
	static Logger _logger;
	
	ParsingTraceAspect() {
		
	}
	
	/***************************************************************************/
	/*********************** Class logger  *************************************/
	/***************************************************************************/

	/**
	 * Define a logger for the poincuted class
	 */
	pointcut loggerPoint() :
		execution(* *.*(..)) 
		// FIXME: fix setters & getters exclusion
		//&& !execution(* *.set*(..)) 
		//&& !execution(* *.get*(..)) 
		&& !within(com.codesquale.view.*.*)
		&& !within(ParsingTraceAspect);
	
	/**
	 * Class logger instanciation 
	 */
	after():loggerPoint()
	{
			_logger = Logger.getLogger(thisJoinPoint.getSignature().getDeclaringType());
		
	}
	
	/***************************************************************************/
	/*********************** /Class logger  ************************************/
	/***************************************************************************/

	/***************************************************************************/
	/********************** Parsing Logging ************************************/
	/***************************************************************************/

	/**
	 * Pointcut for Parsing unit factory unit instanciation
	 */
	pointcut debugParsingUnitFactory(com.codesquale.parser.ParsingUnitFactory f):
		target(f) 
		&& call(public IParsingUnit createInstance()) 
		&& !within(ParsingTraceAspect);

	/**
	 * Pointcut for parsing file
	 */
	pointcut traceParseFile(com.codesquale.parser.ParsingUnit u)
		: target(u) 
		&& call(public void DoParse(java.io.File) ) 
		&& !within(ParsingTraceAspect);
	
	/**
	 * Poincut for Abstract Transformation
	 */
	pointcut traceASTTransform(com.codesquale.parser.ParsingUnit u)
		: target(u) 
		&& call(public FileOutputStream ASTToXML(String)) 
		&& !within(ParsingTraceAspect);
	
	/**
	 * Before parsing unit instanciation by factory   
	 */
	before(com.codesquale.parser.ParsingUnitFactory f) : debugParsingUnitFactory(f)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();
		ParsingTraceAspect._logger.debug(sig.getDeclaringTypeName() + "." + sig.getName() + 
				 "  call");
	}
	
	/**
	 * Before parsing file 
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
	 * Before writing XML AST Transformation 
	 */
	before(com.codesquale.parser.ParsingUnit u) : traceASTTransform(u)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();
		
		ParsingTraceAspect._logger.debug(sig.getDeclaringTypeName() + "."+ sig.getName() + " transform: "
				+ u.getXmlFileName());
		ParsingTraceAspect._logger.trace("writing description: "
				+ u.getXmlFileName());
	}
	
	/***************************************************************************/
	/********************** /Parsing Logging ***********************************/
	/***************************************************************************/

	/***************************************************************************/
	/********************** Project browsing Logging ***************************/
	/***************************************************************************/

	
	/**
	 * FileFilter init pointcut
	 */
	pointcut traceFileFilterInit(com.codesquale.file.FileFilter f)
		: target(f)
		&& call (public void addFileType(int))
		&& !within(ParsingTraceAspect);
	/**
	 * ProjectBrowser Initialisation pointcut
	 */
	pointcut traceProjectBrowser(com.codesquale.file.ProjectBrowser p)
		: target(p)
		&& call (public void init(java.io.File,java.io.File,java.io.File,com.codesquale.file.FileFilter))
		&& !within(ParsingTraceAspect);

	/**
	 * Tracing File Filter initialisation
	 */
	before(com.codesquale.file.FileFilter f) : traceFileFilterInit(f)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();
		ParsingTraceAspect._logger.trace(sig.getDeclaringTypeName() + "." + sig.getName() + " Filter initialisation" );
	}
	
	/**
	 * ProjectBrowser Initialisation parsing 
	 */
	before(com.codesquale.file.ProjectBrowser p) : traceProjectBrowser(p)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();
		ParsingTraceAspect._logger.trace(sig.getDeclaringTypeName() + "." + sig.getName() + " Browsing project files" );
	}

	/***************************************************************************/
	/**********************  /Project browsing Logging *************************/
	/***************************************************************************/

/**
 *
 * TOTAL TRACE SAMPLE
 *	
 pointcut totalTrace() : execution(* *.*(..)) && !within(ParsingTraceAspect);
		
 before() : totalTrace() {
	 Signature sig = thisJoinPointStaticPart.getSignature();
	 _logger.log(Level.TRACE, "[Entering - "
	 + sig.getDeclaringType().getName() + "." + sig.getName() + "]");
 }
 after() : totalTrace() {
	 Signature sig = thisJoinPointStaticPart.getSignature();
	 _logger.log(Level.TRACE, "[Exiting - "
	 + sig.getDeclaringType().getName() + "." + sig.getName() + "]");
 }

 */ 

}
