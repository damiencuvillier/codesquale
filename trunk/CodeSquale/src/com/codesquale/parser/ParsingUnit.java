package com.codesquale.parser;

import java.io.FileInputStream;

import antlr.ASTFactory;
import antlr.CommonAST;
import antlr.RecognitionException;
import antlr.Token;
import antlr.TokenStreamException;
import antlr.collections.AST;

import com.codesquale.file.*;
import com.codesquale.metrics.*;
import com.codesquale.parser.java.*;
import com.codesquale.utils.*;

/**
 * Represents the subsystem in charge of parsing a single source file
 * and provides the raw collected data.
 * These data are necessary to calculte the final metrics.
 * @author dwillier
 */
public class ParsingUnit {
	
	// Declaring the Lexer
	JavaLexer myJavaLexer = null;
	JavaLexer myLineLexer = null;
	// Declaring a token unit
	Token currentToken = null;
	JavaRecognizer myJavaRecognizer = null;

	int TypeCount[] = new int[1]; 
	// Raw metrics data
	RawMetricsData sourceFileRawData = new RawMetricsData();;
	
//	 Enables the class to log errors
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ParsingUnit.class);
	
	
	
	public void ParseLineNumber(FileInputStream codeSourceFileStream)
	{
		//	Initialise 
		myLineLexer = new JavaLexer(codeSourceFileStream);
		//	Initializiong the metrics
		
		
		int previousLine = 0;
		int tokenLine = 0;

		// Simple counting on the class number and method
		do
		{
			try {
				currentToken = myLineLexer.nextToken();
			} catch (TokenStreamException e) {
				e.printStackTrace();
				logger.fatal(Utilities.GetCurrentTime()+"Lexer encoutered fatal error. Invalid token sequence detected.");
			}

			// Count the total number of lines in this file
			tokenLine = currentToken.getLine();
			if(previousLine ==0 || tokenLine!=previousLine) sourceFileRawData.IncrementLineCounter();
		    previousLine = tokenLine;
			
		}while(currentToken != null && currentToken.getType()!= JavaTokenTypes.EOF);

	}
	/**
	 *  Parse a fileStream and calculates counters
	 * @param codeSourceFileStream
	 * @return returns a RawMetricsData class necessary to build the final metrics
	 */
	public void ParseCodeSourceStream(FileInputStream codeSourceFileStream)
	{
				
		// Initializing the Lexer
		myJavaLexer = new JavaLexer(codeSourceFileStream);
		//	Initializing the parser
		myJavaRecognizer = new JavaRecognizer(myJavaLexer);	

		// do AST Metrics
		try {
			myJavaRecognizer.compilationUnit();
			
			ASTFactory factory = new ASTFactory();
			AST t = factory.create(0,"ROOT");
			
			t.setFirstChild(myJavaRecognizer.getAST());
			
			// Get Class_Def count
 			getTypeCount(t, JavaRecognizer.CLASS_DEF, TypeCount);
 			sourceFileRawData.SetClassCount(TypeCount[0]);
 			
			// Get Method_Def count
 			TypeCount[0] = 0;
 			getTypeCount(t, JavaRecognizer.METHOD_DEF, TypeCount);
 			sourceFileRawData.SetMethodCount(TypeCount[0]);
 		
			// Get Method_Def count
 			TypeCount[0] = 0;
 			getTypeCount(t, JavaRecognizer.IMPORT, TypeCount);
 			sourceFileRawData.SetImportCount(TypeCount[0]);
			
		} catch (RecognitionException e1) {
			// TODO Bloc catch auto-généré
			e1.printStackTrace();
		} catch (TokenStreamException e1) {
			// TODO Bloc catch auto-généré
			e1.printStackTrace();
		}	
	}
	/**
	 * Find a child of the given AST that has the given type
	 * @returns a child AST of the given type. If it can't find a child of the given type, return null.
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
	 * @param t the Abstract tree that contain the types
	 * @param type the type you need to count
	 * @param count in out parameter, give number of type occurence
	 * 
	 */
	private void getTypeCount(AST t, int type, int count[]) {
		if ( t==null ) return;
		
		if(t.getType() == type)
			count[0]++;
	
		AST child = t.getFirstChild();
		getTypeCount(child, type, count);
		
		AST next = t.getNextSibling();
		getTypeCount(next, type, count);
	
	}
	/**
	 * 
	 * @param t
	 * @param level
	 */
	private  void showTree(AST t, int level) {
		if ( t==null ) return;
		
		System.out.println("text:" + t.getText() + " type=" + t.getType());
	
		AST child = t.getFirstChild();
		showTree(child, level+2);
		AST next = t.getNextSibling();
		showTree(next, level);
	}
	public RawMetricsData getSourceFileRawData() {
		return sourceFileRawData;
	}
	public void setSourceFileRawData(RawMetricsData sourceFileRawData) {
		this.sourceFileRawData = sourceFileRawData;
	}



}
