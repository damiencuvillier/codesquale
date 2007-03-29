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
	int importCounter = 0;

	public void IncrementClassCounter() {classCounter++;}
	public void IncrementMethodCounter() {methodCounter++;}
	public void IncrementLineCounter() {lineCounter++; }
	public void IncrementImportCounter() {importCounter++; }
	
	public int GetClassCount () {return classCounter;}
	public int GetMethodCount () {return methodCounter;}
	public int GetLineCount(){return lineCounter;}
	public int GetImportCount(){return importCounter;}
	
	public void SetClassCount (int count) { classCounter = count;}
	public void SetMethodCount (int count) {methodCounter = count;}
	public void SetLineCount(int count){lineCounter = count;}
	public void SetImportCount(int count){importCounter = count;}
}
