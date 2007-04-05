package com.tests;

import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

import com.codesquale.parser.ParsingUnit;

public class ParsingUnitTest {
	
	private ParsingUnit myParsingUnit = null;
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(ParsingUnitTest.class);
	}
	
	
	@Before public void setUp()
	{
		myParsingUnit = new ParsingUnit();
	}
	
	@Test
	public void testParseCodeSourceStream() {
		fail("Non implémenté actuellement");
	}

}
