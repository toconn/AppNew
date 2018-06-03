package ua.core.util.system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

import ua.core.util.NVStringPair;
import ua.core.util.NVStringPairComparator;


public class EnvironmentUtils {

	public static ArrayList <NVStringPair> getSortedSystemPropertiesNVList() {
		
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		ArrayList <NVStringPair>	nvPairList		= null;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////

		nvPairList = getSystemPropertiesNVList();
		
		Collections.sort (nvPairList, new NVStringPairComparator (NVStringPairComparator.SORT_KEY_NAME));
		
		return nvPairList;
	}
	
	
	public static String getSystemProperty (String propertyName) {
		
		return System.getProperty (propertyName);
	}


	public static ArrayList <NVStringPair> getSystemPropertiesNVList() {
		

		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		NVStringPair				nvPair			= null;
		ArrayList <NVStringPair>	nvPairList		= null;

		Properties					sysProperties	= null;

		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////

		nvPairList = new ArrayList <NVStringPair>();
		sysProperties	= System.getProperties();
		
		for (String propertyName : sysProperties.stringPropertyNames()) {
			
			nvPair = new NVStringPair (propertyName, System.getProperties().getProperty (propertyName));
			nvPairList.add (nvPair);
		}
		
		return nvPairList;
	}
}
