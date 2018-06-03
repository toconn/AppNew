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
public class NVStringPairComparator implements Comparator <NVStringPair> {
	
	private char sortKey;
	
	public static final char	SORT_KEY_NAME	= 'N';
	public static final char	SORT_KEY_VALUE	= 'V';

	/**
	 * 
	 */
	public NVStringPairComparator (char sortKey) {
	
		super();
	
		this.sortKey = sortKey;
		
	}
	
	public int compare (NVStringPair object1, NVStringPair object2) {
	
		/////////////////////////////////
		// Declarations:
		/////////////////////////////////
		
		// final String	METHOD_NAME		= "compare (Object, Object)";

		String			value1			= null;
		String			value2			= null;


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
			
			if (object1 != null && object1 instanceof NVStringPair) {
				
				value1 = getSortKeyValue ((NVStringPair) object1);
			}
			
			if (object2 != null && object2 instanceof NVStringPair) {
				
				value2 = getSortKeyValue ((NVStringPair) object2);
			}

			return StringUtils.compare (value1, value2);
		}
	
	}
	public boolean equals (Object object1, Object object2) {
	
		/////////////////////////////////
		// Declarations:
		/////////////////////////////////
		
		// final String	METHOD_NAME		= "equals (Object, Object)";
		
		String			value1			= null;
		String			value2			= null;
		
		
		/////////////////////////////////
		// Code:
		/////////////////////////////////
		
		if (object1 != null && object1 instanceof NVStringPair) {
		
			value1 = getSortKeyValue ((NVStringPair) object1);
		}
		
		if (object2 != null && object2 instanceof NVStringPair) {
			
			value2 = getSortKeyValue ((NVStringPair) object2);
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
	/* (non-Javadoc)
	 * @see aiu.security.utils.ISortable#getSortKey()
	 */
	private String getSortKeyValue (NVStringPair nvPair) {
		
		/////////////////////////////////
		// Declarations:
		/////////////////////////////////
		
		// final String	METHOD_NAME		= "getSortKeyValue (UserAccount)";
		
	
		switch (sortKey) {
			
			case SORT_KEY_NAME:
			
				return StringUtils.toLowerCase (nvPair.getName());
		
			case SORT_KEY_VALUE:
				
					return StringUtils.toLowerCase (nvPair.getName());
			
			default:
				
				return StringUtils.toLowerCase (nvPair.getName());
			
		}
	
	}
}
