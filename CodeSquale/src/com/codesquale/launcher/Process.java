package com.codesquale.launcher;

import java.io.File;
import java.util.HashMap;

import com.codesquale.ant.AntRunner;
import com.codesquale.utils.ExceptionLevel;
import com.codesquale.utils.ExceptionManager;

/**This class contain the main process. 
 * Thread behavior are included.
 * @author damien cuvillier
 *
 */


public class Process extends Thread {
	/** File containing ant launch Script for CodeSquale process. */
	private static final String XML_LAUNCHER = "xml/launch.xml";
	/** Input Path.*/
	private File source;
	/** Output path. */
	private File target;
	

	
	/** Constructor. 
	 * @param src Source Path
	 * @param tgt Output Path*/
	public Process(final File src, final File tgt) {
		this.source = src;
		this.target = tgt;	
	}
	
	
	
	/** SupressWarnings concern generization. 
	 * It is impossible to use it with Ant eventually.*/
	@SuppressWarnings("unchecked")
	/** Main Process Content.*/
	public final void run() {
		try	{
			HashMap myHash = new HashMap();
			AntRunner.getInstance().init(XML_LAUNCHER);
			myHash.put("OutputDir", target.getAbsolutePath());
			myHash.put("SourceDir", source.getAbsolutePath());
			
			AntRunner.getInstance().setProperties(myHash, true);

			AntRunner.getInstance().runTarget("launch");
			
		}catch(Exception e){
			ExceptionManager.aspectManagedException(e, ExceptionLevel.FATAL);
		}
	}
	/** Source Getter.
	 * @return File*/
	public final File getSource() {
		return source;
	}
	/** Source Setter.
	 * @param src input Path
	 */
	public final void setSource(final File src) {
		this.source = src;
	}
	
	/** Target Getter. 
	 * @return File target*/
	public final File getTarget() {
		return target;
	}
	
	/** target Setter. 
	 * @param tgt outputPath */
	public final void setTarget(final File tgt) {
		this.target = tgt;
	}
 
}
