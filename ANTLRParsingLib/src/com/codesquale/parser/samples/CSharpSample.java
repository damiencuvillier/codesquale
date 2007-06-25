package com.codesquale.parser.samples;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import antlr.CommonAST;
import antlr.Token;
import antlr.collections.AST;

import com.codesquale.parser.csharp.*;

public class CSharpSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CSharpParser csharpParser = null;
		CSharpLexer csharpLexer = null;
		
		FileInputStream fileStream;
		try {
			fileStream = new FileInputStream(new File("C:\\Users\\xplwa\\Devel\\Java\\Workspace\\ANTLRParsingLib\\src\\com\\codesquale\\parser\\samples\\files\\NHibernateInit.cs"));
			
			
			csharpLexer = new CSharpLexer(fileStream);
			
		//	csharpLexer.consumeUntil(CSharpTokenTypes.EOF);
			
		//	System.out.println(csharpLexer.getText());
						
			csharpParser = new CSharpParser(csharpLexer);
		
			csharpParser.compilation_unit();
			
			CommonAST ast = (CommonAST)csharpParser.getAST();
			
			

//			Writer writer = new OutputStreamWriter( new FileOutputStream("C:\\Users\\xplwa\\Devel\\Java\\Workspace\\ANTLRParsingLib\\src\\com\\codesquale\\parser\\samples\\files\\NHibernateInit.xml"));
////			ast.xmlSerialize(writer);
////			writer.flush();
//			CommonAST current = (CommonAST)ast.getFirstChild();
//			
//			current.xmlSerialize(writer);
			
			System.out.println(ast.toStringList());
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			csharpParser.getErrorOccured();
			if(e.getCause()!=null)
			{
				e.getCause().printStackTrace();
			}
		}
		
		
	}

}
