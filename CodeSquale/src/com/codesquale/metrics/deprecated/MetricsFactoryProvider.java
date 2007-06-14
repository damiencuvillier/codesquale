package com.codesquale.metrics.deprecated;

import com.codesquale.metrics.saxon.SaxonMetricsFactory;

public class MetricsFactoryProvider 
{
	
	private static MetricsFactoryProvider _instance = null;

	private MetricsFactoryProvider()
	{
	}
	
	
	public static MetricsFactoryProvider getInstance()
	{
		if(_instance == null) _instance = new MetricsFactoryProvider();
		
		return _instance;
	}
	
	public IMetricsFactory GetMetricsFactory(MetricsFactoryType type) 
	{
		if(type == MetricsFactoryType.SAXON_FACTORY)
			return (IMetricsFactory)new SaxonMetricsFactory();
		
		return null;	
	}
}
