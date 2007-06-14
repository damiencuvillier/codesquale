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


public class MetricsCollection {

	Hashtable<String,Metric> metricsCollection;

	public Hashtable<String,Metric> getCollection()
	{
		return metricsCollection;
	}
	
	public MetricsCollection() {
		metricsCollection = new Hashtable<String,Metric>();
	}
	
	/**
	 * Initializes the parsing of the metrics collectin file
	 * @param metricsFileDescriptorPath
	 */
	public void ReadAvailableMetricsCollection(String metricsFileDescriptorPath) {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			// Creates the XML Document
			DocumentBuilder db = dbf.newDocumentBuilder();
			// Read the metrics configuration file
			Document doc = db.parse(metricsFileDescriptorPath);

			ParseMetricsCollectionFile(doc);

		} catch (ParserConfigurationException e) {
			// TODO Bloc catch auto-g�n�r�
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Bloc catch auto-g�n�r�
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Bloc catch auto-g�n�r�
			e.printStackTrace();
		}

	}

	/**
	 * Builds the metrics collection from the collection file.
	 * @param doc
	 */
	private void ParseMetricsCollectionFile(Document doc)
	{
		Element docElement = doc.getDocumentElement();
		
		NodeList nodeList = docElement.getElementsByTagName("metric");
		
		if(nodeList!= null && nodeList.getLength()>0)
		{
			for(int i=0;i<nodeList.getLength();i++)
			{
				Element metricElement = (Element)nodeList.item(i);
				Metric m = GetMetricFromElement(metricElement);
				
				metricsCollection.put(m.getMetricShortName(), m);
			}
		}
		
	}

	/**
	 * Construct an object Metric from a valid metric XML element
	 * @param e
	 * @return
	 */
	private Metric GetMetricFromElement(Element e) {
		String metricLongName = getTextValue(e, "metricLongName");
		String metricShortName = getTextValue(e, "metricShortName");
		String metricDecription = getTextValue(e, "metricDescription");
		String associatedXqueryPath = getTextValue(e, "associatedXQueryPath");
		String enabled = getTextValue(e, "metricEnabled");
		String type = getTextValue(e,"returnValueType");
		boolean metricEnabled = Boolean.parseBoolean(enabled);

		
		String literalXQueryExpression=null;
		
		try {
			literalXQueryExpression = Utilities.readFileAsString(associatedXqueryPath);
		} catch (IOException e1) {
			// TODO Bloc catch auto-g�n�r�
			e1.printStackTrace();
		}
		
		XQueryExpression compiledQuery=null;
		try {
			compiledQuery = SaxonProcessor.getInstance().compileXquery(literalXQueryExpression);
		} catch (XPathException e1) {
			// TODO Bloc catch auto-g�n�r�
			e1.printStackTrace();
		}
		
		Metric m = new Metric(associatedXqueryPath, metricDecription,
				metricLongName, metricShortName, metricEnabled,type,compiledQuery);

		return m;
	}

	/**
	 * I take a xml element and the tag name, look for the tag and get the text
	 * content
	 */
	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > 0) {
			Element el = (Element) nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}

}
