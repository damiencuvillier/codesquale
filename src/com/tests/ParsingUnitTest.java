package com.tests;

import static org.junit.Assert.*;

import java.io.File;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

import com.codesquale.metrics.FileUnitRawMetrics;
import com.codesquale.parser.ParsingUnit;

public class ParsingUnitTest {
	
	private ParsingUnit myParsingUnit = null;
	private File myTestFile = null;
	private FileUnitRawMetrics myResults = null;
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(ParsingUnitTest.class);
	}
	
	
	@Before public void setUp()
	{
		myParsingUnit = new ParsingUnit();
		myTestFile = new File("ClassMoney.javatest");
	}
	
	/**
	 * Check if parse method returns right results
	 *
	 */
	@Test
	public void testParse() {
		
		myParsingUnit.DoParse(myTestFile);
		myResults = myParsingUnit.getSourceFileRawData();
		
		assertEquals(myResults.GetLineCount(), 65);
		assertEquals(myResults.GetClassCount(), 1);
		assertEquals(myResults.GetConstructCounter(), 1);
		assertEquals(myResults.GetMethodCount(), 13);
		assertEquals(myResults.GetImportCount(), 0);
		
	}

}
