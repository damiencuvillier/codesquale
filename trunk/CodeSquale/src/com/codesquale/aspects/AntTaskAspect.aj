package com.codesquale.aspects;

import org.aspectj.lang.Signature;

/**
 * Define all ant task logging message 
 */
public aspect AntTaskAspect {
	
	pointcut traceStatusTask (com.codesquale.ant.StatusTask s) : 
		target(s) 
		&& execution(public void  execute()) 
		&& !within(TraceAspect);
	
	before(com.codesquale.ant.StatusTask s) : traceStatusTask(s){
		ParsingTraceAspect._logger.trace(s.getMessage());
	}
	
	pointcut traceAntTask ( org.apache.tools.ant.Task t):  
		target(t) 
		&& execution(public void  execute())
		&& !within(com.codesquale.ant.StatusTask)
		&& !within(TraceAspect);
	
	before(org.apache.tools.ant.Task t): traceAntTask(t)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();
		
		ParsingTraceAspect._logger.trace(sig.getDeclaringTypeName() + "."+ sig.getName() + " starting");
	}
	after(org.apache.tools.ant.Task t): traceAntTask(t)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();
		
		ParsingTraceAspect._logger.trace(sig.getDeclaringTypeName() + "."+ sig.getName() + " end ");
	}

		

	
	
}
