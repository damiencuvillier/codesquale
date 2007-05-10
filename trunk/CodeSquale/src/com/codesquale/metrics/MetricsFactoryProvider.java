package com.codesquale.metrics;

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
	
	public static class MetricsFactoryType
	{
		public static MetricsFactoryType SAXON_FACTORY;
		public static MetricsFactoryType EXIST_FACTORY;
	}
	

	public IMetricsFactory GetMetricsFactory(MetricsFactoryType type) 
	{
		if(type == MetricsFactoryType.SAXON_FACTORY)
			return new SaxonMetricsFactory();
		
		return null;	
	}
}
