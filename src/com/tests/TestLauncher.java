package com.tests;

public class TestLauncher {


	public static void main(String[] args) {
	
		/**
		 * Testing process on unique file
		 */
		junit.textui.TestRunner.run(ParsingUnitTest.suite());
		junit.textui.TestRunner.run(SaxonXqueryTest.suite());
		
		/**
		 *  Testing process on multiple directories and files
		 */
		junit.textui.TestRunner.run(AntlrParsingProcessTest.suite());
		junit.textui.TestRunner.run(CodeSqualeXMLProcessTest.suite());
		junit.textui.TestRunner.run(CodeSqualeMetricsProcessTest.suite());

		
	}

}
