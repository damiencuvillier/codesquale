package com.codesquale.metrics;

import net.sf.saxon.query.XQueryExpression;

/*
 * Represent the main characteristics of a metric
 */
public class Metric {
	
	private String metricXQueryExpression;

	private String metricDescription;

	private String metricLongName;

	private String metricShortName;

	private boolean metricEnabled;
	
	private String metricType;
	
	private XQueryExpression compiledQuery;
	
	public Metric(String queryExpression, String metricDescription,
			String metricLongName, String metricShortName, boolean enabled,String metricType, XQueryExpression compiledQuery) {
		this.metricXQueryExpression = queryExpression;
		this.metricDescription = metricDescription;
		this.metricLongName = metricLongName;
		this.metricShortName = metricShortName;
		this.metricEnabled = enabled;
		this.metricType = metricType;
		this.compiledQuery = compiledQuery;
	}

	public boolean isMetricEnabled() {
		return metricEnabled;
	}

	public void setMetricEnabled(boolean metricEnabled) {
		this.metricEnabled = metricEnabled;
	}

	public String getMetricDescription() {
		return metricDescription;
	}

	public String getMetricLongName() {
		return metricLongName;
	}

	public String getMetricShortName() {
		return metricShortName;
	}

	public String getMetricXQueryExpression() {
		return metricXQueryExpression;
	}

	public XQueryExpression getCompiledQuery() {
		return compiledQuery;
	}

	public String getMetricType() {
		return metricType;
	}

	
	
}
