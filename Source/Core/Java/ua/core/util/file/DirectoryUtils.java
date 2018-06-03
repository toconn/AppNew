package ua.core.util.file;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

import ua.core.exceptions.ExceptionItemNotFound;
import ua.core.exceptions.IExceptionConst;
import ua.core.util.StringList;
import ua.core.util.UaList;

public class DirectoryUtils {

	/**
	 * Copies the entire folder from the source directory to the target diretory.
	 * 
	 * @param sourceDirectory
	 * @param targetDirectory
	 * @throws IOException 
	 * @throws ExceptionItemNotFound 
	 */
	public static void copyDirectory (String sourceDirectory, String targetDirectory) throws IOException, ExceptionItemNotFound {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		File		sourceDirFile	= null;
		File		targetDirFile	= null;
		
		
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		sourceDirFile = new File (sourceDirectory);
		targetDirFile = new File (targetDirectory);
		
		copyDirectory (sourceDirFile, targetDirFile);
	}
	
	
	/**
	 * Copies the entire directory to the target directory.
	 * 
	 * @param sourceDirectory
	 * @param targetDirectory
	 * @throws IOException
	 * @throws ExceptionItemNotFound 
	 */
	public static void copyDirectory (File sourceDirectory, File targetDirectory) throws IOException, ExceptionItemNotFound {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		String[]	dirList		= null;

		File		sourceFile	= null;
		File		targetFile	= null;
		
		
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		if (! sourceDirectory.exists())
			
			throw new ExceptionItemNotFound (IExceptionConst.MESSAGE_FILE_NOT_FOUND.cloneMe (sourceDirectory.getName()));
			
		
		if (sourceDirectory.isDirectory()) {
			
			// Create destination directory...
			
			if (! targetDirectory.exists())
				
				targetDirectory.mkdir();
			
			
			// Read source directory...
			
			dirList = sourceDirectory.list();
			
			// Copy contents...
			
			for (String dirFile: dirList) {
				
				sourceFile = new File (sourceDirectory,	dirFile);
				targetFile = new File (targetDirectory,	dirFile);
				
				copyDirectory (sourceFile, targetFile);
			}
		}
		else
			
			FileUtils.copyFile (sourceDirectory, targetDirectory);
		
	}
	
	
	public static void createDirectory (String path) {

		// //////////////////////////////////////////////////////////////////////////
		// Declarations:
		// //////////////////////////////////////////////////////////////////////////

		File file = null;

		// //////////////////////////////////////////////////////////////////////////
		// Code:
		// //////////////////////////////////////////////////////////////////////////

		file = new File (path);

		if (!file.exists()) {

			file.mkdirs();
		}

	}

	
	/**
	 * Searches a list if paths to find where a directory is located.
	 * Will return either the path to the directory or null if nothing is found.
	 * 
	 * @param pathList
	 * @param directoryName
	 * @return
	 */
	public static String findDirectoryPath (StringList pathList, String directoryName) {
    	
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		String	directoryPath	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		
    	for (String pathName: pathList) {
    		
    		if (isDirectoryExists (FileUtils.getFileName (pathName, directoryName))) {
    		
    			directoryPath = pathName;
    			break;
    		}
    	}
    	
    	return directoryPath;
    }
    
    
    /** 
     * Finds one or more paths containing a directory from a list of possible paths.
     * Will return either the path list or null if nothing is found.
     * 
     * @param pathList
     * @param directoryName
     * @return
     */
    public static StringList findDirectoryPathRList (StringList pathList, String directoryName) {
    	
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		StringList	directoryPathList	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		
    	for (String pathName: pathList) {
    		
    		if (isDirectoryExists (FileUtils.getFileName (pathName, directoryName))) {
    			
    			if (directoryPathList == null)
    				
    				directoryPathList = new StringList();
    		
    			directoryPathList.add (pathName);
    			break;
    		}
    	}
    	
    	return directoryPathList;
    }


	/**
	 * Returns a listing of subdirectories only.
	 * 
	 * 
	 * @param directoryName
	 * @param includeSubdirectories
	 * @return
	 */
	public static ArrayList <File> getDirectoryDirectoryList (String directory) {
		
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		File			directoryFile		= null;
		UaList <File>	directoryFileList	= null;
		
		
		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////
		
		// Get directory listing...

		directoryFile		= new File (directory);
		directoryFileList	= new UaList <File> (directoryFile.listFiles(new FileFilterDirectory()));
		
		
		return directoryFileList;
	}


