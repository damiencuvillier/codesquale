package com.codesquale.metrics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.saxon.om.SequenceIterator;
import net.sf.saxon.query.QueryResult;
import net.sf.saxon.trans.XPathException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

/**
 * Class in charge of building the results file from a configuration skeleton
 * file. It parses the input skeleton file and in the same time recopy its
 * structure to the output results file. When it recognize an ID metrics tag,
 * this one is calculated and its value appended to the output result stream;
 * 
 * @author dwillier
 * 
 */
public class MetricsResultFileBuilder {

	/**
	 * Represents the output metrics results XML file
	 */
	private Document newDocument;

	/**
	 * Needed to build the resuslts output file
	 */
	private DocumentBuilderFactory documentBuilderFactory;

	private Hashtable<String, Metric> metricsHashTable;

	
	private String resultFileConfigurationPath;
	/**
	 * This constructor sets the metrics collection available to build the
	 * results
	 * 
	 * @param metricsHashTable
	 */
	public MetricsResultFileBuilder(Hashtable<String, Metric> metricsHashTable, String resultFileConfigurationPath) {
		// Set the entire collection metrics
		this.metricsHashTable = metricsHashTable;

		this.resultFileConfigurationPath = resultFileConfigurationPath;
		
		documentBuilderFactory = DocumentBuilderFactory.newInstance();

	}

	/**
	 * This method parses the configuration file needed to generate the result
	 * file
	 * 
	 * @param inputPathFile
	 *            full path to the configuration file
	 */
	public void BuildMetricsResultFile(String inputPathFile, String outputFileFullPath) {

		try {
			// Preparing a new document to store the results
			createDocument();
			// Creates Document Builder needed to parse the desired XML file
			DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();
			// Read the metrics configuration file
			Document doc = db.parse(resultFileConfigurationPath);
			// Specify the file to analyze
			SaxonProcessor.getInstance().setXMLSourceDocument(inputPathFile);
			// Starts the parsing process
			ParseMetricsResulFileConfiguration(doc, null);
			// Generating the results file filled with the query value
			printToFile(outputFileFullPath);

		} catch (ParserConfigurationException e) {
			// TODO Bloc catch auto-généré
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Bloc catch auto-généré
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Bloc catch auto-généré
			e.printStackTrace();
		}
	}

	/**
	 * Process the entire content of the XML document with recursivity and call
	 * the adapted method when recognizing ID metrics tag
	 * 
	 * @param documentToRead
	 *            It is the XML content to parses and interpret
	 * @param elementToWrite
	 *            It is the element where the results elements generated have to
	 *            be append
	 */
	// TODO Remove Node when is SequenceValue or Sequence
	private void ParseMetricsResulFileConfiguration(Document documentToRead,
			Element elementToWrite) {

		// This element is used as the node appender further in the process
		Element mainAppender;

		// Getting the root element of the XML section or document to read
		// passed as parameter
		Element sourceDocumentRootElement = documentToRead.getDocumentElement();
		// Getting the tag name of this root element
		String rootElementName = sourceDocumentRootElement.getNodeName();

		// Creating a parent node into the results file generated
		Element newDocumentRootElement = newDocument
				.createElement(rootElementName);

		// null element passed in parameter means that we have to append the
		// root element as root element to the result file metrics
		if (elementToWrite == null) {
			// Appending the root node the destination file
			newDocument.appendChild(newDocumentRootElement);
			mainAppender = newDocumentRootElement;
		} else {
			mainAppender = elementToWrite;
			mainAppender.appendChild(newDocumentRootElement);
		}

		// Starting the parsing process
		if (sourceDocumentRootElement != null
				&& sourceDocumentRootElement.hasChildNodes()) {
			// Fetching the child nodes
			NodeList nodeList = sourceDocumentRootElement.getChildNodes();
			int cpt = 0;
			// Stepping trough the child node
			while (nodeList.getLength() > 0 && cpt < nodeList.getLength()) {
				Node childNode = nodeList.item(cpt++);
				// Process the node to check his validity and generates the
				// output element
				Element generatedElementFromNode = ProcessNodeParsing(childNode);
				// Appending the new element to our document
				if (generatedElementFromNode != null)
					mainAppender.appendChild(generatedElementFromNode);
				// Continue to iterate over the xml tree
				if (childNode.hasChildNodes())
					ParseXMLRecursively(childNode, generatedElementFromNode);
			}
		}
	}

