package com.codesquale.file;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.LineNumberInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileElement extends AbstractElement 
{
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FileElement.class);
	
	/**
	 * File informations
	 */
	private int type = -1;
	private String extension= null;
	private String xmlDescription= null;
	private double fileSize = 0;
	private Date lastModified = null;
	private File file = null;
	/**
	 * File static counter
	 */
	/**
	 * Count all lines of code contained in the project without any exception
	 */
	private int topLinesCount  = 0;
	/**
	 * Count all line of source code except blank and commented lines.
	 */
	private int efferentLinesCount = 0;
	/**
	 * Count all line of source code except blank lines.
	 */
	private int physicalLinesCount = 0; 
	/**
	 * Count only blank lines.
	 */
	private int blankLinesCount = 0;
	/**
	 * Count only commented lines.
	 */
	private int commentLineCount = 0;
	
	
	/**
	 * 
	 * @param physicalFile
	 */
	@SuppressWarnings("deprecation")
	public FileElement(File physicalFile)
	{
		super(physicalFile);
		file = physicalFile;
		// (0.0f) force convert long->float
		float size = (0.0f+physicalFile.length())/1000;
		fileSize = Math.floor(size*100+0.5)/100;
		lastModified = new Date(physicalFile.lastModified());

	    //Open the file for reading
	      try {

		       // chain the DataInputStream to a LineNumberInputStream
		       LineNumberInputStream lnis = new LineNumberInputStream(new FileInputStream(file));
		       String theLine;
	
		       // now turn the FileInputStream into a DataInputStream
		       DataInputStream myInput = new DataInputStream(lnis);
		       
		       // Get the current line
	           while ((theLine = myInput.readLine()) != null) {  
	             // blank line if bytes = 0
	             if(theLine.getBytes().length == 0)
	                  	 blankLinesCount++;
	             
	           } // while loop ends here
	           // set topLinesCount
	           topLinesCount = lnis.getLineNumber();
	           physicalLinesCount = topLinesCount - blankLinesCount;
	           
	      }catch (Exception e) {
	       System.err.println("Error: " + e);
	      }		
	}
	
	/*
	 * Returns the extension of the file
	 */
	public String getExtension(){
		if(extension == null){
			String name = file.getName();
			extension = name.substring(name.lastIndexOf(".") + 1);
		}
		return extension;
	}
	
	public int getType(){
		if(type == -1){
			type = FileFilter.getType(getExtension());
		}
		return type;
	}
	public String toString() {
		// FIXME à génériser car ici on part du principe qu'il s'agit de fichiers java uniquement
		String message = "<FILE name=\""+getName()+"\" xmlDescription=\""+ getXmlDescription() +"\" />";
		return message;
	}

	/**
	 * @return path to XML Description
	 */
	public String getXmlDescription() {
		return xmlDescription;
	}
	/**
	 * Set the path to the XML description
	 * @param xmlDescription
	 */
	public void setXmlDescription(String xmlDesc) {
		this.xmlDescription = xmlDesc;
	}

	/**
	 * @return size of source file
	 */
	public double getFileSize() {
		return fileSize;
	}

	/**
	 * @return last time the file was modified
	 */
	public String getLastModified() {
		String date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd '-' HH:mm:ss");
		return sdf.format(lastModified);
	}
	public void setLastModified(String date) {
		try {
			throw new Exception("Not implemeted yet");
		} catch (Exception e) {
			// TODO Bloc catch auto-généré
			e.printStackTrace();
		}
	}

	/**
	 * @param efferentLinesCount 
	 */
	public void setEfferentLinesCount(int efferentLinesCount) {
		this.efferentLinesCount = efferentLinesCount;
	}

	/**
	 * @return efferentLinesCount
	 */
	public int getEfferentLinesCount() {
		return efferentLinesCount;
	}

	/**
	 * @param physicalLinesCount physicalLinesCount à définir
	 */
	public void setPhysicalLinesCount(int physicalLinesCount) {
		this.physicalLinesCount = physicalLinesCount;
	}

	/**
	 * @return physicalLinesCount
	 */
	public int getPhysicalLinesCount() {
		return physicalLinesCount;
	}

	/**
	 * @param blankLinesCount blankLinesCount à définir
	 */
	public void setBlankLinesCount(int blankLinesCount) {
		this.blankLinesCount = blankLinesCount;
	}

	/**
	 * @return blankLinesCount
	 */
	public int getBlankLinesCount() {
		return blankLinesCount;
	}

	/**
	 * @param commentLineCount commentLineCount à définir
	 */
	public void setCommentLineCount(int commentLineCount) {
		this.commentLineCount = commentLineCount;
	}

	/**
	 * @return commentLineCount
	 */
	public int getCommentLineCount() {
		return commentLineCount;
	}

	/**
	 * @param topLinesCount topLinesCount à définir
	 */
	public void setTopLinesCount(int topLinesCount) {
		this.topLinesCount = topLinesCount;
	}

	/**
	 * @return topLinesCount
	 */
	public int getTopLinesCount() {
		return topLinesCount;
	}
}
