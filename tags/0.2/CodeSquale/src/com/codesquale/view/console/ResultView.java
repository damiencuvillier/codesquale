package com.codesquale.view.console;

import com.codesquale.metrics.FileUnitRawMetrics;

/**
 * Display on the console a formated view of the results
 * @author dwillier
 *
 */
public class ResultView {
	
	public void DisplayResults(FileUnitRawMetrics rawData)
	{
		System.out.println("* Number of lines: "+rawData.GetLineCount());
		System.out.println("* Number of class: "+rawData.GetClassCount());
		System.out.println("* Number of constructor: "+rawData.GetConstructCounter());
		System.out.println("* Number of methods: "+rawData.GetMethodCount());
		System.out.println("* Number of import: "+rawData.GetImportCount());
	}

}
