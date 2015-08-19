package ua.core.service.environment;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ua.app.all.AppStore;
import ua.app.all.IAppConst;
import ua.core.util.*;
import ua.core.util.file.FileUtils;

public class EnvironmentService {
	
	
	/**
	 * Returns a list of strings with the string environment variables expanded.
	 * 
	 * Null safe (returns empty list).
	 * 
	 * @param textList
	 * @return
	 */
	public static List <String> expandEnvironmentString (List <String> textList) {
		
		// DECLARATIONS:
		
		List <String>	list;
		
		
		// MAIN:
		
		list = new ArrayList <String> ();
		
		if (CollectionUtils.isNonEmpty (textList)) {
			
			for (String text : textList) {
				list.add (expandEnvironmentString (text));
			}
		}
		
		return list;
	}
	

    /** Process a string and replace all %environment% tokens with the 
     * actual environment variable values (if they exist). 
     * 
     * @param envString
     */
    public static String expandEnvironmentString (String envString) {
    	

		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

    	String			envName					= null;
    	String			envValue				= null;
    	
		StringBuilder	expandedStringBuilder	= null;
		
		int				previousIndex			= 0;
		int				nextIndex				= 0;
		
		boolean			startingPercentFound	= false;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		if (StringUtils.isNonEmpty (envString)) {
			
			expandedStringBuilder = new StringBuilder ();
			
			while (nextIndex > -1 && previousIndex < envString.length ()) {
				
		    	// find next %
				
				nextIndex = envString.indexOf ('%', previousIndex);
				
				if (nextIndex > -1) {
		    	
					if (! startingPercentFound) {
						
						// if opening %...
		    	
						// append previous text to return string
						
						expandedStringBuilder.append (envString.substring (previousIndex, nextIndex));
						
						
		    			// update previous index...
					
						previousIndex = nextIndex + 1;
						startingPercentFound = true;
						
					}
					else {
		    		
						// if closing %...
						
						// lookup environment variable...
						
						if (previousIndex < nextIndex - 1) {
						
							envName  = envString.substring (previousIndex, nextIndex);
							envValue = System.getenv (envName);
						}
						else {
							
							envValue = null;
						}
		    	
		    			if (StringUtils.isNonEmpty (envValue)) {
		    				
		    				// Append environment value...
		    				
		    				expandedStringBuilder.append (envValue);
		    			}
		    			else {
		    				
		    				// Has no value. Append as it was in original string...
		    				
		    				expandedStringBuilder.append (envString.substring (previousIndex -1, nextIndex + 1));
		    			}

		    			
		    			// update previous index...
						
						previousIndex = nextIndex + 1;
						startingPercentFound = false;
					}
				}

			}
			
			if (previousIndex < envString.length ()) {
				
				// Append remaining text
				expandedStringBuilder.append (envString.substring (previousIndex));
			}
	    	
	    	// return string
				
			return expandedStringBuilder.toString ();
			
		}
	    else {
	    	
	    	return null;
	    }
    }
    
    
    /**
	 * Returns the application directory. 
	 * Important: The method getAppDir (Class) has to be called first.
	 * 
	 * Depends on AppStore, IAppConst
	 * 
	 * @return
	 */
	public static String getAppDirectory () {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		String appDirectory = null;
		
		
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
		
		if (AppStore.appSettings.hasProperty (IAppConst.DIR_APP)) {
			
			appDirectory = AppStore.appSettings.getProperty (IAppConst.DIR_APP);
		}
		else {
			
			appDirectory = getAppDir (EnvironmentService.class);
		}
		
		return appDirectory;
	}
	
	
	public static String getEnvironmentVariable (String variableName) {
		
		return System.getenv (variableName);
	}
	
	
	public static String getAppDir (Class <?> appClass) {
		
		// Return the directory the application executable / source is located.
		
		//////////////////////////////////////////////////////////////////
		// Declarations:
		//////////////////////////////////////////////////////////////////
		
		String		className			= null;
		URL			classURL			= null;
		String		directoryName		= null;
		
		int			trimStart			= 0;
		int			trimEnd				= 0;
		
		
		//////////////////////////////////////////////////////////////////
		// Code:
		//////////////////////////////////////////////////////////////////

		className		= appClass.getSimpleName() + ".class";
		classURL		= appClass.getResource (className);
		directoryName	= classURL.getPath();
		
		
		// Find starting trim location...
		
		if (directoryName.contains (":")) {
			
			// Contains URL Desrciptor. Remove it.
			
			trimStart = directoryName.lastIndexOf (':') - 1;
		}
		else {
			
			// Remove leading '/'.
			
			trimStart = 1;
		}
			
		
		
		// Find ending trim location...
		
		if (directoryName.contains ("!")) {
			
			// Is in a jar file. 
			
			// Find '!' location in name...
				
			trimEnd = directoryName.indexOf ('!');
		
			// Find previous '/'.
			
			trimEnd = directoryName.lastIndexOf ('/', trimEnd - 1);
		
		}
		else {
			
			// should be a file.
			
			// Trim to class name.
			
			trimEnd = directoryName.length () - appClass.getName().length() - 7;
		}
		
		
		// Trim and format directory...
		
		directoryName	= directoryName.substring (trimStart, trimEnd);
		directoryName	= URLUtils.decode (directoryName);

		
		// Change forward slashes to back slashes...
		
		if (System.getProperty (IEnvironmentConst.JAVA_OS_NAME).contains("Windows")) {
			
			directoryName = FileUtils.formatFileToWindows (directoryName);
		}


		return directoryName;
	}
	
