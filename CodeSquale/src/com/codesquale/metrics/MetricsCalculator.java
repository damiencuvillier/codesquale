package com.codesquale.metrics;

public class MetricsCalculator {
	
	public static float CalculateRatioMethodByClass(int classCount, int methodCount) {
		return methodCount/classCount;
	}

}
