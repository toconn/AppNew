package ua.app.appnew;

import java.util.ArrayList;
import java.util.List;

import ua.app.all.*;
import ua.core.service.parameters.IParamConst;
import ua.core.service.parameters.Param;
import ua.shared.appgenerator.AppGeneratorController;
import ua.shared.appgenerator.AppGeneratorUtils;
import ua.shared.appgenerator.IAppGeneratorConst;
import ua.shared.appgenerator.beans.AppGeneratorInfo;
import ua.core.exceptions.ExceptionRuntime;
import ua.core.exceptions.ExceptionValidation;
import ua.core.ui.all.ICompUI;
import ua.core.ui.console.ConsoleCompUI;
import ua.core.util.StringList;
import ua.core.util.StringUtils;
import ua.core.util.UaProperties;


public class AppNewApp implements IApp {


	public void config() throws ExceptionRuntime, ExceptionValidation {

	}
	
	
	public void init()  {

		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////

		AppStore.appInfo.setProperty (IAppConst.APP_NAME,			IAppNewConst.APP_NAME);
		AppStore.appInfo.setProperty (IAppConst.APP_VERSION,		IAppNewConst.APP_VERSION);
		AppStore.appInfo.setProperty (IAppConst.APP_AUTHOR,			IAppNewConst.APP_AUTHOR);
		AppStore.appInfo.setProperty (IAppConst.APP_CREATION_DATE,	IAppNewConst.APP_CREATION_DATE);
		AppStore.appInfo.setProperty (IAppConst.APP_BUILD_DATE,		IAppNewConst.APP_BUILD_DATE);
		AppStore.appInfo.setProperty (IAppConst.APP_BUILD_NUMBER,	IAppNewConst.APP_BUILD_NUMBER);

		AppStore.paramDefPack				= IAppNewConst.PARAM_DEF_PACK;
		AppStore.versionHistoryArrayArray	= IVersionHistory.VERSION_HISTORY_ARRAY_ARRAY;
	}
		
	
	public void run() throws ExceptionRuntime {

		//////////////////////////////////////////////////////////////////
		// Declarations:
		//////////////////////////////////////////////////////////////////
		
		Param					defaultParam		= null;
		Param					prefixParam			= null;
		
		AppGeneratorInfo		appGenInfo			= null;
		UaProperties			appProperties		= null;
		
		ICompUI					compUI				= null;
		AppGeneratorController	appGeneratorControl	= null;
		
		boolean					isSuccessful		= false;
				
		
		//////////////////////////////////////////////////////////////////
		// Code:
		//////////////////////////////////////////////////////////////////
		
		// get default parameter...
		
		defaultParam	= AppStore.paramMap.get (IParamConst.PARAM_DEFAULT);
		prefixParam		= AppStore.paramMap.get (IAppNewConst.PARAM_PREFIX);
		
		try {
			
			
			// User Info Request Options...
			
			if (AppStore.paramMap.hasKey (IAppNewConst.PARAM_LIST_TEMPLATES)) {
				
				showTemplates();
			}
			
			
			
			// Generator Options...
			
			appGenInfo		= new AppGeneratorInfo();			
			appProperties	= new UaProperties();
			
			
			if (defaultParam != null && defaultParam.hasSubParams()) {
			

				// Get User Parameters...
						
				// Template Name...
				appGenInfo.setTemplateName (defaultParam.getSubParam(0));
				
				// App Name...
				appGenInfo.setAppName (getAppName(defaultParam));
				
				// Properties - User Override...
				
				if (prefixParam != null && prefixParam.hasSubParams()) {
					
					appProperties.setProperty (IAppGeneratorConst.TAG_PROJECT_DIR_PREFIX, prefixParam.getSubParam (0));
				}
					
				
				appGenInfo.setAppProperties (appProperties);
				
				
				// Call Generator...
				
				compUI = new ConsoleCompUI (AppStore.appLocale);

				appGeneratorControl	= new AppGeneratorController (compUI, appGenInfo);
				isSuccessful		= appGeneratorControl.createApp();
				
				
				// Check results...
				
				if (isSuccessful) {
					
					AppUI.print();
					AppUI.print (IAppNewConst.MESSAGE_APPLICATION_CREATED.cloneMe (appGenInfo.getAppName()));
					AppUI.print ("Target directory: \"" + appGeneratorControl.getTargetDirectory() + "\"");
				}
				else {
					
					AppUI.print();
					AppUI.print (IAppNewConst.MESSAGE_OPERATION_FAILED);
				}
			}
			
				
		}
		catch (ExceptionValidation e) {
			
			AppUI.print (e.getMessageList());
		}
	}
	
	
	public void showTemplates() {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		StringList	templateNameList	= null;

		StringList	displayList			= null;

		
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		// Retrieve...
		
		templateNameList	= AppGeneratorUtils.getGeneratorNameList();
		
		
		// Format...
		
		displayList			= new StringList();
		
		displayList.add ("Generator Template Names:");
		displayList.add ("");
		
		for (String name: templateNameList) {
			
			displayList.add (IAppConst.TEXT_INDENT + name);
		}
		
		
		// Print...

		AppUI.print (displayList);
	}
	
	private String getAppName (Param defaultParam) {

		String[] paramArray;
		List <String> paramList;
		boolean isFirst;
		
		
		paramArray = defaultParam.getSubparamStringArray();
		
		paramList = new ArrayList <String>();
		isFirst=true;

		for (String paramString : paramArray) {
			if (isFirst) {
				// Skip 1st.
				isFirst = false;
			}
			else {
				paramList.add (paramString);
			}
		}
		
		return StringUtils.merge(paramList, " ");
	}	
}