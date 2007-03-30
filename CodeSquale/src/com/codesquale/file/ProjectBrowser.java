package com.codesquale.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.codesquale.exceptions.NotDirectoryException;
import com.codesquale.parser.ParsingUnit;

/**
 * Class for browsing a path
 *   - List files in a path and in subdirectories thanks to DirectoryElement Class
 *   - 
 * @author DCUVILLIER
 *
 */
public class ProjectBrowser {
	
	private static Logger logger = Logger.getLogger(ProjectBrowser.class);
	
	private DirectoryElement basePath = null;
	
	
	/**
	 * Filter enables to filter file types
	 */
	//private FileFilter fileFilter = null;
	
	//private TreeSet<AbstractElement> mainTree = new TreeSet<AbstractElement>();
	
	/**
	 * Constructor
	 * 
	 * @param path : Directory where source code is located
	 * @param fileTypes : list of authorized file types (Constants are available in class File)
	 * @throws NotDirectoryException 
	 */
	public ProjectBrowser(File path, File outputFile, FileFilter fileFilter) throws NotDirectoryException{
		if( ! path.isDirectory() ){
			/* if the param is not a directory, 
			 * throws NotDirectoryException
			 * */
			logger.fatal("Specified path is not a directory");
			throw new NotDirectoryException(path);
		}
		//this.fileFilter = fileFilter;
		basePath = new DirectoryElement(path,fileFilter);
		
		
		/*
		 * Initialize outputFile
		 */
		FileOutputStream outputFileStream = null;
		try {
			outputFileStream = new FileOutputStream(outputFile);
		} catch (FileNotFoundException e) {
			logger.fatal("Output file cannot be opened");
		}
		
		/**
		 * Browse javaFiles & get basic metrics
		 */
		logger.debug("Populate metrics");
		populateMetrics();
		
		try {
			outputFileStream.write(basePath.toString().getBytes());
			outputFileStream.close();
		} catch (IOException e) {
			logger.fatal("IOException at ctor() ProjectBrowser : " +e.getMessage());
		}
		
	}
	
	
	
	/**
	 * Get Metrics
	 */
	private void populateMetrics(){
		
		int fileCount = 0;
		int classCount = 0;
		int methodCount = 0;
		int linesCount = 0;
		int constructorCount = 0;
		int interfaceCount = 0;
		
		//FIXME a placer dans la deuxieme moulinette
		ParsingUnit parsingUnit =null;
		
		
		
		for(FileElement fileElement : basePath.getGlobalFileList())
		{
			
			logger.debug("Parsing "+fileElement.getName());
			
			fileCount ++;
			parsingUnit = new ParsingUnit();
			
			

			parsingUnit.DoParse(fileElement.getIOElement());
			
		    fileElement.setMetricsData(parsingUnit.getSourceFileRawData());
				
				classCount+=fileElement.getMetricsData().GetClassCount();
				methodCount += fileElement.getMetricsData().GetMethodCount();
				linesCount += fileElement.getMetricsData().GetLineCount();
				constructorCount+= fileElement.getMetricsData().GetConstructCounter();
                interfaceCount += fileElement.getMetricsData().GetInterfaceCounter();

		}
		
		
		System.out.println("Le projet contient: ");
		System.out.println(fileCount+" fichiers, ");
		System.out.println(classCount+" classes, ");
		System.out.println(methodCount+" méthodes, ");
		System.out.println(linesCount+ " lignes, ");
		System.out.println(constructorCount+" constructeurs, ");
		System.out.println(interfaceCount+" interfaces, ");
	
	}
	
}
