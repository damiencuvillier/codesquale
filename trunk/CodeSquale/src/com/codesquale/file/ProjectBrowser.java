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
import javax.xml.transform.OutputKeys;
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
import org.w3c.dom.ProcessingInstruction;

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
	private String XMLoutputPath = "in\\output";
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
	 * @param InputPath : Directory where source code is located
	 * @param fileTypes : list of authorized file types (Constants are available in class File)
	 * @throws NotDirectoryException 
	 */
	public ProjectBrowser(File InputPath,File outputPath, File outputFile, FileFilter fileFilter) throws NotDirectoryException{
		if( ! InputPath.isDirectory() ){
			/* if the param is not a directory, 
			 * throws NotDirectoryException
			 * */
			logger.fatal("Specified path is not a directory");
			throw new NotDirectoryException(InputPath);
		}
		//this.fileFilter = fileFilter;
		basePath = new DirectoryElement(InputPath,fileFilter);
		XMLoutputPath = outputPath.getAbsolutePath()+ "\\xmlOutput";
		
		/*
		 * Initialize outputFile
		 */
		try {
			outputFileStream = new FileOutputStream(outputFile);
		} catch (FileNotFoundException e) {
			logger.fatal("Output file cannot be opened");
		}
	
	}
	
	/**
	 * Create the XML stream based on project hierarchie
	 * @param element
	 * @param repository
	 */
	private void ProcessXMLTransform(Node element, Vector<DirectoryElement> repository)
	{
		  for(DirectoryElement dir: repository)
		    {
		    	Node node = element.appendChild(doc.createElement("directory"));
		    	((Element)node).appendChild(doc.createTextNode(dir.getName()));
		    	
		    	for(FileElement file: dir.getFilesList())
		    	{
		    		Node child = node.appendChild(doc.createElement("file"));
		
		    		((Element)child).appendChild(doc.createTextNode(file.getName().substring(0,file.getName().length() - (file.getExtension().length()+1))));
		    		
		    		Node desc = child.appendChild(doc.createElement("description"));

		    		((Element)desc).setAttribute("xlink:type", "simple");
		    		((Element)desc).setAttribute("xlink:title", file.getName());
		    		((Element)desc).setAttribute("xlink:href", file.getXmlDesc());
		    		((Element)desc).setAttribute("xlink:show", "new");
		    		((Element)desc).setAttribute("xlink:actuate", "onRequest");
		    		
		    		((Element)desc).appendChild(doc.createTextNode("description"));
		    		
		    	}
		    	ProcessXMLTransform(node, dir.getDirectoriesList());
		    }
	}
	/**
	 * Create a XML file that represent 
	 * project directories, files and XML description link 
	 */
	public void ProcessDescription()
	{
	
		logger.debug("Processing project description in"+ outputFileStream.toString() + "...");
		try {
			
		    //Create instance of DocumentBuilderFactory
		    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		    //Get the DocumentBuilder
		    DocumentBuilder parser;
			parser = factory.newDocumentBuilder();
		
		    //Create blank DOM Document
		    doc = parser.newDocument();
		    // Include a stylesheet
		    ProcessingInstruction pi = (ProcessingInstruction) doc.createProcessingInstruction("xml-stylesheet", "href=\"style.css\" type=\"text/css\"");
		    doc.appendChild(pi);
		    
		    // Insert the root element node
		    Element element = doc.createElement("root");
		    element.setAttribute("path", basePath.getAbsolutePath());
		    element.setAttribute("xmlns:xlink","http://www.w3.org/1999/xlink");
		    	    
		    // proceed XML transformation
		    ProcessXMLTransform(doc.appendChild(element), basePath.getDirectoriesList());
		    
		    //	write it out 
		    TransformerFactory xformFactory  = TransformerFactory.newInstance();
		    Transformer idTransform = xformFactory.newTransformer();
		
		    idTransform.setOutputProperty(OutputKeys.METHOD, "xml"); 
		    idTransform.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1"); 
		    idTransform.setOutputProperty(OutputKeys.INDENT, "yes"); 
		    idTransform.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no"); 
		    
		    Source input = new DOMSource(doc);
		    Result output = new StreamResult(outputFileStream);
			idTransform.transform(input, output);
			
		} catch (ParserConfigurationException e) {
			logger.fatal("IOException at ProcessAnalysis() in ProjectBrowser : " +e.getMessage());
		} catch (TransformerException e) {
			logger.fatal("TransformerException at ProcessAnalysis() in ProjectBrowser : " +e.getMessage());
		}
	}
	
	/**
	 * Browse javaFiles & get basic metrics
	 */
	public void ProcessAnalysis()
	{
	
		logger.debug("Processing project analysis...");
		// Create the project file description		
		projectGlobalMetrics = new ProjectUnitRatioMetrics();
		ParsingUnit parsingUnit = null;
		
		for(FileElement fileElement : basePath.getGlobalFileList())
		{
			// XML description file path
			String fileName = fileElement.getAbsolutePath().substring(basePath.getAbsolutePath().length()+1);
			fileName = fileName.substring(0, fileName.length() - fileElement.getExtension().length());
			fileName = fileName.replace('\\', '.');
			
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
