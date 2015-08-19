/*
 * @author TOCONNEL
 * Created on Jul 27, 2004
 *
 */
package ua.core.service.log;

import java.util.ArrayList;

import ua.core.exceptions.ExceptionRuntime;
import ua.core.exceptions.IExceptionConst;
import ua.core.service.log.ILogConst.LogLevelEnum;
import ua.core.service.log.ILogConst.TraceStatusEnum;
import ua.core.service.log.logger.ILogger;
import ua.core.service.log.logger.LoggerFactory;
import ua.core.service.message.IMessageConst;
import ua.core.service.message.MessageService;
import ua.core.util.Message;
import ua.core.util.StringList;
import ua.core.util.StringUtils;

/**
 * @author TOCONNEL Created on Jul 27, 2004
 * 
 */
public class LogService {

	public static final String CLASS_NAME = LogService.class.getName();
	
	public static final int		TEXT_PADDING_SIZE	= 36;	// Used to report errors when the log craps out.


	
	public static void debug (Message message) {

		write (LogLevelEnum.DEBUG, message);
	}


	public static void debug (ArrayList<Message> messageList) {

		write (LogLevelEnum.DEBUG, messageList);
	}


	public static void debug (String text) {

		write (LogLevelEnum.DEBUG, text);
	}


	public static void error (ExceptionRuntime exception) {

		// ///////////////////////////////
		// Declarations:
		// ///////////////////////////////

		final String METHOD_NAME = "error (ExceptionRuntime)";


		// ///////////////////////////////
		// Code:
		// ///////////////////////////////

		try {

			write (LogLevelEnum.ERROR, exception);
		}
		catch (Exception e) {

			writeToConsoleErr (e, CLASS_NAME, METHOD_NAME);
		}
	}


	public static void error (Message message) {

		// ///////////////////////////////
		// Declarations:
		// ///////////////////////////////

		final String METHOD_NAME = "error (Message)";


		// ///////////////////////////////
		// Code:
		// ///////////////////////////////

		try {

			write (LogLevelEnum.ERROR, message);
		}
		catch (Exception e) {

			writeToConsoleErr (e, CLASS_NAME, METHOD_NAME);
		}
	}


	public static void error (ArrayList<Message> messageList) {

		// ///////////////////////////////
		// Declarations:
		// ///////////////////////////////

		final String METHOD_NAME = "error (ArrayList<Message>)";


		// ///////////////////////////////
		// Code:
		// ///////////////////////////////

		try {

			write (LogLevelEnum.ERROR, messageList);
		}
		catch (Exception e) {

			writeToConsoleErr (e, CLASS_NAME, METHOD_NAME);
		}
	}


	public static void error (String text) {

		// ///////////////////////////////
		// Declarations:
		// ///////////////////////////////

		final String METHOD_NAME = "error (String)";


		// ///////////////////////////////
		// Code:
		// ///////////////////////////////

		try {

			write (LogLevelEnum.ERROR, text);
		}
		catch (Exception e) {

			writeToConsoleErr (e, CLASS_NAME, METHOD_NAME);
		}
	}


	public static void fatalError (ExceptionRuntime exception) {

		// ///////////////////////////////
		// Declarations:
		// ///////////////////////////////

		final String METHOD_NAME = "fatalError (ExceptionRuntime)";


		// ///////////////////////////////
		// Code:
		// ///////////////////////////////

		try {

			write (LogLevelEnum.FATAL_ERROR, exception);
		}
		catch (Exception e) {

			writeToConsoleErr (e, CLASS_NAME, METHOD_NAME);
		}
	}


	public static void fatalError (Message message) {

		// ///////////////////////////////
		// Declarations:
		// ///////////////////////////////

		final String METHOD_NAME = "fatalError (Message)";


		// ///////////////////////////////
		// Code:
		// ///////////////////////////////

		try {

			write (LogLevelEnum.FATAL_ERROR, message);
		}
		catch (Exception e) {

			writeToConsoleErr (e, CLASS_NAME, METHOD_NAME);
		}
	}


	public static void fatalError (ArrayList<Message> messageList) {

		// ///////////////////////////////
		// Declarations:
		// ///////////////////////////////

		final String METHOD_NAME = "fatalError (ArrayList<Message>)";


		// ///////////////////////////////
		// Code:
		// ///////////////////////////////

		try {

			write (LogLevelEnum.FATAL_ERROR, messageList);
		}
		catch (Exception e) {

			writeToConsoleErr (e, CLASS_NAME, METHOD_NAME);
		}
	}


