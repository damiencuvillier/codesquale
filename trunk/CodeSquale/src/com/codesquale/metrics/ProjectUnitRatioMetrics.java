package com.codesquale.metrics;

public class ProjectUnitRatioMetrics {

	///////////////
	// RATIOS	 //
	float RatioMethodByClass = 0;

	public float getRatioMethodByClass() {
		return RatioMethodByClass;
	}

	public void setRatioMethodByClass(int classCount, int methodCount) {
		RatioMethodByClass = methodCount/classCount;
	}
	
	
}
