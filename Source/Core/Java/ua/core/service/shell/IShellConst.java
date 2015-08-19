package ua.core.service.shell;

import ua.core.util.Message;


public interface IShellConst {

	public static final String	CONFIG_SETTINGS_DATA_FILE		= "Component.Shell.Config.File";
	
	public static final String DATA_FILE_DIR					= "All";
	public static final String DATA_FILE_NAME					= "shell.config"; 
	
	public static final String SHELL_DATA_PROPERTY_COMMAND		= "shell.";
	public static final String	SHELL_DATA_PROPERTY_EXTENSION	= "extension.";
	public static final String	SHELL_DATA_PROPERTY_LEAD		= "lead.";
	
	public static final String	SHELL_TYPE_AUDIO				= "Audio";
	public static final String	SHELL_TYPE_AUTO					= "Auto";	// Use auto-select by file type.
	public static final String	SHELL_TYPE_EXEC					= "Exec";	// Execute directly.
	public static final String	SHELL_TYPE_DIR					= "Dir";
	public static final String SHELL_TYPE_BROWSER				= "Browser";

	
	public enum ShellExecType {
		Audio,
		Delete,
		Exec
	};
	
	
	public static final Message	MESSAGE_COMMAND_TYPE_NOT_FOUND		= new Message ("S10010001", "Unknown shell type ''{0}''.");
	public static final Message	MESSAGE_FILE_TYPE_UNKNOWN			= new Message ("S10010003", "Unable to determine file type for ''{0}''.");
	public static final Message	MESSAGE_TYPE_REQUIRES_A_PARAMETER	= new Message ("To Do: Message ID", "The type ''{0}'' requires a parameter.");
}
