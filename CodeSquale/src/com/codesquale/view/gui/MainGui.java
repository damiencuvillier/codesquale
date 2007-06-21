package com.codesquale.view.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.apache.tools.ant.util.FileUtils;
import org.jdesktop.jdic.desktop.Desktop;
import org.jdesktop.jdic.desktop.DesktopException;

import com.codesquale.logging.ConsoleArea;
import com.codesquale.utils.ExceptionLevel;
import com.codesquale.utils.ExceptionManager;

public class MainGui extends JFrame {

	private static final int GLOBALPROCESS_SOCKETPORT = 22020 ;
	private static final int PARSINGPROCESS_SOCKETPORT = 22021 ;
	private static final int TRANSFORMPROCESS_SOCKETPORT = 22022 ;
	private static final int METRICSPROCESS_SOCKETPORT = 22023 ;
	private static final int REPORTPROCESS_SOCKETPORT = 22024 ;
	private static final int DEBUG_SOCKETPORT = 22025 ;
	
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel formPanel = null;

	private JPanel progressPanel = null;

	private JRadioButton[] stepRadioButtons = null;


	private JLabel inputDirLabel = null;

	private JLabel outputDirLabel = null;

	private JTextField inputDirField = null;

	private JTextField outputDirField = null;

	private JButton inputBrowseButton = null;

	private JButton outputBrowseButton = null;

	private JButton actionButton = null;

	private JTabbedPane consolesTabbedPane = null;

	private ConsoleArea globalProcessConsoleArea = null;

