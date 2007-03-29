package com.codesquale.metrics;


/**
 * Represent the raw data collected by the parsing unit class
 * @author dwillier
 */
public class RawMetricsData 
{
	//////////////
	// COUNTERS //
	int classCounter = 0;
	int methodCounter = 0;
	int lineCounter = 0;

	public void IncrementClassCounter() {classCounter++;}
	public void IncrementMethodCounter() {methodCounter++;}
	public void IncrementLineCounter() {lineCounter++; }
	
	public int GetClassCount () {return classCounter;}
	public int GetMethodCount () {return methodCounter;}
	public int GetLine(){return lineCounter;}

}
