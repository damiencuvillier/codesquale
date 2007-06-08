package com.codesquale.file;

public abstract class AbstractElement implements Comparable 
{
	/**
	 * Simple File Name
	 */
	private String name;

	/**
	 * Absolute Path of the file / Directory
	 */
	private String absolutePath;

	/**
	 * File
	 */
	private java.io.File ioElement;

	
	/**
	 * Compare method
	 * Order is directories first and alphabetical order
	 */
	public int compareTo(Object o) {
		if(this instanceof DirectoryElement){
			DirectoryElement de=(DirectoryElement)this;
			if (o instanceof FileElement)
				return 1;
			if(o instanceof DirectoryElement){
				DirectoryElement de2=(DirectoryElement)o;
				return de.getName().compareTo(de2.getName());
			}
		}
		if(this instanceof FileElement){
			FileElement fe=(FileElement)this;
			if (o instanceof DirectoryElement)
				return -1;
			if(o instanceof FileElement){
				FileElement fe2=(FileElement)o;
				return fe.getName().compareTo(fe2.getName());
			}
		}
		return 0;
	}
	public java.io.File getIOElement() {
		return ioElement;
	}

	public void setIOElement(java.io.File file) {
		this.ioElement = file;
	}

	public AbstractElement(java.io.File file) {
		name = file.getName();
		absolutePath = file.getAbsolutePath();
		this.ioElement = file;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
