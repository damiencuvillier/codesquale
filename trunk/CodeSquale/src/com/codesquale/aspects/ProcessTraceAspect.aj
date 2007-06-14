package com.codesquale.aspects;

import org.aspectj.lang.Signature;

import com.codesquale.file.ProjectBrowser;

public aspect ProcessTraceAspect {
	/**
	 * ANTLR Parsing process logging
	 * 
	 */
	pointcut traceParsingProcess(com.codesquale.parser.AntlrParsingProcess p): 
		target(p) 
		&& call(private void processAnalysis()) 
		&& !within(TraceAspect);

	/**
	 * ANTLR XML Description process 
	 */
	pointcut traceDescriptionProcess(com.codesquale.parser.AntlrParsingProcess p): 
		target(p) 
		&& call(private void processDescription()) 
		&& !within(TraceAspect);
	
	/**
	 * Before Intercepting AntlrParsingProcess
	 */
	before(com.codesquale.parser.AntlrParsingProcess p) : traceParsingProcess(p)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();
		ParsingTraceAspect._logger.trace( sig.getDeclaringTypeName() + "." + sig.getName() + " start files analysis");
	}
	/**
	 * After Intercepting AntlrParsingProcess
	 */
	after(com.codesquale.parser.AntlrParsingProcess p) : traceParsingProcess(p)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();
		ParsingTraceAspect._logger.trace( sig.getDeclaringTypeName() + "." + sig.getName() + " finished");
	}
	
	
	/**
	 * Before Intercepting AntlrParsingProcess
	 */
	before(com.codesquale.parser.AntlrParsingProcess p) : traceDescriptionProcess(p)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();
		ParsingTraceAspect._logger.trace( sig.getDeclaringTypeName() + "." + sig.getName() + " writing files description in "+ ProjectBrowser.getInstance().getProjectOutputFileName());
	}
	/**
	 * After Intercepting AntlrParsingProcess
	 */
	after(com.codesquale.parser.AntlrParsingProcess p) : traceDescriptionProcess(p)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();
		ParsingTraceAspect._logger.trace(sig.getDeclaringTypeName() + "." + sig.getName() + " finished");
	}
}
