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
				sourceFileRawData.IncrementClassCounter();
			}
			
			
		}while(currentToken != null && currentToken.getType()!= JavaTokenTypes.EOF);
		
		return sourceFileRawData;
	}

}
