package ua.shared.appgenerator;

import java.io.IOException;
import java.util.HashMap;

import ua.app.all.AppUI;
import ua.core.service.environment.EnvironmentService;
import ua.shared.appgenerator.beans.AppGenerator;
import ua.shared.appgenerator.beans.AppGeneratorInfo;
import ua.shared.freemarker.FreeMarkerComp;
import ua.core.exceptions.ExceptionItemNotFound;
import ua.core.exceptions.ExceptionRuntime;
import ua.core.exceptions.ExceptionValidation;
import ua.core.exceptions.IExceptionConst;
import ua.core.exceptions.ValidationUtils;
import ua.core.ui.all.ICompUI;
import ua.core.util.Message;
import ua.core.util.NVStringPair;
import ua.core.util.StringUtils;
import ua.core.util.UaProperties;
import ua.core.util.dates.DateUtils;
import ua.core.util.file.DirectoryUtils;
import ua.core.util.file.FileUtils;
import ua.core.util.filereader.DataReader;


public class AppGeneratorController {
	
	AppGenerator	appGenerator	= null;
	ICompUI			compUI			= null;
	String			targetDirectory = null;
	

	public AppGeneratorController (ICompUI compUI, AppGeneratorInfo generatorInfo) throws ExceptionValidation {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		AppGenerator		appGenerator	= null;
		
		ExceptionValidation	exceptionVal	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
		
		this.compUI	= compUI;
		
		
		// Validate settings..........
		
		if (generatorInfo == null) {
			
			throw new ExceptionValidation (IExceptionConst.MESSAGE_CONFIG_NOT_FOUND);
		}
		
		
		if (StringUtils.isEmpty (generatorInfo.getAppName()))
			
			exceptionVal = ValidationUtils.addMessage (exceptionVal, IAppGeneratorConst.MESSAGE_APP_NAME_REQUIRED);


		if (StringUtils.isEmpty (generatorInfo.getTemplateName()))
			
			exceptionVal = ValidationUtils.addMessage (exceptionVal, IAppGeneratorConst.MESSAGE_APP_NAME_REQUIRED);

		
		// Load deployment...
		
		if (exceptionVal == null) {

			// Store settings.
			
			appGenerator = new AppGenerator();
			
			appGenerator.setAppName				(generatorInfo.getAppName());
			appGenerator.setGeneratorName		(generatorInfo.getTemplateName());
			appGenerator.setAppTagProperties	(generatorInfo.getAppProperties());
			
			this.appGenerator = appGenerator;

			setGeneratorDefaults();		
		}
		else {
			
			// Error. Throw exception...
			
			throw exceptionVal;
		}
	}
	
	
	private void loadConfig (String configName, String configFileName) throws ExceptionValidation, ExceptionRuntime {

		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////
		
		DataReader <AppGenerator>		dataReader		= null;
		AppTemplateConfigLineReader		lineReader		= null;
		
		ExceptionValidation				exceptionVal	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
		
		// Load data...
		
		try {
		
			lineReader	= new AppTemplateConfigLineReader();
			dataReader	= new DataReader <AppGenerator> (lineReader);
			dataReader.read (configFileName, this.appGenerator);
		}
		catch (ExceptionItemNotFound e) {
			
			throw new ExceptionValidation (e.getMessageList());
		}

		
		// Check for errors...
		
		if (lineReader.hasError()) {
			
			exceptionVal = lineReader.getExceptionValidation();
			exceptionVal.addFirstMessage (IExceptionConst.MESSAGE_CONFIG_FILE_CONTAINS_ERRORS.cloneMe (configName));
			
			throw exceptionVal;
		}
	}
	
	
	
