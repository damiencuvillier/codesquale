package com.codesquale.ant;

import org.apache.tools.ant.Task;
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

