package ua.core.service.log;

import java.util.ArrayList;

import ua.core.service.log.logger.ILogger;


public class Store {

	public static ArrayList<ILogger>	loggerArrayList	= new ArrayList<ILogger>();
	
	
	public static void init (ArrayList<ILogger>	newLoggerArrayList) {
		
		Store.loggerArrayList = newLoggerArrayList;
	}
}
