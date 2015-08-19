/*
 * @author TOCONNEL
 * Created on Jul 27, 2004
 *
 */
package ua.core.service.log.logger;

import java.util.Locale;

import ua.core.service.log.ILogConst.LogLevelEnum;
import ua.core.util.StringList;

/**
 * @author TOCONNEL Created on Jul 27, 2004
 * 
 */
public class ConsoleLogger implements ILogger {

	public static final String CLASS_NAME = ConsoleLogger.class.getName();

	private String			name;
	private LogLevelEnum	logLevel;
	private boolean			trace;
	private Locale			locale;

	
	public ConsoleLogger (String newName, LogLevelEnum newLogLevel, boolean newTrace, Locale newLocale) {

		this.name		= newName;
		this.logLevel	= newLogLevel;
		this.trace		= newTrace;
		this.locale		= newLocale;
	}

	
	public Locale getLocale () {
		
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
		
		for (String text: textStringList) {
			
			write (text);
		}
	}	

	
	public void write (String text) {

		System.out.println (text);
	}

	
	public void write (StringList textStringList) {

		for (String text: textStringList) {
			
			write (text);
		}
	}	
}
