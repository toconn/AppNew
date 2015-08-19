/*
 * Created on Dec 9, 2005
 *
 */
package ua.core.util;

import ua.core.exceptions.ExceptionRuntime;


/**
 * @author TOCONNEL
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ClassUtils {

	@SuppressWarnings ("rawtypes")
	public static String getClassShortName (Class objectClass) {
	
		return getClassShortName (objectClass.getName());
	}
	
	public static String getClassShortName (String className) {
		
		///////////////////////////////////////////
		// Declarations:
		///////////////////////////////////////////
		
		int classNameSeparator;
	
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
		
		classNameSeparator = className.lastIndexOf ('.');
		
		if (classNameSeparator > -1) {
			
			return className.substring (classNameSeparator + 1);
		}
		else {
			
			return null;
		}	
	}
	
	public static String getThisClassName () {
		
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}

	public static String getThisMethodName () {
		
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}

	public static String getUpperClassName () {
		
		return Thread.currentThread().getStackTrace()[3].getMethodName();
	}

	public static String getUpperMethodName () {
		
		return Thread.currentThread().getStackTrace()[3].getMethodName();
	}

	/**
	 * 
	 * 
	 * Creation date: (2002/11/05 10:51:23 AM)
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @return boolean
	 * @param po_Object
	 *            java.lang.Object
	 * @param ps_ClassName
	 *            java.lang.String
	 */
	@SuppressWarnings("rawtypes")
	public static boolean instanceOf (Object object, String className) throws ExceptionRuntime {
	
		// ///////////////////////////////
		// Declarations:
		// ///////////////////////////////
	
		Class objectClass = null;
		Class compareClass = null;
	
		Class[] interfaceList = null;
		int index;
	
		// ///////////////////////////////
		// Code:
		// ///////////////////////////////
	
		// Validations...
	
		// Programmer validations...
	
		if (object == null || className == null) {
	
			throw new ExceptionRuntime (new Message("", "NULL is not allowed as a parameter."));
		}
	
		// Get the class instance of the class name.
	
		try {
	
			compareClass = Class.forName(className);
	
		} catch (Exception e) {
	
			if (e instanceof ClassNotFoundException) {
	
				throw new ExceptionRuntime (new Message("", "Invalid class name \"{0}\".", new Object[] { className }));
			}
		}
	
		// ** See if the classes mathch:
	
		objectClass = object.getClass();
	
		if (compareClass.isInterface()) {
	
			// The class is an interface.
			// Scan the object's interface list.
	
			interfaceList = objectClass.getInterfaces();
	
			for (index = 0; index < interfaceList.length; index++) {
	
				if (compareClass == interfaceList[index]) {
	
					// The interfaces match. Return true.
	
					return true;
				}
			}
		} else {
	
			// The class is a real class.
			// Search the object's hierarchy tree.
	
			while (objectClass != null) {
	
				if (objectClass == compareClass) {
	
					// The classes match. Return true.
	
					return true;
				}
	
				objectClass = objectClass.getSuperclass();
	
			}
		}
	
		return false;
	}

	/**
	 * 
	 * 
	 * Creation date: (2003/01/19 1:49:07 AM)
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @return boolean
	 * @param className
	 *            java.lang.String
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isValidClassName(String className) {
	
		// ///////////////////////////////
		// Declarations:
		// ///////////////////////////////
	
		Class objectClass = null;
		boolean isValid;
	
		// ///////////////////////////////
		// Code:
		// ///////////////////////////////
	
		isValid = false;
	
		try {
	
			objectClass = Class.forName(className);
	
			isValid = (objectClass != null);
	
		} catch (Exception e) {
	
			// Ignore error.
		}
	
		return isValid;
	}
}
