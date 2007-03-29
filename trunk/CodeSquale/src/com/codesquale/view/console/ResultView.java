package com.codesquale.view.console;

import com.codesquale.metrics.RawMetricsData;

public class ResultView {
	
	public void DisplayResults(RawMetricsData rawData)
	{
		System.out.println("* Number of lines in the file : "+rawData.GetLine());
		System.out.println("* Number of class in the file : "+rawData.GetClassCount());
		System.out.println("* Number of methods in the file : "+rawData.GetMethodCount());
	}

}
