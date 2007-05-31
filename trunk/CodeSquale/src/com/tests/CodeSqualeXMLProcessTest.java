package com.tests;

import static org.junit.Assert.assertTrue;

import java.util.Hashtable;

import junit.framework.JUnit4TestAdapter;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.codesquale.ant.AntRunner;


/**
 * 
 * @author mbourguignon
 *
 * class test that execute the codesquale XML ant process
 * this test execute the ant task using the AntRunner
 */
public class CodeSqualeXMLProcessTest {

	private static Logger logger = Logger.getLogger(CodeSqualeXMLProcessTest.class);
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(CodeSqualeXMLProcessTest.class);
	}
	
	@Before
	public void setUp()
	{
			try {
				AntRunner.getInstance().init("xml\\AntScript.xml");
				
				Hashtable hash = new Hashtable();
				
				hash.put("SourceDir", "d:\\tmp\\src");
				hash.put("OutputDir", "d:\\tmp\\out");
				
				AntRunner.getInstance().setProperties(hash, false);
				
			} catch (Exception e) {
				// TODO Bloc catch auto-généré
				e.printStackTrace();
				assertTrue(false);
			}
	}
	@Test
	public void execute()
	{
		try {
			AntRunner.getInstance().runTarget("CodeSqualeXMLProcess");
			
			assertTrue(true);
			
		} catch (Exception e) {
			// TODO Bloc catch auto-généré
			e.printStackTrace();
			assertTrue(false);
		}
	}
}
