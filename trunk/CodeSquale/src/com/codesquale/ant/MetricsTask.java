package com.codesquale.ant;

import java.io.File;
import java.util.Iterator;
import java.util.Vector;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;


public class MetricsTask extends Task {
    private String file;
    private String location;
    private Vector filesets = new Vector();

    public void setFile(String file) {
        this.file = file;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void addFileset(FileSet fileset) {
        filesets.add(fileset);
    }

    public void execute() {
    	 validate();                                                             // 1
         String foundLocation = null;
         for(Iterator itFSets = filesets.iterator(); itFSets.hasNext(); ) {      // 2
             FileSet fs = (FileSet)itFSets.next();
             DirectoryScanner ds = fs.getDirectoryScanner(getProject());         // 3
             String[] includedFiles = ds.getIncludedFiles();
             for(int i=0; i<includedFiles.length; i++) {
                 String filename = includedFiles[i].replace('\\','/');           // 4
                 filename = filename.substring(filename.lastIndexOf("/")+1);
                 if (foundLocation==null && file.equals(filename)) {
                     File base  = ds.getBasedir();                               // 5
                     File found = new File(base, includedFiles[i]);
                     foundLocation = found.getAbsolutePath();
                     // TODO method metric
                 }
             }
         }	
    }
    protected void validate() {
        if (file==null) throw new BuildException("file not set");
        if (location==null) throw new BuildException("location not set");
        if (filesets.size()<1) throw new BuildException("fileset not set");
    } 
}