	/**
	 * Loads the configuration information from the standard application configuration file.
	 * 
	 * @throws ExceptionValidation
	 * @throws ExceptionRuntime
	 */
	private void loadConfigApp() throws ExceptionValidation, ExceptionRuntime {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////
		
		String		configName		= null;
		String		configFileName	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
		
		// Find configuration file...
		
		configName		= IAppGeneratorConst.APP_CONFIG_FILE_NAME;
		configFileName	= AppGeneratorUtils.getAppGeneratorConfigFile();
		
		if (configFileName == null) {
			
			throw new ExceptionValidation (IExceptionConst.MESSAGE_CONFIG_FILE_NOT_FOUND.cloneMe (configName));
		}
		
		
		// Use the generator configuration loader to load default settings...
		
		loadConfig (configName, configFileName);
	}
	
	
	private void loadConfigDeployment() throws ExceptionValidation, ExceptionRuntime {
		
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////
		
		String		templateConfigFileName	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
		
		// Find configuration file...
		
		templateConfigFileName = AppGeneratorUtils.getGeneratorDeploymentFile (this.appGenerator.getGeneratorDir());

		if (! FileUtils.isFileExists (templateConfigFileName))
			
			throw new ExceptionValidation (IExceptionConst.MESSAGE_CONFIG_FILE_NOT_FOUND.cloneMe (IAppGeneratorConst.TEMPLATE_CONFIG_FILE_NAME));
		
		
		// Load the deployment configuration...
		
		loadConfig (IAppGeneratorConst.TEMPLATE_CONFIG_FILE_NAME, templateConfigFileName);
	}
	
	
	/**
	 * Creates the application.
	 * 
	 * will return true if successful.
	 * 
	 * @return
	 */
	
