/*
 * @author TOCONNEL
 * Created on Jul 20, 2004
 *
 */
package ua.core.util;

import java.text.MessageFormat;


/**
 * @author TOCONNEL Created on Jul 20, 2004
 * 
 */
public class Message implements Cloneable {
	

	private String		messageId		= null;
	private String		format			= null;
	private Object[]	parameterArray	= null;

	

	public Message(String messageId, String format) {

		super();

		this.messageId = messageId;
		this.format = format;
	}

	
	public Message(String messageId, String format, Object[] parameterArray) {

		super();

		this.messageId = messageId;
		this.format = format;
		this.parameterArray = parameterArray;
	}

	
	public Message(String messageId, String format, String string1) {

		super();

		this.messageId = messageId;
		this.format = format;
		this.parameterArray = new Object[] {string1};
	}

	
	public Message(String messageId, String format, String string1, long long1) {

		super();

		this.messageId = messageId;
		this.format = format;
		this.parameterArray = new Object[] {string1, new Long (long1).toString ()};
	}

	
	public Message(String messageId, String format, String string1, String string2) {

		super();

		this.messageId = messageId;
		this.format = format;
		this.parameterArray = new Object[] {string1, string2};
	}

	
	public Message(String messageId, String format, String string1, String string2, String string3) {

		super();

		this.messageId = messageId;
		this.format = format;
		this.parameterArray = new Object[] {string1, string2, string3};
	}

	
	
	public Object clone() {

		// /////////////////////////////////////////
		// Declarations:
		// /////////////////////////////////////////

		Object[] newParameterArray = null;

		
		// /////////////////////////////////////////
		// Code:
		// /////////////////////////////////////////

		if (parameterArray != null) {

			newParameterArray = new Object[parameterArray.length];
			System.arraycopy(parameterArray, 0, newParameterArray, 0, parameterArray.length);
		}

		return new Message(messageId, format, newParameterArray);
	}

	
	public Message cloneMe () {
		
		return new Message (this.messageId, this.format);
	}

	
	public Message cloneMe (Object[] objectArray) {
		
		return new Message (this.messageId, this.format, objectArray);
	}
	
	
	public Message cloneMe (String string1) {
		
		return new Message (this.messageId, this.format, string1);
	}

	
	public Message cloneMe (String string1, long long1) {
		
		return new Message (this.messageId, this.format, string1, long1);
	}

	
	public Message cloneMe (String string1, String string2) {
		
		return new Message (this.messageId, this.format, string1, string2);
	}

	
	public Message cloneMe (String string1, String string2, String string3) {
		
		return new Message (this.messageId, this.format, string1, string2, string3);
	}

	
	/**
	 * @return Returns the text.
	 */
	public String getFormat() {

		return format;
	}

	
	/**
	 * @return Returns the messageID.
	 */
	public String getMessageId() {

		return messageId;
	}

	
	/**
	 * @return Returns the parameters.
	 */
	public Object[] getParameters() {

		return parameterArray;
	}

	
	/**
	 * @param messageID
	 *            The messageID to set.
	 */
	public void setMessageId (String messageId) {

		this.messageId = messageId;
	}
	
	
	/**
	 * @param parameters
	 *            The parameters to set.
	 */
	public void setParameters (Object[] parameterArray) {

		this.parameterArray = parameterArray;
	}

	
	public void setParameters (String string1) {

		this.parameterArray = new Object[] {string1};
	}

	
	public void setParameters (String string1, String string2) {

		this.parameterArray = new Object[] {string1, string2};
	}

	
	/**
	 * @param text
	 *            The text to set.
	 */
	public void setText(String format) {

		this.format = format;
	}

	
	public String toString() {

		return "Message: " + new MessageFormat (getFormat());
	}
}
