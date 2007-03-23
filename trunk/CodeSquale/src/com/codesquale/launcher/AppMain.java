package com.codesquale.launcher;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.codesquale.parser.java.*;
import antlr.*;

public class AppMain {

	public static void main(String[] args) throws FileNotFoundException, TokenStreamException {

		// Variables declaration
		FileInputStream sourceCodeStream=null;
		JavaLexer myJavaLexer;
		Token token;
		int class_cpt = 0;
		
		try 
		{
			sourceCodeStream = new FileInputStream(new File("U:\\sampleTest.java"));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			throw e;
		}

		try 
		{
			myJavaLexer = new JavaLexer(sourceCodeStream);
		do
		{		
			int i = 0;
			token = myJavaLexer.nextToken();
			i = token.getType();
			if( token.getType() == myJavaLexer.LITERAL_class)
			{
				class_cpt++;
			}
			System.out.println(token);	
		}while (token.getType() != myJavaLexer.EOF);
		
		System.out.println("Nombre de classes du fichier:" + class_cpt);
		System.out.println("Nombre de ligne du fichier:" + token.getLine());	
				
		} catch (TokenStreamException e) {
			e.printStackTrace();
			throw e;
		}
		

    		
	
    
	
	}

}