	/**
	 * Process a single node, specially if it is an ID metric tag. It verify
	 * that the node is an XML element then dependently on the tag nature, the
	 * node is processed in the correct way.
	 * 
	 * @param childNode
	 *            node to be analyzed and procesed.
	 * @return A well formated element ready to be append
	 */
	private Element ProcessNodeParsing(Node childNode) {

		Element myElement = null;
		// Verifying that the node is an XML element
		if (childNode.getNodeType() == Node.ELEMENT_NODE) {
			String nodeName = childNode.getNodeName();
			// Creating the correspondant Element
			myElement = newDocument.createElement(nodeName);
			// Fetching the element value
			String nodeValue = getNodeValue(childNode);
			// Veryfing that text node value isn't empty
			if (!nodeValue.equals("")) {
				// Fetch in the hashtable the associated Metric with ID got
				// previously
				Metric myMetric = metricsHashTable.get(nodeValue);
				// Verifying that the metric exists
				if (myMetric != null)
					AppendMetricsResultsToElement(myMetric, myElement);
				// Appending the node value and dont calculates any metrics
				else {
					Text textValue = newDocument
							.createTextNode(getNodeValue(childNode));
					myElement.appendChild(textValue);
				}

			}
		}

		return myElement;
	}

	/**
	 * This method is called when an ID metric tagis recognized. This method is
	 * in charge of calculating the metric passed in parameter and append the
	 * value and associated tag to rest of document.
	 * 
	 * @param myMetric
	 *            The metric to be calculated
	 * @param myElement
	 *            The element where the metric tag and value have to be append
	 */
	private void AppendMetricsResultsToElement(Metric myMetric,
			Element myElement) {
		// the metric has an atomic value return
		if (myMetric.getMetricType().equals("single")) {

			Object results = null;
			try {
				// Processing the desired XQuery
				results = myMetric.getCompiledQuery().evaluateSingle(
						SaxonProcessor.getInstance().getDynamicQueryContext());
			} catch (XPathException e) {
				// TODO Bloc catch auto-généré
				e.printStackTrace();
			}

			if (results != null) {
				// Set the value to the element
				Text textValue = newDocument.createTextNode(results.toString());
				myElement.appendChild(textValue);
			}

			// the metric returns a set of elements and value
		} else {
			try {

				SequenceIterator iteratorResults = myMetric.getCompiledQuery()
						.iterator(
								SaxonProcessor.getInstance()
										.getDynamicQueryContext());
				try {
					QueryResult.serializeSequence(iteratorResults,
							SaxonProcessor.getInstance().getConfig(),
							new PrintWriter(new FileOutputStream(new File(
									"temp.xml"))), SaxonProcessor.getInstance()
									.getProperties());
				} catch (FileNotFoundException e1) { // TODO Bloc catch
					// auto-généré
					e1.printStackTrace();
				}

				try {
					// Creates the XML Document
					DocumentBuilder db = documentBuilderFactory
							.newDocumentBuilder();
					// Read the metrics configuration file
					Document doc = db.parse("temp.xml");
					ParseMetricsResulFileConfiguration(doc, myElement);

				} catch (ParserConfigurationException e) {
					// TODO Bloc catch auto-généré
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Bloc catch auto-généré
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Bloc catch auto-généré
					e.printStackTrace();
				}

			} catch (XPathException e) {
				// TODO Bloc catch auto-généré
				e.printStackTrace();
			}
		}
	}

	/**
	 * Get the the value encapsulated by a starting and finishing tag element
	 * 
	 * @param n
	 *            The node which the text value have to be extracted.
	 * @return The extracted sting value.
	 */
	private String getNodeValue(Node n) {
		Node textNode = n.getFirstChild();
		if (textNode != null) {
			return textNode.getNodeValue();
		}

		return "";
	}

	/**
	 * Method that will iterate from a base node trought all the branches of the
	 * XML sub tree.
	 * 
	 * @param n
	 *            Root node from which starts the parsing.
	 * @param childAppender
	 *            Represents the current cursor of the results output file where
	 *            the result data have to be append.
	 */
	private void ParseXMLRecursively(Node n, Element childAppender) {
		// Getting all the child node under the Node n passed as parameter
		NodeList nodeList = n.getChildNodes();
		// Iterating over all the child nodes
		for (int i = 0; i < nodeList.getLength(); i++) {
			// Retrieving the node object
			Node node = nodeList.item(i);
			// Process Node to check value contained in it.
			// If value is a metric ID so the query is processed, also the found
			// value is computed to element
			Element generatedElementFromNode = ProcessNodeParsing(node);

			// The generated element is append to the root element childAppender
			// passed as parameter
			if (generatedElementFromNode != null)
				childAppender.appendChild(generatedElementFromNode);

			// If the iterated node has children, they'll be iterate recursively
			// again
			if (node.hasChildNodes())
				ParseXMLRecursively(node, generatedElementFromNode);
		}
	}

	/**
	 * Creates the output results document
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
			// dump it
			System.out
					.println("Error while trying to instantiate DocumentBuilder "
							+ pce);
			System.exit(1);
		}

	}

	/**
	 * Saving the ouptut data stream to an XML file by serialization.
	 */

	private void printToFile(String pathOutput) {

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
