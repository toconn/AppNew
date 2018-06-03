package ua.core.exceptions;

import java.util.ArrayList;

import ua.core.util.Message;
import ua.core.util.StringList;
import ua.core.util.UaProperties;

public class ValidationUtils {
	
	
	/**
	 * Adds a message to an existing validation exception or creates a new exception and adds the message
	 * to that.
	 * 
	 * @param exceptionValidation
	 * @param message
	 * @return
	 */
	public static ExceptionValidation addMessage (ExceptionValidation exceptionValidation, Message message) {
		
		if (exceptionValidation == null) {
			
			exceptionValidation = new ExceptionValidation (message);
		}
		else {
			
			exceptionValidation.addMessage (message);
		}
		
		return exceptionValidation;
	}
	
	
	/**
	 * Adds a message to an existing validation exception or creates a new exception and adds the message
	 * to that.
	 * 
	 * @param exceptionValidation
	 * @param message
	 * @return
	 */
	public static ExceptionValidation addMessageList (ExceptionValidation exceptionValidation, ArrayList <Message> messageList) {
		
		if (exceptionValidation == null) {
			
			exceptionValidation = new ExceptionValidation (messageList);
		}
		else {
			
			exceptionValidation.addMessageList (messageList);
		}
		
		return exceptionValidation;
	}
	
	
	/**
	 * Merges two validation exceptions into 1 new one. All messages are copied
	 * 
	 * @param expVal1
	 * @param expVal2
	 * @return
	 */
	public static ExceptionValidation merge (ExceptionValidation expVal1, ExceptionValidation expVal2) {
		

		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		ExceptionValidation	expVal3	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		if (expVal1 == null)

			if (expVal2 == null)
			
				return null;
						
			else
				
				return expVal2.cloneMe();
		
		else
			
			if (expVal2 == null)
			
				return expVal1.cloneMe();

			else {
				
				expVal3 = expVal1.cloneMe();
				expVal3.addMessageList (expVal2.getMessageList());
				
				return expVal3;
			}
	}
	

	/**
	 * Checks that the properties named in the propertyNameList exist in the properties object. If they don't,
	 * returns an instance of ExceptionValidation with the appropriate missing property messages. Also will note
	 * the name of the property file.
	 * 
	 * If everything is ok, will return null.
	 * 
	 * @param properties
	 * @param propertyNameList
	 * @param propertyFileName
	 * @return
	 */
	public static ExceptionValidation validateHasProperties (UaProperties properties, StringList propertyNameList, String propertyFileName) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////
	
		ExceptionValidation	exceptionValidation	= null;
	
	
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
		
		exceptionValidation	= validateHasProperties (properties, propertyNameList);
		
		if (exceptionValidation != null) {
			
			exceptionValidation.addFirstMessage (IExceptionConst.MESSAGE_CONFIG_PROPERTIES_MISSING.cloneMe (propertyFileName));
		}
	
		return exceptionValidation;
	}

	
	/**
	 * Checks that the properties named in the propertyNameList exist in the properties object. If they don't,
	 * returns an instance of ExceptionValidation with the appropriate missing property messages.
	 * 
	 * If everything is ok, will return null.
	 * 
	 * @param properties
	 * @param propertyNameList
	 * @return
	 */
	public static ExceptionValidation validateHasProperties (UaProperties properties, StringList propertyNameList) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////
	
		ExceptionValidation	exceptionValidation	= null;
		
		Message				message				= null;
		boolean				exceptionFound		= false;
	
	
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
		
		if (propertyNameList != null && properties != null) {
			
			for (String propertyName: propertyNameList) {
				
				if (! properties.hasProperty (propertyName)) {
					
					message = IExceptionConst.MESSAGE_PROPERTY_NOT_FOUND.cloneMe (propertyName);
					
					if (! exceptionFound) {
						
						exceptionFound = true;
						exceptionValidation = new ExceptionValidation (message);
					}
					else {
						
						exceptionValidation.addMessage (message);
					}
				}
			}
		}
		
		
		return exceptionValidation;
	}

}
