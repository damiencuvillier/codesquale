package com.codesquale.aspects;

import java.util.Hashtable;

import org.aspectj.lang.Signature;

import org.w3c.dom.Node;

import com.codesquale.metrics.Metric;
import com.codesquale.metrics.ResultFileBuildType;

public aspect MetricsTraceAspect {
	
	/**
	 * Point cut that log reading metrics collections
	 */
	pointcut  traceMetricsCollectionInit(com.codesquale.metrics.MetricsCollection c)
			: target(c)
			&& execution(public final void readAvailableMetricsCollection(String))
			&& !within(MetricsTraceAspect);
	/**
	 * Pointcut that log MetricsResultFileBuilder construction
	 */
	pointcut traceMetricsResultConstructor()
			: preinitialization(com.codesquale.metrics.MetricsResultFileBuilder.new(..));
	
	pointcut traceMetricsResultFile(String input,String output, com.codesquale.metrics.ResultFileBuildType type)
			: execution(public final void buildMetricsResultFile( String, String, com.codesquale.metrics.ResultFileBuildType))
			&& args(input,output,type)
			&& !within(MetricsTraceAspect);

	pointcut traceMetricsNodeParsing(com.codesquale.metrics.MetricsResultFileBuilder b)
			: target(b)
			&& call(private org.w3c.dom.Element ProcessNodeParsing( org.w3c.dom.Node))
			&& !within(MetricsTraceAspect);
			
	
	pointcut traceAppendResults(com.codesquale.metrics.MetricsResultFileBuilder b)
			: target(b)
			&& call(private org.w3c.dom.Element AppendMetricsResultsToElement(com.codesquale.metrics.Metric,org.w3c.dom.Element))
			&& !within(MetricsTraceAspect);

	
	
	after(com.codesquale.metrics.MetricsCollection c) : traceMetricsCollectionInit(c)
	{
		doLog(thisJoinPointStaticPart.getSignature(),"Metrics collection initialized");
		Hashtable<String,Metric> metrics = c.getCollection();
		for(String key : metrics.keySet())
		{
			Metric m = (Metric)metrics.get(key);
			ParsingTraceAspect._logger.info(key+ " ["+m.getMetricLongName()+"] " + m.getMetricDescription());
		}
	}
	
	
	before() : traceMetricsResultConstructor()
	{
		doLog(thisJoinPointStaticPart.getSignature(), "");	
	}
	
	after(String input, String output,ResultFileBuildType type) : traceMetricsResultFile(input,output,type)
	{
		doLog(thisJoinPointStaticPart.getSignature(), output );	
	}
	
//	before(com.codesquale.metrics.MetricsResultFileBuilder b) : traceMetricsNodeParsing(b)
//	{
//		Signature sig = thisJoinPointStaticPart.getSignature();
//		ParsingTraceAspect._logger.debug( sig.getDeclaringTypeName() + "."+ sig.getName()+ "calculating metric ");	
//	}
	
	public void doLog(Signature sig, String message)
	{
		if(message == null) message = "";
		
		ParsingTraceAspect._logger.info( sig.getName()+ " " + message);
	}
}
