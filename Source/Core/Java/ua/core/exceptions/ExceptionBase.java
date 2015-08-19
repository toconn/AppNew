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
 * 
 */
public abstract class ExceptionBase extends Throwable {

	private static final long serialVersionUID = -3138741202946024745L;

	ArrayList<Message> messageList = new ArrayList<Message>();
	

	public ArrayList<Message> getMessageList() {

		return messageList;
	}

	public void addMessage (Message message) {

		messageList.add (message);
	}

	public void addMessageList (ArrayList<Message> messageList) {

		this.messageList.addAll (messageList);
	}

	public void addFirstMessage (Message message) {

		messageList.add (0, message);
	}

	public ExceptionBase() {

		super();
	}

	public ExceptionBase (Message message) {

		super();
		messageList.add (message);
	}

	public ExceptionBase (ArrayList<Message> messageList) {

		super();
		this.messageList = messageList;
	}

	public ExceptionBase (Throwable throwable) {

		super (throwable);
	}

	abstract public Message getExceptionType();
}