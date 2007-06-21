package com.codesquale.view.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class ConsoleGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel ctn_contentPane = null;

	private JPanel ctn_formPanel = null;

	private JPanel ctn_form = null;

	private JPanel ctn_console = null;

	private JTextField field_inputPath = null;

	private JTextField field_outputPath = null;

	private JLabel lbl_input = null;

	private JLabel lbl_output = null;

	private JButton browseInputButton = null;

	private JButton browseOutputButton = null;

	private JButton analyseButton = null;

	private JButton cancelButton = null;

	private com.codesquale.launcher.Process process ;

	private JPanel jPanel = null;

	private JTabbedPane consoleTabPanel = null;

	private JScrollPane mainConsolePanel = null;

	private JScrollPane parsingConsolePanel = null;

	private JScrollPane transformingConsolePanel = null;

	private JScrollPane metricsConsolePanel = null;

	private JScrollPane reportingConsolePanel = null;

	private JScrollPane debugConsolePanel = null;

	private JProgressBar progressBar = null;

	private JPanel progressPanel = null;

	private JTextField transformProgressField = null;

	private JTextField copySourceProgressField = null;

	private JTextField parsingProgressField = null;

	private JTextField metricsProgressField = null;

	private JTextField reportProgressField = null;

	/**
	 * This is the default constructor
	 * @throws IOException 
	 */
	public ConsoleGUI()  {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 * @throws IOException 
	 */
	private void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/codesquale/view/gui/icon.jpg")));
		this.setBounds(new Rectangle(100, 100, 867, 642));
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("CodeSquale");
		this.setVisible(true);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 * @throws IOException 
	 */
	private JPanel getJContentPane()  {
		if (ctn_contentPane == null) {
			ctn_contentPane = new JPanel();
			ctn_contentPane.setLayout(new BorderLayout());
			ctn_contentPane.add(getFormPanel(), BorderLayout.CENTER);
		}
		return ctn_contentPane;
	}

	/**
	 * This method initializes FormPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 * @throws IOException 
	 */
	private JPanel getFormPanel() {
		if (ctn_formPanel == null) {
			lbl_output = new JLabel();
			lbl_output.setText("Where will results will be stored ?");
			lbl_output.setBounds(new Rectangle(0, 26, 197, 26));
			ctn_formPanel = new JPanel();
			ctn_formPanel.setLayout(null);
			ctn_formPanel.add(getForm(), null);
			ctn_formPanel.add(getConsole(), null);
			ctn_formPanel.add(getProgressBar(), null);
			ctn_formPanel.add(getProgressPanel(), null);
		}
		return ctn_formPanel;
	}

	/**
	 * This method initializes form	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getForm() {
		if (ctn_form == null) {
			lbl_input = new JLabel();
			lbl_input.setText("Where are located source files ?");
			lbl_input.setBounds(new Rectangle(0, 0, 197, 26));
			ctn_form = new JPanel();
			ctn_form.setLayout(null);
			ctn_form.setBounds(new Rectangle(5, 2, 666, 52));
			ctn_form.add(lbl_input, null);
			ctn_form.add(getInputPath(), null);
			ctn_form.add(getBrowseInput(), null);
			ctn_form.add(lbl_output, null);
			ctn_form.add(getOutputPath(), null);
			ctn_form.add(getBrowseOutput(), null);
			ctn_form.add(getOK(), null);
			ctn_form.add(getCancel(), null);
		}
		return ctn_form;
	}

	/**
	 * This method initializes console	
	 * 	
	 * @return javax.swing.JPanel	
	 * @throws IOException 
	 */
	private JPanel getConsole() {
		if (ctn_console == null) {
			ctn_console = new JPanel();
			ctn_console.setLayout(null);
			ctn_console.setBounds(new Rectangle(0, 87, 860, 520));
			ctn_console.add(getJPanel(), null);
			ctn_console.add(getConsoleTabPanel(), null);
		}
		return ctn_console;
	}

	/**
	 * This method initializes inputPath	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getInputPath() {
		if (field_inputPath == null) {
			field_inputPath = new JTextField();
			field_inputPath.setBounds(new Rectangle(197, 0, 287, 26));
			field_inputPath.addFocusListener(new java.awt.event.FocusListener() {
				public void focusLost(java.awt.event.FocusEvent e) {
					activeAnalyseButton();
				}
				public void focusGained(java.awt.event.FocusEvent e) {
				}
			});
		}
		return field_inputPath;
	}

	/**
	 * This method initializes outputPath	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getOutputPath() {
		if (field_outputPath == null) {
			field_outputPath = new JTextField();
			field_outputPath.setBounds(new Rectangle(197, 26, 287, 26));
			field_outputPath.addFocusListener(new java.awt.event.FocusListener() {
				public void focusLost(java.awt.event.FocusEvent e) {
					activeAnalyseButton();
				}
				public void focusGained(java.awt.event.FocusEvent e) {
				}
			});
		}
		return field_outputPath;
	}

	public void activeAnalyseButton(){
		if(!field_inputPath.getText().equals("")&&!field_outputPath.getText().equals("")) analyseButton.setEnabled(true);
	}
	/**
	 * This method initializes browseInput	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBrowseInput() {
		if (browseInputButton == null) {
			browseInputButton = new JButton();
			browseInputButton.setBounds(new Rectangle(488, 0, 47, 26));
			browseInputButton.setText("...");
			browseInputButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					field_inputPath.setText(new FolderChooser().getFolder());
					activeAnalyseButton();
				}
			});
		}
		return browseInputButton;
	}

	/**
	 * This method initializes browseOutput	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBrowseOutput() {
		if (browseOutputButton == null) {
			browseOutputButton = new JButton();
			browseOutputButton.setBounds(new Rectangle(488, 27, 47, 23));
			browseOutputButton.setText("...");
			browseOutputButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					field_outputPath.setText(new FolderChooser().getFolder());
					activeAnalyseButton();
				}
			});
		}
		return browseOutputButton;
	}

	/**
	 * This method initializes OK	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOK() {
		if (analyseButton == null) {
			analyseButton = new JButton();
			analyseButton.setBounds(new Rectangle(575, 3, 87, 21));
			analyseButton.setEnabled(false);
			analyseButton.setText("Analyse");
			analyseButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					analyseButton.setEnabled(false);
					cancelButton.setEnabled(true);
					
//					field_console.setText("");
					File input = new File(field_inputPath.getText());
					File output = new File(field_outputPath.getText());
					
//					field_console.setText(field_inputPath.getText()+"\n"+field_inputPath);
					
					if(input.exists()&& input.isDirectory())
					{
						process = new com.codesquale.launcher.Process(input,output);
						
						process.start();
						
						analyseButton.setEnabled(false);
					}
					
				}
			});
		}
		return analyseButton;
	}

	/**
	 * This method initializes Cancel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCancel() {
		// FIXME Thread cancel is not efficient
		if (cancelButton == null) {
			cancelButton = new JButton();
			cancelButton.setBounds(new Rectangle(576, 29, 86, 22));
			cancelButton.setEnabled(false);
			cancelButton.setText("Cancel");
			cancelButton.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					analyseButton.setEnabled(false);
					if(process != null){
						if(process.isAlive()) process.stop();
					}
					field_inputPath.setText("");
					field_outputPath.setText("");
				}
			});
		}
		return cancelButton;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.setBounds(new Rectangle(5, 556, 10, 10));
		}
		return jPanel;
	}

	/**
	 * This method initializes consoleTabPanel	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 * @throws IOException 
	 */
	private JTabbedPane getConsoleTabPanel(){
		if (consoleTabPanel == null) {
			consoleTabPanel = new JTabbedPane();
			consoleTabPanel.setBounds(new Rectangle(3, 0, 853, 400));
			consoleTabPanel.addTab("Global Process", null, getMainConsolePanel(), null);
			consoleTabPanel.addTab("Parsing Console", null, getParsingConsolePanel(), null);
			consoleTabPanel.addTab("Transform Console", null, getTransformingConsolePanel(), null);
			consoleTabPanel.addTab("Metrics Console", null, getMetricsConsolePanel(), null);
			consoleTabPanel.addTab("Report Console", null, getReportingConsolePanel(), null);
			consoleTabPanel.addTab("Debug Console", null, getDebugConsolePanel(), null);
		}
		return consoleTabPanel;
	}

	/**
	 * This method initializes mainConsolePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 * @throws IOException 
	 */
	private JScrollPane getMainConsolePanel() {
		if (mainConsolePanel == null) {
			mainConsolePanel = new ConsoleSocketListenerPanel(22020);
		}
		return mainConsolePanel;
	}

	/**
	 * This method initializes parsingConsolePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JScrollPane getParsingConsolePanel() {
		if (parsingConsolePanel == null) {
			parsingConsolePanel = new ConsoleSocketListenerPanel(22021);
		}
		return parsingConsolePanel;
	}

	/**
	 * This method initializes transformingConsolePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 * @throws IOException 
	 */
	private JScrollPane getTransformingConsolePanel()  {
		if (transformingConsolePanel == null) {
			transformingConsolePanel = new ConsoleSocketListenerPanel(22022);
		}
		return transformingConsolePanel;
	}

	/**
	 * This method initializes metricsConsolePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JScrollPane getMetricsConsolePanel() {
		if (metricsConsolePanel == null) {
			metricsConsolePanel = new ConsoleSocketListenerPanel(22023);
		}
		return metricsConsolePanel;
	}

	/**
	 * This method initializes reportingConsolePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JScrollPane getReportingConsolePanel() {
		if (reportingConsolePanel == null) {
			reportingConsolePanel = new ConsoleSocketListenerPanel(22024);
		}
		return reportingConsolePanel;
	}

	/** This method initializes debugConsolePanel.	
	 * @return javax.swing.JPanel	
	 */
	private JScrollPane getDebugConsolePanel() {
		if (debugConsolePanel == null) {
			debugConsolePanel = new ConsoleSocketListenerPanel(22025);
		}
		return debugConsolePanel;
	}

	/** This method initializes progressBar.	
	 * @return javax.swing.JProgressBar	
	 */
	private JProgressBar getProgressBar() {
		if (progressBar == null) {
			progressBar = new JProgressBar();
			progressBar.setBounds(new Rectangle(347, 69, 328, 13));
		}
		return progressBar;
	}

	/**
	 * This method initializes progressPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getProgressPanel() {
		if (progressPanel == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(5);
			gridLayout.setColumns(1);
			progressPanel = new JPanel();
			progressPanel.setLayout(gridLayout);
			progressPanel.setBounds(new Rectangle(676, 6, 177, 77));
			progressPanel.add(getCopySourceProgressField(), null);
			progressPanel.add(getParsingProgressField(), null);
			progressPanel.add(getTransformProgressField(), null);
			progressPanel.add(getMetricsProgressField(), null);
			progressPanel.add(getReportProgressField(), null);
		}
		return progressPanel;
	}

	/**
	 * This method initializes transformProgressField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTransformProgressField() {
		if (transformProgressField == null) {
			transformProgressField = new JTextField();
			transformProgressField.setEnabled(false);
			transformProgressField.setBackground(Color.red);
			transformProgressField.setText("Transforming");
		}
		return transformProgressField;
	}

	/**
	 * This method initializes copySourceProgressField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getCopySourceProgressField() {
		if (copySourceProgressField == null) {
			copySourceProgressField = new JTextField();
			copySourceProgressField.setBackground(Color.red);
			copySourceProgressField.setText("Copying Sources");
			copySourceProgressField.setEnabled(false);
		}
		return copySourceProgressField;
	}

	/**
	 * This method initializes parsingProgressField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getParsingProgressField() {
		if (parsingProgressField == null) {
			parsingProgressField = new JTextField();
			parsingProgressField.setBackground(Color.red);
			parsingProgressField.setText("Parsing Source Code");
			parsingProgressField.setEnabled(false);
		}
		return parsingProgressField;
	}

	/**
	 * This method initializes metricsProgressField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getMetricsProgressField() {
		if (metricsProgressField == null) {
			metricsProgressField = new JTextField();
			metricsProgressField.setBackground(Color.red);
			metricsProgressField.setText("Calculating Metrics");
			metricsProgressField.setEnabled(false);
		}
		return metricsProgressField;
	}

	/** This method initializes reportProgressField.	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getReportProgressField() {
		if (reportProgressField == null) {
			reportProgressField = new JTextField();
			reportProgressField.setBackground(Color.red);
			reportProgressField.setText("Building report");
			reportProgressField.setEnabled(false);
		}
		return reportProgressField;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
