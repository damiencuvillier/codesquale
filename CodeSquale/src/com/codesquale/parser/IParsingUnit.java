package com.codesquale.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public interface IParsingUnit {

//	void getTypeCount(AST t, int type, int count[]);
//	AST getChild(AST ast, int childType);
	void DoParse(File codeSourceFile) throws FileNotFoundException;
	FileOutputStream ASTToXML(String fileName) throws IOException;
}
