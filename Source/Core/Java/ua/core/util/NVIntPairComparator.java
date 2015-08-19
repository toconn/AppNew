/*
 * @author TOCONNEL
 * Created on Nov 18, 2004
 *
 */
package ua.core.util;

import java.util.Comparator;


/**
 * @author TOCONNEL
 * Created on Nov 18, 2004
 *
 */
public class NVIntPairComparator implements Comparator <NVIntPair> {
	
	private char sortKey;
	
	public static final char	SORT_KEY_NAME	= 'N';
	public static final char	SORT_KEY_VALUE	= 'V';

/**
 * 
 */
public NVIntPairComparator (char sortKey) {

	super ();

	this.sortKey = sortKey;
	
}

public int compare (NVIntPair object1, NVIntPair object2) {

	/////////////////////////////////
	// Code:
	/////////////////////////////////
		

	if (object1 == null) {
		
		if (object2 == null) {
			
			return 0;
		}
		else {
		
			return -1;
		}
	}
	else if (object2 == null) {
		
		return 1;
	}
	else {
		
		if (sortKey == SORT_KEY_NAME) {
			
			return object1.getName().compareTo (object2.getName());
		}
		else {
		
			if (object1.getValue() == object2.getValue()) {
				
				return 0;
			}
			else if (object1.getValue() < object2.getValue()) {

				return 1;
			}
			else {
				return -1;
			}
		}
	}
}
public boolean equals (Object object1, Object object2) {

	/////////////////////////////////
	// Declarations:
	/////////////////////////////////
	
	NVIntPair		pair1			= null;
	NVIntPair		pair2			= null;
	
	/////////////////////////////////
	// Code:
	/////////////////////////////////
	
	if (object1 != null && object1 instanceof NVIntPair) {
	
		pair1 = (NVIntPair) object1;
	}
	
	if (object2 != null && object2 instanceof NVStringPair) {
		
		pair2 = (NVIntPair) object2;
	}
		

	if (pair1 == null) {
		
		if (pair2 == null) {
			
			return true;
		}
		else {
		
			return false;
		}
	}
	else if (pair2 == null) {
		
		return false;
	}
	
	else {
		
		if (sortKey == SORT_KEY_NAME) {
			
			return pair1.getName().equals (pair2.getName());
		}
		else {
		
			return pair1.getValue() == pair2.getValue();
		}
	}
}
}