	public boolean createApp() throws ExceptionRuntime {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		ExceptionValidation	exceptionVal	= null;
		
		boolean				hasError		= false;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		
		try {
		
			
			this.compUI.updateStatus (new Message ("", "Creating new app '" + this.appGenerator.getAppName() + "'..."));
			this.compUI.updateStatus (new Message ("", "Using template " + this.appGenerator.getGeneratorName() + "..."));
			
			// ///////////////////////////////////////////////////////////////
			//   Load Configuration
			// ///////////////////////////////////////////////////////////////

			this.compUI.updateStatus (IAppGeneratorConst.MESSAGE_LOADING_CONFIGURATION);
			
			
			// Set deployment directory...
			
			// this.appGenerator.setGeneratorDirName (this.appGenerator.getGeneratorName());
			

			// Load the application config...
			
			try {
				
				loadConfigApp();
			}
			catch (ExceptionValidation e) {
				
				exceptionVal = e;
			}
			
			
			// Load the deployment data...

			try {
				
				loadConfigDeployment();
			}
			catch (ExceptionValidation e) {
				
				exceptionVal = ValidationUtils.merge (e, exceptionVal);
			}
			
						
			if (exceptionVal != null)
				
				throw exceptionVal;
	
			
			// ///////////////////////////////////////////////////////////////
			//   Creating Application...
			// ///////////////////////////////////////////////////////////////
			
			// Process Tags...
			
			this.compUI.updateStatus (IAppGeneratorConst.MESSAGE_PROCESSING_TAGS);
			
			processTags();
			
			
			// Create Directories...
			
			this.compUI.updateStatus (IAppGeneratorConst.MESSAGE_CREATING_DIRECTORIES);
			
			createDirectories();
			
			
			// Copy Directories...
			
			this.compUI.updateStatus (IAppGeneratorConst.MESSAGE_COPYING_DIRECTORIES);
			
			copyDirectories();


			
			// Process Templates...
			
			this.compUI.updateStatus (IAppGeneratorConst.MESSAGE_PROCESSING_TEMPLATES);
			
			processTemplates();
			
			
			// Done.

			this.compUI.updateStatus (IAppGeneratorConst.MESSAGE_PROCESSING_DONE);


		}
		catch (ExceptionValidation e) {
			
			hasError = true;
			this.compUI.reportValidationError (e);
			
		}
		
		
		return ! hasError;
	}
	
	
	@SuppressWarnings ("rawtypes")
	public void createDirectories() throws ExceptionValidation {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		HashMap		dataMap			= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
		
		try {

			// Get dataHash
			
			dataMap = getProcessedTagDataMap();
			
			
			for (String directoryName: this.appGenerator.getDirectoryCreateStringList()) {
				
				directoryName = getProcessedValue (dataMap, directoryName);
				
				DirectoryUtils.createDirectory (directoryName);
			}

		}
		catch (ExceptionValidation e) {
			
			e.addFirstMessage (IAppGeneratorConst.MESSAGE_ERROR_CREATING_DIRECTORIES);
			
			throw e;
		}
	}
	
	
	@SuppressWarnings ("rawtypes")
	public void copyDirectories() throws ExceptionValidation {
		
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		HashMap				dataMap			= null;
		
		String				sourceDirName	= null;
		String				targetDirName	= null;
		
		ExceptionValidation	exceptionVal	= null;

		
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
		
		try {

			// Get dataHash
			
			dataMap = getProcessedTagDataMap();
			
			
			for (NVStringPair dirNVPair: this.appGenerator.getDirectoryCopyNVList()) {
				
				sourceDirName = getProcessedValue (dataMap, dirNVPair.getName());
				targetDirName = getProcessedValue (dataMap, dirNVPair.getValue());
				
				try {
					
					sourceDirName = getSourceDirectory (sourceDirName);
				}
				catch (ExceptionValidation e) {

					if (exceptionVal != null)
					
						exceptionVal.addMessageList (e.getMessageList());
					
					else
						
						exceptionVal = e;
				}
				
				DirectoryUtils.copyDirectory (sourceDirName, targetDirName);
			}
			
			
			// Check for exceptions...
			
			if (exceptionVal != null) {
				
				throw exceptionVal;
			}
		}
		catch (ExceptionItemNotFound e) {
			
			exceptionVal = new ExceptionValidation (e.getMessageList());
			exceptionVal.addFirstMessage (IAppGeneratorConst.MESSAGE_ERROR_COPYING_DIRECTORIES);
			
			throw exceptionVal;
		}
		catch (IOException e) {
			
			exceptionVal = new ExceptionValidation (IExceptionConst.MESSAGE_EMPTY_VALUE.cloneMe (e.getMessage()));
			exceptionVal.addFirstMessage (IAppGeneratorConst.MESSAGE_ERROR_COPYING_DIRECTORIES);
			
			throw exceptionVal;
		}
		catch (ExceptionValidation e) {
			
			e.addFirstMessage (IAppGeneratorConst.MESSAGE_ERROR_COPYING_DIRECTORIES);
			
			throw e;
		}
		
	}
		
	
	@SuppressWarnings ({ "rawtypes" })
	private HashMap getProcessedTagDataMap() {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		FreeMarkerComp		freeMarkerComp		= null;
		
		HashMap				dataMap				= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		
		// Get dataMap
		
		freeMarkerComp = new FreeMarkerComp();
		
		dataMap = freeMarkerComp.getTemplateDataMap (this.appGenerator.getProcessedTagProperties());
		
		freeMarkerComp.setDefaultDataTags (dataMap);
		
		return dataMap;
	}
	
	
	/**
	 * Returns the environment variable expanded and internal tag processed version of the value.
	 * 
	 * @param dataMap
	 * @param value
	 * @return
	 * @throws ExceptionValidation
	 */
	@SuppressWarnings ("rawtypes")
	private String getProcessedValue (HashMap dataMap, String value) throws ExceptionValidation {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////
		
		FreeMarkerComp		freeMarkerComp		= null;

		String				processedValue		= null;
		
		
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		freeMarkerComp = new FreeMarkerComp();
		
		processedValue = EnvironmentService.expandEnvironmentString (value);
		processedValue = freeMarkerComp.processToString (processedValue, dataMap);
		
		return processedValue;
		
	}
	
	
	private String getSourceDirectory (String sourceDirName) throws ExceptionValidation {
		
		// locate source file...
		
		if (! DirectoryUtils.isDirectoryExists (sourceDirName))
			
			// Not absolute path. Check relative to the deployment source directory...
			
			if (DirectoryUtils.isDirectoryExists (FileUtils.getFileName (this.appGenerator.getGeneratorDir(), sourceDirName)))
				
				sourceDirName = FileUtils.getFileName (this.appGenerator.getGeneratorDir(), sourceDirName);
			
			else
				
				// Directory does not exist.
				
				throw new ExceptionValidation (IAppGeneratorConst.MESSAGE_ERROR_SOURCE_DIRECTORY_NOT_FOUND.cloneMe (sourceDirName));
		
		
		return sourceDirName;
	}
	
	
	/**
	 * Returns the general tags.
	 * 
	 * Ensures that free marker default tags are added.
	 * 
	 * @return
	 */
	@SuppressWarnings ({ "rawtypes" })
	private HashMap getTagDataMap() {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////
		
		FreeMarkerComp		freeMarkerComp		= null;

		HashMap				dataMap				= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		
		// Get dataMap
		
		freeMarkerComp = new FreeMarkerComp();

		dataMap = freeMarkerComp.getTemplateDataMap (this.appGenerator.getTagProperties());
		
		freeMarkerComp.setDefaultDataTags (dataMap);
		
		return dataMap;
	}
	
