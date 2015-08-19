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
public class ExceptionItemNotFound extends ExceptionBase {
	

	private static final long	serialVersionUID	= -8049048172640957505L;

	
	public ExceptionItemNotFound (Message message) {
		
		super (message);
	}
	
	public ExceptionItemNotFound (ArrayList <Message> messageList) {
		
		super (messageList);
	}
	
	public Message getExceptionType () {
	
		return IExceptionConst.MESSAGE_ITEM_NOT_FOUND_1;
	}
}
