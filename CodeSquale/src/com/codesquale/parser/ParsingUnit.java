package com.codesquale.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import antlr.ASTFactory;
import antlr.CommonAST;
import antlr.RecognitionException;
import antlr.Token;
import antlr.TokenStreamException;
import antlr.collections.AST;

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

	private JavaLexer myLineLexer = null;

	// Declaring a token unit
	private Token currentToken = null;

	private JavaRecognizer myJavaRecognizer = null;

	private CommonAST abstractTree = null;

	private int TypeCount[] = new int[1];

	protected ParsingUnit() {

	}

	// Enables the class to log errors
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ParsingUnit.class);

	/**
	 * Parse a fileStream and calculates counters
	 * 
	 * @param codeSourceFileStream
	 * @return returns a RawMetricsData class necessary to build the final
	 *         metrics
	 */
	private void ParseCodeSourceStream(FileInputStream codeSourceFileStream) {
		// Initializing the Lexer
		myJavaLexer = new JavaLexer(codeSourceFileStream);
		// Initializing the parser
		myJavaRecognizer = new JavaRecognizer(myJavaLexer);

		// do AST Metrics
		try {
			myJavaRecognizer.compilationUnit();

			ASTFactory factory = new ASTFactory();
			abstractTree = (CommonAST) factory.create(0, "ROOT");

			abstractTree.setFirstChild(myJavaRecognizer.getAST());

		} catch (RecognitionException e1) {
			e1.printStackTrace();
		} catch (TokenStreamException e1) {
			e1.printStackTrace();
		}
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

	public void DoParse(File codeSourceFile) throws FileNotFoundException {
		FileInputStream codeSourceFileStream = null;

		codeSourceFileStream = new FileInputStream(codeSourceFile);

		ParseCodeSourceStream(codeSourceFileStream);
	}

	/**
	 * Find a child of the given AST that has the given type
	 * 
	 * @returns a child AST of the given type. If it can't find a child of the
	 *          given type, return null.
	 */
	private AST getChild(AST ast, int childType) {
		AST child = ast.getFirstChild();
		while (child != null) {
			if (child.getType() == childType) {
				// debug.println("getChild: found:" + name(ast));
				return child;
			}
			child = child.getNextSibling();
		}
		return null;
	}

	/**
	 * Count the number of type occurence in the abstract tree
	 * 
	 * @param t
	 *            the Abstract tree that contain the types
	 * @param type
	 *            the type you need to count
	 * @param count
	 *            in out parameter, give number of type occurence
	 * 
	 */
	private void getTypeCount(AST t, int type, int count[]) {
		if (t == null)
			return;

		if (t.getType() == type)
			count[0]++;

		AST child = t.getFirstChild();
		getTypeCount(child, type, count);

		AST next = t.getNextSibling();
		getTypeCount(next, type, count);

	}
}
