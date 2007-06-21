package com.codesquale.view.gui;

import java.awt.Rectangle;

import javax.swing.JScrollPane;

import com.codesquale.logging.ConsoleArea;
import com.codesquale.logging.SocketLoggerServer;

public class ConsoleSocketListenerPanel extends JScrollPane{
	/** Socket Server for logging tool. */
	private SocketLoggerServer server;
	private ConsoleArea console;
	public ConsoleSocketListenerPanel(int socketPort){
		super();
		console = new ConsoleArea();
		server = new SocketLoggerServer(socketPort, console);
		server.start();
		setBounds(new Rectangle(0, 0, 860, 20));
		setViewportView(console);
	}
}
