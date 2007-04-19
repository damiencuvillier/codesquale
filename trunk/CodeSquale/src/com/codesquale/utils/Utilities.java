package com.codesquale.utils;

import java.util.*;

public class Utilities {
	
	public static String getCurrentTime()
	{
		GregorianCalendar cal = new GregorianCalendar();
		int sec = cal.get(Calendar.SECOND);
		int min = cal.get(Calendar.MINUTE);
		int hour = cal.get(Calendar.HOUR);
			
		return Integer.toString(sec) + Integer.toString(min) + Integer.toString(hour);
	}

}
