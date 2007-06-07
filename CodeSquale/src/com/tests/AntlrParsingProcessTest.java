package com.tests;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import junit.framework.JUnit4TestAdapter;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.codesquale.ant.AntRunner;
import com.codesquale.file.FileFilter;
import com.codesquale.file.NotDirectoryException;

/**
 * 
 * @author mbourguignon
 * 
 * class test that execute the antlr process on test directory
 * 
 * Analyse and Parse the test class : .\tests\ClassMoney.java
 * Generate the AST XML file : .\test\ClassMoney.java
 * 
 */
public class AntlrParsingProcessTest {
	
	private static Logger logger = Logger.getLogger(AntlrParsingProcessTest.class);
	

	private FileFilter filter;
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(AntlrParsingProcessTest.class);
	}
	
	@Before
	public void setUp()
	{
		filter = new FileFilter();
		filter.addFileType(FileFilter.JAVA_SOURCEFILE);
	}
	@Test
	public void execute()
	{

		logger.info("Browsing project files...");
		
			
			// Need to init the project browser
			try {
				
				AntRunner.getInstance().init("xml\\AntScript.xml");
				
				AntRunner.getInstance().runTarget("CodeSqualeAntlrProcess");
				
//				ProjectBrowser.getInstance().init(new File(input),new File(output), new File(output+"\\AntlrProjectOutput.xml"), filter);
				
				assertTrue(true);
				
			} catch (NotDirectoryException e) {
				e.printStackTrace();
				assertTrue(false);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				assertTrue(false);
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
				assertTrue(false);
			} catch (TransformerException e) {
				e.printStackTrace();
				assertTrue(false);
			} catch (IOException e) {
				e.printStackTrace();
				assertTrue(false);
			} catch (Exception e) {
				e.printStackTrace();
				assertTrue(false);
			}
		
	}
	
}
