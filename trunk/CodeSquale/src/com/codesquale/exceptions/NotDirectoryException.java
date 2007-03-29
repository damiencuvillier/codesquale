package com.codesquale.exceptions;

public class NotDirectoryException extends Exception {

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "The specified path is not a directory";
	}
	
}
