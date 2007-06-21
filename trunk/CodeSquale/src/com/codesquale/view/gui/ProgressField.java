package com.codesquale.view.gui;

import java.awt.Color;

import javax.swing.JTextField;

public class ProgressField extends JTextField {

	public ProgressField(String text) {
		super();
		this.setBackground(Color.RED);
		this.setForeground(Color.WHITE);
		this.setEnabled(false);
		this.setText(text);
		
	}

}
