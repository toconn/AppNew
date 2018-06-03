package ua.shared.appgenerator;

import java.io.File;
import java.util.ArrayList;

import ua.core.service.environment.EnvironmentService;
import ua.core.util.StringList;
import ua.core.util.StringUtils;
import ua.core.util.UaMap;
import ua.core.util.file.DirectoryUtils;
import ua.core.util.file.FileUtils;


public class AppGeneratorUtils {

	
	/**
	 * Return the full file name for the standard app generator config file.
	 * 
	 * @return
	 */
	public static String getAppGeneratorConfigFile() {
		
		return EnvironmentService.locateFile (IAppGeneratorConst.APP_CONFIG_FILE_DIR, IAppGeneratorConst.APP_CONFIG_FILE_NAME);
	}
	
	
	/**
	 * Return the list of all app generator config directories.
	 * 
	 * An empty list will be returned if nothing is found.
	 * 
	 * @return
	 */
	public static StringList getAppGeneratorConfigPathList() {
		
		return EnvironmentService.locateDirectory (IAppGeneratorConst.APP_CONFIG_FILE_DIR);
	}

	
	/**
	 * Return the full path to the generator deployment file.
	 * 
	 * @param templateName
	 * @return
	 */
	public static String getGeneratorDeploymentFile (String generatorDirectory) {
		
		return FileUtils.getFileName (generatorDirectory, IAppGeneratorConst.TEMPLATE_CONFIG_FILE_NAME);
	}

	
	/**
	 * Return the full path to the given generator name. All app generator config directories
	 * are searched.
	 * 
	 * @param generatorName
	 * @return
	 */
	public static String getGeneratorDirectory (String generatorName) {
		
		return EnvironmentService.locateFile (IAppGeneratorConst.APP_CONFIG_FILE_DIR, generatorName);
	}
	
	
	/**
	 * Return a list of all available App Generator names.
	 * 
	 * All app generator config directories are searched.
	 * 
	 * Returns an emty list if none are found.
	 * 
	 * @return
	 */
	public static StringList getGeneratorNameList() {
		

		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		StringList			configPathList		= null;
		
		UaMap <String>		generatorNameMap	= null;		// Use a map here to eliminate duplicates. Duplicate templates will be ignored anyway.
		ArrayList <File>	dirFileList			= null;
		
		String				dirName				= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		configPathList = getAppGeneratorConfigPathList();
		
		
		generatorNameMap	= new UaMap <String>();
		
		
		for (String configPath: configPathList) {
		
			dirFileList = DirectoryUtils.getDirectoryDirectoryList (FileUtils.getFileName (configPath, IAppGeneratorConst.APP_CONFIG_FILE_DIR));
			
			for (File dirFile: dirFileList) {
				
				dirName = dirFile.getName();
				
				// Add to name map as long as it isn't a backup directory...
				
				if (! StringUtils.isEqual (dirName, IAppGeneratorConst.BACKUP_FILE_NAME)) {
					
					generatorNameMap.add (dirName, dirName);
				}
			}
		}
		
		
		return generatorNameMap.getSortedKeyList();	// Return map keys. Not interested in the map data.
	}
}
