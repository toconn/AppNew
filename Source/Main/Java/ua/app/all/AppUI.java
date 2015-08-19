package ua.app.all;

import java.util.ArrayList;

import ua.core.exceptions.ExceptionRuntime;
import ua.core.exceptions.IExceptionConst;
import ua.core.service.message.IMessageConst;
import ua.core.service.message.MessageService;
import ua.core.ui.all.IUI;
import ua.core.util.Message;
import ua.core.util.StringList;
import ua.core.util.UaProperties;

public class AppUI {
	
	private static IUI ui = null;

	
	public static void init (IUI newUI) {
		
		ui = newUI;
	}
	
	
	private static String getMessageText (Message message) {
		
		return MessageService.getFormattedMessage (ui.getLocale(), message);
	}
	
	
	private static StringList getMessageTextList (ArrayList <Message> messageList) {
		
		return MessageService.getFormattedMessageList (ui.getLocale(), messageList);
	}
	
	
	public static void print () {
		
		ui.print ();
	}
	
	
	public static void print (Message message) {

		ui.print (getMessageText (message));
	}
	
	
	public static void print (ArrayList<Message> messageList) {
		
		print (getMessageTextList (messageList));
	}

	
	public static void print (String text) {

		ui.print (text);
	}
	
	
	public static void print (String[] textArray) {
		
		for (String text : textArray) {
			
			print (text);
		}
	}
	

	public static void print (StringList textList) {

		ui.print (textList.getArray());
	}

	
	public static void print (UaProperties properties) {

		ui.print (properties.toStringList().toArray());
	}
	
	
	public static void print (ExceptionRuntime exception) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		StringList		stringList	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
		
		stringList	= new StringList ();
		
		if (exception.getOriginalException() != null) {
			
			stringList.add (getMessageText (IExceptionConst.MESSAGE_ERROR_RUNTIME_1.cloneMe (exception.getOriginalException().getClass().getName())));
		}
		else {
			
			stringList.add (getMessageText (IExceptionConst.MESSAGE_ERROR_RUNTIME_2));				
		}
		

		stringList.add (getMessageText (IExceptionConst.MESSAGE_ERROR_RUNTIME_4.cloneMe (IMessageConst.INDENT_SPACES, exception.getClassName())));
		stringList.add (getMessageText (IExceptionConst.MESSAGE_ERROR_RUNTIME_5.cloneMe (IMessageConst.INDENT_SPACES, exception.getMethodName())));
		
		stringList.add ("");

		// Display error messages...

		for (Message message : exception.getErrorMessageList()) {

			stringList.add (getMessageText (IExceptionConst.MESSAGE_ERROR_RUNTIME_6.cloneMe (IMessageConst.INDENT_SPACES, getMessageText (message))));
		}
		
		print (stringList);
	}
}
