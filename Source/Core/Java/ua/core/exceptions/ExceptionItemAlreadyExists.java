/*
 * @author TOCONNEL
 * Created on Aug 17, 2004
 *
 */
package ua.core.exceptions;

import java.util.ArrayList;

import ua.core.util.Message;



/**
 * @author TOCONNEL
 * Created on Aug 17, 2004
 *
 */
public class ExceptionItemAlreadyExists extends ExceptionBase {
	
	private static final long serialVersionUID = -6076637876385094426L;

	public ExceptionItemAlreadyExists (Message message) {
		
		super (message);
	}
	
	public ExceptionItemAlreadyExists (ArrayList <Message> messageList) {
		
		super (messageList);
	}
	
	public Message getExceptionType() {
	
		return IExceptionConst.MESSAGE_ITEM_ALREADY_EXISTS;
	}
}
