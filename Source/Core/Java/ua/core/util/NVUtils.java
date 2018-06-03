/*
 * Created on Mar 7, 2006
 *
 */
package ua.core.util;


/**
 * @author TOCONNEL
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class NVUtils {

	public static final String	CLASS_NAME	= "NVUtils";


	/**
	 * Returns the length of the longest name.
	 * 
	 * @param collection
	 * @return
	 */
	public static int getMaxNameLength (Iterable <NVStringPair> collection) {
		
		int maxLength = 0;
		
		if (collection != null)
			
			for (NVStringPair nvPair: collection)
				
				if (StringUtils.getLength (nvPair.getName()) > maxLength)
					
					maxLength = nvPair.getName().length();
		
		return maxLength;
	}
	
	
	public static NVIntPair getNVIntPair (NVIntPair[] nvIntPairArray, String name) {

		// /////////////////////////////////////////
		// Declarations:
		// /////////////////////////////////////////

		int i;

		// /////////////////////////////////////////
		// Code:
		// /////////////////////////////////////////

		if (nvIntPairArray != null && name != null) {

			for (i = 0; i < nvIntPairArray.length; i++) {

				if (nvIntPairArray[i] != null) {

					if (StringUtils.isEqual (nvIntPairArray[i].getName(), name)) {

						return nvIntPairArray[i];
					}
				}
			}
		}

		return null;

	}


	public static NVIntPair getNVIntPair (NVIntPair[] nvIntPairArray, int value) {

		// /////////////////////////////////////////
		// Declarations:
		// /////////////////////////////////////////

		int i;

		// /////////////////////////////////////////
		// Code:
		// /////////////////////////////////////////

		if (nvIntPairArray != null) {

			for (i = 0; i < nvIntPairArray.length; i++) {

				if (nvIntPairArray[i] != null) {

					if (nvIntPairArray[i].getValue() == value) {

						return nvIntPairArray[i];
					}
				}
			}
		}

		return null;

	}


	public static String getName (NVIntPair[] nvIntPairArray, int value) {

		// /////////////////////////////////////////
		// Declarations:
		// /////////////////////////////////////////

		NVIntPair pair = null;

		// /////////////////////////////////////////
		// Code:
		// /////////////////////////////////////////

		pair = getNVIntPair (nvIntPairArray, value);

		if (pair != null) {

			return pair.getName();
		}

		return null;

	}


	public static int getValue (NVIntPair[] nvIntPairArray, String name) {

		// /////////////////////////////////////////
		// Declarations:
		// /////////////////////////////////////////

		NVIntPair pair = null;

		// /////////////////////////////////////////
		// Code:
		// /////////////////////////////////////////

		pair = getNVIntPair (nvIntPairArray, name);

		if (pair != null) {

			return pair.getValue();
		}

		return 0;

	}


	public static boolean hasName (NVIntPair[] nvIntPairArray, String name) {

		return getNVIntPair (nvIntPairArray, name) != null;
	}


	public static boolean hasValue (NVIntPair[] nvIntPairArray, int value) {

		return getNVIntPair (nvIntPairArray, value) != null;
	}
}
