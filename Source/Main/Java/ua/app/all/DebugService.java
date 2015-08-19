package ua.app.all;

import ua.core.util.*;

public class DebugService {
	
	public static void printSystemSettings () {
		
		///////////////////////////////////////////////////////////////////////
		// Declarations:
		///////////////////////////////////////////////////////////////////////

		StringList	SystemSettingsKeyList = null;  // Ordered Key List.
		StringList	SystemSettingsList    = null;
		int			propertyLengthMax     = 0;
		
		
		///////////////////////////////////////////////////////////////////////
		// Code:
		///////////////////////////////////////////////////////////////////////
		
		SystemSettingsList		= new StringList ();
		SystemSettingsKeyList	= CollectionUtils.getSortedKeyList (System.getProperties());
		
		propertyLengthMax		= StringUtils.getMaxStringLength(System.getProperties().stringPropertyNames()) + 2;

		
		for (String propertyName : SystemSettingsKeyList) {
			
			SystemSettingsList.add (StringUtils.padString (propertyName, ' ', propertyLengthMax) + System.getProperty (propertyName));
		}
		
		AppUI.print (SystemSettingsList);
	}

}