	/**
	 * This is the default constructor
	 */
	public MainGui() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(800, 600);
		this.setResizable(false);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/codesquale/view/gui/icon.jpg")));
		this.setContentPane(getJContentPane());
		this.setTitle("CodeSquale");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getFormPanel(), null);
			jContentPane.add(getProgressPanel(), null);
			jContentPane.add(getConsolesTabbedPane(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes formPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getFormPanel() {
		if (formPanel == null) {
			outputDirLabel = new JLabel();
			outputDirLabel.setBounds(new Rectangle(5, 25, 100, 15));
			outputDirLabel.setFont(new Font("Arial", Font.PLAIN, 12));
			outputDirLabel.setText("Output Dir :");
			inputDirLabel = new JLabel();
			inputDirLabel.setBounds(new Rectangle(5, 5, 100, 15));
			inputDirLabel.setFont(new Font("Arial", Font.PLAIN, 12));
			inputDirLabel.setText("Input Dir :");
			formPanel = new JPanel();
			formPanel.setLayout(null);
			formPanel.setBounds(new Rectangle(-1, 5, 789, 57));
			formPanel.add(inputDirLabel, null);
			formPanel.add(outputDirLabel, null);
			formPanel.add(getInputDirField(), null);
			formPanel.add(getOutputDirField(), null);
			formPanel.add(getInputBrowseButton(), null);
			formPanel.add(getOutputBrowseButton(), null);
			formPanel.add(getActionButton(), null);
		}
		return formPanel;
	}

	/**
	 * This method initializes progressPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getProgressPanel() {
		if (progressPanel == null) {
			progressPanel = new JPanel();
			progressPanel.setLayout(new GridLayout(1,6));
			progressPanel.setBounds(new Rectangle(0, 63, 788, 24));
			for(int i = 0; i<6;i++){
				progressPanel.add(getStepRadioButtons()[i]);
				
			}
		}
		return progressPanel;
	}

	/**
	 * This method initializes step1RadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton[] getStepRadioButtons() {
		if (stepRadioButtons == null) {
			stepRadioButtons = new JRadioButton[]{
					new JRadioButton("0. Copying Sources"),
					new JRadioButton("1. Parsing Sources"),
					new JRadioButton("2. Generics XML"),
					new JRadioButton("3. Metrics Calculation"),
					new JRadioButton("4. Building Report"),
					new JRadioButton("5. External Tools")
			};
			for(int i = 0; i<6;i++){
				stepRadioButtons[i].setEnabled(false);
				stepRadioButtons[i].setFont(new Font("Arial", Font.PLAIN, 10));
			}
		}
		return stepRadioButtons;
	}


	/**
	 * This method initializes inputDirField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getInputDirField() {
		if (inputDirField == null) {
			inputDirField = new JTextField();
			inputDirField.setBounds(new Rectangle(110, 5, 400, 20));
			inputDirField.setToolTipText("Where is source code located ?");
		}
		return inputDirField;
	}

	/**
	 * This method initializes outputDirField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getOutputDirField() {
		if (outputDirField == null) {
			outputDirField = new JTextField();
			outputDirField.setBounds(new Rectangle(110, 25, 400, 20));
			outputDirField.setToolTipText("Where will results be stored ?");
		}
		return outputDirField;
	}

	/**
	 * This method initializes inputBrowseButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getInputBrowseButton() {
		if (inputBrowseButton == null) {
			inputBrowseButton = new JButton();
			inputBrowseButton.setBounds(new Rectangle(510, 5, 30, 20));
			inputBrowseButton.setText("...");
			inputBrowseButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String path = new FolderChooser().getFolder();
					if(!path.equals("")) inputDirField.setText(path);
				}
			});
		}
		return inputBrowseButton;
	}

	/**
	 * This method initializes outputBrowseButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOutputBrowseButton() {
		if (outputBrowseButton == null) {
			outputBrowseButton = new JButton();
			outputBrowseButton.setBounds(new Rectangle(510, 25, 30, 20));
			outputBrowseButton.setText("...");
			outputDirField.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String path = new FolderChooser().getFolder();
					if(!path.equals("")) outputDirField.setText(path);
				}
			});
		}
		return outputBrowseButton;
	}

	/**
	 * This method initializes actionButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getActionButton() {
		if (actionButton == null) {
			actionButton = new JButton();
			actionButton.setBounds(new Rectangle(592, 15, 155, 25));
			actionButton.setText("Analyse Code");
			actionButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					
					/* Starting CodeSquale Process */
					String inputDir = inputDirField.getText();
					String outputDir = outputDirField.getText();
					if(inputDir.equals("")
							|| outputDir.equals("")){
						/* Checks values are enterd */
						JOptionPane.showMessageDialog(getInstance(), "Please refer to valid path",
							      "Not possible",
							      JOptionPane.WARNING_MESSAGE);
						
					}else{
						actionButton.setText("Processing...");
						actionButton.setEnabled(false);
						com.codesquale.launcher.Process process = 
							new com.codesquale.launcher.
								Process(new File(inputDir),
									new File(outputDir));
						process.start();
						new StatusWatcher().start();
					}
					
					
				}
				
			});
		}
		return actionButton;
	}
	private MainGui getInstance(){
		return this;
	}
	private void launchResults(){
		try {
			Desktop.browse(new URL("file:///"+outputDirField.getText()+"/"+"index.html"));
		} catch (MalformedURLException e) {
			ExceptionManager.aspectManagedException(e, 
					ExceptionLevel.ERROR);
		} catch (DesktopException e) {
			ExceptionManager.aspectManagedException(e, 
					ExceptionLevel.ERROR);
		}
	}

	/**
	 * This method initializes consolesTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getConsolesTabbedPane() {
		if (consolesTabbedPane == null) {
			consolesTabbedPane = new JTabbedPane();
			consolesTabbedPane.setBounds(new Rectangle(0, 86, 793, 481));
			consolesTabbedPane.addTab("CodeSquale Process", null, new ConsoleArea(GLOBALPROCESS_SOCKETPORT), null);
			consolesTabbedPane.addTab("Parsing (1)", null, new ConsoleArea(PARSINGPROCESS_SOCKETPORT), null);
			consolesTabbedPane.addTab("Transforming (2)", null, new ConsoleArea(TRANSFORMPROCESS_SOCKETPORT), null);
			consolesTabbedPane.addTab("Metrics (3)", null, new ConsoleArea(METRICSPROCESS_SOCKETPORT), null);
			consolesTabbedPane.addTab("Report (4)", null, new ConsoleArea(REPORTPROCESS_SOCKETPORT), null);
			consolesTabbedPane.addTab("Debug Console", null, new ConsoleArea(DEBUG_SOCKETPORT), null);
		}
		return consolesTabbedPane;
	}
	/** Watch Status file
	 * @author Damien
	 *
	 */
	class StatusWatcher extends Thread{
		private final static String statusFile = "xml/status";
		private int step = -1 ;
		public void run(){
			try {
				while(true){
					sleep(1000);
					int newStep = -1 ;
					try{
						FileReader reader = new FileReader(statusFile);
						 newStep = reader.read();
					}catch(IOException e){
						ExceptionManager.aspectManagedException(e, ExceptionLevel.WARN);
					}
					if(newStep != step){
						step = newStep;
						showStatus(step);
					}
				}
			} catch (InterruptedException e) {
				ExceptionManager.aspectManagedException(e, ExceptionLevel.DEBUG);
			} finally{
				
				
			}
			
		}
		public synchronized void showStatus(int step) {
			if(step >0){
				getStepRadioButtons()[step-1].setSelected(true);
			}
			if(step < 6){
				getStepRadioButtons()[step].setFont(new Font("Arial", Font.BOLD, 11));
			}else{
				/* At the end, delete status file */
				FileUtils.delete(new File(statusFile));
				launchResults();
				actionButton.setText("CodeSqualing Done");
			}
		}
	}
	
}

