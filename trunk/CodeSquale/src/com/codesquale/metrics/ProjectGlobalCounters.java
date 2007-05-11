package com.codesquale.metrics;

public class ProjectGlobalCounters 
{
	
	private static ProjectGlobalCounters _instance = null;
	
	private ProjectGlobalCounters() {}
	
	public static ProjectGlobalCounters getInstance()
	{
		if(_instance == null) _instance = new ProjectGlobalCounters();
		
		return _instance;
	}
	
	private int numberOfClasses=0;
	private int numberOfPrivateClasses=0;
	private int numberOfPublicClasses=0;
	
	private int numberOfMethods=0;
	private int numberOfPublicMethods=0;
	private int numberOfPrivateMethods=0;
	
	private int numberOfAttributes=0;
	private int numberOfPublicAttributes=0;
	private int numberOfPrivateAttributes=0;
	
	private int numberOfInterfaces=0;
	
	
	public void incrementNumberOfClasses(int num) { this.numberOfClasses+=num; }
	public void incrementNumberOfPublicClasses(int num) { this.numberOfPublicClasses+=num; }
	public void incrementNumberPrivateOfClasses(int num) { this.numberOfPrivateClasses+=num; }
	
	public void incrementNumberOfMethods(int num) { this.numberOfMethods+=num; }
	public void incrementNumberOfPublicMethods(int num) { this.numberOfPublicMethods+=num; }
	public void incrementNumberPrivateOfMethods(int num) { this.numberOfPrivateMethods+=num; }

	public void incrementNumberOfAttributes(int num) { this.numberOfAttributes+=num; }
	public void incrementNumberOfPublicAttributes(int num) { this.numberOfPublicAttributes+=num; }
	public void incrementNumberPrivateOfAttributes(int num) { this.numberOfPrivateAttributes+=num; }
	
	public void incrementNumberOfInterfaces(int num) { this.numberOfInterfaces+=num; }


}
