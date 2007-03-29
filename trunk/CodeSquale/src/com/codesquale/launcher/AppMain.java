package com.codesquale.launcher;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.codesquale.parser.java.*;
import antlr.*;
import antlr.collections.AST;

/**
 * 
 * @author dwillier
 *
 */
public class AppMain {

	@SuppressWarnings("static-access")
	public static void main(String[] args)
	{
		/////////////////
		// DECLARATION //
		
		////////////////////////
		// PARSING ATTRIBUTES //
		// Declaring the source file stream
		FileInputStream sourceCodeStream=null;
		// Declaring the Lexer
		JavaLexer myJavaLexer = null;
		// Declaring the Parser
		JavaTreeParser treeParser=null;
		// Declaring a token unit
		Token currentToken = null;
		
		//////////////
		// COUNTERS //
		int classCounter = 0;
		int methodCounter = 0;
		
		
		try 
		{
			// Opening the source file stream
			sourceCodeStream = new FileInputStream(new File("U:\\sampleTest.java"));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		

		// Initializing the Lexer
		myJavaLexer = new JavaLexer(sourceCodeStream);

		// Creating a filter for the lexer
		TokenStreamBasicFilter filter = new TokenStreamBasicFilter(myJavaLexer);
		filter.discard(JavaTokenTypes.WS);
		filter.discard(JavaTokenTypes.ANNOTATION);
		
		// Simple counting on the class number and method
		do
		{
			try {
				currentToken = myJavaLexer.nextToken();
			} catch (TokenStreamException e) {
				e.printStackTrace();
			}
			
			if(currentToken.getType()== JavaTokenTypes.CLASS_DEF)
			{
				classCounter++;
			}
			
			
		}while(currentToken != null && currentToken.getType()!= JavaTokenTypes.EOF);
	
	
	}

}
