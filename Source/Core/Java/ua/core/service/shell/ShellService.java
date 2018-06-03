package ua.core.service.shell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ua.app.all.AppStore;
import ua.app.all.AppUI;
import ua.core.exceptions.*;
import ua.core.service.environment.EnvironmentService;
import ua.core.util.*;
import ua.core.util.file.FileUtils;
import ua.core.util.media.audio.AudioPlayer;


public class ShellService {
	
	/**
	 * Explicitly executes the command as it is. Does not attempt to match to shell names.
	 * 
	 * @param command
	 * @param windowStyle
	 */
	public static void execute (String command) throws ExceptionRuntime {
		
		execute (command, null);
	}
	
	
	public static void execute (String command, String parameters) throws ExceptionRuntime {

		// DECLARATIONS:

		List <String>	execParamList	= null;
		List <String>	paramList		= null;
		
		
		// MAIN:
		
		// Test if command exists...
		
		if (ParameterUtils.isNotFileExists (command)) {
		
			// Split execName...
			
			execParamList	= ParameterUtils.asParameterList (command);
		}
		else {
			
			// execName exists as a file (no included parameters).
			// Create list with just the one item...
			
			execParamList = new ArrayList <String>();
			execParamList.add (command);
		}
		
		
		// Split Params...
		
		if (StringUtils.isNonEmpty(parameters)) {

			if (ParameterUtils.isNotFileExists (parameters)) {
				
				// Split execName...
				
				paramList	= ParameterUtils.asParameterList (parameters);
			}
			else {
				
				// execName exists as a file (no included parameters).
				// Create list with just the one item...
				
				paramList = new ArrayList <String>();
				paramList.add (parameters);
			}
		}
		
		
		// Combine params (if any)...
		
		if (CollectionUtils.isNonEmpty (paramList)) {
			
			execParamList.addAll (paramList);
		}
		
		
		// Clean up parameters...
		
		execParamList = ParameterUtils.stripOuterQuotes (execParamList);
		execParamList = EnvironmentService.expandEnvironmentString (execParamList);
		
		
		// Execute...
		
		execute (execParamList);
	}
	
	
	public static void executeAudio (String fileName) {
		
		(new AudioPlayer()).playAsync (fileName);
	}
	
	
	/**
	 * Looks up the command for the given type and executes it without parameters.
	 * 
	 * Handles aliases to other shell command types.
	 * Ex: Execute command type "Text" which is aliased to "Notepad". This method will look up the command  
	 * for "Notepad" and execute it.
	 * 
	 * @param command
	 * @param parameters
	 * @param windowStyle
	 * @throws ExceptionInvalidAction 
	 * @throws ExceptionItemNotFound 
	 * @throws ExceptionRuntime 
	 */
	public static void executeAuto (String autoCommand) throws ExceptionInvalidAction, ExceptionRuntime, ExceptionItemNotFound {


		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		String command	= null;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////
		
		if (StringUtils.isEqual (autoCommand, IShellConst.SHELL_TYPE_AUDIO) || StringUtils.isEqual (autoCommand, IShellConst.SHELL_TYPE_AUTO) || StringUtils.isEqual (autoCommand, IShellConst.SHELL_TYPE_EXEC)) {
			
			// Execute Type requiring a parameter: No can do...
			
			throw new ExceptionInvalidAction (IShellConst.MESSAGE_TYPE_REQUIRES_A_PARAMETER.cloneMe (autoCommand));
			
		}
		else {
			
			// Check to see if shell command type...

			command = getTypeCommandFinal (autoCommand);
			
			if (command != null) {
				
				// Execute the command...
				
				execute (command);
			}


			// Check extension type...
			
			if (command == null) {
			
				command = getFileTypeCommandType (autoCommand);
	
				
				// Execute...
				
				if (command != null) {
					
					executeByType (command, autoCommand);
				}
			}
			
			
			// Direct command. Execute as is...
			
			if (command == null) {
				
				execute (autoCommand);
			}
		}
	}
	
	
	/**
	 * Looks up the appropriate command based on the file extension type or lead type and executes it passing the file name as a parameter.
	 * 
	 * @param command
	 * @param windowStyle
	 * @throws ExceptionRuntime 
	 * @throws ExceptionItemNotFound 
	 */
	public static void executeByFileType (String fileName) throws ExceptionRuntime, ExceptionItemNotFound {
		

		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		String	commandType	= null;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////
		
		commandType = getFileTypeCommandType (fileName);

		
		// Execute...
		
		if (commandType != null) {
			
			executeByType (commandType, fileName);
		}
		else {
			
			throw new ExceptionItemNotFound (IShellConst.MESSAGE_FILE_TYPE_UNKNOWN.cloneMe (fileName));
		}
	}
	
	
	/**
	 * Looks up the command for the given type and executes it without parameters.
	 * 
	 * Handles aliases to other shell command types.
	 * Ex: Execute command type "Text" which is aliased to "Notepad". This method will look up the command  
	 * for "Notepad" and execute it.
	 * 
	 * @param command
	 * @param parameters
	 * @param windowStyle
	 * @throws ExceptionItemNotFound 
	 * @throws ExceptionRuntime 
	 */
	public static void executeType (String commandType) throws ExceptionItemNotFound, ExceptionRuntime, ExceptionInvalidAction {


		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		String command	= null;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////
		
		if (StringUtils.isEqual (commandType, IShellConst.SHELL_TYPE_AUDIO) || StringUtils.isEqual (commandType, IShellConst.SHELL_TYPE_AUTO) || StringUtils.isEqual (commandType, IShellConst.SHELL_TYPE_EXEC)) {
			
			// Execute Type requiring a parameter: No can do...
			
			throw new ExceptionInvalidAction (IShellConst.MESSAGE_TYPE_REQUIRES_A_PARAMETER.cloneMe (commandType));
			
		}
		else {

			command = getTypeCommandFinal (commandType);
			
			if (command != null) {
				
				// Execute the command...
				
				execute (command);
			}
			else {
				
				throw new ExceptionItemNotFound (IShellConst.MESSAGE_COMMAND_TYPE_NOT_FOUND.cloneMe (command));
			}
		}
	}
	
	
	/**
	 * Looks up the command for the given type and executes it with the supplied parameters.
	 * 
	 * Handles aliases to other shell command types.
	 * Ex: Execute command type "Text" which is aliased to "Notepad". This method will look up the command  
	 * for "Notepad" if it finds that "Text" references it.
	 * 
	 * @param command
	 * @param parameters
	 * @param windowStyle
	 * @throws ExceptionItemNotFound 
	 * @throws ExceptionRuntime 
	 */
	public static void executeByType (String commandType, String parameters) throws ExceptionItemNotFound, ExceptionRuntime {


		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		String command	= null;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////
		
		if (StringUtils.isEqual (commandType, IShellConst.SHELL_TYPE_AUDIO)) {
			
			// Auto-detect the file type...
			
			executeAudio (parameters);
			
		}
		else if (StringUtils.isEqual (commandType, IShellConst.SHELL_TYPE_AUTO)) {
			
			// Auto-detect the file type...
			
			executeByFileType (parameters);
			
		}
		else if (StringUtils.isEqual (commandType, IShellConst.SHELL_TYPE_EXEC)) {
			
			// Execute the file...
			
			execute (parameters);
			
		}
		else {

			command = getTypeCommandFinal (commandType);
			
			if (command != null) {
				
				// Execute the command...
				
				execute (command, parameters);
			}
			else {
				
				throw new ExceptionItemNotFound (IShellConst.MESSAGE_COMMAND_TYPE_NOT_FOUND.cloneMe (command));
			}
		}
	}
	
	
	public static String getConfigFileName() {
		
		return Store.shell.getConfigFileName();
	}
	
	
	/**
	 * Returns the commandType for the given file based on extension or other inferred information.
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileTypeCommandType (String fileName) {
		
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		String	extension	= null;
		String	commandType	= null;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////

		// Check extension...
		
		extension = FileUtils.getExtension (StringUtils.stripQuotes (fileName));
		
		if (extension != null) {
			
			commandType = Store.shell.getExtensionCommandType (extension);
		}
		
		
		// Check lead characters (http:, etc.)...
		
		if (commandType == null) {
			
			commandType = Store.shell.getLeadCommandType (fileName);
		}
		
		
		return commandType;
	}
	
	
	/**
	 * Looks up the command type and returns the actual command. 
	 * Will dereference any internal aliasing and environment variables.
	 *
	 * Ex: command Type "Text", command "Notepad.exe".
	 * 
	 * @param commandType
	 * @return
	 */
	public static String getTypeCommandFinal (String commandType) {
		

		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		String	command		= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		command = Store.shell.getTypeCommand (commandType);
		
		
		// Check to see if the command type is aliased to another command. 
		
		if (StringUtils.isNonEmpty (command) && Store.shell.hasTypeCommand (command)) {
			
			// The command is an alias. Look up the actual command.
			
			command = Store.shell.getTypeCommand (command);
		}
		
		
		// Expand environment variables...
		
		if (StringUtils.isNonEmpty (command)) {
			
			command = EnvironmentService.expandEnvironmentString (command);
		}

		return command;
	}
	
	
	
