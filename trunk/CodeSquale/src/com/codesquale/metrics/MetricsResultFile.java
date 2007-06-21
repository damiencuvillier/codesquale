package com.codesquale.metrics;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

/**
 * Represents the generated results file
 * @author dwillier
 *
 */
public class MetricsResultFile {

	/**
	 * Represents the output metrics results XML file.
	 */
	private Document newDocument;
	
	
	/**
	 * Constructor by default. Creates a new xml document.
	 *
	 */
	public MetricsResultFile(){
		// Preparing a new document to store the results
		createDocument();
	}
	
	/**
	 * Creates for a text node for the document.
	 * @param value Value from which to create text node
	 * @return Text element
	 */
	public final Text createTextNode(final String value){
		return newDocument.createTextNode(value);
	}
	
	/**
	 * Append the element to the document.
	 * @param e Element to append
	 */
	public final void appendChild(final Element e)
	{
		newDocument.appendChild(e);
	}
	
	/**
	 * Creates an element.
	 * @param elementName Name of the element to create
	 * @return The created element
	 */
	public final Element createElement(final String elementName){
		return newDocument.createElement(elementName);
	}
	
	
	/**
	 * Creates the output results document.
	 * 
	 */
	private void createDocument() {

		// get an instance of factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			// get an instance of builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// create an instance of DOM
			newDocument = db.newDocument();

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		}

	}
	
	
	/**
	 * Saving the ouptut data stream to an XML file by serialization.
	 * @param pathOutput The path where the file has to be saved
	 */
	public final void writeToFile(final String pathOutput) {

		try {
			// print
			OutputFormat format = new OutputFormat(newDocument);
			format.setIndenting(true);

			// to generate output to console use this serializer
			// XMLSerializer serializer = new XMLSerializer(System.out, format);

			// to generate a file output use fileoutputstream instead of
			// system.out
			XMLSerializer serializer = new XMLSerializer(new FileOutputStream(
					new File(pathOutput)), format);

			serializer.serialize(newDocument);

		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}
	
}
