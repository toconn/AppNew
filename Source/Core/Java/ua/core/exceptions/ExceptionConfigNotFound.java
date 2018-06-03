/**
 * 
 */
package ua.core.exceptions;

import java.util.ArrayList;

import ua.core.util.Message;

/**
 * @author Tadhg
 *
 */
public class ExceptionConfigNotFound extends ExceptionBase {

	private static final long serialVersionUID = -6427253132725598675L;

	
	public ExceptionConfigNotFound (Message message) {
		
		super (message);
	}
	
	
	public ExceptionConfigNotFound (ArrayList <Message> messageList) {
		
		super (messageList);
	}
	
	
	public Message getExceptionType() {
	
		return IExceptionConst.MESSAGE_CONFIG_NOT_FOUND;
	}

}
