package com.tests;

public class TestLauncher {


	public static void main(String[] args) {
	
		junit.textui.TestRunner.run(ParsingUnitTest.suite());
		junit.textui.TestRunner.run(AntlrParsingProcessTest.suite());
		junit.textui.TestRunner.run(CodeSqualeXMLProcessTest.suite());
		junit.textui.TestRunner.run(SaxonXqueryProcessingTest.suite());
		
	}

}
