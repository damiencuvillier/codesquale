package com.tests;

public class TestLauncher {


	public static void main(String[] args) {
	
		junit.textui.TestRunner.run(ParsingUnitTest.suite());
		junit.textui.TestRunner.run(AntRunnerTest.suite());
		
	}

}