	public String getTargetDirectory() {
		
		String targetDirectory;
		
		targetDirectory = this.appGenerator.getProcessedTagProperties().getProperty("ProjectDir");

		return targetDirectory;
	}
	
	@SuppressWarnings ("rawtypes")
	public void processTemplates() throws ExceptionValidation {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////
		
		FreeMarkerComp		freeMarkerComp		= null;

		HashMap				dataMap				= null;
		
		String				targetFileName		= null;
		String				templateFileName	= null;
		
		ExceptionValidation	exceptionVal		= null;
		
		
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
		
		try {

			freeMarkerComp = new FreeMarkerComp();
			
			
			// Get dataHash
			
			dataMap = getProcessedTagDataMap();
			
			
			for (NVStringPair nvPair: this.appGenerator.getTemplateNVList()) {
				
				try {
				
					templateFileName	= getProcessedValue (dataMap, nvPair.getName());
					targetFileName		= getProcessedValue (dataMap, nvPair.getValue());
				}
				catch (ExceptionValidation e) {
					
					exceptionVal = ValidationUtils.addMessage (exceptionVal, IAppGeneratorConst.MESSAGE_INVALID_TAG.cloneMe (templateFileName));
				}
				
				// Prepend the deployment path to the template directory...
				
				templateFileName	= FileUtils.getFileName (this.appGenerator.getGeneratorDir(), templateFileName);
				
				// Process the template...
				
				try {
				
					freeMarkerComp.processToFile (templateFileName, targetFileName, dataMap);
				}
				catch (ExceptionValidation e) {
					
					exceptionVal = ValidationUtils.addMessage (exceptionVal, IAppGeneratorConst.MESSAGE_ERROR_INVALID_TEMPLATE_FILE.cloneMe (nvPair.getName()));
				}
				catch (Exception e) {
					
					exceptionVal = ValidationUtils.addMessage (exceptionVal, IAppGeneratorConst.MESSAGE_ERROR_INVALID_TEMPLATE_FILE.cloneMe (nvPair.getName()));
				}
			}
			
			
			// Handle any errors...
			
			if (exceptionVal != null)
				
				throw exceptionVal;

		}
		catch (ExceptionValidation e) {
			
			e.addFirstMessage (IAppGeneratorConst.MESSAGE_ERROR_PROCESSING_TEMPLATES);
			
			throw e;
		}
		
	}
	
