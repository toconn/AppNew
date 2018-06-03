package ua.core.transactions;

import java.util.Locale;


public class UaUser {
	
	String	userId			= null;
	String	userPassword	= null;
	String	userName		= null;
	
	Locale	userLocale		= null;
	
	boolean	loggedIn		= false;
	
	
	public UaUser() { }


	
	public String getUserId() {
	
		return userId;
	}


	
	public void setUserId (String userId) {
	
		this.userId = userId;
	}


	
	public String getUserPassword() {
	
		return userPassword;
	}


	
	public void setUserPassword (String userPassword) {
	
		this.userPassword = userPassword;
	}


	
	public String getUserName() {
	
		return userName;
	}


	
	public void setUserName (String userName) {
	
		this.userName = userName;
	}


	
	public Locale getUserLocale() {
	
		return userLocale;
	}


	
	public void setUserLocale (Locale userLocale) {
	
		this.userLocale = userLocale;
	}


	
	public boolean isLoggedIn() {
	
		return loggedIn;
	}


	
	public void setLoggedIn (boolean loggedIn) {
	
		this.loggedIn = loggedIn;
	}
	
	
}
