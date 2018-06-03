package ua.core.util;

import java.util.*;


public class CollectionUtils {
	
	
	/**
	 * Align an array list of string lists to the given column sizes.
	 * 
	 * @param stringListArrayList
	 * @param columnSizeArray
	 * @param indentSpacer
	 * @param columnSpacer
	 * @return
	 */
	public static StringList getAlignedStringList (ArrayList <StringList> stringListList, int [] columnSizeArray, String indentSpacer, String columnSpacer) {
		
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////
	
		StringList		alignedStringList		= null;
		StringBuilder	alignedStringBuilder	= null;
		
		int				columnCount				= 0;
		int				columnIndex				= 0;
	
		
		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////
		
		// Align columns...
		
		columnCount			= columnSizeArray.length;
		
		alignedStringList	= new StringList();
		
		
		for (StringList currentStringList: stringListList) {
		
			if (currentStringList != null) {
				
				alignedStringBuilder = new StringBuilder();
				
				if (indentSpacer != null)
					
					// Insert the indend spacer if there is one...
					
					alignedStringBuilder.append (indentSpacer);

				
				for (columnIndex = 0; columnIndex < currentStringList.size() && columnIndex < columnCount; columnIndex++) {
					
					if (columnSpacer != null && columnIndex > 0)
						
						// Add the column spacer between the last and the next string (if this is not the first string)...
						
						alignedStringBuilder.append (columnSpacer);
					

					if (columnIndex < currentStringList.size() - 1)
						
						// Add padding to end of string...
						
						alignedStringBuilder.append (StringUtils.padString (currentStringList.get (columnIndex), ' ', columnSizeArray[columnIndex]));
					
					else
						
						// Last string. Do not pad.
						
						alignedStringBuilder.append (currentStringList.get (columnIndex));

				}
				
				alignedStringList.add (alignedStringBuilder.toString());
			}
			else {
				
				alignedStringList.add ("");
			}
		}
		
		return alignedStringList;
	}
		
	
	/**
	 * Converts an ArrayList of StringLists into a column aligned StringList.
	 * 
	 * @param stringListArrayList
	 * @param spacerString
	 * @param indentString
	 * @return
	 */
	public static StringList getAlignedStringList (ArrayList <StringList> stringListList, String indentSpacer, String columnSpacer) {
	
	
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////
	
		StringList		alignedStringList		= null;
		
		int				columnCount				= 0;
		int[]			columnSizeArray			= null;
	
		
		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////
	
		alignedStringList	= new StringList();
		
		if (stringListList.size() > 0 && stringListList.get(0) != null) {
	
			columnCount			= getColumnCount (stringListList);
			columnSizeArray		= new int[columnCount];
			columnSizeArray		= getColumnSizeMaxArray (stringListList, columnSizeArray);
			
			// Align columns...
			
			alignedStringList	= getAlignedStringList (stringListList, columnSizeArray, columnSpacer, indentSpacer);
		}
		else {
			
			alignedStringList	= new StringList();
		}
	
			
		return alignedStringList;
	}
	

	/**
	 * Align a single String List to the sizes in the column size array.
	 * 
	 * @param stringList
	 * @param columnSizeArray
	 * @param indentSpacer
	 * @param columnSpacer
	 * @return
	 */
	public static StringList getAlignedStringList (StringList stringList, int [] columnSizeArray, String indentSpacer, String columnSpacer) {
		
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////
	
		StringList		alignedStringList		= null;
		StringBuilder	alignedStringBuilder	= null;
		
		int				columnCount				= 0;
		int				columnIndex				= 0;
	
		
		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////
		
		// Align columns...
		
		columnCount			= columnSizeArray.length;
		
		alignedStringList	= new StringList();
		
		
		if (stringList != null) {
			
			alignedStringBuilder = new StringBuilder();
			
			if (indentSpacer != null)
				
				// Append the indent string at the beginning if there is an indent string...
				
				alignedStringBuilder.append (indentSpacer);

			
			// Loop through each column...
			
			for (columnIndex = 0; columnIndex < stringList.size() && columnIndex < columnCount; columnIndex++) {
				
				if (columnIndex != 0) 
					
					// Add the column spacer between the last and the next string (if this is not the first string)...
					
					alignedStringBuilder.append (columnSpacer);

				if (columnIndex < stringList.size() - 1)
					
					// Add padding to end of string...
					
					alignedStringBuilder.append (StringUtils.padString (stringList.get (columnIndex), ' ', columnSizeArray[columnIndex]));
				
				else
					
					// Last string. Do not pad.
					
					alignedStringBuilder.append (stringList.get (columnIndex));
			}
			
			alignedStringList.add (alignedStringBuilder.toString());
		}
		else {
			
			alignedStringList.add ("");
		}
		
		return alignedStringList;
	}


