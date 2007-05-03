package com.codesquale.view.gui;

/**
 * Class for put OutPutStream in a JTextArea
 * Necessairy to display logs in the swing console
 * @author rbittel
 */

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

public class CSOut extends OutputStream{
	private JTextArea consoleText;
	
	public CSOut(){
		super();
	}
	
	public CSOut(JTextArea t){
		super();
		consoleText = t;
	}
	 
	// @Override 
	   /**
	    * Write a char in a JTextArea.
	    * @see java.io.OutputStream#write(int)
	    */
	
	   public void write(int b) throws IOException {
	      byte[] bytes = new byte[1];
	      bytes[0] = (byte)b;
	      String newText = new String(bytes);
	      consoleText.append(newText);
	      if (newText.indexOf('\n') > -1) {
	         try {
	        	 consoleText.scrollRectToVisible(consoleText.modelToView(
	        	 consoleText.getDocument().getLength()));
	         } catch (javax.swing.text.BadLocationException err) {
	            err.printStackTrace();
	         }
	      }
	   }

	   /**
	    * Write a byte tables in a JTextArea.
	    * @see java.io.OutputStream#write(byte[])
	    */
	   
	   public final void write(byte[] arg0) throws IOException {
	      String txt = new String(arg0);
	      consoleText.append(txt);
	      try {
	    	  consoleText.scrollRectToVisible(consoleText.modelToView(consoleText.getDocument().getLength()));
	      } catch (javax.swing.text.BadLocationException err) {
	         err.printStackTrace();
	      }
	   }
}
