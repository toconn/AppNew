/*
 * @author TOCONNEL
 * Created on Jul 27, 2004
 *
 */
package ua.core.service.log;

import ua.core.util.Message;


/**
 * @author TOCONNEL
 * Created on Jul 27, 2004
 *
 */
public interface ILogConst {
	
	public static final int		SERVICE_ID				= 1001;
	
	public enum LogLevelEnum {
		DEBUG,
		INFO,
		WARNING,
		ERROR,
		FATAL_ERROR,
	}
	
	public enum TraceStatusEnum {
		OK,
		FAIL,
		EXCEPTION,
	}

	public static final Message		MESSAGE_LEVEL_DEBUG				= new Message ("S10010010", "Debug");
	public static final Message		MESSAGE_LEVEL_INFO				= new Message ("S10010011", "Info");
	public static final Message		MESSAGE_LEVEL_WARNING			= new Message ("S10010012", "Warning");
	public static final Message		MESSAGE_LEVEL_ERROR				= new Message ("S10010013", "Error");
	public static final Message		MESSAGE_LEVEL_FATAL_ERROR		= new Message ("S10010014", "Fatal Error");
	
	public static final Message		MESSAGE_TRACE_ENTER				= new Message ("S10010020", "  Trace        : {0}.{1}");
	public static final Message		MESSAGE_TRACE_EXIT_DEFAULT		= new Message ("S10010021", "  Trace (Exit) : {0}.{1}");
	public static final Message		MESSAGE_TRACE_EXIT_EXCEPTION	= new Message ("S10010022", "  Trace (Excep): {0}.{1}");
	public static final Message		MESSAGE_TRACE_EXIT_FAIL			= new Message ("S10010023", "  Trace (Fail) : {0}.{1}");
}