	/**
	 * Returns true if the type is defined and has an associated command.
	 * 
	 * @param typeName
	 * @return
	 */
	public static boolean hasTypeAndCommand (String typeName) {
		
		
		return Store.shell.hasTypeCommand (typeName);
	}
	
	
	/**
	 * Initialize the shell service. Read shell properties from the shell configuration file.
	 * 
	 * @throws ExceptionRuntime
	 * @throws ExceptionConfigNotFound
	 */
	public static void init() throws ExceptionRuntime, ExceptionConfigNotFound {
		
		///////////////////////////////////////////
		// Declarations:
		///////////////////////////////////////////
	
		String			shellConfigFileNames		= null;
		StringList		shellConfigFileList			= null;
		UaProperties	shellProperties				= null;
		
		UaProperties	fileProperties				= null;
		
		UaProperties	shellCommandProperties		= null;
		UaProperties	shellExtensionProperties	= null;
		UaProperties	shellLeadProperties			= null;
		
		Message			errorMessage				= null;
		
		Shell			newShell					= null;
		
		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
		
		shellProperties				= new UaProperties();
		shellCommandProperties		= new UaProperties();
		shellExtensionProperties	= new UaProperties();
		shellLeadProperties			= new UaProperties();
	
		
		try {
			
			// Load shell data from the shell file.
			
			shellConfigFileList		= EnvironmentService.locateFileList (IShellConst.DATA_FILE_DIR, IShellConst.DATA_FILE_NAME);
			
			if (CollectionUtils.isNonEmpty (shellConfigFileList)) {
				
				shellConfigFileNames	= shellConfigFileList.toString();
			
				for (String fileName: shellConfigFileList) {
					
					fileProperties = new UaProperties();
					fileProperties.load (fileName);
					
					shellProperties.setProperties (fileProperties);	
				}
			}
		
			
			// Get shell command
			
			shellCommandProperties = shellProperties.getPropertiesSubset (IShellConst.SHELL_DATA_PROPERTY_COMMAND);
			
			// Get extensions
			
			shellExtensionProperties = shellProperties.getPropertiesSubset (IShellConst.SHELL_DATA_PROPERTY_EXTENSION);
			
			// Get leads
			
			shellLeadProperties = shellProperties.getPropertiesSubset (IShellConst.SHELL_DATA_PROPERTY_LEAD);


		}
		catch (ExceptionItemNotFound e) {

			// Log warning... file not found...
			
			errorMessage = IExceptionConst.MESSAGE_CONFIG_FILE_NOT_FOUND.cloneMe (new Object[] {shellConfigFileNames});
			
			throw new ExceptionConfigNotFound (errorMessage);
		}
		
		newShell = new Shell (shellConfigFileNames, shellCommandProperties, shellExtensionProperties, shellLeadProperties);
		Store.shell = newShell;
		AppStore.configSettings.setProperty (IShellConst.CONFIG_SETTINGS_DATA_FILE, shellConfigFileNames);
	}
	
	
	private static void execute (List <String> execParamList) {
		
		// DECLARATIONS:
		
		ProcessBuilder	processBuilder;
		
		
		// MAIN:

		// Execute:
				
		try {
			processBuilder	= new ProcessBuilder (execParamList);
			processBuilder.start();	
		}
		catch (IOException e) {

			new ExceptionRuntime (e);
			AppUI.print ("Command: " + execParamList.toString());
		}
	}
}
