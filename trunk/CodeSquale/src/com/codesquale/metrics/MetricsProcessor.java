import java.io.File;
import java.util.Properties;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;

import net.sf.saxon.Configuration;
import net.sf.saxon.query.DynamicQueryContext;
import net.sf.saxon.query.StaticQueryContext;
import net.sf.saxon.query.XQueryExpression;
import net.sf.saxon.trans.XPathException;


public class MetricsProcessor {
	
	
	private static Logger logger = Logger.getLogger(MetricsProcessor.class);
	
	
	@SuppressWarnings("deprecation")
	public void generateResultFile(String in, String out)
	{
		logger.debug("Entering method GenerateResultFile...");
		
		XQueryExpression exp = null;
		File inputFile = null;
		StreamSource inputStreamSource = null;
		
		// Initialisation du flux d'entrée
		inputFile = new File(in);
		inputStreamSource = new StreamSource(inputFile);
	
		//////////////////////////////////////////
		// Initialisation de l'environnement XQ //
		//////////////////////////////////////////
		// Création de la configuration
		Configuration config = new Configuration();
		// Création du contexte statique
		StaticQueryContext staticContext = new StaticQueryContext(config);
		// Création du contexte statique
		DynamicQueryContext dynamicContext = new DynamicQueryContext(config);
		
		
		try {
			// Compilation de la requête
			exp = staticContext.compileQuery(
							"let $classCount := count(//directory/fileSet/file/classSet/class)" +
							"let $methodCount := count(//directory/fileSet/file/classSet/class/methodSet/method)"+
							"let $attributeCount := count(//directory/fileSet/file/classSet/class/attributeSet/attribute)"+
							"let $interfaceCount := count(//directory/fileSet/file/classSet/class/implementedInterfaceSet/interface)"+
							"let $classes := //directory/fileSet/file/classSet/class \n"+
							"return"+ 
							"<resultFile>"+
							    "<globalCounters>"+
								    "<classCount>{$classCount}</classCount>"+
									"<methodCount>{$methodCount}</methodCount>"+
									"<attributeCount>{$attributeCount}</attributeCount>"+
									"<interfaceCount>{$interfaceCount}</interfaceCount>"+
								"</globalCounters>"+
								"<classResults>"+
								"{"+
									"for $x in $classes \n"+
										"return"+
											"<class name=\"{$x/@name}\">"+
												"<methodCount value=\"{count($x/methodSet/method)}\" />"+
												"<attributeCount value=\"{count($x/attributeSet/attribute)}\" />"+
												"<interfaceCount value=\"{count($x/implementedInterfaceSet/interface)}\" />"+
											"</class>"+
								"}"+
								"</classResults>"+
							"</resultFile>"
						);
	
		} 
		catch (XPathException e) 
		{
			logger.fatal(e.getMessageAndLocation());
		}
		
		
		try 
		{
			// Paramètrage du fichier d'entrée
			dynamicContext.setContextNode(
					config.buildDocument(inputStreamSource));
			
			Properties props = new Properties();
			props.setProperty(OutputKeys.METHOD, "xml");
			props.setProperty(OutputKeys.INDENT, "yes");
			
			exp.run(dynamicContext, new StreamResult(new File(out)), null);
			
		} 
		catch (XPathException e) 
		{
			logger.fatal(e.getMessageAndLocation());
		}
		
		logger.debug("Exiting method GenerateResultFile...");
	}

}