	/**
	 * Returns a listing of subdirectories only.
	 * 
	 * 
	 * @param directoryName
	 * @param includeSubdirectories
	 * @return
	 */
	public static ArrayList <File> getDirectoryDirectoryList (String directory, boolean includeSubdirectories) {
		
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		File			directoryFile		= null;
		UaList <File>	directoryFileList	= null;
		
		
		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////
		
		// Get directory listing...

		directoryFile		= new File (directory);
		directoryFileList	= new UaList <File> (directoryFile.listFiles(new FileFilterDirectory()));
		
		
		// If include subdirectories...
		
		if (includeSubdirectories) {
		
			for (File subDirFile : directoryFileList.cloneMe()) {
				
				directoryFileList.addAll (getDirectoryDirectoryList (subDirFile.getPath(), includeSubdirectories));
			}
		}
		
		return directoryFileList;
	}


	/**
	 * Get the directory listing for the directory.
	 * 
	 * 
	 * @param directory
	 * @param includeSubdirectories
	 * @return
	 */
	public static ArrayList <File> getDirectoryFileList (String directory) {

		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		File			directoryFile		= null;
		UaList <File>	directoryFileList	= null;
		
		
		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////
		
		// Get directory listing...

		directoryFile		= new File (directory);
		directoryFileList	= new UaList <File> (directoryFile.listFiles());

		return directoryFileList;
	}

	
	/**
	 * Get the directory listing for the directory.
	 * Optionally include subdirectories as well.
	 * 
	 * 
	 * @param directory
	 * @param includeSubdirectories
	 * @return
	 */
	public static ArrayList <File> getDirectoryFileList (String directory, boolean includeSubdirectories) {

		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		File			directoryFile		= null;
		UaList <File>	directoryFileList	= null;
		
		
		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////
		
		// Get directory listing...

		directoryFile		= new File (directory);
		directoryFileList	= new UaList <File> (directoryFile.listFiles());
		
		
		// If include subdirectories...
		
		if (includeSubdirectories) {
		
			for (File subFile : directoryFileList.cloneMe()) {
				
				if (subFile.isDirectory()) {
					
					directoryFileList.addAll (getDirectoryFileList (subFile.getPath(), includeSubdirectories));
				}
			}
		}
		
		return directoryFileList;
	}


	/**
	 * Return a filtered file listing of the directory.
	 * Optionally includes subdirectories.
	 * 
	 */
	public static ArrayList <File> getDirectoryFileList (String directory, boolean includeSubdirectories, FilenameFilter filter) {

		// ////////////////////////////////////////////////////////////////
		// Declarations
		// ////////////////////////////////////////////////////////////////

		ArrayList <File>	directoryFileList		= null;

		ArrayList <File>	directoryDirectoryList	= null;

		
		// ////////////////////////////////////////////////////////////////
		// Code
		// ////////////////////////////////////////////////////////////////
		
		// Get the current directory's files...
		
		directoryFileList	= getDirectoryFileList (directory, filter);
		
		
		// Check the subdirectories...
		
		if (includeSubdirectories) {
			
			directoryDirectoryList = getDirectoryDirectoryList (directory);
			
			for (File directoryFile : directoryDirectoryList) {
				
				directoryFileList.addAll (getDirectoryFileList (directoryFile.getPath(), includeSubdirectories, filter));
			}
		}

		return directoryFileList;
	}


	/**
	 * Get the directory listing for the current directory.
	 * 
	 * 
	 * @param directory
	 * @param includeSubdirectories
	 * @return
	 */
	public static ArrayList <File> getDirectoryFileList (String directory, FilenameFilter filter) {

		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		File			directoryFile		= null;
		UaList <File>	directoryFileList	= null;
		
		
		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////
		
		// Get directory listing...

		directoryFile		= new File (directory);
		directoryFileList	= new UaList <File> (directoryFile.listFiles (filter));

		return directoryFileList;
	}

	
	/**
	 * Return a file listing of the directory.
	 * Returns a string list only.
	 * 
	 */
	public static StringList getDirectoryFileStringList (String directory) {

		// ////////////////////////////////////////////////////////////////
		// Declarations
		// ////////////////////////////////////////////////////////////////

		File		directoryFile	= null;
		StringList	dirStringList	= null;

		
		// ////////////////////////////////////////////////////////////////
		// Code
		// ////////////////////////////////////////////////////////////////

		directoryFile	= new File (directory);
		dirStringList	= new StringList (directoryFile.list());

		return dirStringList;
	}

	
	/**
	 * Return a filtered file listing of the directory.
	 * Returns a string list only.
	 * 
	 */
	public static StringList getDirectoryFileStringList (String directory, FilenameFilter filter) {

		// ////////////////////////////////////////////////////////////////
		//   Declarations
		// ////////////////////////////////////////////////////////////////

		File		directoryFile	= null;
		StringList	dirStringList	= null;

		
		// ////////////////////////////////////////////////////////////////
		//   Code
		// ////////////////////////////////////////////////////////////////


		directoryFile	= new File (directory);
		dirStringList	= new StringList (directoryFile.list (filter));

		return dirStringList;
	}
	
	
	public static boolean isDirectoryExists (String directoryName) {

		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		File	directory		= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		directory = new File (directoryName);
		
		return (directory.exists() && directory.isDirectory());
	}
}
