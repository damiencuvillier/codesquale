package com.codesquale.view.gui;


import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FolderChooser {
	private String folder;
	
	public FolderChooser()
	{
		JFileChooser choix = new JFileChooser();
		choix.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int retour = choix.showOpenDialog(new JFrame());
		if(retour == JFileChooser.APPROVE_OPTION) {
			this.folder = choix.getSelectedFile().getAbsolutePath();
		} else {
			this.folder = "You must choose a folder!"; 
		}
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}
	
}
