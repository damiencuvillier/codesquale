package com.codesquale.file;

import java.io.File;

public class NotDirectoryException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String message = "The specified path is not a directory";
	public NotDirectoryException(File file){
		message+=" ("+file.getAbsolutePath()+file.getName()+")";
	}
	public String getMessage() {
		return message;
	}
	
}
