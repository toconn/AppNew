/*
 * @author TOCONNEL
 * Created on Jan, 2011
 *
 */
package ua.core.service.log.logger;

import java.util.Locale;

import ua.app.all.AppUI;
import ua.core.service.log.ILogConst.LogLevelEnum;
import ua.core.util.StringList;

/**
 * @author TOCONNEL Created on Jan 31, 2011
 * 
 * Sends logged output to the standard AppUI output.
 *
 */
public class DefaultLogger implements ILogger {

	public static final String CLASS_NAME = DefaultLogger.class.getName();

	private String			name;
	private LogLevelEnum	logLevel;
	private boolean			trace;
	private Locale			locale;

	
	public DefaultLogger (String newName, LogLevelEnum newLogLevel, boolean newTrace, Locale newLocale) {

		this.name		= newName;
		this.logLevel	= newLogLevel;
		this.trace		= newTrace;
		this.locale		= newLocale;
	}

	
	public Locale getLocale() {
		
		return locale;
	}

	
	/**
	 * @return Returns the name.
	 */
	public LogLevelEnum getLogLevel() {

		return logLevel;
	}
	
	
	/**
	 * @return Returns the name.
	 */
	public String getName() {

		return name;
	}

	
	/**
	 * @return Returns the trace.
	 */
	public boolean isTrace() {

		return trace;
	}

	
	public void write (LogLevelEnum logLevel, String text) {

		write (text);
	}

	
	public void write (LogLevelEnum logLevel, StringList textStringList) {
		
		write (textStringList);
	}	

	
	public void write (String text) {

		AppUI.print (text);
	}

	
	public void write (StringList textStringList) {

		AppUI.print (textStringList);
	}	
}
