package com.codesquale.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import antlr.ANTLRException;
import antlr.ASTFactory;
import antlr.CommonAST;
import antlr.RecognitionException;
import antlr.Token;
import antlr.TokenStreamException;

import com.codesquale.parser.java.JavaLexer;
import com.codesquale.parser.java.JavaRecognizer;

/**
 * Represents the subsystem in charge of parsing a single source file and
 * provides the raw collected data. These data are necessary to calculte the
 * final metrics.
 * 
 * @author dwillier
 */
public class ParsingUnit implements IParsingUnit {
	// Declaring the Lexer
	private JavaLexer myJavaLexer = null;

	private JavaRecognizer myJavaRecognizer = null;

	private CommonAST abstractTree = null;

	protected ParsingUnit() {

	}
	private String filename = "";
	private String xmlfilename ="";
	
	public String getFileName() {
		return filename;
	}
	public void setFileName(String fileName)
	{
		filename = fileName;
	}
	public String getXmlFileName(){
		return xmlfilename;
	}
	public void setXmlFileName(String fileName)
	{
		xmlfilename = fileName;
	}
	

	/**
	 * Parse a fileStream and calculates counters
	 * 
	 * @param codeSourceFileStream
	 * @return returns a RawMetricsData class necessary to build the final
	 *         metrics
	 * @throws ANTLRException 
	 */
	private void ParseCodeSourceStream(FileInputStream codeSourceFileStream) throws Exception, TokenStreamException{
		
		// Initializing the Lexer
		myJavaLexer = new JavaLexer(codeSourceFileStream);
		// Initializing the parser
		myJavaRecognizer = new JavaRecognizer(myJavaLexer);

		// do AST Metrics
		myJavaRecognizer.compilationUnit();
	
		ASTFactory factory = new ASTFactory();
		abstractTree = (CommonAST) factory.create(0, "ROOT");

		abstractTree.setFirstChild(myJavaRecognizer.getAST());

	}

	/**
	 * Save the abstract syntaxic tree to XML file structure
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public FileOutputStream ASTToXML(String fileName) throws IOException {
				
		FileOutputStream output = new FileOutputStream(fileName);
		
		Writer writer = new OutputStreamWriter(output);
		abstractTree.xmlSerialize(writer);
		writer.flush();

		return output;
	}

	public void DoParse(File codeSourceFile) throws Exception {
			
		FileInputStream codeSourceFileStream = null;

		codeSourceFileStream = new FileInputStream(codeSourceFile);
		ParseCodeSourceStream(codeSourceFileStream);
		
	
		
	
	}




}