	public static <ValueType> ArrayList <ValueType> getArrayList (Hashtable <?, ValueType> hashTable) {
	
		// /////////////////////////////////////////
		// Declarations:
		// /////////////////////////////////////////
	
		ArrayList <ValueType> newList = null;
	
		// /////////////////////////////////////////
		// Code:
		// /////////////////////////////////////////
	
		newList = new ArrayList <ValueType> (hashTable.size());
		newList.addAll (hashTable.values());
	
		return newList;
	
	}

	
	public static int getColumnCount (ArrayList <StringList> stringListList) {
		
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////
	
		int	columnCount	= 0;
		
		
		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////
	
		for (StringList stringList : stringListList) {
			
			if (stringList != null && columnCount < stringList.size()) {
				
				columnCount = stringList.size();
			}
		}
		
		return columnCount;
	}

	
	/**
	 * Combine the maximum column size from an array of max column sizes and Array List of String Lists. Each item in a String List is considered a column.
	 * 
	 * @param stringListArrayList
	 * @param originalColumnSizeArray
	 * @return
	 */
	public static int [] getColumnSizeMaxArray (ArrayList <StringList> stringListList, int [] originalColumnSizeArray) {
		
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////
	
		int		columnIndex		= 0;
		int[]	columnSizeArray	= null;
		
		
		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////
		
		if (originalColumnSizeArray != null) {
			
			columnSizeArray = new int[originalColumnSizeArray.length];
			System.arraycopy (originalColumnSizeArray, 0, columnSizeArray, 0, originalColumnSizeArray.length);
		}
		else {
			
			columnSizeArray = new int[getColumnCount (stringListList)];
		}
		
		
		for (StringList currentStringList: stringListList) {
			
			// Check max size for each column...
			
			if (currentStringList != null) {
			
				for (columnIndex = 0; columnIndex < currentStringList.size() && columnIndex < columnSizeArray.length; columnIndex++) {
					
					if (currentStringList.get(columnIndex) != null && columnSizeArray[columnIndex] < currentStringList.get(columnIndex).length()) {
						
						columnSizeArray[columnIndex] = currentStringList.get(columnIndex).length();
					}
				}
			}
		}
		
		return columnSizeArray;
		
	}

	
	public static int getKeyMaxSize (Map <String, ?> map) {
		
		int maxLength = 0;
		
		for (String key: map.keySet()) {
			
			if (key != null && key.length() > maxLength) {
				
				maxLength = key.length();
			}
		}
		
		return maxLength;
	}

	
	public static StringList getSortedKeyList (Map <String, ?> map) {
		
		///////////////////////////////////////////////////////////////////////
		// Declarations:
		///////////////////////////////////////////////////////////////////////

		StringList keyList = null;
		
		
		///////////////////////////////////////////////////////////////////////
		// Code:
		///////////////////////////////////////////////////////////////////////

		keyList = new StringList();
		
		for (String key : map.keySet()) {
			
			keyList.add (key);
		}

		keyList.sort();
		
		return keyList;
	}
	
	
	public static StringList getSortedKeyList (Properties properties) {
		
		///////////////////////////////////////////////////////////////////////
		// Declarations:
		///////////////////////////////////////////////////////////////////////

		StringList keyList = null;
		
		
		///////////////////////////////////////////////////////////////////////
		// Code:
		///////////////////////////////////////////////////////////////////////

		keyList = new StringList();
		
		for (String key : properties.stringPropertyNames()) {
			
			keyList.add (key);
		}

		keyList.sort();
		
		return keyList;
	}
	
	
	public static boolean isEmpty (Collection <?> collection) {
		
		return (collection == null || collection.size() < 1);
	}


	public static boolean isEmpty (HashMap <?, ?> map) {
		
		return (map == null || map.size() < 1);
	}
	
	
	public static boolean isEmpty (StringList list) {
		
		return (list == null || list.size() < 1);
	}


	public static boolean isEmpty (UaMap <?> map) {
		
		return (map == null || map.size() < 1);
	}
	
	
	public static boolean isNonEmpty (Collection <?> collection) {
		
		return ! isEmpty (collection);
	}


	public static boolean isNonEmpty (HashMap <?, ?> map) {
		
		return ! isEmpty (map);
	}
	
	
	public static boolean isNonEmpty (StringList list) {
		
		return ! isEmpty (list);
	}


	public static boolean isNonEmpty (UaMap <?> map) {
		
		return ! isEmpty (map);
	}
	
	
	public static ArrayList <StringList> toStringListArrayList (ArrayList <NVStringPair> nvPairArrayList) {

		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		ArrayList <StringList>	stringListArrayList		= null;
		
		
		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////


		// Collect Command Data...
		
		stringListArrayList = new ArrayList <StringList>();
		
		for (NVStringPair nvPair : nvPairArrayList) {
			
			stringListArrayList.add (new StringList (new String[] {nvPair.getName(), nvPair.getValue()}));
		}
		
		return stringListArrayList;


	}

}
