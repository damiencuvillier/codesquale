package com.codesquale.launcher;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.codesquale.parser.java.*;
import antlr.*;
import antlr.collections.AST;

public class AppMain {

	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws FileNotFoundException, TokenStreamException {

		// Variables declaration
		FileInputStream sourceCodeStream=null;
		JavaLexer myJavaLexer = null;
		JavaRecognizer myJavaParser = null; 
		Token token = null;
		
		int class_cpt = 0;
		int method_cpt = 0;
		
		AST ast = null;
		ASTFactory factory = null;
		
		JavaTreeParser treeParser = new JavaTreeParser();
		
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
			
			// Creation du parser utilisant le scanner
			myJavaParser = new JavaRecognizer(myJavaLexer);
			// Et on parse
			myJavaParser.compilationUnit();
			
			// Factory pour l'ast
			factory = new ASTFactory();
			
			ast = factory.create();
		
			ast.setFirstChild(myJavaParser.getAST());
						
			// Creation de l'arbre logique
			treeParser = new JavaTreeParser();
			// On rempli
			treeParser.compilationUnit(ast);
			
			ast.toStringList();
			
		} catch (RecognitionException e) {
			// TODO Bloc catch auto-généré
			e.printStackTrace();
		}
//		do
//		{		
//			token = myJavaLexer.nextToken();
//			int i = token.getType();
//			
//			if( i == myJavaLexer.LITERAL_class)
//			{
//				class_cpt++;
//			}
//			else if(i == myJavaLexer.METHOD_CALL)
//			{
//				method_cpt++;
//			}
//				
//			
//			System.out.println(token);	
//			
//		}while (token.getType() != myJavaLexer.EOF);
		
//		System.out.println("Nombre de classes du fichier:" + class_cpt);
//		System.out.println("Nombre de method de la classe:" + method_cpt);
//		System.out.println("Nombre de ligne du fichier:" + token.getLine());	
//			
			
//		} catch (TokenStreamException e) {
//			e.printStackTrace();
//			throw e;
//		}					
			
	
    		
	
    
	
	}

}
