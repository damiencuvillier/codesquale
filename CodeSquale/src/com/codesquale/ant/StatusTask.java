package com.codesquale.ant;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.tools.ant.Task;

import com.codesquale.utils.ExceptionLevel;
import com.codesquale.utils.ExceptionManager;
/** <h1>Progress Ant Task </h1><BR>
 * 
 * Provides progress status indicators for CodeSquale global Process
 * <br />
 * 
 * Uses Log4J Logger.
 * @author Damien Cuvillier
 *
 */
public class StatusTask extends Task {
	
	
	/** Step indicator in the global Process <br />*/
	private int step;
	
	private String message;
	
	private int levelLogger;
	
	/** AspectJ Log
	 */
	public void execute() {

		// TraceAspect is logging here

	}
	/** Step Variable setter. */
	public void setStep(int step) {
		this.step = step;
		/* Write to a binary file the status
		 * TODO Change Status system
		 */
		try {
			FileWriter writer = new FileWriter("status");
			writer.write(step);
			writer.close();
		} catch (IOException e) {
			ExceptionManager.aspectManagedException(e, ExceptionLevel.WARN);
		}
	}
	/** Message Accessor.
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public void setLevelLogger(int levelLogger){
		
	}
}

