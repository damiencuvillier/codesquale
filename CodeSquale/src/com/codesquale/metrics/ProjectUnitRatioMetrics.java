package com.codesquale.metrics;

public class ProjectUnitRatioMetrics
{
	int fileCount = 0;
	int classCount = 0;
	int methodCount = 0;
	int linesCount = 0;
	int interfaceCount = 0;


	public void IncrementClassCounter() {classCount++;}
	public void IncrementMethodCounter() {methodCount++;}
	public void IncrementLineCounter() {linesCount++; }
	public void IncrementInterfaceCounter() {interfaceCount++; }
	public void IncrementFileCounter() {fileCount++; }
	
	
	public int GetClassCount () {return classCount;}
	public int GetMethodCount () {return methodCount;}
	public int GetLineCount(){return linesCount;}
	public int GetInterfaceCounter() {return interfaceCount;}
	public int GetFileCounter() {return fileCount;}
	
	public void SetClassCount (int count) { classCount = count;}
	public void SetMethodCount (int count) { methodCount = count;}
	public void SetlinesCount(int count){this.linesCount = count;}
	public void SetInterfaceCounter(int interfaceCounter) {this.interfaceCount = interfaceCounter;}
	public void SetFileCounter(int fileCounter) {this.fileCount = fileCounter;}
	
	
}
