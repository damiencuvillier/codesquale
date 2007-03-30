package com.codesquale.metrics;


/**
 * Represent the raw data collected by the parsing unit class
 * @author dwillier
 */
public class FileUnitRawMetrics 
{
	//////////////
	// COUNTERS //
	int classCounter = 0;
	int methodCounter = 0;
	int constructCounter = 0;
	int lineCounter = 0;
	int importCounter = 0;
	int interfaceCounter = 0;

	public void IncrementClassCounter() {classCounter++;}
	public void IncrementMethodCounter() {methodCounter++;}
	public void IncrementLineCounter() {lineCounter++; }
	public void IncrementImportCounter() {importCounter++; }
	public void IncrementInterfaceCounter() {interfaceCounter++; }
	
	
	public int GetClassCount () {return classCounter;}
	public int GetMethodCount () {return methodCounter;}
	public int GetLineCount(){return lineCounter;}
	public int GetImportCount(){return importCounter;}
	public int GetConstructCounter() {return constructCounter;	}
	public int GetInterfaceCounter() {return interfaceCounter;}
	
	public void SetClassCount (int count) { classCounter = count;}
	public void SetMethodCount (int count) { methodCounter = count;}
	public void SetLineCount(int count){this.lineCounter = count;}
	public void SetImportCount(int count){this.importCounter = count;}
	public void SetConstructCounter(int constructCounter) {	this.constructCounter = constructCounter;}
	public void SetInterfaceCounter(int interfaceCounter) {this.interfaceCounter = interfaceCounter;	}


}
