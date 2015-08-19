/*
 * @author TOCONNEL
 * Created on Jul 27, 2004
 *
 */
package ua.core.service.log;


import java.util.Locale;

import ua.core.service.log.ILogConst.LogLevelEnum;
import ua.core.service.message.MessageService;
import ua.core.util.Message;


/**
 * @author TOCONNEL
 * Created 2004.
 * Completely redone 2011.
 *
 */
public class LogUtils {
	
	
	public static String getLogLevelDescription (LogLevelEnum logLevel, Locale locale) {
		
		return MessageService.getFormattedMessage (locale, getLogLevelDescriptionMessage (logLevel));
	}
	
	
	public static Message getLogLevelDescriptionMessage (LogLevelEnum logLevel) {
		
		switch (logLevel) {
		
			case DEBUG:
				
				return ILogConst.MESSAGE_LEVEL_DEBUG;
				
			case INFO:
				
				return ILogConst.MESSAGE_LEVEL_INFO;
				
			case WARNING:
				
				return ILogConst.MESSAGE_LEVEL_WARNING;
				
			case ERROR:
				
				return ILogConst.MESSAGE_LEVEL_ERROR;
				
			case FATAL_ERROR:
				
				return ILogConst.MESSAGE_LEVEL_ERROR;
				
			default:
				
				return ILogConst.MESSAGE_LEVEL_DEBUG;
		}
	}
	
}
