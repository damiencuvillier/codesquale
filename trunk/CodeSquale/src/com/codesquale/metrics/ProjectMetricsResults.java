package com.codesquale.metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class ProjectMetricsResults {
	
	private List<PackageMetricsResults> packagesResults;
	private Hashtable<String, Object> projectResults;
	
	public ProjectMetricsResults()
	{
		packagesResults = new ArrayList<PackageMetricsResults>();
		projectResults = new Hashtable<String, Object>();
	}
	
	public void addPackageMetricsResults(PackageMetricsResults p)
	{
		packagesResults.add(p);
	}
	
	
	public double getValueFromMetricID(String metricID)
	{
		Object result = projectResults.get(metricID);
		if(result!=null)
			return Double.parseDouble(result.toString());
		else
			return 0.00;
	}
	

	public void populateMetrics()
	{
		for (PackageMetricsResults p : packagesResults) {
			Hashtable<String, Object> metricsResults = p.getMetricsResults();
			
			Enumeration<String> keys = metricsResults.keys();
			while(keys.hasMoreElements())
			{
				String key = (String)keys.nextElement();
				Object results = metricsResults.get(key);
				updateMetricsResults(key, results);
			}
		
		}
	}
	
	private void updateMetricsResults(String key, Object results)
	{
		if(projectResults.containsKey(key))
		{
			Object otherResult = projectResults.get(key);
			double decimalResult = Double.parseDouble(otherResult.toString()) + Double.parseDouble(results.toString());
			projectResults.put(key,decimalResult);
		}
		else
		{
			projectResults.put(key, results);
		}
		
	}

}