	/**
	 * Return the user application data directory (directory where the application stores its config information).
	 * 
	 * @return
	 */
    public static String getAppDataAllDir () {
    	
    	// Declarations
    	
    	String	 value;
    	
    	
    	// Main
    	
    	value = System.getenv (IEnvironmentConst.ENVIRONMENT_APP_DATA_SHARED);
    	
    	if (StringUtils.isEmpty(value)) {
    		
    		value = System.getenv (IEnvironmentConst.ENVIRONMENT_APP_DATA_SHARED2);
    	}
    	
        return value;
    }


    /**
     * Return the default user application data directory (directory where the application stores its config information).
     * 
     * Parses the all application data directory environment variable for multiple directories and returns the first.
     * 
     * Note: Returns null if it can't find anything.
     * 
     * @return
     */
    public static String getAppDataAllDirDefault () {
    	
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

    	StringList	directoryPathList	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
    	
    	directoryPathList = FileUtils.expandPathToList (getAppDataAllDir());
  
    	if (directoryPathList != null && directoryPathList.size () > 0) {
    		
    		return directoryPathList.get (0);
    	}
    	else {
    		
    		return null;
    	}
    }
    

    /**
     * Parses the all application data directory (directory where the application stores its config information) and
     * return it as a list. 
     * 
     * @return
     */
    public static StringList getAppDataAllDirList () {
    	
    	return FileUtils.expandPathToList (getAppDataAllDir());
    }


    /**
     * Return the user application data directory (directory where the application stores its config information).
     * 
     * @return
     */
    public static String getAppDataUserDir () {
    	
    	// Declarations
    	
    	String	 value;
    	
    	
    	// Main
    	
    	value = System.getenv (IEnvironmentConst.ENVIRONMENT_APP_DATA_DIR_USER);
    	
    	if (StringUtils.isEmpty(value)) {
    		
    		value = System.getenv (IEnvironmentConst.ENVIRONMENT_APP_DATA_DIR_USER2);
    	}
    	
    	return value;
    }


    /**
     * Return the default user application data directory (directory where the application stores its config information).
     * 
     * Parses the user application data directory environment variable for multiple directories and returns the first.
     * 
     * Note: Returns null if it can't find anything.
     * 
     * @return
     */
    public static String getAppDataUserDirDefault () {
    	
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

    	StringList	directoryPathList	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
    	
    	directoryPathList = FileUtils.expandPathToList (getAppDataUserDir());
  
    	if (directoryPathList != null && directoryPathList.size () > 0) {
    		
    		return directoryPathList.get (0);
    	}
    	else {
    		
    		return null;
    	}
    }

    
    /**
     * Parses the user application data directory (directory where the application stores its config information) and
     * return it as a list. 
     * 
     * @return
     */
    public static StringList getAppDataUserDirList () {
    	
         return FileUtils.expandPathToList (getAppDataUserDir());
    }
    