	/**
	 * processes the tags for environment variables and embedded tags in the values.
	 * 
	 * The results are stored in appNew.processedTagProperties.
	 * 
	 * @throws ExceptionValidation
	 */
	@SuppressWarnings ({ "rawtypes", "unchecked" })
	public void processTags() throws ExceptionValidation {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////
		
		FreeMarkerComp		freeMarkerComp		= null;

		HashMap				dataMap				= null;
		UaProperties		processedTagsProps	= null;
		
		String				tagValue			= null;
		
		ExceptionValidation	exceptionVal		= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
		
		
		// Move app tags (tags passed in from the app) to TagProperties...
		
		copyAppTagsToGeneralTags();
		
		freeMarkerComp = new FreeMarkerComp();

		
		// Get dataMap
		
		dataMap = getTagDataMap();
		
		freeMarkerComp.setDefaultDataTags (dataMap);
		

		// Loop through properties and store in data Hash
		
		for (NVStringPair nvPair: this.appGenerator.getTagProperties()) {
		
			try {
			
				// Process for environment variables and internal tags..
				
				tagValue = getProcessedValue (dataMap, nvPair.getValue());
				
				
				// Store...
				
				dataMap.put (nvPair.getName(), tagValue);
				
			}
			catch (ExceptionValidation e) {
				
				// record error. handle later.
				
				
				// Debug Remove: //////////////////////////////////////////////
				AppUI.print (nvPair.getName() + ":     " + nvPair.getValue());
				AppUI.print();
				// Debug Remove: //////////////////////////////////////////////
				
				exceptionVal = ValidationUtils.addMessage (exceptionVal, IAppGeneratorConst.MESSAGE_INVALID_TAG.cloneMe (nvPair.getName()));
			}
		}
		
		
		// Loop through properties again but this time store in processedTagProperties.
		
		if (exceptionVal == null) {
			
			processedTagsProps = this.appGenerator.getProcessedTagProperties();
	
			for (NVStringPair nvPair: this.appGenerator.getTagProperties()) {
				
				try {
				
					// Process for environment variables and internal tags..
					
					tagValue = getProcessedValue (dataMap, nvPair.getValue());
					
					
					// Store...
					
					processedTagsProps.setProperty (nvPair.getName(), tagValue);
					
				}
				catch (ExceptionValidation e) {
					
					// record error. handle later.
					
					exceptionVal = ValidationUtils.addMessage (exceptionVal, IAppGeneratorConst.MESSAGE_INVALID_TAG.cloneMe (nvPair.getName()));
				}
			}
		}	


		// Process Project.Dir.Ref
		// Loop through properties again but this time store in processedTagProperties.
		
		if (exceptionVal == null)
	
			if (this.appGenerator.getTagProperties().hasProperty (IAppGeneratorConst.TAG_PROJECT_DIR_REF)) {
				
				tagValue = this.appGenerator.getTagProperties().getProperty (IAppGeneratorConst.TAG_PROJECT_DIR_REF);
				
				processedTagsProps.setProperty (IAppGeneratorConst.TAG_PROJECT_DIR_REF, freeMarkerComp.processToString (tagValue, dataMap));
			}
			
		
		if (exceptionVal != null) {
			
			throw exceptionVal;
		}
		
	}
	
	
	/**
	 * Sets the default values in the app generator object.
	 * 
	 * Locates the actual generator directory.
	 * Creates application name tags in all the correct forms.
	 * 
	 */
	private void setGeneratorDefaults() {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		String	appName						= null;
		String	appNameDots					= null;
		String	appNameDotsLowerCase		= null;
		String	appNameHyphens				= null;
		String	appNameHyphensLowerCase		= null;
		String	appNameNoSpaces				= null;
		String	appNameNoSpacesLowerCase	= null;
		String	appNameUnderscores			= null;
		String	appNameUnderscoresLowerCase	= null;

		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
		
		// Locate Template Directory...
		
		this.appGenerator.setGeneratorDir			(AppGeneratorUtils.getGeneratorDirectory (this.appGenerator.getGeneratorName()));
		
		
		// Set App Name...
		
		appName = this.appGenerator.getAppName();
		
		this.appGenerator.getProcessedTagProperties	().setProperty (IAppGeneratorConst.TAG_APP_NAME, appName);
		this.appGenerator.getTagProperties			().setProperty (IAppGeneratorConst.TAG_APP_NAME, appName);

		
		// Set App Name - Dots...
		
		appNameDots = appName.replace(' ', '.');
		
		this.appGenerator.getProcessedTagProperties().setProperty (IAppGeneratorConst.TAG_APP_NAME_DOTS, appNameDots);
		this.appGenerator.getTagProperties().setProperty (IAppGeneratorConst.TAG_APP_NAME_DOTS, appNameDots);

		
		// Set App Name - Dots, Lower Case
		
		appNameDotsLowerCase = appNameDots.toLowerCase();
		
		this.appGenerator.getProcessedTagProperties().setProperty (IAppGeneratorConst.TAG_APP_NAME_DOTS_LOWER_CASE, appNameDotsLowerCase);
		this.appGenerator.getTagProperties().setProperty (IAppGeneratorConst.TAG_APP_NAME_DOTS_LOWER_CASE, appNameDotsLowerCase);


		// Set App Name - Hyphens...
		
		appNameHyphens = appName.replace(' ', '-');
		
		this.appGenerator.getProcessedTagProperties().setProperty (IAppGeneratorConst.TAG_APP_NAME_HYPHENS, appNameHyphens);
		this.appGenerator.getTagProperties().setProperty (IAppGeneratorConst.TAG_APP_NAME_HYPHENS, appNameHyphens);

		
		// Set App Name - Hyphens, Lower Case
		
		appNameHyphensLowerCase = appNameHyphens.toLowerCase();
		
		this.appGenerator.getProcessedTagProperties().setProperty (IAppGeneratorConst.TAG_APP_NAME_HYPHENS_LOWER_CASE, appNameHyphensLowerCase);
		this.appGenerator.getTagProperties().setProperty (IAppGeneratorConst.TAG_APP_NAME_HYPHENS_LOWER_CASE, appNameHyphensLowerCase);


		// Set App Name - No Spaces...
		
		appNameNoSpaces = StringUtils.strip (appName, ' ');
		
		this.appGenerator.getProcessedTagProperties().setProperty (IAppGeneratorConst.TAG_APP_NAME_NO_SPACES, appNameNoSpaces);
		this.appGenerator.getTagProperties().setProperty (IAppGeneratorConst.TAG_APP_NAME_NO_SPACES, appNameNoSpaces);

		
		// Set App Name - No Spaces, Lower Case
		
		appNameNoSpacesLowerCase = appNameNoSpaces.toLowerCase();
		
		this.appGenerator.getProcessedTagProperties().setProperty (IAppGeneratorConst.TAG_APP_NAME_NO_SPACES_LOWER_CASE, appNameNoSpacesLowerCase);
		this.appGenerator.getTagProperties().setProperty (IAppGeneratorConst.TAG_APP_NAME_NO_SPACES_LOWER_CASE, appNameNoSpacesLowerCase);

		
		// Set App Name - Underscores...
		
		appNameUnderscores = appName.replace(' ', '_');
		
		this.appGenerator.getProcessedTagProperties().setProperty (IAppGeneratorConst.TAG_APP_NAME_UNDERSCORES, appNameUnderscores);
		this.appGenerator.getTagProperties().setProperty (IAppGeneratorConst.TAG_APP_NAME_UNDERSCORES, appNameUnderscores);

		
		// Set App Name - Underscores, Lower Case
		
		appNameUnderscoresLowerCase = appNameUnderscores.toLowerCase();
		
		this.appGenerator.getProcessedTagProperties().setProperty (IAppGeneratorConst.TAG_APP_NAME_UNDERSCORES_LOWER_CASE, appNameUnderscoresLowerCase);
		this.appGenerator.getTagProperties().setProperty (IAppGeneratorConst.TAG_APP_NAME_UNDERSCORES_LOWER_CASE, appNameUnderscoresLowerCase);
		
		
		// Set Today's Date:
		
		this.appGenerator.getTagProperties().setProperty(IAppGeneratorConst.TAG_DATE_NOW, DateUtils.getISODate());
		this.appGenerator.getTagProperties().setProperty(IAppGeneratorConst.TAG_DATE_NOW_DASH, DateUtils.getISODashDate());
		this.appGenerator.getTagProperties().setProperty(IAppGeneratorConst.TAG_DATE_NOW_DOT, DateUtils.getISODotDate());
	}
	
	
	/**
	 * Copies the calling application tags into the general tags (stored in tagProperties).
	 * 
	 */
	private void copyAppTagsToGeneralTags() {
	
		
		for (NVStringPair vPair: this.appGenerator.getAppTagProperties()) {
			
			this.appGenerator.getTagProperties().setProperty (vPair);
		}
	}
}
