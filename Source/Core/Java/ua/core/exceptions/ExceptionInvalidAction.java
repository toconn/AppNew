/*
 * @author TOCONNEL
 * Created on Jul 15, 2004
 *
 */
package ua.core.exceptions;

import java.util.ArrayList;

import ua.core.util.Message;



/**
 */
public class ExceptionInvalidAction extends ExceptionBase {
	
	private static final long	serialVersionUID	= 6071753634231687432L;
	
	public ExceptionInvalidAction (Message message) {
		
		super (message);
	}
	
	public ExceptionInvalidAction (ArrayList <Message> messageList) {
		
		super (messageList);
	}
	
	public Message getExceptionType() {
	
		return IExceptionConst.MESSAGE_INVALID_ACTION;
	}
}
