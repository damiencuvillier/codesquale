package com.codesquale.metrics;

import com.codesquale.metrics.saxon.SaxonMetricsFactory;

public class AbstractMetricsFactory 
{
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
