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
		// TODO Raccord de m�thode auto-g�n�r�
		
	}

	public void clearFilters() {
		// TODO Raccord de m�thode auto-g�n�r�
		
	}

	public void close() {
		// TODO Raccord de m�thode auto-g�n�r�
		
	}

	public void doAppend(LoggingEvent arg0) {
		// TODO Raccord de m�thode auto-g�n�r�
		text.setText(text.getText()+ "test\n");
		
	}

	public ErrorHandler getErrorHandler() {
		// TODO Raccord de m�thode auto-g�n�r�
		return null;
	}

	public Filter getFilter() {
		// TODO Raccord de m�thode auto-g�n�r�
		return null;
	}

	public Layout getLayout() {
		// TODO Raccord de m�thode auto-g�n�r�
		return null;
	}

	public String getName() {
		// TODO Raccord de m�thode auto-g�n�r�
		return null;
	}

	public boolean requiresLayout() {
		// TODO Raccord de m�thode auto-g�n�r�
		return false;
	}

	public void setErrorHandler(ErrorHandler arg0) {
		// TODO Raccord de m�thode auto-g�n�r�
		
	}

	public void setLayout(Layout arg0) {
		// TODO Raccord de m�thode auto-g�n�r�
		
	}

	public void setName(String arg0) {
		// TODO Raccord de m�thode auto-g�n�r�
		
	}

	public JTextArea getTextArea() {
		return text;
	}

	public void setTextArea(JTextArea text) {
		this.text = text;
	}
	
}
