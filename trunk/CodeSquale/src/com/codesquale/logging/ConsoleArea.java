package com.codesquale.logging;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
	extends JTextArea
	implements MessageReceiver {
	/** Generated Serialization  ID. */
	private static final long serialVersionUID = 6161025167016289600L;
	
	
	/** Constructor. */
	public ConsoleArea() {
		super();
	}
	
	/** log method. <br />.
	 * Display message in the JTextArea
	 * @param message Message to add to the JTextArea
	 */
	public final void log(final String message) {
		this.setText(message + "\n" + this.getText());
	}

	public void sendMessage(String message){
		log(message);
	}


	
}
