package ua.core.exceptions;

import java.util.Locale;

import ua.core.service.message.IMessageConst;
import ua.core.service.message.MessageService;
import ua.core.util.Message;
import ua.core.util.StringList;


public class ExceptionUtils {
	
	public static StringList getExceptionStringList (ExceptionRuntime exception, Locale locale) {
		
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		StringList		stringList	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
		
		stringList	= new StringList();
		
		if (exception.getOriginalException() != null) {
			
			stringList.add (getMessageText (locale, IExceptionConst.MESSAGE_ERROR_RUNTIME_1.cloneMe (exception.getOriginalException().getClass().getName())));
		}
		else {
			
			stringList.add (getMessageText (locale, IExceptionConst.MESSAGE_ERROR_RUNTIME_2));				
		}
		

		stringList.add (getMessageText (locale, IExceptionConst.MESSAGE_ERROR_RUNTIME_4.cloneMe (IMessageConst.INDENT_SPACES, exception.getClassName())));
		stringList.add (getMessageText (locale, IExceptionConst.MESSAGE_ERROR_RUNTIME_5.cloneMe (IMessageConst.INDENT_SPACES, exception.getMethodName())));
		
		stringList.add ("");

		// Display error messages...

		for (Message message : exception.getErrorMessageList()) {

			stringList.add (getMessageText (locale, IExceptionConst.MESSAGE_ERROR_RUNTIME_6.cloneMe (IMessageConst.INDENT_SPACES, getMessageText (locale, message))));
		}
		
		return stringList;
	}
	
	
	private static String getMessageText (Locale locale, Message message) {
		
		return MessageService.getFormattedMessage (locale, message);
	}
}
