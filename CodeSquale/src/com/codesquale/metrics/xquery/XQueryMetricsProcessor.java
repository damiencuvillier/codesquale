package com.codesquale.metrics.xquery;

import java.io.File;


public class XQueryMetricsProcessor {

	
	public void BuildMetricsResultFile(String inFilePath, String outFilePath)
	{
		XQueryBuilder myBuilder = new XQueryBuilder();
		File preparedXQuery =  myBuilder.BuildMetricsQuery(inFilePath);
	}
	
}
