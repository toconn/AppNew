package ua.app.all;

import java.util.Locale;

import ua.core.service.parameters.ParamDef;
import ua.core.util.Message;

public interface IAppConst {

	// Constant Declarations...
	
	public static final String APP_NAME					= "App.Name";
	public static final String APP_VERSION				= "App.Version";
	public static final String APP_CREATION_DATE		= "App.Creation.Date";
	public static final String APP_BUILD_DATE			= "App.Build.Date";
	public static final String APP_BUILD_NUMBER			= "App.Build.Number";
	public static final String APP_AUTHOR               = "App.Author";
	
	public static final String APP_CONFIG_FILE_NAME		= "App.Config.File.Name";
	public static final String APP_CONFIG_FILE			= "App.Config.File";
	
	public static final String DIR_APP					= "Dir.App";
	public static final String DIR_WORKING				= "Dir.Working";
	public static final String DIR_DATA_USER_APPS		= "Dir.Data.User.Apps";

	public static final String PARAM_IDENTIFIER			= "-";
	
    public static final String PARAM_APP_INFO			= "AppInfo";
    public static final String PARAM_APP_SETTINGS		= "AppSettings";
    public static final String PARAM_CONFIG_SETTINGS	= "ConfigSettings";		
    public static final String PARAM_HELP				= "Help";
    public static final String PARAM_HELP2				= "?";
    public static final String PARAM_HELP_ALL			= "HelpAll";
    public static final String PARAM_PARAMS				= "Params";
    public static final String PARAM_VERSION_HISTORY	= "VersionHistory";
    
    public static final String TEXT_INDENT				= "    ";
    
    public static final ParamDef PARAM_DEF_ARRAY_STANDARD[] = {
    	
    	new ParamDef (PARAM_HELP,				0, new Message ("A10000010", "Show program options.")),
    	new ParamDef (PARAM_HELP2,				0, new Message ("A10000011", "Same as Help.")),
    	new ParamDef (PARAM_HELP_ALL,			0, new Message ("A10000012", "View all help and application information.")),
    	new ParamDef (PARAM_APP_INFO,			0, new Message ("A10000013", "View application version info.")),
    	new ParamDef (PARAM_APP_SETTINGS,		0, new Message ("A10000014", "View the application settings.")),
    	new ParamDef (PARAM_CONFIG_SETTINGS,	0, new Message ("A10000015", "View the configuration settings.")),
    	new ParamDef (PARAM_PARAMS,				0, new Message ("A10000016", "View the parameters sent to the program at startup.")),
    	new ParamDef (PARAM_VERSION_HISTORY,	0, new Message ("A10000017", "View the application version history.")),
    };
    
    public static final Locale LOCALE_DEFAULT			= Locale.US;
    
    
    public static final Message MESSAGE_ERROR_INITIALIZING_APP	= new Message ("To Do: id", "Error initializing {0}.");
}
