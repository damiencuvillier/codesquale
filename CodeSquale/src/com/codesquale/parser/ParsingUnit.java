package com.codesquale.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import antlr.ASTFactory;
import antlr.CommonAST;

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
	/**
	 * Antlr java scanner.
	 */
	private JavaLexer myJavaLexer = null;

	/**
	 * Antlr Java parser.
	 */
	private JavaRecognizer myJavaRecognizer = null;

	/**
	 * Antlr Common abstract tree.
	 */
	private CommonAST abstractTree = null;

	/**
	 * Protected ctor().
	 */
	protected ParsingUnit() {

	}

	/**
	 * Parsed unit file name.
	 */
	private String filename = "";

	/**
	 * XML description filename.
	 */
	private String xmlfilename = "";

	/**
	 * GET Parsed unit file name.
	 * 
	 * @return String filename
	 */
	public final String getFileName() {
		return filename;
	}

	/**
	 * SET Parsed unit file name.
	 * 
	 * @param fileName
	 *            filename to be parsed
	 */
	public final void setFileName(final String fileName) {
		filename = fileName;
	}

	/**
	 * GET XML description filename.
	 * 
	 * @return String xml description filename
	 */
	public final String getXmlFileName() {
		return xmlfilename;
	}

	/**
	 * SET XML description filename.
	 * 
	 * @param fileName
	 *            xml filename to write
	 */
	public final void setXmlFileName(final String fileName) {
		xmlfilename = fileName;
	}

	/**
	 * Parse a fileStream and calculates counters.
	 * 
	 * @param codeSourceFileStream
	 *            file input stream that need to be parse
	 * @throws Exception
	 *             catching compilationUnit and getAST Exception
	 */
	private void parseCodeSourceStream(
			final FileInputStream codeSourceFileStream) throws Exception {

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
	 * Save the abstract syntaxic tree to XML file structure.
	 * @param fileName file to save
	 * @throws IOException  raised during serializing file 
	 * @return FileOutputStream file to be saved
	 */
	public final FileOutputStream astToXml(final String fileName)
			throws IOException {

		FileOutputStream output = new FileOutputStream(fileName);

		Writer writer = new OutputStreamWriter(output);
		abstractTree.xmlSerialize(writer);
		writer.flush();

		return output;
	}
	/**
	 * Launch parsing on file specified in argument.
	 * @param codeSourceFile file to be parsed
	 * @throws	Exception FileInputStream exception
	 */
	public final void doParse(final File codeSourceFile) throws Exception {

		FileInputStream codeSourceFileStream = null;

		codeSourceFileStream = new FileInputStream(codeSourceFile);
		parseCodeSourceStream(codeSourceFileStream);

	}

}
