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
	}

	public void clearFilters() {		
	}

	public void close() {
		
	}

	public void doAppend(LoggingEvent arg0) {
		text.setText(text.getText()+ "test\n");
		
	}

	public ErrorHandler getErrorHandler() {
		return null;
	}

	public Filter getFilter() {
		return null;
	}

	public Layout getLayout() {
		return null;
	}

	public String getName() {
		return null;
	}

	public boolean requiresLayout() {
		return false;
	}

	public void setErrorHandler(ErrorHandler arg0) {
		
	}

	public void setLayout(Layout arg0) {
		
	}

	public void setName(String arg0) {
		
	}

	public JTextArea getTextArea() {
		return text;
	}

	public void setTextArea(JTextArea text) {
		this.text = text;
	}
	
}
