package ua.shared.appgenerator;

import ua.core.util.Message;


public interface IAppGeneratorConst {

	// Configuration File Settings...
	
	public String		APP_CONFIG_FILE_DIR					= "AppNew";
	public String		APP_CONFIG_FILE_NAME				= "AppNew.Config";
	
	public String		BACKUP_FILE_NAME					= "Backup";
	
	public String		TEMPLATE_CONFIG_FILE_NAME			= "Template.Config";
	
	public String		CONFIG_SECTION_COPY_DIR				= "Copy Directories";
	public String		CONFIG_SECTION_CREATE_DIR			= "Create Directories";
	public String		CONFIG_SECTION_PROCESS_TEMPLATES	= "Process Templates";
	public String		CONFIG_SECTION_TAGS					= "Tags";

	public String		TAG_APP_NAME						= "AppName"; 
	public String		TAG_APP_NAME_DOTS					= "AppNameDots"; 
	public String		TAG_APP_NAME_DOTS_LOWER_CASE		= "AppNameDotsLowerCase";
	public String		TAG_APP_NAME_HYPHENS				= "AppNameHyphens"; 
	public String		TAG_APP_NAME_HYPHENS_LOWER_CASE		= "AppNameHyphensLowerCase";
	public String		TAG_APP_NAME_UNDERSCORES			= "AppNameUnderscores"; 
	public String		TAG_APP_NAME_UNDERSCORES_LOWER_CASE	= "AppNameUnderscoresLowerCase";
	public String		TAG_APP_NAME_NO_SPACES				= "AppNameNoSpaces"; 
	public String		TAG_APP_NAME_NO_SPACES_LOWER_CASE	= "AppNameNoSpacesLowerCase";
	
	public String		TAG_DATE_NOW						= "DateNow";
	public String		TAG_DATE_NOW_DASH					= "DateNowDash";
	public String		TAG_DATE_NOW_DOT					= "DateNowDot";

	public String		TAG_PROJECT_DIR						= "ProjectDir";
	public String		TAG_PROJECT_DIR_PREFIX				= "ProjectDirPrefix";
	public String		TAG_PROJECT_DIR_REF					= "ProjectDirRef";
	
	public String		TAG_DEPLOYMENT_NAME					= "DeploymentName"; 
	public String		TAG_DEPLOYMENT_LANGUAGE				= "DeploymentLanguage"; 
	public String		TAG_DEPLOYMENT_UI					= "DeploymentUI"; 
	
	
	// Messages...
	
	public Message		MESSAGE_APP_NAME_REQUIRED					= new Message ("To Do: Id", "The application name is required.");
	public Message		MESSAGE_GENERATOR_NAME_REQUIRED				= new Message ("To Do: Id", "The generator template name is required.");
	
	public Message		MESSAGE_INVALID_TARGET_LANGUAGE				= new Message ("To Do: Id", "Invalid target language \"{0}\".");
	public Message		MESSAGE_INVALID_TARGET_UI					= new Message ("To Do: Id", "Invalid target UI \"{0}\".");
	
	public Message		MESSAGE_INVALID_CONFIGURATION				= new Message ("To Do: Id", "Invalid target UI \"{0}\".");
	public Message		MESSAGE_INVALID_TAG							= new Message ("To Do: Id", "Invalid tag \"{0}\".");
	
	public Message		MESSAGE_ERROR_COPYING_DIRECTORIES			= new Message ("To Do: Id", "Error copying directories.");
	public Message		MESSAGE_ERROR_CREATING_DIRECTORIES			= new Message ("To Do: Id", "Error creating directories.");
	public Message		MESSAGE_ERROR_PROCESSING_TEMPLATES			= new Message ("To Do: Id", "Error processing templates.");
	public Message		MESSAGE_ERROR_INVALID_TEMPLATE_FILE			= new Message ("To Do: Id", "Template error in file \"{0}\".");
	public Message		MESSAGE_ERROR_SOURCE_DIRECTORY_NOT_FOUND	= new Message ("To Do: Id", "Source directory \"{0}\" not found.");
	
	public Message		MESSAGE_LOADING_CONFIGURATION				= new Message ("To Do: Id", "Loading configuration...");
	public Message		MESSAGE_PROCESSING_TAGS						= new Message ("To Do: Id", "Processing tags...");
	public Message		MESSAGE_CREATING_DIRECTORIES				= new Message ("To Do: Id", "Creating directories...");
	public Message		MESSAGE_COPYING_DIRECTORIES					= new Message ("To Do: Id", "Copying files...");
	public Message		MESSAGE_PROCESSING_TEMPLATES				= new Message ("To Do: Id", "Generating template files...");
	public Message		MESSAGE_PROCESSING_DONE						= new Message ("To Do: Id", "Files Created.");
	
	public Message		MESSAGE_PROCESSING_FAILED					= new Message ("To Do: Id", "Operation failed.");
}
