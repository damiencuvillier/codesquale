package com.codesquale.parser;

import java.io.FileOutputStream;
import java.io.IOException;
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
import com.codesquale.utils.Utilities;

/**
 * Process that run antlr parsing and ast description from a source file list
 * @author mbourguignon
 *
 */
public class AntlrParsingProcess {

	//private ProjectBrowser project = null;
	// XML project file linking AST files 
	private Document doc = null;
	
    // Static instance  
    private static AntlrParsingProcess instance;
    
	private AntlrParsingProcess()
    {
    }
    /**
     * Private ctr() 
     * @return Single instance of AntlrParsingProcess
     */
    public static AntlrParsingProcess getInstance()
    {
      if (instance == null)
          // it's ok, we can call this constructor
    	  instance = new AntlrParsingProcess();		
      return instance;
    }
    /**
     * Clone not implemented
     */
    public AntlrParsingProcess clone() throws CloneNotSupportedException
    {
      throw new CloneNotSupportedException(); 
    }

	/**
	 * Browse javaFiles and serialize the antlr AST to the project XMLoutputPath 
	 * @throws IOException 
	 * 
	 */
	private void processAnalysis() throws Exception
	{
	
		IParsingUnit parsingUnit = null;
		
		for(FileElement fileElement : ProjectBrowser.getInstance().getBasePath().getGlobalFileList())
		{
			// XML description file path
			String fileName = fileElement.getAbsolutePath().substring(ProjectBrowser.getInstance().getBasePath().getAbsolutePath().length()+1);
			fileName = fileName.substring(0, fileName.length() - fileElement.getExtension().length());
			fileName = fileName.replace('\\', '.');
			
			String absolutePath = ProjectBrowser.getInstance().getXMLoutputPath() +"\\"+fileName + "xml";
			
			// set the XML filname path to the fileElement
			fileElement.setXmlDescription(absolutePath);
				
			parsingUnit = ParsingUnitFactory.getInstance().createInstance();
			parsingUnit.setFileName(fileElement.getName());
			parsingUnit.setXmlFileName(absolutePath.substring(absolutePath.lastIndexOf("\\")+1));
			parsingUnit.doParse(fileElement.getIOElement());
			
			FileOutputStream xmlFile = parsingUnit.astToXml(absolutePath);
			if(xmlFile==null)	throw new Exception(Utilities.getCurrentTime()+"AST encoutered fatal error. Impossible to serialize AST to XML File.");
		
		}
	}
	/**
	 * Create the XML stream based on project hierarchy
	 * @param element
	 * @param repository
	 */
	private void processXMLTransform(Node element, Vector<DirectoryElement> repository)
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
		    		((Element)child).setAttribute("href", file.getXmlDescription());
		    		((Element)child).setAttribute("size",String.valueOf(file.getFileSize()));
		    		((Element)child).setAttribute("modified",file.getLastModified());
		    		((Element)child).setAttribute("toli",String.valueOf(file.getTopLinesCount()));
		    		((Element)child).setAttribute("ploc",String.valueOf(file.getPhysicalLinesCount()));
		    		((Element)child).setAttribute("blli",String.valueOf(file.getBlankLinesCount()));
    		
		    	}
		    	processXMLTransform(node, dir.getDirectoriesList());
		    }
	}
	/**
	 * Create a XML file that represent 
	 * project directories, files and XML description link 
	 */
	private void processDescription() throws ParserConfigurationException, TransformerException
	{
	
	    //Create instance of DocumentBuilderFactory
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    //Get the DocumentBuilder
	    DocumentBuilder parser;
		parser = factory.newDocumentBuilder();
	
	    //Create blank DOM Document
	    doc = parser.newDocument();
	
	    // Include a stylesheet
		//	ProcessingInstruction pi = (ProcessingInstruction) doc.createProcessingInstruction("xml-stylesheet", "href=\"style.css\" type=\"text/css\"");
		//  doc.appendChild(pi);
	    
	    // Insert the root element node
	    Element element = doc.createElement("root");
	   // element.setAttribute("path", ProjectBrowser.getInstance().getBasePath().getAbsolutePath());
	    element.setAttribute("xmlns:xi","http://www.w3.org/2001/XInclude");
	    
	    Node node = element.appendChild(doc.createElement("directory"));
    	((Element)node).setAttribute("value", ProjectBrowser.getInstance().getBasePath().getName());
    	((Element)node).setAttribute("href", ProjectBrowser.getInstance().getBasePath().getAbsolutePath());
    	
    	
	    // proceed XML transformation
	    processXMLTransform(doc.appendChild(element).getFirstChild(), ProjectBrowser.getInstance().getBasePath().getDirectoriesList());
	    
	    //	write it out 
	    TransformerFactory xformFactory  = TransformerFactory.newInstance();
	    Transformer idTransform = xformFactory.newTransformer();
	
	    idTransform.setOutputProperty(OutputKeys.METHOD, "xml"); 
	    idTransform.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1"); 
	    idTransform.setOutputProperty(OutputKeys.INDENT, "yes"); 
	    idTransform.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no"); 
	    
	    Source input = new DOMSource(doc);
	    Result output = new StreamResult(ProjectBrowser.getInstance().getProjectOutputFile());
		idTransform.transform(input, output);
	
	}
	
	/**
	 * Launch AntlrParsing process (Analysis and Description)
	 * @throws Exception
	 */
	public void execute() throws Exception
	{
			if(ProjectBrowser.getInstance().isLoaded())
			{
					processAnalysis();
					processDescription();
			}else{
				throw new Exception("ProjectBrowser initialization error");
			}
		
	}	
}
