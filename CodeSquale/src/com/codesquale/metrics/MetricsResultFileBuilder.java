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
	 * Represents the XML template results file.
	 */
	private Document templateResultFile;

	/**
	 * Represents the generated results file.
	 */
	private MetricsResultFile theResultFile;

	/**
	 * Needed to build the results output file.
	 */
	private DocumentBuilderFactory documentBuilderFactory;

	/**
	 * Available collection of metrics.
	 */
	private Hashtable<String, Metric> metricsHashTable;

	/**
	 * Path of the template results file.
	 */
	private String resultFileConfigurationPath;

	/**
	 * This constructor sets the metrics collection available to build the
	 * results.
	 * 
	 * @param aMetricsHashTable
	 *            Set the metrics collection available
	 * @param aResultFileConfigurationPath
	 *            That is the path to the template results file
	 */
	public MetricsResultFileBuilder(
			final Hashtable<String, Metric> aMetricsHashTable,
			final String aResultFileConfigurationPath) {

		// Set the entire collection metrics
		this.metricsHashTable = aMetricsHashTable;
		// Set the path of the template result file
		this.resultFileConfigurationPath = aResultFileConfigurationPath;

		try {
			documentBuilderFactory = DocumentBuilderFactory.newInstance();
			// Creates Document Builder needed to parse the desired XML file
			DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();
			// Read the metrics configuration file
			templateResultFile = db.parse(resultFileConfigurationPath);
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
	 * This method parses the configuration file needed to generate the result
	 * file.
	 * 
	 * @param inputPathFile
	 *            full path to the configuration file
	 * @param outputFileFullPath
	 *            full path of the destination result file
	 */
	public final void buildMetricsResultFile(final String inputPathFile,
			final String outputFileFullPath) {

		theResultFile = new MetricsResultFile();

		// Specify the file to analyze
		SaxonProcessor.getInstance().setXMLSourceDocument(inputPathFile);

		// Starts the parsing process
		parseResultFileConfiguration(templateResultFile, null);

		// Generating the results file filled with the query value
		theResultFile.writeToFile(outputFileFullPath);
	}

	/**
	 * Process the entire content of the XML document with recursivity and call
	 * the adapted method when recognizing ID metrics tag.
	 * 
	 * @param documentToRead
	 *            It is the XML content to parses and interpret
	 * @param elementToWrite
	 *            It is the element where the results elements generated have to
	 *            be append
	 */
	private void parseResultFileConfiguration(final Document documentToRead,
			final Element elementToWrite) {

		// This element is used as the node appender further in the process
		Element mainAppender;

		// Getting the root element of the XML section or document to read
		// passed as parameter
		Element sourceDocumentRootElement = documentToRead.getDocumentElement();
		// Getting the tag name of this root element
		String rootElementName = sourceDocumentRootElement.getNodeName();

		// Creating a parent node into the results file generated
		Element newDocumentRootElement = theResultFile
				.createElement(rootElementName);

		// null element passed in parameter means that we have to append the
		// root element as root element to the result file metrics
		if (elementToWrite == null) {
			// Appending the root node the destination file
			theResultFile.appendChild(newDocumentRootElement);
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
				Element generatedElementFromNode = processNodeParsing(childNode);
				// Appending the new element to our document
				if (generatedElementFromNode != null) {
					mainAppender.appendChild(generatedElementFromNode);
				}
				// Continue to iterate over the xml tree
				if (childNode.hasChildNodes()) {
					parseXMLRecursively(childNode, generatedElementFromNode);
				}
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
	private Element processNodeParsing(final Node childNode) {

		Element myElement = null;
		String nodeValue = getNodeValue(childNode);
		String nodeName = childNode.getNodeName();

		// Verifying that the node is an XML element
		// Veryfing that text node value isn't empty
		if (childNode.getNodeType() == Node.ELEMENT_NODE
				&& !nodeValue.equals("")) {
			// Creating the correspondant Element
			myElement = theResultFile.createElement(nodeName);
			// Fetch the associated Metric in the hashtable
			Metric myMetric = metricsHashTable.get(nodeValue);
			// Verifying that the metric exists
			if (myMetric != null) {
				appendMetricsResultsToElement(myMetric, myElement);
				// Appending the node value and don't calculate any metrics
			} else {
				Text textValue = theResultFile.createTextNode(nodeValue);
				myElement.appendChild(textValue);
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
	private void appendMetricsResultsToElement(final Metric myMetric,
			final Element myElement) {

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
				Text textValue = theResultFile.createTextNode(results
						.toString());
				myElement.appendChild(textValue);
			}

			return;
		}

		if (myMetric.getMetricType().equals("list")) {
			try {

				SequenceIterator iteratorResults = myMetric.getCompiledQuery()
						.iterator(
								SaxonProcessor.getInstance()
										.getDynamicQueryContext());

				QueryResult.serializeSequence(iteratorResults, SaxonProcessor
						.getInstance().getConfig(), new PrintWriter(
						new FileOutputStream(new File("temp.xml"))),
						SaxonProcessor.getInstance().getProperties());

				// Creates the XML Document
				DocumentBuilder db = documentBuilderFactory
						.newDocumentBuilder();
				// Read the metrics configuration file
				Document doc = db.parse("temp.xml");

				Element rootElement = doc.getDocumentElement();

				// Starting the parsing process
				if (rootElement != null && rootElement.hasChildNodes()) {
					// Fetching the child nodes
					NodeList nodeList = rootElement.getChildNodes();
					int cpt = 0;
					// Stepping trough the child node
					while (nodeList.getLength() > 0
							&& cpt < nodeList.getLength()) {
						Node childNode = nodeList.item(cpt++);
						// Process the node to check his validity and generates
						// the
						// output element
						Element generatedElementFromNode = processNodeParsing(childNode);
						// Appending the new element to our document
						if (generatedElementFromNode != null) {
							myElement.appendChild(generatedElementFromNode);
						}
						// Continue to iterate over the xml tree
						if (childNode.hasChildNodes()) {
							parseXMLRecursively(childNode,
									generatedElementFromNode);
						}
					}
				}

			} catch (FileNotFoundException e1) { // TODO Bloc catch
				// auto-généré
				e1.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Bloc catch auto-généré
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Bloc catch auto-généré
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Bloc catch auto-généré
				e.printStackTrace();
			} catch (XPathException e) {
				// TODO Bloc catch auto-généré
				e.printStackTrace();
			}
			return;
		}
	}

	/**
	 * Get the the value encapsulated by a starting and finishing tag element.
	 * 
	 * @param n
	 *            The node which the text value have to be extracted.
	 * @return The extracted sting value.
	 */
	private String getNodeValue(final Node n) {
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
	 *            Root node from which starts the parsing
	 * @param childAppender
	 *            Represents the current cursor of the results output file where
	 *            the result data have to be append
	 */
	private void parseXMLRecursively(final Node n, final Element childAppender) {
		// Getting all the child node under the Node n passed as parameter
		NodeList nodeList = n.getChildNodes();
		// Iterating over all the child nodes
		for (int i = 0; i < nodeList.getLength(); i++) {
			// Retrieving the node object
			Node node = nodeList.item(i);
			// Process Node to check value contained in it.
			// If value is a metric ID so the query is processed, also the found
			// value is computed to element
			Element generatedElementFromNode = processNodeParsing(node);

			// The generated element is append to the root element childAppender
			// passed as parameter
			if (generatedElementFromNode != null) {
				childAppender.appendChild(generatedElementFromNode);
			}

			// If the iterated node has children, they'll be iterate recursively
			// again
			if (node.hasChildNodes()) {
				parseXMLRecursively(node, generatedElementFromNode);
			}
		}
	}

}
