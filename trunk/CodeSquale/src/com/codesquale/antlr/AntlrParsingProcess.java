package com.codesquale.antlr;

import java.io.FileOutputStream;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.codesquale.file.DirectoryElement;
import com.codesquale.file.FileElement;
import com.codesquale.file.ProjectBrowser;
import com.codesquale.parser.ParsingUnit;

public class AntlrParsingProcess {

	private static Logger logger = Logger.getLogger(AntlrParsingProcess.class);
	
	private ProjectBrowser project = null;
	// XML project file linking AST files 
	private Document doc = null;
	
	public AntlrParsingProcess(ProjectBrowser p)
	{
		project = p;
	}
	/**
	 * Browse javaFiles and serialize the antlr AST to the project XMLoutputPath 
	 * 
	 */
	public void ProcessAnalysis()
	{
	
		logger.debug("Processing project analysis...");

		ParsingUnit parsingUnit = null;
		
		for(FileElement fileElement : project.getBasePath().getGlobalFileList())
		{
			// XML description file path
			String fileName = fileElement.getAbsolutePath().substring(project.getBasePath().getAbsolutePath().length()+1);
			fileName = fileName.substring(0, fileName.length() - fileElement.getExtension().length());
			fileName = fileName.replace('\\', '.');
			
			String absolutePath = project.getXMLoutputPath() +"\\"+fileName + "xml";
			
			// set the XML filname path to the fileElement
			fileElement.setXmlDesc(absolutePath);
			
			// Debug information about file being parsed
			logger.debug("Parsing "+fileElement.getName());
			
			parsingUnit = new ParsingUnit();
			
			parsingUnit.DoParse(fileElement.getIOElement());
			
			// Get the AST XML of the source file
			FileOutputStream xmlFile = parsingUnit.ASTToXML(absolutePath);
		
		}
	}
	/**
	 * Create the XML stream based on project hierarchy
	 * @param element
	 * @param repository
	 */
	private void ProcessXMLTransform(Node element, Vector<DirectoryElement> repository)
	{
		  for(DirectoryElement dir: repository)
		    {
		    	Node node = element.appendChild(doc.createElement("directory"));
		    	((Element)node).setAttribute("value", dir.getName());
		    	((Element)node).setAttribute("href", dir.getAbsolutePath());
		    	
		    	for(FileElement file: dir.getFilesList())
		    	{
		    		Node child = node.appendChild(doc.createElement("file"));
		    		((Element)child).setAttribute("value", file.getName());
		    		((Element)child).setAttribute("href", file.getXmlDesc());
    		
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
	
		logger.debug("Processing project description in "+ project.getProjectOutputFile() + "...");
		try {
			
		    //Create instance of DocumentBuilderFactory
		    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		    //Get the DocumentBuilder
		    DocumentBuilder parser;
			parser = factory.newDocumentBuilder();
		
		    //Create blank DOM Document
		    doc = parser.newDocument();
		
		    // Include a stylesheet
//		    ProcessingInstruction pi = (ProcessingInstruction) doc.createProcessingInstruction("xml-stylesheet", "href=\"style.css\" type=\"text/css\"");
//		    doc.appendChild(pi);
		    
		    // Insert the root element node
		    Element element = doc.createElement("root");
		    element.setAttribute("path", project.getBasePath().getAbsolutePath());
		    element.setAttribute("xmlns:xi","http://www.w3.org/2001/XInclude");
		    	    
		    // proceed XML transformation
		    ProcessXMLTransform(doc.appendChild(element), project.getBasePath().getDirectoriesList());
		    
		    //	write it out 
		    TransformerFactory xformFactory  = TransformerFactory.newInstance();
		    Transformer idTransform = xformFactory.newTransformer();
		
		    idTransform.setOutputProperty(OutputKeys.METHOD, "xml"); 
		    idTransform.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1"); 
		    idTransform.setOutputProperty(OutputKeys.INDENT, "yes"); 
		    idTransform.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no"); 
		    
		    Source input = new DOMSource(doc);
		    Result output = new StreamResult(project.getProjectOutputFile());
			idTransform.transform(input, output);
			
		} catch (ParserConfigurationException e) {
			logger.fatal("IOException at ProcessAnalysis() in ProjectBrowser : " +e.getMessage());
		} catch (TransformerException e) {
			logger.fatal("TransformerException at ProcessAnalysis() in ProjectBrowser : " +e.getMessage());
		}
	}
	
}
