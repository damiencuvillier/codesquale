package com.codesquale.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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
	// TODO Implement constant manager with XML file
	private String XMLoutputPath = "U:\\temp\\output";
	// Represent the projet XML file
	private Document doc = null;
	
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
	
	private void ProcessXMLTransform(Node element, Vector<DirectoryElement> repository)
	{
		  for(DirectoryElement dir: repository)
		    {
		    	Node node = element.appendChild(doc.createElement("directory"));
		    	((Element)node).setAttribute("path", dir.getName());
		    	
		    	for(FileElement file: dir.getFilesList())
		    	{
		    		Node child = node.appendChild(doc.createElement("file"));
		    		((Element)child).setAttribute("xlink:type", "locator");
		    		((Element)child).setAttribute("xlink:href", file.getXmlDesc());
		    		((Element)child).setAttribute("xlink:show", "embed");
		    		((Element)child).setAttribute("name", file.getName());
		    		
		    	}
		    	ProcessXMLTransform(node, dir.getDirectoriesList());
		    }
	}
	
	public void ProcessDescription()
	{
		/**
		 * Create a XML file that represent 
		 * project directories, files and XML description link 
		 */
		logger.debug("Processing project description in"+ outputFileStream.toString() + "...");
		try {
			
		    //Create instance of DocumentBuilderFactory
		    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		    //Get the DocumentBuilder
		    DocumentBuilder parser;
			parser = factory.newDocumentBuilder();
		
		    //Create blank DOM Document
		    doc = parser.newDocument();
		    
		    // Insert the root element node
		    Element element = doc.createElement("root");
		    element.setAttribute("path", basePath.getName());
		    element.setAttribute("xmlns:xlink", "http://www.w3.org/1999/xlink");
		    element.setAttribute("xlink:type", "extended");
		    
		    ProcessXMLTransform(doc.appendChild(element), basePath.getDirectoriesList());
		    
		    //	Write it out again
		    TransformerFactory xformFactory  = TransformerFactory.newInstance();
		    Transformer idTransform;
				
		    idTransform = xformFactory.newTransformer();
		
		    Source input = new DOMSource(doc);
		    Result output = new StreamResult(outputFileStream);
			idTransform.transform(input, output);
			
		    
			//	outputFileStream.write(basePath.toString().getBytes());
			//	outputFileStream.close();
		} catch (ParserConfigurationException e) {
			logger.fatal("IOException at ProcessAnalysis() in ProjectBrowser : " +e.getMessage());
		} catch (TransformerException e) {
			logger.fatal("TransformerException at ProcessAnalysis() in ProjectBrowser : " +e.getMessage());
		}
	}
	public void ProcessAnalysis()
	{
		/**
		 * Browse javaFiles & get basic metrics
		 */
		logger.debug("Processing project analysis...");
		// Create the project file description		
		projectGlobalMetrics = new ProjectUnitRatioMetrics();
		ParsingUnit parsingUnit = null;
		
		for(FileElement fileElement : basePath.getGlobalFileList())
		{
			// XML description file path
			String fileName = fileElement.getName().substring(0, fileElement.getName().length() - fileElement.getExtension().length());
			String absolutePath =XMLoutputPath+"\\"+fileName + "xml";
			
			// set the XML filname path to the fileElement
			fileElement.setXmlDesc(absolutePath);
			
			// Debug information about file being parsed
			logger.debug("Parsing "+fileElement.getName());
			
			projectGlobalMetrics.incrementFileCounter();
			parsingUnit = new ParsingUnit();
			
			parsingUnit.DoParse(fileElement.getIOElement());
			
			// Get the AST XML of the source file
			FileOutputStream xmlFile = parsingUnit.ASTToXML(absolutePath);
		
		}
	}
	
	
	
	private void populateProjectDescription()
	{
	
			
			// TODO Build the XML AST project file
									
//		    fileElement.setMetricsData(parsingUnit.getSourceFileRawData());
//				
//				classCount+=fileElement.getMetricsData().GetClassCount();
//				methodCount += fileElement.getMetricsData().GetMethodCount();
//				linesCount += fileElement.getMetricsData().GetLineCount();
//                interfaceCount += fileElement.getMetricsData().GetInterfaceCounter();
             
	
		

//		projectGlobalMetrics.setClassCount(classCount);
//		projectGlobalMetrics.setMethodCount(methodCount);
//		projectGlobalMetrics.setlinesCount(linesCount);
//		projectGlobalMetrics.setInterfaceCounter(interfaceCount);
//		
//		System.out.println("Project global Metrics : ");
//		System.out.println(projectGlobalMetrics.getFileCounter()+" files, ");
//		System.out.println(projectGlobalMetrics.getClassCount()+" classes, ");
//		System.out.println(projectGlobalMetrics.getMethodCount()+" methodes, ");
//		System.out.println(projectGlobalMetrics.getLineCount()+ " lines, ");
//
//		System.out.println(interfaceCount+" interfaces, ");
//		
//		System.out.println("Project Ratio Metrics : ");
//		System.out.println("\tAverage number of methods by class: " + 
//				Float.toString(MetricsCalculator.CalculateRatioMethodByClass
//				(projectGlobalMetrics.getClassCount(),
//				 projectGlobalMetrics.getMethodCount())));
		
		
	
	}
	
}
