package com.codesquale.launcher;

import java.io.File;

import com.codesquale.parser.ParsingUnit;

public class AppMain {

	@SuppressWarnings("static-access")
	public static void main(String[] args)
	{
		File source=null;
		ParsingUnit myParsingUnit = null;
		
		source = new File("ClassMoney.javatest");
		
		
		// Instantiates the class responsible to display the result
		// Instiates the parsing unit
		myParsingUnit = new ParsingUnit();
		// Executing Parse
		myParsingUnit.DoParse(source);

		myParsingUnit.ASTToXML("sampleAST.xml");
		
	}

}
