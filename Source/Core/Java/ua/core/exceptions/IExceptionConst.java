package ua.core.exceptions;

import ua.core.util.Message;

public interface IExceptionConst {

	public static final Message MESSAGE_UNFORMATTED						= new Message ("", "{0}");	// Used for system errors, other messages not from the application.
	
	public static final Message MESSAGE_CONFIG_NOT_FOUND				= new Message ("C0000005",			"Configuration not found.");
	public static final Message MESSAGE_CONFIG_FILE_NOT_FOUND			= new Message ("C0000008",			"Configuration file \"{0}\" not found.");
	public static final Message MESSAGE_CONFIG_FILE_CONTAINS_ERRORS		= new Message ("To Do: Message Id", "Configuration file \"{0}\" contains errors.");
	public static final Message MESSAGE_CONFIG_PROPERTIES_MISSING		= new Message ("To Do: Message Id", "Configuration properties are missing from file \"{0}\".");
	public static final Message MESSAGE_CONFIG_PROPERTY_NOT_FOUND		= new Message ("To Do: Message Id", "Configuration property \"{0}\" not found in file \"{1}\".");
	public static final Message MESSAGE_EMPTY_VALUE						= new Message ("C0000006",			"Empty value.");
	public static final Message MESSAGE_INVALID_ACTION					= new Message ("To Do: Message Id",	"Invalid Action.");
	public static final Message MESSAGE_ITEM_ALREADY_EXISTS				= new Message ("C0000007",			"Item already exists.");
	public static final Message MESSAGE_ITEM_NOT_FOUND_1				= new Message ("C0000001",			"Item not found.");
	public static final Message MESSAGE_ITEM_NOT_FOUND_2				= new Message ("C0000002",			"Item \"{0}\" not found.");
	public static final Message MESSAGE_FILE_NAME						= new Message ("To Do: Message Id", "File name \"{0}\".");
	public static final Message MESSAGE_FILE_NOT_FOUND					= new Message ("C0000003",			"File \"{0}\" not found.");
	public static final Message MESSAGE_NULL_POINTER					= new Message ("C0000009",			"Null pointer reference.");
	public static final Message MESSAGE_PROPERTY_NOT_FOUND				= new Message ("To Do: Message Id", "The configuration property \"{0}\" is missing.");
	public static final Message MESSAGE_RUNTIME_ERROR					= new Message ("C0000004",			"Error {0}: {1}");
	
	public static final Message MESSAGE_ERROR_RUNTIME_1					= new Message ("S10010001",			"Exception      : {0}"); 
	public static final Message MESSAGE_ERROR_RUNTIME_2 				= new Message ("S10010002",			"Exception:"); 
	public static final Message MESSAGE_ERROR_RUNTIME_3 				= new Message ("S10010003",			"{0}Level      : {1}"); 
	public static final Message MESSAGE_ERROR_RUNTIME_4 				= new Message ("S10010004",			"{0}Class      : {1}"); 
	public static final Message MESSAGE_ERROR_RUNTIME_5 				= new Message ("S10010005",			"{0}Method     : {1}"); 
	public static final Message MESSAGE_ERROR_RUNTIME_6					= new Message ("S10010006",			"{0}Message    : {1}"); 

}
