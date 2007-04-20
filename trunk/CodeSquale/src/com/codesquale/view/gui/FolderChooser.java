package com.codesquale.view.gui;

/**
 * Create a FolderChooser 
 * @param folder contain the folder selected
 * @author RBITTEL
 *
 */

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FolderChooser {
	private String folder;
	
	public FolderChooser()
	{
		// Instantiate a File chooser
		JFileChooser choix = new JFileChooser();
		// Set to folder only
		choix.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int retour = choix.showOpenDialog(new JFrame());
		if(retour == JFileChooser.APPROVE_OPTION) {
			this.folder = choix.getSelectedFile().getAbsolutePath();
		} else {
			this.folder = ""; 
		}
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}
	
}
