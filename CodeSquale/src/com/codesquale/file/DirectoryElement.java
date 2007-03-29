package com.codesquale.file;

import java.io.File;
import java.util.TreeSet;
import java.util.Vector;

import com.codesquale.exceptions.NotDirectoryException;


/**
 * Tree Element which represents a directoruy
 * @author DCUVILLIER
 *
 */
public class DirectoryElement extends AbstractElement{
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
		.getLogger(DirectoryElement.class);
	
	/**
	 * List of included files & directories
	 */
	private TreeSet<AbstractElement> listFiles = null;
	
	
	/**
	 * 
	 * @param dir
	 * @throws NotDirectoryException
	 */
	public DirectoryElement(java.io.File dir) throws NotDirectoryException {
		super(dir);
		if(! dir.isDirectory())
			throw new NotDirectoryException(dir);
		listFiles = new TreeSet<AbstractElement>();
		browseFiles(dir);
	}
	
	/**
	 * Browses all files with the specified filter
	 * @param dir
	 */
	private void browseFiles(java.io.File dir, Vector<Integer> filter ){
		File[] list = dir.listFiles();
		for (int i = 0; i<list.length; i++){
			File file = list[i];
			if(file.isDirectory())
				try {
					listFiles.add(new DirectoryElement(file));
				} catch (NotDirectoryException e) {
					logger.error(e.getMessage());
				}
			if(file.isFile())
				if(filter != null)
				listFiles.add(new FileElement(file));
		}
	}
	private void browseFiles(java.io.File dir){
		browseFiles(dir, null);
	}

	
	
}
