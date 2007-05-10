package com.codesquale.file;

import java.io.File;
import java.io.FileNotFoundException;
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

import com.codesquale.ant.AntRunner;
import com.codesquale.parser.ParsingUnit;

/**
 * Singleton Class for browsing a path
 *   - List files in a path and in subdirectories thanks to DirectoryElement Class
 *   - 
 * @author DCUVILLIER
 * @TODO Implement a initialization checker
 */
public class ProjectBrowser 
{
	private static Logger logger = Logger.getLogger(ProjectBrowser.class);
	
	private DirectoryElement basePath = null;
	private FileOutputStream projectOutputFile = null;
	private String XMLoutputPath = "";
	private String projectOutputFileName = null;
	
	// check if project browser is init
	private Boolean init = false;
	
    private ProjectBrowser()
    {
    }
    /**
     * Private ctr() 
     * @return Single instance of ProjectBrowser
     */
    public static ProjectBrowser getInstance()
    {
      if (instance == null)
          // it's ok, we can call this constructor
    	  instance = new ProjectBrowser();		
      return instance;
    }
    /**
     * Clone not implemented
     */
    public ProjectBrowser clone() throws CloneNotSupportedException
    {
      throw new CloneNotSupportedException(); 
    }
    // Static instance  
    private static ProjectBrowser instance;


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
	 * @throws FileNotFoundException 
	 */
	public void init(File InputPath,File outputPath, File outputFile, FileFilter fileFilter) throws NotDirectoryException, FileNotFoundException{
		if( ! InputPath.isDirectory() ){
			/* if the param is not a directory, 
			 * throws NotDirectoryException
			 * */
			logger.fatal("Specified path is not a directory");
			init = false;
			throw new NotDirectoryException(InputPath);
		}
		//this.fileFilter = fileFilter;
		setBasePath(new DirectoryElement(InputPath,fileFilter));
		setXMLoutputPath(outputPath.getAbsolutePath()+ "\\xmlOutput");
		setProjectOutputFileName(outputFile.getName());
		/*
		 * Initialize outputFile
		 */
		try {
			setProjectOutputFile(new FileOutputStream(outputFile));
			init = true;
		} catch (FileNotFoundException e) {
			init = false;
			logger.fatal("Output file cannot be opened");
			throw e;
		}
	
	}
	
	public void setBasePath(DirectoryElement basePath) {
		this.basePath = basePath;
	}
	public DirectoryElement getBasePath() {
		return basePath;
	}

	public void setXMLoutputPath(String xMLoutputPath) {
		XMLoutputPath = xMLoutputPath;
	}
	public String getXMLoutputPath() {
		return XMLoutputPath;
	}
	public void setProjectOutputFile(FileOutputStream projectOutputFile) {
		this.projectOutputFile = projectOutputFile;
	}

	public FileOutputStream getProjectOutputFile() {
		return projectOutputFile;
	}
	public void setProjectOutputFileName(String projectOutputFileName) {
		this.projectOutputFileName = projectOutputFileName;
	}
	public String getProjectOutputFileName() {
		return projectOutputFileName;
	}
	public Boolean isLoaded()
	{
		return init;
	}
}
