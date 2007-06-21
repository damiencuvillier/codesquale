package com.codesquale.metrics;

import java.io.IOException;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.saxon.query.XQueryExpression;
import net.sf.saxon.trans.XPathException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.codesquale.utils.Utilities;

/**
 * Represents the collection of metrics proposed by CodeSquale.
 * 
 * @author dwillier
 * 
 */
public class MetricsCollection {

	/**
	 * Hashtable representating the metrics collection. The key is identified by
	 * the metric short name and the associated value is the metric object.
	 */
	private Hashtable<String, Metric> metricsCollection;

	/**
	 * Returns the metrics collections.
	 * 
	 * @return Hashtable of the metrics colelction
	 */
	public final Hashtable<String, Metric> getCollection() {
		return metricsCollection;
	}

	/**
	 * Constructor by default. Initializes the metrics collection.
	 */
	public MetricsCollection() {
		metricsCollection = new Hashtable<String, Metric>();
	}

	/**
	 * Initializes the parsing of the metrics collectin file.
	 * 
	 * @param metricsFileDescriptorPath
	 *            Path of the file containing the metrics collection.
	 */
	public final void readAvailableMetricsCollection(
			final String metricsFileDescriptorPath) {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			// Creates the XML Document
			DocumentBuilder db = dbf.newDocumentBuilder();
			// Read the metrics configuration file
			Document doc = db.parse(metricsFileDescriptorPath);

			parseMetricsCollectionFile(doc);

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
	 * Builds the metrics collection from the collection file.
	 * 
	 * @param doc
	 *            Input file to parse.
	 */
	private void parseMetricsCollectionFile(final Document doc) {
		Element docElement = doc.getDocumentElement();

		NodeList nodeList = docElement.getElementsByTagName("metric");

		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element metricElement = (Element) nodeList.item(i);
				Metric m = getMetricFromElement(metricElement);

				metricsCollection.put(m.getMetricShortName(), m);
			}
		}

	}

	/**
	 * Construct an object Metric from a valid metric XML element.
	 * 
	 * @param e
	 *            Metric element read from the metrics collection file
	 * @return A metric object instancied with the values contained in the
	 *         element
	 */
	private Metric getMetricFromElement(final Element e) {
		String metricLongName = getTextValue(e, "metricLongName");
		String metricShortName = getTextValue(e, "metricShortName");
		String metricDecription = getTextValue(e, "metricDescription");
		String associatedXqueryPath = getTextValue(e, "associatedXQueryPath");
		String enabled = getTextValue(e, "metricEnabled");
		String type = getTextValue(e, "returnValueType");
		String populated = getTextValue(e, "populationLevel");
		boolean metricEnabled = Boolean.parseBoolean(enabled);
		int populationLevel = Integer.parseInt(populated);
		String literalXQueryExpression = null;

		try {
			literalXQueryExpression = Utilities
					.readFileAsString(associatedXqueryPath);
		} catch (IOException e1) {
			// TODO Bloc catch auto-généré
			e1.printStackTrace();
		}

		XQueryExpression compiledQuery = null;
		try {
			compiledQuery = SaxonProcessor.getInstance().compileXquery(
					literalXQueryExpression);
		} catch (XPathException e1) {
			// TODO Bloc catch auto-généré
			e1.printStackTrace();
		}

		Metric m = new Metric(associatedXqueryPath, metricDecription,
				metricLongName, metricShortName, metricEnabled, type,
				compiledQuery,populationLevel);

		return m;
	}

	/**
	 * I take a xml element and the tag name, look for the tag and get the text
	 * content.
	 * 
	 * @param ele
	 *            Element to read
	 * @param tagName
	 *            Element tag name for which value has to be retrieved
	 * @return Read value
	 */
	private String getTextValue(final Element ele, final String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > 0) {
			Element el = (Element) nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}

}
