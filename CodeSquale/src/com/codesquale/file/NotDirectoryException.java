package com.codesquale.file;

public class NotDirectoryException extends Exception {

	public String getMessage() {
		return "The specified path is not a directory";
	}
	
}
