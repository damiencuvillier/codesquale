package com.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;

import junit.framework.JUnit4TestAdapter;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;



import com.codesquale.parser.IParsingUnit;
import com.codesquale.parser.ParsingUnitFactory;

public class ParsingUnitTest {
	
	private static Logger logger = Logger.getLogger(ParsingUnitTest.class);
	
	private IParsingUnit myParsingUnit = null;
	private File myTestFile = null;
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(ParsingUnitTest.class);
	}
	
	
	@Before public void setUp()
	{
	
		myParsingUnit = ParsingUnitFactory.getInstance().createInstance();
		myTestFile = new File("testfiles\\src\\ClassMoney.java");	
	}
	
	@Test
	public void parseFile()
	{
		try
		{		
			logger.info("Parsing file...");
			myParsingUnit.DoParse(myTestFile);
			
			logger.info("Transform file to AST xml file");
			FileOutputStream xmlFile = myParsingUnit.ASTToXML("ClassMoney.xml");
			
			if(xmlFile.equals(null))
			{
				fail("error xml file can't be null");
			 	throw new Exception();
			}
		}
		catch(Exception e)
		{
			logger.fatal(e);
			e.printStackTrace();
			fail("error in file parsing");
		}
	}

}
