package com.codesquale.utils;

import java.io.BufferedReader;
import java.io.FileReader;
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
	
	public static String readFileAsString(String filePath) throws java.io.IOException
    {
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();
        return fileData.toString();
    }

}
