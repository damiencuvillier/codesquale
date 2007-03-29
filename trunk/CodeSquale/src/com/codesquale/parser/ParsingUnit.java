package com.codesquale.parser;

import java.io.FileInputStream;

import antlr.Token;
import antlr.TokenStreamBasicFilter;
import antlr.TokenStreamException;

import com.codesquale.metrics.RawMetricsData;
import com.codesquale.parser.java.JavaLexer;
import com.codesquale.parser.java.JavaTokenTypes;

/**
 * Represents the subsystem in charge of parsing a single source file
 * and provides the raw collected data.
 * These data are necessary to calculte the final metrics.
 * @author dwillier
 */
public class ParsingUnit {
	
	
	// Declaring the Lexer
	JavaLexer myJavaLexer = null;
	// Declaring a token unit
	Token currentToken = null;
	
	
	// Raw metrics data
	RawMetricsData sourceFileRawData = null;
	
	/**
	 *  Parse a fileStream and calculates counters
	 * @param codeSourceFileStream
	 * @return returns a RawMetricsData class necessary to build the final metrics
	 */
	public RawMetricsData ParseCodeSourceStream(FileInputStream codeSourceFileStream)
	{
		// Initializing the Lexer
		myJavaLexer = new JavaLexer(codeSourceFileStream);
		// Initializiong the metrics
		sourceFileRawData = new RawMetricsData();

		int previousLine = 0;
		int tokenLine = 0;

		// Simple counting on the class number and method
		do
		{
			try {
				currentToken = myJavaLexer.nextToken();
			} catch (TokenStreamException e) {
				e.printStackTrace();
			}

			// Count the number of class
			if(currentToken.getType() == JavaTokenTypes.LITERAL_class)
				sourceFileRawData.IncrementClassCounter();
			
			// Count the number of methods
			if(currentToken.getType() == JavaTokenTypes.METHOD_CALL )
				sourceFileRawData.IncrementMethodCounter();
			
			
			// Count the total number of lines
			tokenLine = currentToken.getLine();
			if(previousLine ==0 || tokenLine!=previousLine) sourceFileRawData.IncrementLineCounter();
		    previousLine = tokenLine;
			
			
		}while(currentToken != null && currentToken.getType()!= JavaTokenTypes.EOF);
		
		return sourceFileRawData;
	}

}
