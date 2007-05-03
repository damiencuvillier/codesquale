package com.tests;

import static org.junit.Assert.*;

import java.io.File;

import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;

import com.codesquale.parser.ParsingUnit;

public class ParsingUnitTest {
	
	private ParsingUnit myParsingUnit = null;
	private File myTestFile = null;
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(ParsingUnitTest.class);
	}
	
	
	@Before public void setUp()
	{
		myParsingUnit = new ParsingUnit();
		myTestFile = new File("ClassMoney.javatest");
	}

}