	public static void fatalError (String text) {

		// ///////////////////////////////
		// Declarations:
		// ///////////////////////////////

		final String METHOD_NAME = "fatalError (String)";


		// ///////////////////////////////
		// Code:
		// ///////////////////////////////

		try {

			write (LogLevelEnum.FATAL_ERROR, text);
		}
		catch (Exception e) {

			writeToConsoleErr (e, CLASS_NAME, METHOD_NAME);
		}
	}
	
	
	private static String getMessageText (ILogger logger, Message message) {
		
		return MessageService.getFormattedMessage (logger.getLocale(), message);
	}
	
	
	private static StringList getMessageTextList (ILogger logger, ArrayList <Message> messageList) {
		
		return MessageService.getFormattedMessageList (logger.getLocale(), messageList);
	}
	
	
	/** Returns the error in a string list in text form.
	 * 
	 * Messages are translated into the correct locale format.
	 * 
	 * @param logger
	 * @param messageLevel
	 * @param exception
	 * @return
	 */
	private static StringList getErrorTextList (ILogger logger, LogLevelEnum messageLevel, ExceptionRuntime exception) {

		// //////////////////////////////////////////////////////////////
		// Declarations:
		// //////////////////////////////////////////////////////////////
		
		StringList	stringList	= null;


		
		// //////////////////////////////////////////////////////////////
		// Code:
		// //////////////////////////////////////////////////////////////
		
		
		stringList = new StringList ();
		
		// Display error details...

		if (exception.getOriginalException() != null) {
			
			stringList.add (getMessageText (logger, IExceptionConst.MESSAGE_ERROR_RUNTIME_1.cloneMe (exception.getOriginalException().getClass().getName())));

		}
		else {
			
			stringList.add (getMessageText (logger, IExceptionConst.MESSAGE_ERROR_RUNTIME_2));
				
		}
		
		stringList.add ("");
		
		stringList.add (getMessageText (logger, IExceptionConst.MESSAGE_ERROR_RUNTIME_3.cloneMe (IMessageConst.INDENT_SPACES, LogUtils.getLogLevelDescription (messageLevel, logger.getLocale()))));
		stringList.add (getMessageText (logger, IExceptionConst.MESSAGE_ERROR_RUNTIME_4.cloneMe (IMessageConst.INDENT_SPACES, exception.getClassName())));
		stringList.add (getMessageText (logger, IExceptionConst.MESSAGE_ERROR_RUNTIME_5.cloneMe (IMessageConst.INDENT_SPACES, exception.getMethodName())));
		
		stringList.add ("");

		// Display error messages...

		for (Message message : exception.getErrorMessageList()) {

			stringList.add (getMessageText (logger, IExceptionConst.MESSAGE_ERROR_RUNTIME_6.cloneMe (IMessageConst.INDENT_SPACES, MessageService.getFormattedMessage (logger.getLocale(), message))));
		}

		stringList.add ("");

		return stringList;
	}


	public static void info (Message message) {

		write (LogLevelEnum.INFO, message);
	}


	public static void info (ArrayList<Message> messageList) {

		write (LogLevelEnum.INFO, messageList);
	}


