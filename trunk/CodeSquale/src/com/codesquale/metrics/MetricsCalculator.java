package com.codesquale.metrics;


/**
 * This class is dedicated to metrics calculation  
 * Contains all the metrics methods needed to execute a ratio calcul
 * 
 * @author mbourguignon
 * 
 * TODO Implement Ratio Calculation XML File 
 * 		 - process that define all ratio calcul 
 * 		 - reflexive method generation system over XML ratio calcul definition
 */
public class MetricsCalculator {
	
	public static float CalculateRatioMethodByClass(int classCount, int methodCount) {
		return methodCount/classCount;
	}

}
