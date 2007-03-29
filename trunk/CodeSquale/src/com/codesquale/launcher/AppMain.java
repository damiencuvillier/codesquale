package com.codesquale.launcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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

			 
		}	catch (IOException e) {
			// TODO Bloc catch auto-généré
			e.printStackTrace();
		}
		// Instantiates the class responsible to display the result
		myResultView = new ResultView();
		// Instiates the parsing unit
		myParsingUnit = new ParsingUnit();
		// Parses the source code
		
		
		try {
			myParsingUnit.ParseLineNumber(new FileInputStream(new File("U:\\sampleTest.java")));
		} catch (FileNotFoundException e) {
			// TODO Bloc catch auto-généré
			e.printStackTrace();
		}
		
	
		myParsingUnit.ParseCodeSourceStream(sourceStream);
		
	
		myResultView.DisplayResults(myParsingUnit.getSourceFileRawData());

		
	}

}
