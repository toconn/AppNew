package ua.app.all;

import ua.core.service.message.MessageService;
import ua.core.service.parameters.*;
import ua.core.util.*;

public class AppService {
	
	
	public static StringList getAppInfoDescriptionStringList () {
		
		return getAppInfoDescriptionStringList (AppStore.appInfo.getKeyMaxSize() + 2);
	}
	
	
	public static StringList getAppInfoDescriptionStringList (int keyPaddingLength) {
		
		//////////////////////////////////////////////////////////////////
		// Declarations:
		//////////////////////////////////////////////////////////////////
		
		StringList		descriptionStringList	= null;
		
		
		//////////////////////////////////////////////////////////////////
		// Code:
		////////////////////////////////////////////////////////////////// LanguageController.getFormattedMessage (null, 
		
		descriptionStringList = new StringList ();

		descriptionStringList.add (StringUtils.padString (MessageService.getFormattedMessage (null, new Message ("", "Name")),			' ', keyPaddingLength) + AppStore.appInfo.getProperty (IAppConst.APP_NAME));
		descriptionStringList.add (StringUtils.padString (MessageService.getFormattedMessage (null, new Message ("", "Version")),		' ', keyPaddingLength) + AppStore.appInfo.getProperty (IAppConst.APP_VERSION));
		descriptionStringList.add ("");
		descriptionStringList.add (StringUtils.padString (MessageService.getFormattedMessage (null, new Message ("", "Author")),		' ', keyPaddingLength) + AppStore.appInfo.getProperty (IAppConst.APP_AUTHOR));
		descriptionStringList.add ("");
		descriptionStringList.add (StringUtils.padString (MessageService.getFormattedMessage (null, new Message ("", "Creation Date")),	' ', keyPaddingLength) + AppStore.appInfo.getProperty (IAppConst.APP_CREATION_DATE));
		descriptionStringList.add (StringUtils.padString (MessageService.getFormattedMessage (null, new Message ("", "Build Date")),	' ', keyPaddingLength) + AppStore.appInfo.getProperty (IAppConst.APP_BUILD_DATE));
		descriptionStringList.add (StringUtils.padString (MessageService.getFormattedMessage (null, new Message ("", "Build Number")),	' ', keyPaddingLength) + AppStore.appInfo.getProperty (IAppConst.APP_BUILD_NUMBER));
		
		return descriptionStringList;
	}
	
	
	public static StringList getHelpDescriptionStringList (UaMap <Param> paramMap) {
		
		//////////////////////////////////////////////////////////////////
		// Declarations:
		//////////////////////////////////////////////////////////////////
		
		StringList		descriptionStringList	= null;
		
		StringList		itemList			= null;
		UaMap <Param>	tempParamMap		= null;
		Param			param				= null;
		
		int				keyPaddingLength	= 0;
		int				keyMaxLength		= 0;
		int				keyMaxLengthTemp	= 0;
		
		int				i					= 0;
		String[]		versionArray		= null;
		
		boolean			hasHelpAll			= false;
		
		
		//////////////////////////////////////////////////////////////////
		// Code:
		//////////////////////////////////////////////////////////////////
		
		descriptionStringList = new StringList ();
		
		hasHelpAll = (paramMap.containsKey (IAppConst.PARAM_HELP_ALL));
		
		
		/////////////////////////////////////////////////////////////////
		// Max Key Length...
		/////////////////////////////////////////////////////////////////
		
		// Get The Max Key Length for Params, Defs, App Info ////////////
		
		// Parameters...
		
		keyMaxLengthTemp = paramMap.getKeyMaxLength();
		if (keyMaxLengthTemp > keyMaxLength)   { keyMaxLength = keyMaxLengthTemp; }
			
		// Param Defs...

		if (AppStore.paramDefPack.paramDefArray != null) {
			
    		keyMaxLengthTemp = AppStore.paramDefPack.getParamDefMap().getKeyMaxLength();
    		if (keyMaxLengthTemp > keyMaxLength)   { keyMaxLength = keyMaxLengthTemp; }
		}

		// App Info...
		
		keyMaxLengthTemp = AppStore.appInfo.getKeyMaxSize();
		if (keyMaxLengthTemp > keyMaxLength)   { keyMaxLength = keyMaxLengthTemp; }
					
		
		keyPaddingLength = keyMaxLength + 4;
		
		
		/////////////////////////////////////////////////////////////////
		// Help, App Info, Params...
		/////////////////////////////////////////////////////////////////
		
		// Help Info /////////////////////////////////////////////////////
		
		if (hasParamHelp (paramMap) || (AppStore.paramDefPack.defaultShowHelp && paramMap.size() == 0) || hasHelpAll) {
			
			descriptionStringList.add (MessageService.getFormattedMessage (null, AppStore.paramDefPack.paramDescriptionMessage));
			descriptionStringList.add ("");
			
			if (AppStore.paramDefPack.getParamDefCount() > 0) {
				
				itemList = AppStore.paramDefPack.toStringListParamDefs (keyPaddingLength);
				itemList.prependAllExceptBlanks (IAppConst.TEXT_INDENT + AppStore.paramDefPack.paramIdentifier);
				
			    descriptionStringList.add (itemList);	
				descriptionStringList.add ("");
			}
		}
		
		
		// App Info //////////////////////////////////////////////////////
		
		if (paramMap.containsKey (IAppConst.PARAM_APP_INFO) || hasHelpAll) {
		
			descriptionStringList.add (MessageService.getFormattedMessage (null, new Message ("", "Application Info")));
			descriptionStringList.add ("");
			
			if (AppStore.appInfo != null) {
				
				itemList = getAppInfoDescriptionStringList (keyPaddingLength + 1);
				itemList.prependAll (IAppConst.TEXT_INDENT);
				
				descriptionStringList.add (itemList);	
				descriptionStringList.add ("");
			}
		}
		

		// Parameters ////////////////////////////////////////////////////
		
		if (paramMap.containsKey (IAppConst.PARAM_PARAMS) || hasHelpAll) {
			
			descriptionStringList.add (MessageService.getFormattedMessage (null, new Message ("", "Parameters")));
			descriptionStringList.add ("");

			if (paramMap.size() > 0) {

				itemList = ParamService.toStringListParamMap (paramMap, keyPaddingLength);
				itemList.prependAllExceptBlanks (IAppConst.TEXT_INDENT + AppStore.paramDefPack.paramIdentifier);
				
				descriptionStringList.add (itemList);
			}
			else {
				
				descriptionStringList.add ("None");
			}
	
			descriptionStringList.add ("");
		}

		
		/////////////////////////////////////////////////////////////////
		// Max Key Length...
		/////////////////////////////////////////////////////////////////
		
		// Get Max Key Length For App Settings, Config Setting ///////////
		
		keyMaxLength = 0;
		
		// App Settings...
		
		if (AppStore.appSettings != null) {
			
    		keyMaxLengthTemp = AppStore.appSettings.getKeyMaxSize();
    		if (keyMaxLengthTemp > keyMaxLength)   { keyMaxLength = keyMaxLengthTemp; }
		}
			
		// Config Settings...

		if (AppStore.configSettings != null) {
			
    		keyMaxLengthTemp = AppStore.configSettings.getKeyMaxSize();
    		if (keyMaxLengthTemp > keyMaxLength)   { keyMaxLength = keyMaxLengthTemp; }
		}
			

		keyPaddingLength = keyMaxLength + 4;


		/////////////////////////////////////////////////////////////////
		// App Setting, Config Settings...
		/////////////////////////////////////////////////////////////////

		// App Settings /////////////////////////////////////////////////

		if (paramMap.containsKey (IAppConst.PARAM_APP_SETTINGS) || hasHelpAll) {
			
			descriptionStringList.add (MessageService.getFormattedMessage (null, new Message ("", "Application Settings")));
			descriptionStringList.add ("");
			
			if (AppStore.appSettings != null && AppStore.appSettings.size() > 0) {
				
				itemList = AppStore.appSettings.toStringList (keyPaddingLength);
				itemList.prependAll (IAppConst.TEXT_INDENT);
				
				descriptionStringList.add (itemList);	
			}
			else {
				
				descriptionStringList.add (IAppConst.TEXT_INDENT + "None");
			}
			
			descriptionStringList.add ("");
		}

		
		// Config Settings ///////////////////////////////////////////////
		
		if (paramMap.containsKey (IAppConst.PARAM_CONFIG_SETTINGS) || hasHelpAll) {
			
			descriptionStringList.add (MessageService.getFormattedMessage (null, new Message ("", "Config Settings")));
			descriptionStringList.add ("");
			
			if (AppStore.configSettings != null && AppStore.configSettings.size() > 0) {
			
				itemList = AppStore.configSettings.toStringList (keyPaddingLength);
				itemList.prependAll (IAppConst.TEXT_INDENT);
				
				descriptionStringList.add (itemList);	
			}
			else {
				
				descriptionStringList.add (IAppConst.TEXT_INDENT + "None");
			}
			
			descriptionStringList.add ("");
		}
		

		// Version History ///////////////////////////////////////////////
		
		if (paramMap.containsKey (IAppConst.PARAM_VERSION_HISTORY) || hasHelpAll) {
			
			descriptionStringList.add (MessageService.getFormattedMessage (null, new Message ("", "Version History")));
			descriptionStringList.add ("");
			
			if (AppStore.versionHistoryArrayArray != null && AppStore.versionHistoryArrayArray.length > 0) {
				
				itemList = new StringList (AppStore.versionHistoryArrayArray.length);
				
				for (i = 0; i < AppStore.versionHistoryArrayArray.length; i++) {
					
					versionArray = AppStore.versionHistoryArrayArray [i];
					itemList.add (IAppConst.TEXT_INDENT + StringUtils.padString (versionArray[0], ' ', 10) + StringUtils.padString (versionArray[1], ' ', 12) + versionArray[2]);
				}
				
				descriptionStringList.add (itemList);	
			}
			else {
				
				descriptionStringList.add (IAppConst.TEXT_INDENT + "None");
			}
			
			descriptionStringList.add ("");
		}
		

		// Invalid Parameters ////////////////////////////////////////////
		
		param = paramMap.get (IParamConst.PARAM_INVALID);
		
		if (param != null) {

			descriptionStringList.add (MessageService.getFormattedMessage (null, new Message ("", "The following are not valid parameters")));
			descriptionStringList.add ("");
			
			itemList = new StringList (param.getSubparamStringArray());
			itemList.prependAll (IAppConst.TEXT_INDENT);
				
		    descriptionStringList.add (itemList);	
		    descriptionStringList.add ("");
		}

		
		
		// Parameters With Errors ////////////////////////////////////////
		
		tempParamMap = ParamService.getErrorParamMap (paramMap);
		
		if (tempParamMap.size() > 0) {
			
			descriptionStringList.add (MessageService.getFormattedMessage (null, new Message ("", "The following parameters have errors")));
			descriptionStringList.add ("");
			
			itemList = ParamService.toStringListParamMap (tempParamMap, keyPaddingLength);
			itemList.prependAll (IAppConst.TEXT_INDENT + AppStore.paramDefPack.paramIdentifier);
				
		    descriptionStringList.add (itemList);
		    descriptionStringList.add ("");
		}
		
		
		return descriptionStringList;
	}
	
	
	public static boolean hasParamHelp (UaMap <Param> paramMap) {
		
		return paramMap.containsKey (IAppConst.PARAM_HELP) || paramMap.containsKey (IAppConst.PARAM_HELP2);
	}
	
	
	public static boolean hasStandardParam (UaMap <Param> paramMap) {
		
		return	paramMap.containsKey (IAppConst.PARAM_HELP) ||
				paramMap.containsKey (IAppConst.PARAM_HELP2) ||
				paramMap.containsKey (IAppConst.PARAM_HELP_ALL) ||
				paramMap.containsKey (IAppConst.PARAM_APP_INFO) ||
				paramMap.containsKey (IAppConst.PARAM_PARAMS) ||
				paramMap.containsKey (IAppConst.PARAM_APP_SETTINGS) ||
				paramMap.containsKey (IAppConst.PARAM_CONFIG_SETTINGS) ||
				paramMap.containsKey (IAppConst.PARAM_VERSION_HISTORY) ||
				paramMap.containsKey (IParamConst.PARAM_INVALID);
	}
	
	
	public static void showHelp (UaMap <Param> paramMap) {
		
		//////////////////////////////////////////////////////////////////
		// Declarations:
		//////////////////////////////////////////////////////////////////
		
		StringList			displayStringList	= null;
		
		
		//////////////////////////////////////////////////////////////////
		// Code:
		//////////////////////////////////////////////////////////////////
		
		displayStringList = getHelpDescriptionStringList (paramMap);
		
		if (displayStringList != null && displayStringList.size () > 0) {
			
			AppUI.print (displayStringList);
		}
	}
}
