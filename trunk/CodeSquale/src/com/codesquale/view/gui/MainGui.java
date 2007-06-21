package com.codesquale.view.gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.jdesktop.jdic.desktop.Desktop;
import org.jdesktop.jdic.desktop.DesktopException;

import com.codesquale.logging.ConsoleArea;
import com.codesquale.utils.ExceptionLevel;
import com.codesquale.launcher.process;
import com.codesquale.utils.ExceptionManager;

public class MainGui extends JFrame {

	private static final int GLOBALPROCESS_SOCKETPORT = 22020 ;
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel formPanel = null;

	private JPanel progressPanel = null;

	private JRadioButton step1RadioButton = null;

	private JRadioButton step2RadioButton = null;

	private JRadioButton step0RadioButton = null;

	private JRadioButton step3RadioButton = null;

	private JRadioButton step4RadioButton = null;

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
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.insets = new Insets(0, 0, 0, 3);
			gridBagConstraints4.gridy = 0;
			gridBagConstraints4.ipadx = 31;
			gridBagConstraints4.ipady = 3;
			gridBagConstraints4.gridx = 5;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 4;
			gridBagConstraints3.ipadx = 25;
			gridBagConstraints3.ipady = 3;
			gridBagConstraints3.gridy = 0;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 2;
			gridBagConstraints2.ipadx = -34;
			gridBagConstraints2.ipady = 3;
			gridBagConstraints2.weightx = 0.0D;
			gridBagConstraints2.gridwidth = 2;
			gridBagConstraints2.gridy = 0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 1;
			gridBagConstraints1.ipadx = 38;
			gridBagConstraints1.ipady = 3;
			gridBagConstraints1.gridy = 0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.ipadx = 37;
			gridBagConstraints.ipady = 3;
			gridBagConstraints.gridy = 0;
			progressPanel = new JPanel();
			progressPanel.setLayout(new GridBagLayout());
			progressPanel.setBounds(new Rectangle(0, 63, 788, 24));
			progressPanel.add(getStep0RadioButton(), gridBagConstraints);
			progressPanel.add(getStep1RadioButton(), gridBagConstraints1);
			progressPanel.add(getStep2RadioButton(), gridBagConstraints2);
			progressPanel.add(getStep3RadioButton(), gridBagConstraints3);
			progressPanel.add(getStep4RadioButton(), gridBagConstraints4);
		}
		return progressPanel;
	}

	/**
	 * This method initializes step1RadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getStep1RadioButton() {
		if (step1RadioButton == null) {
			step1RadioButton = new JRadioButton();
			step1RadioButton.setText("1. Parsing Sources");
			step1RadioButton.setEnabled(false);
			step1RadioButton.setFont(new Font("Arial", Font.BOLD, 10));
		}
		return step1RadioButton;
	}

	/**
	 * This method initializes step2RadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getStep2RadioButton() {
		if (step2RadioButton == null) {
			step2RadioButton = new JRadioButton();
			step2RadioButton.setFont(new Font("Arial", Font.BOLD, 10));
			step2RadioButton.setEnabled(false);
			step2RadioButton.setText("2. Transform to generic XML Data ");
		}
		return step2RadioButton;
	}

	/**
	 * This method initializes step0RadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getStep0RadioButton() {
		if (step0RadioButton == null) {
			step0RadioButton = new JRadioButton();
			step0RadioButton.setFont(new Font("Arial", Font.BOLD, 10));
			step0RadioButton.setEnabled(false);
			step0RadioButton.setText("0. Copying Sources");
		}
		return step0RadioButton;
	}

	/**
	 * This method initializes step3RadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getStep3RadioButton() {
		if (step3RadioButton == null) {
			step3RadioButton = new JRadioButton();
			step3RadioButton.setFont(new Font("Arial", Font.BOLD, 10));
			step3RadioButton.setEnabled(false);
			step3RadioButton.setText("3. Metrics Calculation");
		}
		return step3RadioButton;
	}

	/**
	 * This method initializes step4RadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getStep4RadioButton() {
		if (step4RadioButton == null) {
			step4RadioButton = new JRadioButton();
			step4RadioButton.setFont(new Font("Arial", Font.BOLD, 10));
			step4RadioButton.setEnabled(false);
			step4RadioButton.setText("4. Generating Report");
		}
		return step4RadioButton;
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
					com.codesquale.launcher.Process process = 
						new com.codesquale.launcher.Process(inputDirField.getText(),outputDirField.getText());
				}
				
			});
		}
		return actionButton;
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
			consolesTabbedPane.addTab("CodeSquale Process", null, getGlobalProcessConsoleArea(), null);
		}
		return consolesTabbedPane;
	}

	/**
	 * This method initializes consoleArea	
	 * 	
	 * @return com.codesquale.logging.ConsoleArea	
	 */
	private ConsoleArea getGlobalProcessConsoleArea() {
		if (globalProcessConsoleArea == null) {
			globalProcessConsoleArea = new ConsoleArea(GLOBALPROCESS_SOCKETPORT);
		}
		return globalProcessConsoleArea;
	}
}
