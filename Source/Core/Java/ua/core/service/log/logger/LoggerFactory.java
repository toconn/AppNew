package ua.core.service.log.logger;

import java.util.Locale;

import ua.core.exceptions.ExceptionRuntime;
import ua.core.service.log.ILogConst.LogLevelEnum;


public class LoggerFactory {
	
	
	public static ILogger getConsoleLogger (String newName, LogLevelEnum newLogLevel, boolean newTrace, Locale newLocale) {
		
		return new ConsoleLogger (newName, newLogLevel, newTrace, newLocale);
	}
	
	
	public static ILogger getDefaultLogger (String newName, LogLevelEnum newLogLevel, boolean newTrace, Locale newLocale) {
		
		return new DefaultLogger (newName, newLogLevel, newTrace, newLocale);
	}
	
	
	public static ILogger getFileLogger (String newName, LogLevelEnum newLogLevel, boolean newTrace, Locale newLocale, String fileName) throws ExceptionRuntime {
		
		return new FileLogger (newName, newLogLevel, newTrace, newLocale, fileName);
	}
}
