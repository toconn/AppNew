/*
 * @author TOCONNEL
 * Created on Jul 15, 2004
 *
 */
package ua.core.exceptions;

import java.util.ArrayList;

import ua.core.util.Message;



/**
 * @author TOCONNEL
 * Created on Jul 15, 2004
 *
 */
public class ExceptionNotAuthorized extends ExceptionBase {

	private static final long serialVersionUID = -2685876906636617664L;
	
	public ExceptionNotAuthorized (Message message) {
		
		super (message);
	}
	
	public ExceptionNotAuthorized (ArrayList <Message> messageList) {
		
		super (messageList);
	}
	
	public Message getExceptionType () {
	
		return new Message ("10172", "Not Authorized");
	}
}
