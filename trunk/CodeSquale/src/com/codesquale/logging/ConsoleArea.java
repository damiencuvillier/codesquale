package com.codesquale.logging;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/** ConsoleArea.
 * <br />
 * Display Log messages in a swing JtextArea.
 * <br />
 * Associated with SocketLogger (LOG4J Socket Appender). 
 * @author Damien
 *
 */
public class ConsoleArea 
	extends JScrollPane
	implements MessageReceiver {
	/** Generated Serialization  ID. */
	private static final long serialVersionUID = 6161025167016289600L;
	/** Console TextArea. */
	private JTextArea textarea;
	/** Constructor.
	 * @param port TCP Socket Port */
	public ConsoleArea(int port) {
		super();
		setBounds(new Rectangle(0, 0, 800, 475));
		
		textarea = new JTextArea();
		textarea.setEditable(false);
		textarea.setFont(new Font("Arial", Font.BOLD, 10));
		setViewportView(textarea);
		new SocketLoggerServer(port, this).start();
	}
	
	/** log method. <br />.
	 * Display message in the JTextArea
	 * @param message Message to add to the JTextArea
	 */
	public final void log(final String message) {

		String oldText = textarea.getText();
		// Keep only 10000 end lines
		if(oldText.length() > 10000 ){
			oldText = oldText.substring(oldText.length()-10000);
		}
		
		textarea.setText(oldText + "\n" + message);
		// Scroll Back
		this.getVerticalScrollBar().setValue(this.getVerticalScrollBar().getMaximum());
	}
	/** MessageReceiver Method. */
	public void sendMessage(String message){
		log(message);
	}


	
}
