package com.codesquale.file;

import java.io.File;
import java.util.TreeSet;
import java.util.Vector;


/**
 * Tree Element which represents a directoruy
 * 
 * @author DCUVILLIER
 * 
 */
public class DirectoryElement extends AbstractElement {
	
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
	 * @throws Exception 
	 */
	public DirectoryElement(java.io.File dir) throws Exception {
		this(dir, null);
	}
	public DirectoryElement(java.io.File dir, FileFilter filter) throws Exception {
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
	 * @throws Exception s
	 */
	private void browseFiles(java.io.File dir) throws Exception {
		File[] list = dir.listFiles();
		for (int i = 0; i < list.length; i++) {
			File file = list[i];
			if(!file.getName().subSequence(0, 1).equals("."))
			{
				if (file.isDirectory()){
	 					DirectoryElement directoryElement = new DirectoryElement(file,filter);
						globalList.add(directoryElement);
						directoriesList.add(directoryElement);
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
	}
	/**
	 * Get files list included in directory AND subdirectory
	 * @return
	 */
	public Vector<FileElement> getGlobalFileList(){
		Vector<FileElement> liste = new Vector<FileElement>();
		for (FileElement element:getFilesList()) liste.add(element);
		for(DirectoryElement dir : getDirectoriesList()){
			for(FileElement file: dir.getGlobalFileList()) liste.add(file);
		}
		return liste;
	}
	
	/**
	 * Getters
	 * @return
	 */
	public TreeSet<AbstractElement> getGlobalList() {
		return globalList;
	}

	public Vector<DirectoryElement> getDirectoriesList() {
		return directoriesList;
	}
	
	public Vector<FileElement> getFilesList() {
		return filesList;
	}
	/**
	 * Display Methods
	 */
	public String toString() {
		String superMessage = "<DIR value=\""+this.getName()+"\">\n";
		for(DirectoryElement dir :this.getDirectoriesList()) superMessage+=dir.toString(1)+"\n";
		for(FileElement file :this.getFilesList()) superMessage+=file.toString()+"\n";
		superMessage += "</DIR>";
		return superMessage;
	}
	public String toString(int level){
		String superMessage = "";
		
		superMessage += printLevel(level)+"<DIR value=\""+this.getName()+"\">\n";
		for(DirectoryElement dir :this.getDirectoriesList()) superMessage+=dir.toString(level+1)+"\n";
		for(FileElement file :this.getFilesList()) superMessage+=printLevel(level+1)+file.toString()+"\n";
		superMessage  += printLevel(level)+"</DIR>";
		return superMessage;
	}
	private String printLevel(int level){
		String message = "";
		for(int i =0;i<level;i++)message+="\t";
		return message;
	}
	
}
