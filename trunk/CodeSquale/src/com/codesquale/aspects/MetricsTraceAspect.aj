package com.codesquale.aspects;

import org.aspectj.lang.Signature;

import org.w3c.dom.Node;

public aspect MetricsTraceAspect {
	
	/**
	 * Point cut that log reading metrics collections
	 */
	pointcut  traceMetricsCollectionInit(com.codesquale.metrics.MetricsCollection c)
			: target(c)
			&& call(public void ReadAvailableMetricsCollection(String))
			&& !within(MetricsTraceAspect);
	/**
	 * Pointcut that log MetricsResultFileBuilder construction
	 */
	pointcut traceMetricsResultConstructor()
			: preinitialization(com.codesquale.metrics.MetricsResultFileBuilder.new(..));
	
	pointcut traceMetricsResultFile(com.codesquale.metrics.MetricsResultFileBuilder b)
			: target(b)
			&& call(public void BuildMetricsResultFile(String,String))
			&& !within(MetricsTraceAspect);
	
	pointcut traceMetricsNodeParsing(com.codesquale.metrics.MetricsResultFileBuilder b)
			: target(b)
			&& call(private org.w3c.dom.Element ProcessNodeParsing( org.w3c.dom.Node))
			&& !within(MetricsTraceAspect);
			
	
	pointcut traceAppendResults(com.codesquale.metrics.MetricsResultFileBuilder b)
			: target(b)
			&& call(private org.w3c.dom.Element AppendMetricsResultsToElement(com.codesquale.metrics.Metric,org.w3c.dom.Element))
			&& !within(MetricsTraceAspect);

	
	
	before(com.codesquale.metrics.MetricsCollection c) : traceMetricsCollectionInit(c)
	{
		doLog(thisJoinPointStaticPart.getSignature(),"Initialising Metrics collections");
	}
	
	
	before() : traceMetricsResultConstructor()
	{
		doLog(thisJoinPointStaticPart.getSignature(), "");	
	}
	
	before(com.codesquale.metrics.MetricsResultFileBuilder b) : traceMetricsResultFile(b)
	{
		doLog(thisJoinPointStaticPart.getSignature(), "parsing configuration file to generate the results");	
	}
	
	before(com.codesquale.metrics.MetricsResultFileBuilder b) : traceMetricsNodeParsing(b)
	{
		Signature sig = thisJoinPointStaticPart.getSignature();
		ParsingTraceAspect._logger.debug( sig.getDeclaringTypeName() + "."+ sig.getName()+ "calculating metric ");	
	}
	
	public void doLog(Signature sig, String message)
	{
		if(message == null) message = "";
		
		ParsingTraceAspect._logger.info( sig.getName()+ " " + message);
	}
}
