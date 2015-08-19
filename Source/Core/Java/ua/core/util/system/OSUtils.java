package ua.core.util.system;

import ua.core.util.StringUtils;


public class OSUtils {
	
	
	public static String getLineSeparator () {
		
		return System.getProperty (IOSConst.SYSTEM_lINE_SEPARATOR);
	}
	
	
	public static String getOS () {
		
		return System.getProperty (IOSConst.SYSTEM_PROPERTY_OS);
	}
	
	
	public static boolean isOSWindows () {
		
		return StringUtils.contains (getOS(), IOSConst.OS_WINDOWS);
	}
}
