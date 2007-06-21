package com.codesquale.logging;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.codesquale.utils.ExceptionLevel;
import com.codesquale.utils.ExceptionManager;
import com.codesquale.utils.Utilities;
/** SocketLoggerServer. Uses Log4J Socket Appender. <br />
 * Message receiver. <br />
 * Display messages in a JTextArea.
 * @author Damien Cuvillier
 *
 */
public class SocketLoggerServer extends Thread {
	/** Server Socket. */
	private ServerSocket server;
	
	private int port ;
	
	private MessageReceiver mReceiver ;
	
	public SocketLoggerServer(int port, MessageReceiver messageReceiver){
		super("SocketLoggerServer");
		setDaemon(true);
		this.port = port ;
		this.mReceiver = messageReceiver ;
	}
	public synchronized void start() {
		
		try{
			server = new ServerSocket(port);
//			mReceiver.sendMessage("Init Console");
		}catch (IOException e){
			ExceptionManager.aspectManagedException(e, ExceptionLevel.ERROR);
		}
		super.start();
	}

	public void run() {
		while (true) {
            Socket client = null;
			try {
				client = server.accept();
			} catch (IOException e) {
				mReceiver.sendMessage("Technical Error : Unable Finding a client");
				ExceptionManager.aspectManagedException(e, ExceptionLevel.ERROR);
			}
			
            Thread t = new SocketLoggerClient(client,mReceiver);
            t.setDaemon(true);
            t.start();
        }
    }
}