	public static void info (String text) {

		write (LogLevelEnum.INFO, text);
	}

	
	public static void init() {

		// /////////////////////////////////////////
		// Declarations:
		// /////////////////////////////////////////


		ILogger				logger = null;
		ArrayList<ILogger>	loggerArrayList = null;

		// /////////////////////////////////////////
		// Code:
		// /////////////////////////////////////////

		// Initialize the default log...

		loggerArrayList = new ArrayList<ILogger>();

		logger = LoggerFactory.getDefaultLogger ("Default", LogLevelEnum.DEBUG, false, null);
		loggerArrayList.add(logger);

		Store.init (loggerArrayList);
	}

	
	public static void trace (Message message) {

		// /////////////////////////////////////////
		// Declarations:
		// /////////////////////////////////////////

		String text = null;

		
		// /////////////////////////////////////////
		// Code:
		// /////////////////////////////////////////

		for (ILogger logger : Store.loggerArrayList) {
			
			if (logger.isTrace()) {

				text = MessageService.getFormattedMessage (logger.getLocale(), message);
				
				logger.write (text);
			}
		}
	}

	
	public static void trace (String text) {

		for (ILogger logger : Store.loggerArrayList) {
			
			if (logger.isTrace()) {

				logger.write (text);
			}
		}
	}

	
	public static void traceEnter (String className, String methodName) {
		
		// //////////////////////////////////////////////////////////////
		// Declarations:
		// //////////////////////////////////////////////////////////////

		String text	= null;

		
		// //////////////////////////////////////////////////////////////
		// Code:
		// //////////////////////////////////////////////////////////////

		for (ILogger logger : Store.loggerArrayList) {
			
			if (logger.isTrace()) {
				
				text = MessageService.getFormattedMessage (logger.getLocale(), ILogConst.MESSAGE_TRACE_ENTER.cloneMe (className, methodName));

				logger.write (text);
			}
		}
	}

	
	public static void traceExit (String className, String methodName, TraceStatusEnum traceStatus) {

		// //////////////////////////////////////////////////////////////
		// Declarations:
		// //////////////////////////////////////////////////////////////

		String text	= null;

		
		// //////////////////////////////////////////////////////////////
		// Code:
		// //////////////////////////////////////////////////////////////

		for (ILogger logger : Store.loggerArrayList) {
			
			if (logger.isTrace()) {
				
				switch (traceStatus) {

					case EXCEPTION:
						
						text = MessageService.getFormattedMessage (logger.getLocale(), ILogConst.MESSAGE_TRACE_EXIT_EXCEPTION.cloneMe (className, methodName));
						break;

					case FAIL:
		
						text = MessageService.getFormattedMessage (logger.getLocale(), ILogConst.MESSAGE_TRACE_EXIT_FAIL.cloneMe (className, methodName));
						break;
		
					default:
		
						text = MessageService.getFormattedMessage (logger.getLocale(), ILogConst.MESSAGE_TRACE_EXIT_DEFAULT.cloneMe (className, methodName));
						break;
				}
				
				logger.write (text);
			}
		}
	}

	
	public static void warning (Message message) {

		write (LogLevelEnum.WARNING, message);
	}
	

	public static void warning (ArrayList<Message> messageList) {

		write (LogLevelEnum.WARNING, messageList);
	}
	

	public static void warning (String text) {

		write (LogLevelEnum.WARNING, text);
	}
	
	
	public static void write (LogLevelEnum messageLevel, ExceptionRuntime exception) {
		
		for (ILogger logger : Store.loggerArrayList) {
			
			if (messageLevel.compareTo (logger.getLogLevel()) >= 0) {
				
				// Display error details...
				
				logger.write (getErrorTextList (logger, messageLevel, exception));
				
			}
		}
	}
	
	
	public static void write (LogLevelEnum messageLevel, Message message) {
	
		for (ILogger logger : Store.loggerArrayList) {
			
			if (messageLevel.compareTo (logger.getLogLevel()) <= 0) {
				
				logger.write (messageLevel, getMessageText (logger, message));
			}
		}
	}
	
	
	public static void write (LogLevelEnum messageLevel, ArrayList<Message> messageList) {
		
		for (ILogger logger : Store.loggerArrayList) {
			
			if (messageLevel.compareTo (logger.getLogLevel()) <= 0) {
					
				logger.write (messageLevel, getMessageTextList (logger, messageList));

			}
		}
	}
	
	
	public static void write (LogLevelEnum messageLevel, String text) {
	
		// //////////////////////////////////////////////////////////////
		// Code:
		// //////////////////////////////////////////////////////////////

		for (ILogger logger : Store.loggerArrayList) {
			
			if (messageLevel.compareTo (logger.getLogLevel()) <= 0) {
			
				logger.write (messageLevel, text);
			}
		}
	}
	
	
	
	private static void writeToConsoleErr (Exception e, String className, String methodName) {
		
		// Used to report errors when the log really craps out...
		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////

		writeToConsoleErr ("Exception ",	e.getClass().getName());
		writeToConsoleErr ("   Message  ",	e.getMessage());
		writeToConsoleErr ("   Class  ",	className);
		writeToConsoleErr ("   Method  ",	methodName);
		
		System.err.println ("");
	}
	
	
	private static void writeToConsoleErr (String text1, String text2) {
		
		// Used to report errors when the log really craps out...
		
		System.err.println (StringUtils.padString (text1, " ", TEXT_PADDING_SIZE) + ": " + text2);

	}

}
