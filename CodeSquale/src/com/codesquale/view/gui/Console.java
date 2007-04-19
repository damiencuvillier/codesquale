package com.codesquale.view.gui;

/**
 * GUI to get the source folder and the destination's XML file
 * and run the CodeSquale process
 * @author rbittel
 */

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.WriterAppender;


public class Console extends JFrame {
	
	private JTextField sourceFolder;
	private JTextField targetFolder;
	private JTextArea consoleOut;
	private JList level;
	private JButton submit;
	private JButton folder ;
	private JButton file ;
	private Logger root;

	// Retrieve logger log4j in log4j.XML
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Console.class);

	/**
	 * Launch GUI
	 * 
	 */
	
	public Console() {

	// Initialisation of swing componement	
		setTitle("Console");
		setSize(500, 500);
		setLayout(new GridLayout(2, 1));
		JPanel param = new JPanel();
		param.setLayout(new GridLayout(5, 2));
		param.setSize(150, 500);
		
	// SWING Console pannel	
		JPanel scrConsole = new JPanel();
		scrConsole.setLayout(new GridLayout(1,1));
		scrConsole.setSize(250, 500);

		sourceFolder 	= new JTextField();
		targetFolder 	= new JTextField();
		consoleOut 		= new JTextArea();
		folder 	= new JButton("Choose the folder source");
		file 	= new JButton("Choose the destination folder ");
		submit 	= new JButton("Run");
		submit.setEnabled(false);
		JScrollPane areaScrollPane = new JScrollPane(consoleOut);		
		
		String[] dataLevel = {"Debug", "Info", "Warning", "Error", "Fatal"};
		level		= new JList(dataLevel);
		level.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		level.setSelectedIndex(0);
		JScrollPane levelScrollPane = new JScrollPane(level);
		
	// Setting to put log4j in a JTextArea	
		
		CSOut os = new CSOut(consoleOut);
		System.setOut(new PrintStream(os));
		System.setErr(new PrintStream(os));

		root = Logger.getRootLogger();
		WriterAppender appender = new WriterAppender(new SimpleLayout(), os);
		appender.setName("consoleSwing");
		appender.setImmediateFlush(true);
		root.addAppender(appender);
		root.setPriority(Priority.DEBUG);
		
		
	// Listener for the source folder button	
		folder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sourceFolder.setText(new FolderChooser().getFolder());
				if(!(sourceFolder.getText().equals("")) && !(targetFolder.getText().equals("")))
					submit.setEnabled(true);
			}
		});
		
	// Listener for the destination file button	
		file.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				targetFolder.setText(new FolderChooser().getFolder());
				if(!(sourceFolder.getText().equals("")) && !(targetFolder.getText().equals("")))
					submit.setEnabled(true);
			}
		});

		level.addListSelectionListener(new ListSelectionListener(){

			public void valueChanged(ListSelectionEvent e) {
				// TODO Raccord de m�thode auto-g�n�r�
				switch(level.getSelectedIndex()){
					case 0 : root.setPriority(Priority.DEBUG); break;
					case 1 : root.setPriority(Priority.INFO); break;
					case 2 : root.setPriority(Priority.WARN); break;
					case 3 : root.setPriority(Priority.ERROR); break;
					case 4 : root.setPriority(Priority.FATAL); break;
					default: root.setPriority(Priority.INFO); break;
				}
			}
			
		});
		
	// Listener for the run process button
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				com.codesquale.launcher.Process p =new com.codesquale.launcher.Process(new File(sourceFolder.getText()),new File(targetFolder.getText()));
				p.start();
				submit.setEnabled(false);

			}
		});

	// GUI parameters
		param.add(folder);
		param.add(sourceFolder);
		param.add(file);
		param.add(targetFolder);
		param.add(new JLabel("Log level :"));
		param.add(levelScrollPane);
		param.add(new JLabel("Launch process :"));
		param.add(submit);
		param.add(new JLabel("Console :"));
		
		add(param);
		scrConsole.add(areaScrollPane);
		add(scrConsole);
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				dispose();
				System.exit(0);
			}
		});
		
		setVisible(false);
		setVisible(true);

	}

	public JTextField getSourceFolder() {
		return sourceFolder;
	}

	public void setSourceFolder(JTextField sourceFolder) {
		this.sourceFolder = sourceFolder;
	}

	public JTextField getTargetFolder() {
		return targetFolder;
	}

	public void setTargetFolder(JTextField targetFile) {
		this.targetFolder = targetFile;
	}

}
