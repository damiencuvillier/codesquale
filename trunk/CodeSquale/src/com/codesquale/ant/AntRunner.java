package com.codesquale.ant;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.listener.Log4jListener;

/**
 * 
 * This class is designed to call Ant targets from any Java application. 1.
 * Initialize a new Project by calling "init" 2. Feed Ant with some properties
 * by calling "setProperties" (optional) 3. Run an Ant target by calling
 * "runTarget"
 * 
 * source: http://www.junlu.com/msg/17442.html
 * 
 * Example :
 * 
 * try { //init init("/home/me/build.xml","/home/me/"); //properties HashMap m =
 * new HashMap(); m.put("event", "test"); m.put("subject", "sujet java 3");
 * m.put("message", "message java 3"); setProperties(m, false); //run
 * runTarget("test"); } catch (Exception e) { e.printStackTrace(); }
 * 
 */

public final class AntRunner {

	private AntRunner() {
	}

	/**
	 * Private ctr()
	 * 
	 * @return Single instance of antRunner
	 */
	public static AntRunner getInstance() {
		if (instance == null) {
			// it's ok, we can call this constructor
			instance = new AntRunner();
		}
		return instance;
	}

	/**
	 * Clone not implemented
	 */
	public AntRunner clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	// Static instance
	private static AntRunner instance;

	private Project project;

	/**
	 * Initializes a new Ant Project.
	 * 
	 * @param buildFile
	 *            The build File to use. If none is provided, it will be
	 *            defaulted to "build.xml".
	 * @param _baseDir
	 *            The project's base directory. If none is provided, will be
	 *            defaulted to "." (the current directory).
	 * @exception Exceptions
	 *                are self-explanatory (read their Message)
	 */
	public void init(String buildFile) throws Exception {
		// Create a new project, and perform some default initialization
		project = new Project();
		try {
			project.init();
		} catch (final BuildException e) {
			throw new Exception("The default task list could not be loaded.");
		}
		// project.setBasedir(".");
		// Set the base directory. If none is given, "." is used.
		// if (_baseDir == null) _baseDir=new String(".");
		// try { project.setBasedir(_baseDir); }
		// catch (BuildException e)
		// { throw new Exception("The given basedir doesn't exist, " +
		// "or isn't a directory."); }

		// Parse the given buildfile. If none is given, "build.xml" is used.
		if (buildFile == null) {
			buildFile = new String("build.xml");
		}
		try {
			ProjectHelper.getProjectHelper().parse(project,
					new File(buildFile));
		} catch (final BuildException e) {
			throw new Exception("Configuration file " + buildFile
					+ " is invalid, or cannot be read.");
		}
	}

	/**
	 * Sets the project's properties. May be called to set project-wide
	 * properties, or just before a target call to set target-related properties
	 * only.
	 * 
	 * @param properties
	 *            A map containing the properties' name/value couples
	 * @param overridable
	 *            If set, the provided properties values may be overriden by the
	 *            config file's values
	 * @exception Exception
	 *                Exceptions are self-explanatory (read their Message)
	 */
	public void setProperties(final Map properties, final boolean overridable)
			throws Exception {
		// Test if the project exists
		if (project == null) {
			throw new Exception("Properties cannot be set"
					+ "because the project has not been initialized. "
					+ "Please call the 'init' methodfirst !");
		}

		// Property hashmap is null
		if (properties == null) {
			throw new Exception("The provided property map is null.");
		}

		// Loop through the property map
		Set propertyNames = properties.keySet();
		Iterator iter = propertyNames.iterator();
		while (iter.hasNext()) {
			// Get the property's name and value
			String propertyName = (String) iter.next();
			String propertyValue = (String) properties.get(propertyName);
			if (propertyValue == null) {
				continue;
			}

			// Set the properties
			if (overridable) {
				project.setProperty(propertyName, propertyValue);
			} else {
				project.setUserProperty(propertyName, propertyValue);
			}
		}
	}

	/**
	 * Runs the given Target.
	 * 
	 * @param target
	 *            The name of the target to run. If null, the project's default
	 *            target will be used.
	 * @exception Exception
	 *                Exceptions are self-explanatory (read their Message)
	 */
	public boolean runTarget(String target) throws Exception {
		// Test if the project exists
		if (project == null) {
			throw new Exception("No target can be launched "
					+ "because the project has not been initialized. "
					+ "Please call the 'init' method first !");
		}
		project.addBuildListener(new Log4jListener());

		// If no target is specified, run the default one.
		if (target == null) {
			target = project.getDefaultTarget();
		}

		// Run the target
		try {
			project.executeTarget(target);
			return true;
		} catch (BuildException e) {
			throw new Exception(e.getMessage());
		}
	}

}
