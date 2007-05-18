package com.codesquale.metrics;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class ProjectGlobalCounters 
{
	
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
		        out.write("\t<counter>\r\n");
		        out.write("\t\t<classes>\r\n");
		        out.write("\t\t<all>"+ numberOfClasses +"</all>\r\n");
		        out.write("\t\t<public>"+ numberOfPublicClasses +"</public>\r\n");
		        out.write("\t\t<private>"+ numberOfPrivateClasses +"</private>\r\n");
		        out.write("\t\t</classes>\r\n");
		        out.write("\t</counter>\r\n");
		        out.write("</projectResults>\r\n"); 
		        
		        out.flush();  // Don't forget to flush!
		        out.close();
		      }
		      catch (UnsupportedEncodingException e) {
		        System.out.println("This VM does not support the Latin-1 character set.");
		      }
		      catch (IOException e) {
		        System.out.println(e.getMessage());        
		      }
	}
	
	
	private int numberOfClasses=0;
	private int numberOfPrivateClasses=0;
	private int numberOfPublicClasses=0;
	
	private int numberOfMethods=0;
	private int numberOfPublicMethods=0;
	private int numberOfPrivateMethods=0;
	
	private int numberOfAttributes=0;
	private int numberOfPublicAttributes=0;
	private int numberOfPrivateAttributes=0;
	
	private int numberOfInterfaces=0;
	
	
	public void incrementNumberOfClasses(int num) { this.numberOfClasses+=num; }
	public void incrementNumberOfPublicClasses(int num) { this.numberOfPublicClasses+=num; }
	public void incrementNumberOfPrivateClasses(int num) { this.numberOfPrivateClasses+=num; }
	
	public void incrementNumberOfMethods(int num) { this.numberOfMethods+=num; }
	public void incrementNumberOfPublicMethods(int num) { this.numberOfPublicMethods+=num; }
	public void incrementNumberOfPrivateMethods(int num) { this.numberOfPrivateMethods+=num; }

	public void incrementNumberOfAttributes(int num) { this.numberOfAttributes+=num; }
	public void incrementNumberOfPublicAttributes(int num) { this.numberOfPublicAttributes+=num; }
	public void incrementNumberOfPrivateAttributes(int num) { this.numberOfPrivateAttributes+=num; }
	
	public void incrementNumberOfInterfaces(int num) { this.numberOfInterfaces+=num; }


}
