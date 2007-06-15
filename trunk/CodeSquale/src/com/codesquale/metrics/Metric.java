package com.codesquale.metrics;

import net.sf.saxon.query.XQueryExpression;

/**
 * Represent and descrive the main characteristics of a metric.
 * 
 * @author dwillier
 * 
 */
public class Metric {

	/**
	 * Represents the literal string query.
	 */
	private String metricXQueryExpression;

	/**
	 * metricDescription is the verbose description of the metric.
	 */
	private String metricDescription;

	/**
	 * metricLongName represents the long name of the metric.
	 */
	private String metricLongName;

	/**
	 * metricShortName is the short name of the metric, usually on four
	 * characters.
	 */
	private String metricShortName;

	/**
	 * Defines if the metric is enabled or not.
	 */
	private boolean metricEnabled;

	/**
	 * Defines the return value type of the metric. For moment two return value
	 * types are considered : "single" or "list".
	 */
	private String metricType;

	/**
	 * It is the compiled object from  
	 */
	private XQueryExpression compiledQuery;

	public Metric(String queryExpression, String metricDescription,
			String metricLongName, String metricShortName, boolean enabled,
			String metricType, XQueryExpression compiledQuery) {
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
