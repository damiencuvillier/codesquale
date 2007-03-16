package com.codesquale.file;

import java.io.File;

import org.apache.log4j.Logger;

/**
 * Class for browsing a path
 *   - List files in a path and in subdirectories
 *   - Count files and lines
 * @author DCUVILLIER
 *
 */
public class RepositoryBrowser {
	
	private static Logger logger = Logger.getLogger(RepositoryBrowser.class);
	
	private File basePath = null;
	
	private int filesCount = 0;
	private int linesCount = 0;
	
	/**
	 * Constructor
	 * 
	 * @param path : Directory where source code is located
	 * @throws NotDirectoryException 
	 */
	public RepositoryBrowser(File path) throws NotDirectoryException{
		if( ! path.isDirectory() ){
			/* if the param is not a directory, 
			 * throws NotDirectoryException
			 * */
			logger.fatal("Specified path is not a directory");
			throw new NotDirectoryException();
		}
		basePath = path ;
	}
	
	
}
