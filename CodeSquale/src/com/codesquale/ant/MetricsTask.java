package com.codesquale.ant;

import java.io.File;
import java.util.Iterator;
import java.util.Vector;


import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;

import com.codesquale.metrics.MetricsCollection;
import com.codesquale.metrics.MetricsResultFileBuilder;

public class MetricsTask extends Task {

	private String outputDir;

	private Vector filesets = new Vector();

	private MetricsCollection myMetricsCollection = null;

	private MetricsResultFileBuilder myMetricsResultFileBuilder = null;

	// TODO : Remove the queryFile attribute from this class and from the
	// antTask.xml
	private String queryFile;


	public MetricsTask() {
		String myConfigurationPath = "XQuery/MetricsCollection.xml";
		String myResultFileTemplatePath = "XQuery/MetricsResultFileConfiguration.xml";

		// Init the metrics collection reader
		myMetricsCollection = new MetricsCollection();
		myMetricsCollection.readAvailableMetricsCollection(myConfigurationPath);

		// Init the result builder
		myMetricsResultFileBuilder = new MetricsResultFileBuilder(
				myMetricsCollection.getCollection(), myResultFileTemplatePath);
	}

	public void addFileset(FileSet fileset) {
		filesets.add(fileset);
	}


    public void execute() {
   
    	// Ant Task Format validator
    	 validate();         
    	 
    	 // Fileset Manager
         String foundLocation = null;
         for(Iterator itFSets = filesets.iterator(); itFSets.hasNext();) {      // 2
             FileSet fs = (FileSet)itFSets.next();
             DirectoryScanner ds = fs.getDirectoryScanner(getProject());         // 3
             String[] includedFiles = ds.getIncludedFiles();
             for(int i=0; i<includedFiles.length; i++) {
                 String filename = includedFiles[i].replace('\\','/');           // 4
                 filename = filename.substring(filename.lastIndexOf("/")+1);

                     File base  = ds.getBasedir();                               // 5
                     File found = new File(base, includedFiles[i]);
                     foundLocation = found.getAbsolutePath();
                     
                     myMetricsResultFileBuilder.buildMetricsResultFile(foundLocation, outputDir+found.getName());	
             }
         }
         
         //ProjectGlobalCounters.getInstance().SerializeProjectsResult(outputDir+"\\project.xml");
    }
    protected void validate() {
        if (filesets.size()<1) throw new BuildException("fileset not set");
        if(outputDir == null || outputDir.equals("")) throw new BuildException("outputdir not set");
    }


	public Vector getFilesets() {
		return filesets;
	}

	public void setFilesets(Vector filesets) {
		this.filesets = filesets;
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}

	public String getQueryFile() {
		return queryFile;
	}

	public void setQueryFile(String queryFile) {
		this.queryFile = queryFile;
	}
}
