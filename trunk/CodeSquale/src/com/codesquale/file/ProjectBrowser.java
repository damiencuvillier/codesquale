package com.codesquale.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.codesquale.exceptions.NotDirectoryException;
import com.codesquale.metrics.MetricsCalculator;
import com.codesquale.metrics.ProjectUnitRatioMetrics;
import com.codesquale.parser.ParsingUnit;

/**
 * Class for browsing a path
 *   - List files in a path and in subdirectories thanks to DirectoryElement Class
 *   - 
 * @author DCUVILLIER
 *
 */
public class ProjectBrowser 
{
	private ProjectUnitRatioMetrics projectGlobalMetrics = null;
	
	private static Logger logger = Logger.getLogger(ProjectBrowser.class);
	
	private DirectoryElement basePath = null;
	private FileOutputStream outputFileStream = null;
	
	
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
		try {
			outputFileStream = new FileOutputStream(outputFile);
		} catch (FileNotFoundException e) {
			logger.fatal("Output file cannot be opened");
		}
		
		
		
	}
	
	
	
	
	public void ProcessAnalysis()
	{
		/**
		 * Browse javaFiles & get basic metrics
		 */
		logger.debug("Processing analysis of the project source code...");
		populateMetrics();
		
		try {
			outputFileStream.write(basePath.toString().getBytes());
			outputFileStream.close();
		} catch (IOException e) {
			logger.fatal("IOException at ctor() ProjectBrowser : " +e.getMessage());
		}
	}
	
	

	private void populateMetrics()
	{
		projectGlobalMetrics = new ProjectUnitRatioMetrics();
		ParsingUnit parsingUnit =null;
		
		
		
		int classCount=0;
		int methodCount=0;
		int linesCount=0;
		int interfaceCount=0;
		for(FileElement fileElement : basePath.getGlobalFileList())
		{
			// Debug information about file being parsed
			logger.debug("Parsing "+fileElement.getName());
			
			projectGlobalMetrics.IncrementFileCounter();
			parsingUnit = new ParsingUnit();
			
			

			parsingUnit.DoParse(fileElement.getIOElement());
			
		    fileElement.setMetricsData(parsingUnit.getSourceFileRawData());
				
				classCount+=fileElement.getMetricsData().GetClassCount();
				methodCount += fileElement.getMetricsData().GetMethodCount();
				linesCount += fileElement.getMetricsData().GetLineCount();
                interfaceCount += fileElement.getMetricsData().GetInterfaceCounter();

		}
		

		projectGlobalMetrics.SetClassCount(classCount);
		projectGlobalMetrics.SetMethodCount(methodCount);
		projectGlobalMetrics.SetlinesCount(linesCount);
		projectGlobalMetrics.SetInterfaceCounter(interfaceCount);
		
		
		
		
		System.out.println("Project global Metrics : ");
		System.out.println(projectGlobalMetrics.GetFileCounter()+" files, ");
		System.out.println(projectGlobalMetrics.GetClassCount()+" classes, ");
		System.out.println(projectGlobalMetrics.GetMethodCount()+" methodes, ");
		System.out.println(projectGlobalMetrics.GetLineCount()+ " lines, ");

		System.out.println(interfaceCount+" interfaces, ");
		
		System.out.println("Project Ratio Metrics : ");
		System.out.println("\tAverage number of methods by class: " + 
				Float.toString(MetricsCalculator.CalculateRatioMethodByClass
				(projectGlobalMetrics.GetClassCount(),
				 projectGlobalMetrics.GetMethodCount())));
		
		
	
	}
	
}