/*
 * @author TOCONNEL
 * Created on Jul 20, 2004
 *
 */
package ua.core.exceptions;

import java.util.ArrayList;

import ua.core.util.Message;



/**
 * @author TOCONNEL
 * Created on Jul 20, 2004
 *
 */
public class ExceptionValidation extends ExceptionBase {

	private static final long serialVersionUID = 780594615616764084L;
	
	
	public ExceptionValidation (Message message) {
		
		super (message);
	}
	
	
	public ExceptionValidation (Message message, ArrayList <Message> messageList) {
		
		super (message);
		
		addMessageList (messageList);
	}
	
	
	public ExceptionValidation (ArrayList <Message> messageList) {
		
		super (messageList);
	}
	
	
	public ExceptionValidation cloneMe() {

		return new ExceptionValidation (this.getMessageList());
	}
	
	
	public Message getExceptionType() {
	
		return new Message ("10171", "Validation Error");
	}
}
