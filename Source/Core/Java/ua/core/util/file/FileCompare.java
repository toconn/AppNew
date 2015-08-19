package ua.core.util.file;

import java.io.*;
import java.util.Comparator;

public class FileCompare <objectType extends File> implements Comparator <objectType>  {
	
	public int compare (objectType object1, objectType object2) {
		
		/////////////////////////////////
		// Declarations:
		/////////////////////////////////
		
		// final String	METHOD_NAME		= "compare (Object, Object)";
		
		String			value1			= null;
		String			value2			= null;
		
		
		/////////////////////////////////
		// Code:
		/////////////////////////////////
		
		if (object1 != null ) {
		
			value1 = object1.getAbsolutePath();
		}
		
		if (object2 != null) {
			
			value2 = object2.getAbsolutePath();
		}
			
	
		if (value1 == null) {
			
			if (value2 == null) {
				
				return 0;
			}
			else {
			
				return -1;
			}
		}
		else if (value2 == null) {
			
			return 1;
		}
		else {
			
			return value1.compareTo (value2);
		}
	
	}
	public boolean equals (objectType object1, objectType object2) {
	
		/////////////////////////////////
		// Declarations:
		/////////////////////////////////
		
		// final String	METHOD_NAME		= "equals (Object, Object)";
		
		String			value1			= null;
		String			value2			= null;
		
		
		/////////////////////////////////
		// Code:
		/////////////////////////////////
		
		if (object1 != null ) {
			
			value1 = object1.getAbsolutePath();
		}
		
		if (object2 != null) {
			
			value2 = object2.getAbsolutePath();
		}
			
	
		if (value1 == null) {
			
			if (value2 == null) {
				
				return true;
			}
			else {
			
				return false;
			}
		}
		else if (value2 == null) {
			
			return false;
		}
		else {
			
			return value1.equals (value2);
		}
	
	}
}
