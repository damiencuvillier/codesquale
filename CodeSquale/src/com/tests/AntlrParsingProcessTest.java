package com.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import junit.framework.JUnit4TestAdapter;

import org.apache.log4j.Logger;
import org.apache.tools.ant.BuildException;
import org.junit.Before;
import org.junit.Test;


import com.codesquale.ant.AntLRTask;
import com.codesquale.file.FileFilter;
import com.codesquale.file.NotDirectoryException;
import com.codesquale.file.ProjectBrowser;
import com.codesquale.parser.AntlrParsingProcess;
/*
 * Class test that execute the antlr process on test directory
 * 
 * Analyse and Parse the test class : .\tests\ClassMoney.java
 * Generate the AST XML file : .\test\ClassMoney.java
 * 
 */
public class AntlrParsingProcessTest {
	
	private static Logger logger = Logger.getLogger(AntlrParsingProcessTest.class);
	
	private String input;
	private String output;

	private FileFilter filter;
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(AntlrParsingProcessTest.class);
	}
	
	@Before
	public void setUp()
	{
		input = "test";
		output = "test\\testOutput";
		filter = new FileFilter();
		filter.addFileType(FileFilter.JAVA_SOURCEFILE);
	}
	@Test
	public void execute()
	{

		logger.info("Browsing project files...");
		
			
			// Need to init the project browser
			try {
				ProjectBrowser.getInstance().init(new File(input),new File(output), new File(output+"AntlrProjectOutput.xml"), filter);

				AntlrParsingProcess.getInstance().execute();
				
				assertTrue(true);
				
			} catch (NotDirectoryException e) {
				// TODO Bloc catch auto-généré
				e.printStackTrace();
				assertTrue(false);
			} catch (FileNotFoundException e) {
				// TODO Bloc catch auto-généré
				e.printStackTrace();
				assertTrue(false);
			} catch (ParserConfigurationException e) {
				// TODO Bloc catch auto-généré
				e.printStackTrace();
				assertTrue(false);
			} catch (TransformerException e) {
				// TODO Bloc catch auto-généré
				e.printStackTrace();
				assertTrue(false);
			} catch (IOException e) {
				// TODO Bloc catch auto-généré
				e.printStackTrace();
				assertTrue(false);
			}
		
	}
	
}