    public static String getAppDataUserAllDir () {
    	
    	// Return the generic all application config directory.

        return getAppDataUserDir() + FileUtils.getFileSeparator() + IEnvironmentConst.APP_DATA_SUBDIRECTORY_ALL;
    }
    

    /**
     * Return the working directory (the directory where the program was launched from).
     * 
     * @return
     */
	public static String getWorkingDir () {
		
		return System.getProperty (IEnvironmentConst.JAVA_PROPERTIES_APP_DIR);
	}
	

	/**
	 * Returns a list of path locations where the directory can be found.
	 * 
	 * The current directory is searched first, then all Apps.Data.Directory paths are searched.
	 * 
	 * @param directoryName
	 * @return
	 */
	public static StringList locateDirectory (String directoryName) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

    	boolean		directoryFound			= false;
    	
    	String		fullPath				= null;
    	StringList	directcoryPathList		= null;
    	
    	StringList	directorySearchList		= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

    	
    	directcoryPathList = new StringList();
    	
    	
        // Check Application Executable Directory...

    	fullPath = getAppDirectory ();

        if (FileUtils.isFileExists (FileUtils.getFileName (fullPath, directoryName))) {

        	directcoryPathList.add (fullPath);
        	
            return directcoryPathList;
        }


        // Check User App Data Subdirectory Directories...

        directorySearchList = getAppDataUserDirList();
        
        for (String path: directorySearchList) {
        	
        	fullPath = FileUtils.getFileName (path, directoryName);

	        if (FileUtils.isFileExists  (fullPath)) {
	
	        	directcoryPathList.add (path);
	        	directoryFound = true;
	        }
        }


        // Check Shared App Data Subdirectory Directories...
        
		directorySearchList = getAppDataAllDirList();
		        
        for (String path: directorySearchList) {
        	
        	fullPath = FileUtils.getFileName (path, fullPath);

        	if (FileUtils.isFileExists  (fullPath)) {

        		directcoryPathList.add (path);
	        	directoryFound = true;
        	}
        }


        // Not found...

