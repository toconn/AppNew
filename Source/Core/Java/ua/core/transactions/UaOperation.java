package ua.core.transactions;

import java.util.ArrayList;

import ua.core.exceptions.ExceptionBase;
import ua.core.util.Message;
import ua.core.util.UaMap;


public class UaOperation {
	
	
	UaUser						user				= null;
	IUaTransaction				transaction			= null;
	@SuppressWarnings ("rawtypes")
	UaMap						requestMap			= null;
	@SuppressWarnings ("rawtypes")
	UaMap						responseMap			= null;
	
	ArrayList <ExceptionBase>	errorList			= null;
	
	ArrayList <Message>			messageList			= null;
	
	
	public UaOperation () {
		
		init ();
	}
	public UaOperation (UaUser user) {
		
		init ();
		
		this.user = user;
	}
	
	
	public ArrayList <ExceptionBase> getErrorList () {
	
		return errorList;
	}
	
	
	public ArrayList <Message> getMessageList () {
	
		return messageList;
	}
	
	
	@SuppressWarnings ("rawtypes")
	public UaMap getRequestMap () {
	
		return requestMap;
	}


	
	@SuppressWarnings ("rawtypes")
	public UaMap getResponseMap () {
	
		return responseMap;
	}


	
	public IUaTransaction getTransaction () {
	
		return transaction;
	}


	
	public UaUser getUser () {
	
		return user;
	}


	
	@SuppressWarnings ("rawtypes")
	private void init () {
		
		
		requestMap	= new UaMap ();
		responseMap	= new UaMap ();
		
		errorList	= new ArrayList <ExceptionBase> ();
		messageList	= new ArrayList <Message> ();
	}


	
	public boolean isErrors () {
		
		return ! this.errorList.isEmpty ();
	}

	
	
	public boolean isMessages () {
		
		return ! this.messageList.isEmpty ();	
	}

	
	public void setTransaction (IUaTransaction transaction) {
	
		this.transaction = transaction;
	}
	
	
	public void setUser (UaUser user) {
	
		this.user = user;
	}

}
