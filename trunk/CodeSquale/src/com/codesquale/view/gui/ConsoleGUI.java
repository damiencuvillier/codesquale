package com.codesquale.view.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.io.File;
import java.io.PrintStream;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.WriterAppender;

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

	private JButton btn_browseInput = null;

	private JButton btn_browseOutput = null;

	private JTextArea field_console = null;

	private JButton btn_ok = null;

	private JButton btn_cancel = null;

	private Logger rootLogger;
	private com.codesquale.launcher.Process process ;
	/**
	 * This is the default constructor
	 */
	public ConsoleGUI() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(new Rectangle(100, 100, 867, 642));
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("CodeSquale");
		this.setVisible(true);
		rootLogger = Logger.getRootLogger();
		// Setting standard out to a JTextArea	
		CSOut os = new CSOut(field_console);
		System.setOut(new PrintStream(os));
		System.setErr(new PrintStream(os));
//		 Setting to put log4j in a JTextArea	
		WriterAppender appender = new WriterAppender(new SimpleLayout(), os);
		appender.setName("consoleSwing");
		appender.setImmediateFlush(true);
		rootLogger.addAppender(appender);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
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
	 */
	private JPanel getConsole() {
		if (ctn_console == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.gridx = 0;
			ctn_console = new JPanel();
			ctn_console.setLayout(new GridBagLayout());
			ctn_console.setBounds(new Rectangle(0, 56, 860, 551));
			ctn_console.add(getConsoleTextArea(), gridBagConstraints);
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
		if(!field_inputPath.getText().equals("")&&!field_outputPath.getText().equals("")) btn_ok.setEnabled(true);
	}
	/**
	 * This method initializes browseInput	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBrowseInput() {
		if (btn_browseInput == null) {
			btn_browseInput = new JButton();
			btn_browseInput.setBounds(new Rectangle(488, 0, 47, 26));
			btn_browseInput.setText("...");
			btn_browseInput.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					field_inputPath.setText(new FolderChooser().getFolder());
					activeAnalyseButton();
				}
			});
		}
		return btn_browseInput;
	}

	/**
	 * This method initializes browseOutput	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBrowseOutput() {
		if (btn_browseOutput == null) {
			btn_browseOutput = new JButton();
			btn_browseOutput.setBounds(new Rectangle(488, 27, 47, 23));
			btn_browseOutput.setText("...");
			btn_browseOutput.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					field_outputPath.setText(new FolderChooser().getFolder());
					activeAnalyseButton();
				}
			});
		}
		return btn_browseOutput;
	}

	/**
	 * This method initializes consoleTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getConsoleTextArea() {
		if (field_console == null) {
			field_console = new JTextArea();
		}
		return field_console;
	}

	/**
	 * This method initializes OK	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOK() {
		if (btn_ok == null) {
			btn_ok = new JButton();
			btn_ok.setBounds(new Rectangle(575, 3, 87, 21));
			btn_ok.setEnabled(false);
			btn_ok.setText("Analyse");
			btn_ok.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					field_console.setText("");
					process = new com.codesquale.launcher.Process(new File(field_inputPath.getText()),
							new File(field_outputPath.getText()));
					
					process.start();
					
					btn_ok.setEnabled(false);
					
				}
			});
		}
		return btn_ok;
	}

	/**
	 * This method initializes Cancel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCancel() {
		if (btn_cancel == null) {
			btn_cancel = new JButton();
			btn_cancel.setBounds(new Rectangle(576, 29, 86, 22));
			btn_cancel.setEnabled(false);
			btn_cancel.setText("Cancel");
			btn_cancel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					btn_ok.setEnabled(false);
					if(process != null){
						if(process.isAlive()) process.stop();
					}
					field_inputPath.setText("");
					field_outputPath.setText("");
				}
			});
		}
		return btn_cancel;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
