/*
 * @author TOCONNEL
 * Created on Jul 15, 2004
 *
 */
package ua.core.exceptions;

import java.util.ArrayList;

import ua.core.service.log.LogService;
import ua.core.util.Message;
import ua.core.util.StringUtils;


/**
 * @author TOCONNEL
 * Created on Jul 15, 2004
 *
 */
public class ExceptionRuntime extends ExceptionBase {

	private static final long	serialVersionUID = 7007027350769737415L;
	
	private Throwable			originalException	= null;
	private String				className			= null;
	private	String				methodName			= null;
	
	// This array list is used to store non user readable messages intended for system logs.
	private ArrayList <Message>	errorMessageList	= new ArrayList <Message> ();
	
	
	public ExceptionRuntime (Throwable originalException) {
	
		super (originalException);
		setClassMethodInfo ();
		
		this.originalException	= originalException;

		// Add previous messages...
		
		addExceptionBaseMessage (originalException);
		addExceptionMessage (originalException);
		
		LogService.error (this);
	}
	
	public ExceptionRuntime (Throwable originalException, Message message, Message errorMessage, boolean logError) {
	
		super (originalException);
		setClassMethodInfo ();
		
		this.originalException	= originalException;
		
		// Add user message...
		
		if (message != null) {
			
			addMessage (message);
		}
		
		// Add original messages...
		
		addExceptionBaseMessage (originalException);
		addExceptionMessage (originalException);

		
		// Add supplied error message...
		
		if (errorMessage != null) {
			
			addErrorMessage (errorMessage);
		}
		
		if (logError) {
			
			LogService.error (this);
		}
	}
	
	public ExceptionRuntime (Throwable originalException, ArrayList <Message> messageList, ArrayList <Message> errorMessageList, boolean logError) {
	
		super (originalException);
		setClassMethodInfo ();
		
		this.originalException	= originalException;

		
		// Add user message...
		
		if (messageList != null) {
			
			addMessageList (messageList);
		}
		
		// Add previous messages...
		
		if (originalException instanceof ExceptionBase) {
		
			addMessageList (((ExceptionBase) originalException).getMessageList());
		}
		
		// Add message from original exception...
		
		if (originalException != null && ! StringUtils.isEmpty (originalException.getMessage())) {
			
			addErrorMessage (new Message ("", originalException.getMessage()));
		}
		
		// Add supplied error message...
		
		if (errorMessageList != null) {
			
			addErrorMessageList (errorMessageList);
		}
		
		if (logError) {
			
			LogService.error (this);
		}
	}
	
	public ExceptionRuntime (ArrayList <Message> messageList, ArrayList <Message> errorMessageList, boolean logError) {
	
		super ();
		setClassMethodInfo ();
		
		if (messageList != null) {
			
			addMessageList (messageList);
		}
		
		if (errorMessageList != null) {
			
			addErrorMessageList (errorMessageList);
		}
		
		if (logError) {
			
			LogService.error (this);
		}
	}
	
	public ExceptionRuntime (Message errorMessage) {
	
		super ();
		setClassMethodInfo ();
		
		if (errorMessage != null) {
			
			addErrorMessage (errorMessage);
		}
		
		LogService.error (this);
	}
	
	public ExceptionRuntime (Message message, Message errorMessage, boolean logError) {
	
		super ();
		setClassMethodInfo ();
		
		if (message != null) {
			
			addMessage (message);
		}
		
		if (errorMessage != null) {
			
			addErrorMessage (errorMessage);
		}
		
		if (logError) {
			
			LogService.error (this);
		}
	}
	
	/**
	 * @return Returns the className.
	 */
	public String getClassName () {
	
		return className;
	}
	
	/**
	 * @return Returns the methodName.
	 */
	public String getMethodName () {
	
		return methodName;
	}
	
	/**
	 * @param className The className to set.
	 */
	public void setClassName (String className) {
	
		this.className = className;
	}
	
	/**
	 * @param methodName The methodName to set.
	 */
	public void setMethodName (String methodName) {
	
		this.methodName = methodName;
	}
	
	public Throwable getOriginalException () {
		
		return originalException;
	}
	
	public ArrayList <Message> getErrorMessageList () {
	
		return errorMessageList;
	}
	
	public void addErrorMessage (Message message) {
		
		errorMessageList.add (message);
	}
	
	/** Adds the descriptive error from a standard java exception if it is of type Exception.
	 * 
	 * 
	 * @param Exception
	 */
	public void addExceptionMessage (Throwable originalException) {
		
		Exception	castException	 = null;
		
		if (originalException instanceof Exception) {
			
			castException = (Exception) originalException;
			
			addErrorMessage (IExceptionConst.MESSAGE_UNFORMATTED.cloneMe (castException.getMessage ()));
		}	
	}
	

	/** Adds the descriptive error from a ua base exception if it is of type ExceptionBase.
	 * 
	 * 
	 * @param Exception
	 */
	public void addExceptionBaseMessage (Throwable Exception) {
		
		if (originalException instanceof ExceptionBase) {
			
			addMessageList (((ExceptionBase) originalException).getMessageList());
		}
		
	}
	
	
	public void addFirstErrorMessage (Message message) {
		
		errorMessageList.add (0, message);
	}
	
	public void addErrorMessageList (ArrayList <Message> errorMessageList) {
	
		this.errorMessageList.addAll (errorMessageList);
	}
	
	public Message getExceptionType () {
	
		return new Message ("10173", "Runtime Exception");
	}
	
	private void setClassMethodInfo () {
		
		this.className	= (Thread.currentThread().getStackTrace()[3].getClassName());
		this.methodName	= (Thread.currentThread().getStackTrace()[3].getMethodName());
	}
}
