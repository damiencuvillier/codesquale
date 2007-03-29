package com.codesquale.launcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.codesquale.metrics.RawMetricsData;
import com.codesquale.parser.ParsingUnit;
import com.codesquale.view.console.ResultView;


public class AppMain {

	@SuppressWarnings("static-access")
	public static void main(String[] args)
	{
		FileInputStream sourceStream=null;
		ParsingUnit myParsingUnit = null;
		RawMetricsData rawData = null;
		ResultView myResultView = null;
		
		// Open ONE file
		try {
			
			sourceStream = new FileInputStream(new File("U:\\sampleTest.java"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// Instantiates the class responsible to display the result
		myResultView = new ResultView();
		// Instiates the parsing unit
		myParsingUnit = new ParsingUnit();
		// Parses the source code
		rawData = myParsingUnit.ParseCodeSourceStream(sourceStream);
		
		myResultView.DisplayResults(rawData);

		
	}

}
