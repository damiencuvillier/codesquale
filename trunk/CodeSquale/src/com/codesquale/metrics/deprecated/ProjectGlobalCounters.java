package com.codesquale.metrics.deprecated;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

import com.codesquale.file.AbstractElement;

public class ProjectGlobalCounters 
{
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ProjectGlobalCounters.class);
	
	private static ProjectGlobalCounters _instance = null;
	
	private ProjectGlobalCounters() {}
	
	public static ProjectGlobalCounters getInstance()
	{
		if(_instance == null) _instance = new ProjectGlobalCounters();
		
		return _instance;
	}
	
	
	public void SerializeProjectsResult(String outputFile)
	{
		 try {    
			 
		        OutputStream fout= new FileOutputStream(outputFile);
		        OutputStream bout= new BufferedOutputStream(fout);
		        OutputStreamWriter out = new OutputStreamWriter(bout, "8859_1");
		      
		        out.write("<?xml version=\"1.0\" ");
		        out.write("encoding=\"ISO-8859-1\"?>\r\n");  
		        out.write("<projectResults>\r\n");  
		        out.write("\t<projectGlobalMetrics>\r\n");
		        out.write("\t\t<files>"+ numberOfFiles +"</files>\r\n");
		        out.write("\t\t<size unit=\"kB\">"+ projectSize +"</size>\r\n");
		        out.write("\t\t<linesOfCode>\r\n");
		        out.write("\t\t\t<toli>"+numberOfToli+"</toli>\r\n");
		        out.write("\t\t\t<ploc>"+numberOfPloc+"</ploc>\r\n");
		        out.write("\t\t\t<blli>"+numberOfBlli+"</blli>\r\n");
		        out.write("\t\t</linesOfCode>\r\n");
		        out.write("\t\t<classes>\r\n");
		        out.write("\t\t<all>"+ numberOfClasses +"</all>\r\n");
		        out.write("\t\t<public>"+ numberOfPublicClasses +"</public>\r\n");
		        out.write("\t\t<others>"+ numberOfOtherClasses +"</others>\r\n");
		        out.write("\t\t</classes>\r\n");
		        out.write("\t\t<methods>\r\n");
		        out.write("\t\t<all>"+ numberOfMethods +"</all>\r\n");
		        out.write("\t\t<public>"+ numberOfPublicMethods +"</public>\r\n");
		        out.write("\t\t<others>"+ numberOfOtherMethods +"</others>\r\n");
		        out.write("\t\t</methods>\r\n");
		        out.write("\t\t<attributes>\r\n");
		        out.write("\t\t<all>"+ numberOfAttributes +"</all>\r\n");
		        out.write("\t\t<public>"+ numberOfPublicAttributes +"</public>\r\n");
		        out.write("\t\t<others>"+ numberOfOtherAttributes +"</others>\r\n");
		        out.write("\t\t</attributes>\r\n");
		        out.write("\t</projectGlobalMetrics>\r\n");
		        
		        if(numberOfClasses != 0)
		        {
		        	// Converting integer to float in order to obtain the decimal precision
		        	float fnumberOfClasses = new Float(numberOfClasses);
		        	
		        	float fnumberOfMethods = Float.valueOf(numberOfMethods);
		            float fnumberOfPublicMethods = Float.valueOf(numberOfPublicMethods);
		        	float fnumberOfOtherMethods = Float.valueOf(numberOfOtherMethods);
		        	
		        	float fnumberOfAttributes = Float.valueOf(numberOfAttributes);
		        	float fnumberOfPublicAttributes = Float.valueOf(numberOfPublicAttributes);
		        	float fnumberOfOtherAttributes = Float.valueOf(numberOfOtherAttributes);
		        	
		        	// Calculating basic Ratios
		        	float averageNumberOfMethodsPerClass =   fnumberOfMethods/fnumberOfClasses;
		        	float averageNumberOfPublicMethodsPerClass =  fnumberOfPublicMethods/fnumberOfClasses;
		        	float averageNumberOfOtherMethodsPerClass =  fnumberOfOtherMethods/fnumberOfClasses;
		        	
		        	float averageNumberOfAttributesPerClass =  fnumberOfAttributes/fnumberOfClasses ;
		        	float averageNumberOfPublicAttributesPerClass = fnumberOfPublicAttributes/fnumberOfClasses;
		        	float averageNumberOfOtherAttributesPerClass = fnumberOfOtherAttributes/fnumberOfClasses;

		        	
		        	DecimalFormat df = new DecimalFormat("#.###");
		        	
		        	out.write("\t<projectGlobalRatios>\r\n");
		        	
		        	out.write("\t\t<averageNumberMethodsPerClass>\r\n");
			        out.write("\t\t\t<all>"+ df.format(averageNumberOfMethodsPerClass) +"</all>\r\n");
			        out.write("\t\t\t<public>"+ df.format(averageNumberOfPublicMethodsPerClass) +"</public>\r\n");
			        out.write("\t\t\t<others>"+ df.format(averageNumberOfOtherMethodsPerClass) +"</others>\r\n");
		        	out.write("\t\t</averageNumberMethodsPerClass>\r\n");
		        	
		        	
		        	out.write("\t\t<averageNumberAttributesPerClass>\r\n");
			        out.write("\t\t\t<all>"+ df.format(averageNumberOfAttributesPerClass) +"</all>\r\n");
			        out.write("\t\t\t<public>"+ df.format(averageNumberOfPublicAttributesPerClass) +"</public>\r\n");
			        out.write("\t\t\t<others>"+ df.format(averageNumberOfOtherAttributesPerClass) +"</others>\r\n");
		        	out.write("\t\t</averageNumberAttributesPerClass>\r\n");
		        	
		        	out.write("\t</projectGlobalRatios>\r\n");
		        }
		        
		        
		        out.write("</projectResults>\r\n"); 
		        
		        out.flush();  // Don't forget to flush!
		        out.close();
		      }
		      catch (UnsupportedEncodingException e) {
		        logger.fatal("This VM does not support the Latin-1 character set.");
		      }
		      catch (IOException e) {
		       logger.fatal(e.getMessage());        
		      }
	}
	
	
	private int numberOfClasses=0;
	private int numberOfOtherClasses=0;
	private int numberOfPublicClasses=0;
	
	private int numberOfMethods=0;
	private int numberOfPublicMethods=0;
	private int numberOfOtherMethods=0;
	
	private int numberOfAttributes=0;
	private int numberOfPublicAttributes=0;
	private int numberOfOtherAttributes=0;
	
	private int numberOfInterfaces=0;
	
	private int numberOfFiles=0;
	
	private int numberOfToli=0;
	private int numberOfPloc=0;
	private int numberOfBlli=0;
	
	private double projectSize=0;
	
	public void incrementNumberOfClasses(int num) { this.numberOfClasses+=num; }
	public void incrementNumberOfPublicClasses(int num) { this.numberOfPublicClasses+=num; }
	public void incrementNumberOfOtherClasses(int num) { this.numberOfOtherClasses+=num; }
	
	public void incrementNumberOfMethods(int num) { this.numberOfMethods+=num; }
	public void incrementNumberOfPublicMethods(int num) { this.numberOfPublicMethods+=num; }
	public void incrementNumberOfOtherMethods(int num) { this.numberOfOtherMethods+=num; }

	public void incrementNumberOfAttributes(int num) { this.numberOfAttributes+=num; }
	public void incrementNumberOfPublicAttributes(int num) { this.numberOfPublicAttributes+=num; }
	public void incrementNumberOfOtherAttributes(int num) { this.numberOfOtherAttributes+=num; }
	
	public void incrementNumberOfInterfaces(int num) { this.numberOfInterfaces+=num; }
	
	public void incrementNumberOfFiles(int num) {this.numberOfFiles+=num;}
	
	public void incrementNumberOfToli(int num) {this.numberOfToli+=num;}
	public void incrementNumberOfPloc(int num) {this.numberOfPloc+=num;}
	public void incrementNumberOfBlli(int num) {this.numberOfBlli+=num;}
	
	public void incrementProjectSize(double num){this.projectSize +=num;}

}
