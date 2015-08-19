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
 * @author TOCONNEL
 * Created on Jul 27, 2004
 *
 */
public interface ILogger {

	public String			getName();
	public LogLevelEnum		getLogLevel();
	public Locale			getLocale();
	public boolean			isTrace();
	
	public void		write		(LogLevelEnum logLevel, String		text);
	public void		write		(LogLevelEnum logLevel, StringList	textStringList);
	public void		write		(String		text);
	public void		write		(StringList	textStringList);
}
