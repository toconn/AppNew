package ua.core.service.environment;

import ua.core.util.StringList;


public class IEnvironmentConst {

	public static final String	ENVIRONMENT_APP_DATA_SHARED		= "Shared.App.Data.Dir";
	public static final String	ENVIRONMENT_APP_DATA_SHARED2	= "Shared_App_Data_Dir";
    public static final String	ENVIRONMENT_APP_DATA_DIR_USER	= "User.App.Data.Dir";
    public static final String	ENVIRONMENT_APP_DATA_DIR_USER2	= "User_App_Data_Dir";
    public static final String	APP_DATA_SUBDIRECTORY_ALL		= "All";
    
    public static final String	JAVA_PROPERTIES_APP_DIR			= "user.dir";
    public static final String	JAVA_OS_NAME					= "os.name";
    
    public static final StringList	PathParseSeparatorList		= new StringList (new String [] {";", ":"});
}
