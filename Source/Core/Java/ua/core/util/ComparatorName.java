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
public class ComparatorName implements Comparator <IName> {


	/**
	 * 
	 */
	public ComparatorName () {
	
		super ();
	}
	
	public int compare (IName object1, IName object2) {
		
		return StringUtils.compare (object1.getName(), object2.getName());
	}
	
	public boolean equals (Object object1, Object object2) {
	

		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		boolean		isEqual	= false;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////

		
		isEqual	= false;

		if (object1 == null && object2 == null) {
			
			isEqual	= true;
		}
		else if (object1 != null && object1 instanceof IName && object2 != null && object2 instanceof IName) {
			
			isEqual = StringUtils.isEqual (((IName) object1).getName(), ((IName) object2).getName());
		}

		return isEqual;
	}
}
