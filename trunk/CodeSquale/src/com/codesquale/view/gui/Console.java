package com.codesquale.view.gui;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.chainsaw.Main;

import com.codesquale.exceptions.NotDirectoryException;
import com.codesquale.file.FileFilter;
import com.codesquale.file.ProjectBrowser;

public class Console extends JFrame{
	private JTextField sourceFolder;
	private JTextField targetFile;
	private JTextArea consoleOut;

	
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Console.class);
		
	
	public Console (){

		
		setTitle("Console");
		setSize(500,500);
		setLayout(new GridLayout(2,1));
		JPanel param = new JPanel();
		param.setLayout(new GridLayout(3,2));
		param.setSize(250,500);
		
		JPanel outConsole = new JPanel();
		outConsole.setLayout(new GridLayout(1,1));
		outConsole.setSize(250,500);
		
		sourceFolder = new JTextField();
		targetFile = new JTextField();
		consoleOut = new JTextArea(20,5);
		JButton folder = new JButton("Choose the folder source");
		JButton file = new JButton("Choose the xml file ");
		JButton submit = new JButton("Run");
		
		
		folder.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				sourceFolder.setText(new FolderChooser().getFolder());
			}
		});
		
		file.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				targetFile.setText(new FileChooser().getFile());
			}
		});
		
		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try 
				{
					
					consoleOut.setText(consoleOut.getText()+"File Filter init\n");
					FileFilter filter = new FileFilter();
					filter.addFileType(FileFilter.JAVA_SOURCEFILE);
					consoleOut.setText(consoleOut.getText()+"Browsing File...\n");
					ProjectBrowser browser = new ProjectBrowser(new File(sourceFolder.getText()),new File(targetFile.getText()),filter);
					browser.ProcessAnalysis();
					browser.ProcessDescription();
					
				} catch (NotDirectoryException e) {
					consoleOut.setText(consoleOut.getText()+"Param is not a valid directory \n");
				}
				consoleOut.setText(consoleOut.getText()+"Done... \n");
				consoleOut.setText(consoleOut.getText()+"Results written in "+targetFile.getText()+"\n");
				
				
			}
		});
		
	
		
		param.add(folder);
		param.add(sourceFolder);
		param.add(file);
		param.add(targetFile);
		param.add(submit);
		add(param);
		outConsole.add(consoleOut);
		add(outConsole);
		setVisible(false);
		show();
		
	}


	public JTextField getSourceFolder() {
		return sourceFolder;
	}


	public void setSourceFolder(JTextField sourceFolder) {
		this.sourceFolder = sourceFolder;
	}


	public JTextField getTargetFile() {
		return targetFile;
	}


	public void setTargetFile(JTextField targetFile) {
		this.targetFile = targetFile;
	}
	
	
}