        if (directoryFound) {
        	
        	return directcoryPathList;
        }
        else {
        	
        	return null;
        }	
	}
	
	/**
	 * Locate a file in the current directory or in the AppData/All subdirectory.
	 * Returns the full path and file name.
	 *
     * The AppData directory is defined by the environment variable Apps.Data.Directory.All.
	 * 
	 * @param fileName
	 * @return
	 */
    public static String locateFile (String fileName) {

        return locateFile (IEnvironmentConst.APP_DATA_SUBDIRECTORY_ALL, fileName);
    }
	
	
    /** 
     * Locate a file in either the current directory in the supplied subdirectory of AppData directory.
     * Returns the full path and file name.
     * 
     * The AppData directory is defined by the environment variable Apps.Data.Directory.All.
     * 
     * @param appDataSubdirectory
     * @param fileName
     * @return
     */
    public static String locateFile (String appDataSubdirectory, String fileName) {

        String directoryName = null;

        // Attempt to locate the correct directory...
        
        directoryName = locateFileDirectory (appDataSubdirectory, fileName);
        
        
        // If found, append the file name to the directory and return it...

        if (StringUtils.isNonEmpty (directoryName)) {

            return directoryName + FileUtils.getFileSeparator() + fileName;

        }
        else {

        	// Not found. Return nothing.
        	
            return null;
        }
    }


    public static String locateFileDirectory (String appDataSubdirectory, String fileName) {

		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

    	String		fileDirectory			= null;
    	StringList	directorySearchList		= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

    	
        // Check Application Executable Directory...

        fileDirectory = getAppDirectory ();

        if (FileUtils.isFileExists (FileUtils.getFileName (fileDirectory, fileName))) {

            return fileDirectory;
        }


        // Check User App User Subdirectory Data Directories...

        directorySearchList = FileUtils.expandPathToList (getAppDataUserDir());
        
        for (String directory: directorySearchList) {
        	
        	fileDirectory = FileUtils.getFileName (directory, appDataSubdirectory);

	        if (FileUtils.isFileExists (FileUtils.getFileName (fileDirectory, fileName))) {
	
	        	return fileDirectory;
	        }
        }


        // Check Shared App All Data Subdirectory Directories...
        
		directorySearchList = FileUtils.expandPathToList (getAppDataAllDir());
		        
        for (String directory: directorySearchList) {
        	
        	fileDirectory = FileUtils.getFileName (directory, appDataSubdirectory);

        	if (FileUtils.isFileExists  (FileUtils.getFileName (fileDirectory, fileName))) {

        		return fileDirectory;
        	}
        }


        // Not found...

        return null;
    }
    
    
	/**
	 * Locate one or more files of the same name in either the current directory or in one or more AppData/All subdirectories.
	 *
     * The AppData directory is defined by the environment variable Apps.Data.Directory.All. Directories are separated by ;.
	 * 
	 * @param fileName
	 * @return
	 */
    public static StringList locateFileList (String fileName) {

        return locateFileList (IEnvironmentConst.APP_DATA_SUBDIRECTORY_ALL, fileName);
    }
	
	
    /** 
     * Locate one or more files of the same name in either the current directory or in one or more of the supplied subdirectory of AppData directory.
     * 
     * The AppData directory is defined by the environment variable Apps.Data.Directory.All. Directories are separated by ;.
     * 
     * @param appDataSubdirectory
     * @param fileName
     * @return
     */
    public static StringList locateFileList (String appDataSubdirectory, String fileName) {

		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

    	StringList directoryNameList	= null;
        StringList fileNameList			= null;

        
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
        
        directoryNameList	= locateFileDirectoryList (appDataSubdirectory, fileName);
        fileNameList		= FileUtils.getFileNameList (directoryNameList, fileName);

        return fileNameList;
    }
    
    

    /**
     * Return one or more directories where the file is located. If in the current directory,
     * no other directories will be searched. Otherwise all possible locations will be searched
     * and all matching files will be returned.
     * 
     * Searches the current directory, the user app subdirectories, the user all subdirectories.
     * 
     * Note: Returns null if none are found.
     * 
     * Note: App User files are placed first over App All files in the returned list.
     * 
     * @param appDataSubdirectory
     * @param fileName
     * @return
     */
    public static StringList locateFileDirectoryList (String appDataSubdirectory, String fileName) {

		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

    	boolean		directoryFound			= false;
    	
    	String		fileDirectory			= null;
    	StringList	fileDirectoryList		= null;
    	
    	StringList	directorySearchList		= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

    	
    	fileDirectoryList = new StringList();
    	
        // Check Application Executable Directory...

        fileDirectory = getAppDirectory ();

        if (FileUtils.isFileExists (FileUtils.getFileName (fileDirectory, fileName))) {

        	fileDirectoryList.add (fileDirectory);
        	
            return fileDirectoryList;
        }


        // Check User App User Subdirectory Data Directories...

        directorySearchList = FileUtils.expandPathToList (getAppDataUserDir());
        
        if (CollectionUtils.isNonEmpty(directorySearchList)) {

	        for (String directory: directorySearchList) {
	        	
	        	fileDirectory = FileUtils.getFileName (directory, appDataSubdirectory);
	
		        if (FileUtils.isFileExists  (FileUtils.getFileName (fileDirectory, fileName))) {
		
		        	fileDirectoryList.add (fileDirectory);
		        	directoryFound = true;
		        }
	        }
    	}
    

        // Check Shared App All Data Subdirectory Directories...
        
		directorySearchList = FileUtils.expandPathToList (getAppDataAllDir());
		        
        if (CollectionUtils.isNonEmpty(directorySearchList)) {

	        for (String directory: directorySearchList) {
	        	
	        	fileDirectory = FileUtils.getFileName (directory, appDataSubdirectory);
	
	        	if (FileUtils.isFileExists  (FileUtils.getFileName (fileDirectory, fileName))) {
	
	        		fileDirectoryList.add (fileDirectory);
		        	directoryFound = true;
	        	}
	        }
        }


        // Not found...

        if (directoryFound) {
        	
        	return fileDirectoryList;
        }
        else {
        	
        	return null;
        }
    }
}
