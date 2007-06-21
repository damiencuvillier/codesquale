package com.codesquale.view.gui;

import java.io.File;



public class Launcher {

	/**
	 * Launch Repository Browser Which calls the parsing process
	 * @author rbittel
	 *
	 */

		
	public static void main(String[] args) 
	{
		if(args.length == 2){
			// Console Mode
			System.out.println("CodeSquale - Console Mode");
			File input = new File(args[0]);
			File output = new File(args[1]);
			com.codesquale.launcher.Process process = 
				new com.codesquale.launcher.Process(input, output);
			process.start();
		}else if(args.length != 0){
			System.err.println("CodeSquale - Console Mode\nSyntax : ");
			System.err.println("CodeSquale <Input Path> <Output Path>");
			System.err.println("\tInput path : Where source code is located ?");
			System.err.println("\tOutput path : Where results will be stored ?");
			System.err.println("\nYou can also launch CodeSquale in graphical mode (no arguments)");
		}else{
			// Graphic mode
			new ConsoleGUI();
		}
	}

}
