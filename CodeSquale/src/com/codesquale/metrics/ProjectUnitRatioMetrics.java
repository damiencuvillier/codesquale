package com.codesquale.metrics;

public class ProjectUnitRatioMetrics
{
	
	FileUnitRawMetrics projectRawMetrics;
	
	int fileCount = 0;
	int classCount = 0;
	int methodCount = 0;
	int linesCount = 0;
	int interfaceCount = 0;


	public void incrementClassCounter() {classCount++;}
	public void incrementMethodCounter() {methodCount++;}
	public void incrementLineCounter() {linesCount++; }
	public void incrementInterfaceCounter() {interfaceCount++; }
	public void incrementFileCounter() {fileCount++; }
	
	
	public int getClassCount () {return classCount;}
	public int getMethodCount () {return methodCount;}
	public int getLineCount(){return linesCount;}
	public int getInterfaceCounter() {return interfaceCount;}
	public int getFileCounter() {return fileCount;}
	public FileUnitRawMetrics getProjectRawMetrics() {return projectRawMetrics;}
	
	public void setClassCount (int count) { this.classCount = count;}
	public void setMethodCount (int count) { this.methodCount = count;}
	public void setlinesCount(int count){this.linesCount = count;}
	public void setInterfaceCounter(int interfaceCounter) {this.interfaceCount = interfaceCounter;}
	public void setFileCounter(int fileCounter) {this.fileCount = fileCounter;}
	public void setProjectRawMetrics(FileUnitRawMetrics projectRawMetrics) {this.projectRawMetrics = projectRawMetrics;
	}
	
	
}
