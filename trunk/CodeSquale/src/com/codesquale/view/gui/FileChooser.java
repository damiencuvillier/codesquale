package com.codesquale.view.gui;


import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileChooser {
	private String file;
	
	public FileChooser()
	{
		
		
		JFileChooser chooser = new JFileChooser();
	    // Note: source for ExampleFileFilter can be found in FileChooserDemo,
	    // under the demo/jfc directory in the Java 2 SDK, Standard Edition.
		CSFilter filter = new CSFilter();
	    filter.addExtension("xml");
	    chooser.setMultiSelectionEnabled(false);
	    filter.setDescription("xml file");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(new JFrame());
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	String ext = chooser.getSelectedFile().getName();
	    	if(!ext.contains(".")) ext =chooser.getSelectedFile().getAbsolutePath()+".xml";
	    	else ext =chooser.getSelectedFile().getAbsolutePath();
	    	file = ext;
	    }else{
	    	file = "";   
	    }
	    
	}
//	

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
}
