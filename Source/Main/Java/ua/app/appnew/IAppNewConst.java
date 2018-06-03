package ua.app.appnew;
import ua.app.all.IAppConst;
import ua.core.service.parameters.ParamDef;
import ua.core.service.parameters.ParamDefPack;
import ua.core.util.Message;

public interface IAppNewConst {


	//////////////////////////////////////////////////////////////////
	// App Info
	//////////////////////////////////////////////////////////////////
		
	public String	APP_NAME 					= "AppNew";
	public String	APP_VERSION 				= "1.00.08";

	public String	APP_CREATION_DATE			= "2011-07-22";
	public String	APP_BUILD_DATE				= "2018-04-21";
	public String	APP_BUILD_NUMBER			= "10";

	public String	APP_AUTHOR					= "Tadhg Ua Conaill";


	
	//////////////////////////////////////////////////////////////////
	// App Constants
	//////////////////////////////////////////////////////////////////
	
	public static final	String	DATA_FILE_DIR	= "AppNew";
		

	//////////////////////////////////////////////////////////////////
	// Parameters
	//////////////////////////////////////////////////////////////////
	
	public Message	PARAM_DESCRIPTION_MESSAGE	= new Message ("A10010000", "{0} TemplateName AppName [-List] [-Prefix DirPrefix] [-Help]", APP_NAME);
	
	public String	PARAM_LIST_TEMPLATES		= "List";
	public String	PARAM_PREFIX				= "Prefix";
	
    public ParamDef	PARAM_DEFS[]	= {
    	
    	new ParamDef (PARAM_LIST_TEMPLATES,	0, false,	new Message ("To Do: Message Id", 	"List all templates.")),
    	new ParamDef (PARAM_PREFIX,			1, false,	new Message ("To Do: Message Id", 	"Sets the prefix to the project directory name (form Prefix + AppName)")),
    };
    
	public ParamDefPack	PARAM_DEF_PACK = new ParamDefPack (PARAM_DESCRIPTION_MESSAGE, IAppConst.PARAM_DEF_ARRAY_STANDARD, PARAM_DEFS, IAppConst.PARAM_IDENTIFIER, true);

	
	//////////////////////////////////////////////////////////////////
	// Messages
	//////////////////////////////////////////////////////////////////

	
	public static Message	MESSAGE_APPLICATION_CREATED	= new Message ("To Do: Id", "{0} created.");
	public static Message	MESSAGE_OPERATION_FAILED	= new Message ("To Do: Id", "Operation failed.");
}
