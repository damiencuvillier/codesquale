package com.codesquale.parser;

import java.io.FileInputStream;

import antlr.Token;
import antlr.TokenStreamBasicFilter;
import antlr.TokenStreamException;

import com.codesquale.metrics.RawMetricsData;
import com.codesquale.parser.java.JavaLexer;
import com.codesquale.parser.java.JavaTokenTypes;

public class ParsingUnit {
	
	////////////////////////
	// PARSING ATTRIBUTES //
	// Declaring the Lexer
	JavaLexer myJavaLexer = null;
	// Declaring a token unit
	Token currentToken = null;
	
	
	// Raw metrics data
	RawMetricsData sourceFileRawData = null;
	
	
	
	public RawMetricsData ParseCodeSourceStream(FileInputStream codeSourceFileStream)
	{

		
		// Initializing the Lexer
		myJavaLexer = new JavaLexer(codeSourceFileStream);
		// Initializiong the metrics
		sourceFileRawData = new RawMetricsData();

		int previousLine = 0;
		int tokenLine = 0;
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

			if(currentToken.getType()== JavaTokenTypes.LITERAL_class)
			{
				sourceFileRawData.IncrementClassCounter();
			}
			
			// Retrieve the number of line
			tokenLine = currentToken.getLine();
			if(previousLine ==0 || tokenLine!=previousLine) sourceFileRawData.IncrementLineCounter();
		    previousLine = tokenLine;
			
			
		}while(currentToken != null && currentToken.getType()!= JavaTokenTypes.EOF);
		
		return sourceFileRawData;
	}

}
