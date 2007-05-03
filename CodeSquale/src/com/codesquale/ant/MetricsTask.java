package com.codesquale.ant;

import java.io.File;
import java.util.Iterator;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;

import com.codesquale.metrics.MetricsProcessor;


public class MetricsTask extends Task {
	
	private static Logger logger = Logger.getLogger(MetricsTask.class);
	
    private String outputDir;
    private Vector filesets = new Vector();


    public void addFileset(FileSet fileset) {
        filesets.add(fileset);
    }

    public void execute() {
    	logger.info("Launch MetricsProcess Ant Task");
    	 validate();                                                             // 1
         String foundLocation = null;
         for(Iterator itFSets = filesets.iterator(); itFSets.hasNext(); ) {      // 2
             FileSet fs = (FileSet)itFSets.next();
             DirectoryScanner ds = fs.getDirectoryScanner(getProject());         // 3
             String[] includedFiles = ds.getIncludedFiles();
             for(int i=0; i<includedFiles.length; i++) {
                 String filename = includedFiles[i].replace('\\','/');           // 4
                 filename = filename.substring(filename.lastIndexOf("/")+1);
//                 if (foundLocation==null) {
                     File base  = ds.getBasedir();                               // 5
                     File found = new File(base, includedFiles[i]);

                     foundLocation = found.getAbsolutePath();
                     MetricsProcessor processor = new MetricsProcessor();
                     processor.generateResultFile(foundLocation, outputDir+found.getName());
//                 }
             }
         }	
    }
    protected void validate() {
        if (filesets.size()<1) throw new BuildException("fileset not set");
        if(outputDir == null || outputDir.equals("")) throw new BuildException("outputdir not set");
        logger.debug("Metrics Task is valid");
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
	
}
