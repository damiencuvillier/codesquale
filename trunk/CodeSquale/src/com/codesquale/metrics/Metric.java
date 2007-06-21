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
	 * It is the compiled object from the literal query.
	 */
	private XQueryExpression compiledQuery;

	/**
	 * Indicates if the metric have to be cumulated for package level. Can take
	 * the value 0 or 1.
	 */
	private int populationLevel;

	/**
	 * Specialized constructor.
	 * 
	 * @param queryExpression
	 *            Represents the literal query
	 * @param aMetricDescription
	 *            Description of the behaviour of the metric
	 * @param aMetricLongName
	 *            Long name of the metric
	 * @param aMetricShortName
	 *            Short name of the metric (ID)
	 * @param enabled
	 *            Determines if the metric is enabled or not
	 * @param aMetricType
	 *            Gives the type of the metric
	 * @param aCompiledQuery
	 *            It is the compiled query object
	 * @param popLevel
	 *            Indicates if the metric have to be populated
	 */
	public Metric(final String queryExpression,
			final String aMetricDescription, final String aMetricLongName,
			final String aMetricShortName, final boolean enabled,
			final String aMetricType, final XQueryExpression aCompiledQuery,
			final int popLevel) {
		this.metricXQueryExpression = queryExpression;
		this.metricDescription = aMetricDescription;
		this.metricLongName = aMetricLongName;
		this.metricShortName = aMetricShortName;
		this.metricEnabled = enabled;
		this.metricType = aMetricType;
		this.compiledQuery = aCompiledQuery;
		this.populationLevel = popLevel;
	}

	/**
	 * Returns if metric is enabled.
	 * 
	 * @return true if enabled
	 */
	public final boolean isMetricEnabled() {
		return metricEnabled;
	}

	/**
	 * Set the metricEnabled to true or false.
	 * 
	 * @param isMetricEnabled
	 *            Set to true if you want to enale the metric
	 */
	public final void setMetricEnabled(final boolean isMetricEnabled) {
		this.metricEnabled = isMetricEnabled;
	}

	/**
	 * Returns the metric description.
	 * 
	 * @return A string representing the metric description.
	 */
	public final String getMetricDescription() {
		return metricDescription;
	}

	/**
	 * Returns the metric long name.
	 * 
	 * @return String that is the metric long name
	 */
	public final String getMetricLongName() {
		return metricLongName;
	}

	/**
	 * Returns the metric short name.
	 * 
	 * @return String that is the metric short name.
	 */
	public final String getMetricShortName() {
		return metricShortName;
	}

	/**
	 * Returns the XQuery as String.
	 * 
	 * @return String that is the XQuery.
	 */
	public final String getMetricXQueryExpression() {
		return metricXQueryExpression;
	}

	/**
	 * Returns the XQuery as a compiled object.
	 * 
	 * @return The compiled XQuery expression
	 */
	public final XQueryExpression getCompiledQuery() {
		return compiledQuery;
	}

	/**
	 * Returns the type of the metric as string.
	 * 
	 * @return Metric type as a string
	 */
	public final String getMetricType() {
		return metricType;
	}

	/**
	 * Indicates if the metric have to be populated.
	 * @return The population code.
	 */
	public final int getPopulationLevel() {
		return populationLevel;
	}

}
