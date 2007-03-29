package com.codesquale.file;

import java.io.File;
import java.util.TreeSet;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.codesquale.exceptions.NotDirectoryException;

/**
 * Class for browsing a path
 *   - List files in a path and in subdirectories thanks to DirectoryElement Class
 *   - 
 * @author DCUVILLIER
 *
 */
public class ProjectBrowser {
	
	private static Logger logger = Logger.getLogger(ProjectBrowser.class);
	
	private File basePath = null;
	

	/**
	 * Filter enables to filter file types
	 */
	private FileFilter fileFilter = null;
	
	private TreeSet<AbstractElement> mainTree = new TreeSet<AbstractElement>();
	
	/**
	 * Constructor
	 * 
	 * @param path : Directory where source code is located
	 * @param fileTypes : list of authorized file types (Constants are available in class File)
	 * @throws NotDirectoryException 
	 */
	public ProjectBrowser(File path, FileFilter fileFilter) throws NotDirectoryException{
		if( ! path.isDirectory() ){
			/* if the param is not a directory, 
			 * throws NotDirectoryException
			 * */
			logger.fatal("Specified path is not a directory");
			throw new NotDirectoryException(path);
		}
		this.fileFilter = fileFilter;
		basePath = path ;
		DirectoryElement directory = new DirectoryElement(path);
	}
	
	
}
