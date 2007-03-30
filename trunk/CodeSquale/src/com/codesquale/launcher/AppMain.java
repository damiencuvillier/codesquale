package com.codesquale.launcher;

import java.io.File;

import com.codesquale.parser.ParsingUnit;
import com.codesquale.view.console.ResultView;


public class AppMain {

	@SuppressWarnings("static-access")
	public static void main(String[] args)
	{
		File source=null;
		ParsingUnit myParsingUnit = null;
		ResultView myResultView = null;
		
		source = new File("U:\\sampleTest.java");
		
		
		// Instantiates the class responsible to display the result
		myResultView = new ResultView();
		// Instiates the parsing unit
		myParsingUnit = new ParsingUnit();
		// Executing Parse
		myParsingUnit.DoParse(source);
		// Display results
		myResultView.DisplayResults(myParsingUnit.getSourceFileRawData());

		
	}

}
