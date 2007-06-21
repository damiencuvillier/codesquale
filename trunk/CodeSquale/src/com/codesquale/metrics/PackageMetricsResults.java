package com.codesquale.metrics;

import java.util.Hashtable;

public class PackageMetricsResults {
	
	private String packageName;
	
	private Hashtable<String, Object> metricsResults;
	
	public PackageMetricsResults()
	{
		metricsResults = new Hashtable<String, Object>();
	}
	
	public void setMetricsResults(String metricId, Object results)
	{
		metricsResults.put(metricId, results);
	}
	
	public Hashtable<String, Object> getMetricsResults()
	{
		return metricsResults;
	}
}
