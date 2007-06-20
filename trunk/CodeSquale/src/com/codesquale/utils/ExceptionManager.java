package com.codesquale.utils;
/**
 * Class used to log Exception
 * this class build on singleton pattern
 * @author mbourguignon
 *
 */
public class ExceptionManager {
	/**
	 * Private ctr()
	 * @return Single instance of ExceptionManager
	 */
	public static ExceptionManager getInstance() {
		if (instance == null) {
			// it's ok, we can call this constructor
			instance = new ExceptionManager();
		}
		return instance;
	}
	/**
	 * Clone not implemented
	 * @throws CloneNotSupportedException raised when try to clone instance
	 */
	public ExceptionManager clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	/**
	 * Static private instance
	 */
	private static ExceptionManager instance;
	
	/**
	 * Manage exception with Aspectj
	 * Exception is logged by traceManagedException pointcut
	 * @param e Exception to log
	 * @param level logging level of the exception
	 */
	public static void aspectManagedException(Exception e, ExceptionLevel level)
	{
		
	}
}
