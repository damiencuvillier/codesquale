package com.codesquale.view.gui;

import javax.swing.JTextArea;

import org.apache.log4j.Appender;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

public class TextAreaAppender implements Appender {
	private JTextArea text;
	
	public void addFilter(Filter arg0) {
		// TODO Raccord de méthode auto-généré
		
	}

	public void clearFilters() {
		// TODO Raccord de méthode auto-généré
		
	}

	public void close() {
		// TODO Raccord de méthode auto-généré
		
	}

	public void doAppend(LoggingEvent arg0) {
		// TODO Raccord de méthode auto-généré
		text.setText(text.getText()+ "test\n");
		
	}

	public ErrorHandler getErrorHandler() {
		// TODO Raccord de méthode auto-généré
		return null;
	}

	public Filter getFilter() {
		// TODO Raccord de méthode auto-généré
		return null;
	}

	public Layout getLayout() {
		// TODO Raccord de méthode auto-généré
		return null;
	}

	public String getName() {
		// TODO Raccord de méthode auto-généré
		return null;
	}

	public boolean requiresLayout() {
		// TODO Raccord de méthode auto-généré
		return false;
	}

	public void setErrorHandler(ErrorHandler arg0) {
		// TODO Raccord de méthode auto-généré
		
	}

	public void setLayout(Layout arg0) {
		// TODO Raccord de méthode auto-généré
		
	}

	public void setName(String arg0) {
		// TODO Raccord de méthode auto-généré
		
	}

	public JTextArea getTextArea() {
		return text;
	}

	public void setTextArea(JTextArea text) {
		this.text = text;
	}
	
}
