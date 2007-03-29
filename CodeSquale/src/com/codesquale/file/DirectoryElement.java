package com.codesquale.file;

import java.io.File;
import java.util.TreeSet;
import java.util.Vector;

import com.codesquale.exceptions.NotDirectoryException;

/**
 * Tree Element which represents a directoruy
 * 
 * @author DCUVILLIER
 * 
 */
public class DirectoryElement extends AbstractElement {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(DirectoryElement.class);

	/**
	 * List of included files & directories
	 */
	private TreeSet<AbstractElement> globalList = null;
	private Vector<DirectoryElement> directoriesList = null;
	private Vector<FileElement> filesList = null;
	
	private FileFilter filter = null;
	/**
	 * 
	 * @param dir
	 * @throws NotDirectoryException
	 */
	public DirectoryElement(java.io.File dir) throws NotDirectoryException {
		this(dir, null);
	}
	public DirectoryElement(java.io.File dir, FileFilter filter) throws NotDirectoryException {
		super(dir);
		
		if (!dir.isDirectory())
			throw new NotDirectoryException(dir);
		globalList = new TreeSet<AbstractElement>();
		directoriesList = new Vector<DirectoryElement>();
		filesList = new Vector<FileElement>();
		this.filter=filter;
		browseFiles(dir);
		
		
	}
	/**
	 * Browses all files with the specified filter
	 * 
	 * @param dir
	 */
	private void browseFiles(java.io.File dir) {
		File[] list = dir.listFiles();
		for (int i = 0; i < list.length; i++) {
			File file = list[i];
			if (file.isDirectory())
				try {
 					DirectoryElement directoryElement = new DirectoryElement(file);
					globalList.add(directoryElement);
					directoriesList.add(directoryElement);
				} catch (NotDirectoryException e) {
					logger.error(e.getMessage());
				}
			if (file.isFile()) {
				FileElement fileElement = new FileElement(file);
				if (filter == null
						|| filter.isAllowed(fileElement.getExtension())){
					globalList.add(fileElement);
					filesList.add(fileElement);
				}
				
			}

		}
	}


	public TreeSet<AbstractElement> getGlobalList() {
		return globalList;
	}

	public Vector<DirectoryElement> getDirectoriesList() {
		return directoriesList;
	}

	public Vector<FileElement> getFilesList() {
		return filesList;
	}
	
	public String toString() {
		String superMessage = "DIR "+this.getName()+"\n";
		for(DirectoryElement dir :this.getDirectoriesList()) superMessage+=dir.toString(1)+"\n";
		for(FileElement file :this.getFilesList()) superMessage+=file.toString()+"\n";
		return superMessage;
	}
	public String toString(int level){
		String superMessage = "";
		
		superMessage += printLevel(level)+"DIR "+this.getName()+"\n";
		for(DirectoryElement dir :this.getDirectoriesList()) superMessage+=dir.toString(level+1)+"\n";
		for(FileElement file :this.getFilesList()) superMessage+=printLevel(level+1)+file.toString()+"\n";
		return superMessage;
	}
	private String printLevel(int level){
		String message = "";
		for(int i =0;i<level;i++)message+="\t";
		return message;
	}
}
