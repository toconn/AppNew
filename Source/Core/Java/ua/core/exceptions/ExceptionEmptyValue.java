/*
 * @author TOCONNEL
 * Created on Aug 17, 2004
 *
 */
package ua.core.exceptions;

import java.util.ArrayList;

import ua.core.util.Message;

/**
 * @author TOCONNEL Created on Aug 17, 2004
 * 
 */
public class ExceptionEmptyValue extends ExceptionBase {

	private static final long serialVersionUID = -4758261598132767309L;
	

	public ExceptionEmptyValue (Message message) {

		super (message);
	}

	public ExceptionEmptyValue (ArrayList <Message> messageList) {

		super (messageList);
	}

	@Override
	public Message getExceptionType() {

		return IExceptionConst.MESSAGE_EMPTY_VALUE;
	}
}
