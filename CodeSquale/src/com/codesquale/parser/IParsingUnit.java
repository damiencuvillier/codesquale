package com.codesquale.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public interface IParsingUnit {

//	void getTypeCount(AST t, int type, int count[]);
//	AST getChild(AST ast, int childType);
	void doParse(File codeSourceFile) throws FileNotFoundException, Exception;
	FileOutputStream astToXml(String fileName) throws IOException;
	String getFileName() ;
	String getXmlFileName();
	void setFileName(String fileName);
	void setXmlFileName(String fileName);
	
}
