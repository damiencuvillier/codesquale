package com.codesquale.logging;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import org.apache.log4j.spi.LoggingEvent;

import com.codesquale.utils.ExceptionLevel;
import com.codesquale.utils.ExceptionManager;
/** Socket Logger Client. <br />
 * Uses LOG4J appender<br />
 * 
 * @author Damien
 *
 */
class SocketLoggerClient extends Thread {
	/** Socket Client. Uses LOG4J SocketAppender. */
	private Socket client;
	
	/** ConsoleArea : Display messages. */
	private MessageReceiver mReceiver;
	
	/** Constructor. */
	SocketLoggerClient(Socket client, MessageReceiver mReceiver){
		super("SocketLoggerClient");
		this.client = client;
		this.mReceiver = mReceiver;
	}
	/** Main Thread Method. */
	public void run() {
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(client.getInputStream());
			LoggingEvent event = (LoggingEvent) ois.readObject();
			while (true) {
                mReceiver.sendMessage(event.getMessage().toString());
                event = (LoggingEvent) ois.readObject();
            }
		} catch (IOException e) {
			ExceptionManager.aspectManagedException(e, ExceptionLevel.ERROR);
		} catch (ClassNotFoundException e) {
			ExceptionManager.aspectManagedException(e, ExceptionLevel.FATAL);
		}
	}
}
