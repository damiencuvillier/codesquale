package com.codesquale.aspects;

import org.aspectj.lang.Signature;

/**
 * Define all ant task logging message 
 * @author mbourguignon
 */
public aspect AntTaskTraceAspect {
	
	
	/***************************************************************************/
	/***************** Display Status Task Message *****************************/
	/***************************************************************************/
	
	/**
	 * Status task aspectj logging pointcut
	 */
	pointcut traceStatusTask (com.codesquale.ant.StatusTask s) : 
		target(s) 
		&& execution(public void  execute()) 
		&& !within(AntTaskTraceAspect);
	
	/**
	 * Display status message before executing the task
	 */
	before(com.codesquale.ant.StatusTask s) : traceStatusTask(s){
		ParsingTraceAspect._logger.trace(s.getMessage());
	}
	
	/***************************************************************************/
	/***************** /Display Status Task Message ****************************/
	/***************************************************************************/
	

	/***************************************************************************/
	/***************** Log CodeSquale Ant Task *********************************/
	/***************************************************************************/
	
	/**
	 * Pointcut defined for every code squale ant task    
	 */
	pointcut traceAntTask ( org.apache.tools.ant.Task t):  
		target(t) 
		&& execution(public void  execute())
		&& !within(com.codesquale.ant.StatusTask)
		&& !within(TraceAspect);
	
	/**
	 * Trace before starting the task
	 */
	before(org.apache.tools.ant.Task t): traceAntTask(t)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();
		
		ParsingTraceAspect._logger.trace(sig.getDeclaringTypeName() + "."+ sig.getName() + " start");
	}
	
	/**
	 * Trace after finishing the task
	 */
	after(org.apache.tools.ant.Task t): traceAntTask(t)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();
		
		ParsingTraceAspect._logger.trace(sig.getDeclaringTypeName() + "."+ sig.getName() + " finish");
	}
	
	/***************************************************************************/
	/***************** /Log CodeSquale Ant Task ********************************/
	/***************************************************************************/
	

		

	
	
}
