/*
 * @author TOCONNEL
 * Created on Jul 27, 2004
 *
 */
package ua.core.util.file;

import java.io.*;
import java.nio.channels.*;
import java.net.URL;
import java.net.URLDecoder;

import ua.core.exceptions.ExceptionRuntime;
import ua.core.service.environment.IEnvironmentConst;
import ua.core.util.CollectionUtils;
import ua.core.util.NVStringPair;
import ua.core.util.StringList;
import ua.core.util.StringParser;

/**
 * @author TOCONNEL Created on Jul 27, 2004
 * 
 */
public class FileUtils {

	public static final void copyFile (File sourceFile, File destinationFile) throws IOException {

		// //////////////////////////////////////////////////////////////////////////
		// Declarations:
		// //////////////////////////////////////////////////////////////////////////

		FileChannel sourceFileChannel		= null;
		FileChannel destinationFileChannel	= null;

		
		// //////////////////////////////////////////////////////////////////////////
		// Code:
		// //////////////////////////////////////////////////////////////////////////

		if (! destinationFile.exists ()) {

			destinationFile.createNewFile ();
		}

		try {

			sourceFileChannel = new FileInputStream (sourceFile).getChannel ();
			destinationFileChannel = new FileOutputStream (destinationFile).getChannel ();
			destinationFileChannel.transferFrom (sourceFileChannel, 0, sourceFileChannel.size ());

			destinationFile.setLastModified (sourceFile.lastModified ());
		}
		finally {

			if (sourceFileChannel != null) {

				sourceFileChannel.close ();
			}

			if (destinationFileChannel != null) {

				destinationFileChannel.close ();
			}
		}
	}


	public static String findFileInClassPath (String fileName) throws ExceptionRuntime {

		// ////////////////////////////////////////////////////////////////////////////////////
		// Declarations:
		// ////////////////////////////////////////////////////////////////////////////////////

		NVStringPair dummyClass = null; // Used to get class loader.

		URL fileURL = null;
		String filePath = null;

		// ////////////////////////////////////////////////////////////////////////////////////
		// Code:
		// ////////////////////////////////////////////////////////////////////////////////////

		if (fileName != null) {

			try {

				dummyClass = new NVStringPair ();
				fileURL = dummyClass.getClass ().getClassLoader ().getResource (fileName);

				if (fileURL != null) {
					filePath = URLDecoder.decode (fileURL.getPath (), IFileConst.FILE_ENCODING_UTF8);
				}

				if (filePath != null) {
					filePath = FileUtils.getParentPath (filePath);
				}
			}
			catch (Exception e) {

				throw new ExceptionRuntime (e);
			}
		}

		return filePath;
	}
    
    
    /** 
     * Finds the path to a file from a list of possible paths.
     * Will return either the path or null if nothing is found.
     * 
     * @param pathList
     * @param fileName
     * @return
     */
    public static String findFilePath (StringList pathList, String fileName) {
    	
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		String	filePath	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		
    	for (String pathName: pathList) {
    		
    		if (isFileExists (getFileName (pathName, fileName))) {
    		
    			filePath = pathName;
    			break;
    		}
    	}
    	
    	return filePath;
    }
    
    
    /** 
     * Finds one or more paths containing a file from a list of possible paths.
     * Will return either the path list or null if nothing is found.
     * 
     * @param pathList
     * @param fileName
     * @return
     */
    public static StringList findFilePathRList (StringList pathList, String fileName) {
    	
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		StringList	filePathList	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		
    	for (String pathName: pathList) {
    		
    		if (isFileExists (getFileName (pathName, fileName))) {
    			
    			if (filePathList == null)
    				
    				filePathList = new StringList ();
    		
    			filePathList.add (pathName);
    			break;
    		}
    	}
    	
    	return filePathList;
    }


	public static String formatFileToUnix (String fileName) {

		if (fileName != null) {

			return fileName.replace ('\\', '/');
		}
		else {

			return null;
		}
	}


	public static String formatFileToWindows (String fileName) {

		if (fileName != null) {

			return fileName.replace ('/', '\\');
		}
		else {

			return null;
		}
	}
	
	
    /**
     * Takes a string of ";" separated paths and return in a StringList.
     * 
     * @param pathString
     * @return
     */
    public static StringList expandPathToList (String pathString) {
    	
    	if (pathString != null) {
    	
    		return (new StringParser (IEnvironmentConst.PathParseSeparatorList)).parse (pathString);
    	}
    	else {
    		
    		return null;
    	}
    }



	public static String getCanonicalPath (String path) throws IOException {

		// ///////////////////////////////
		// Declarations:
		// ///////////////////////////////

		File file = null;
		String newPath = null;

		// ///////////////////////////////
		// Code:
		// ///////////////////////////////

		file = new File (path);
		newPath = file.getCanonicalPath ();

		return newPath;
	}
	
	
	/**
	 * Returns a list of file names given a list of directories and one file name.
	 * 
	 * Used to get the full file path for the same file in multiple paths.
	 * 
	 * @param directoryList
	 * @param fileName
	 */
	public static StringList getFileNameList (StringList directoryNameList, String fileName) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

        StringList fileNameList			= null;

        
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
        
        if (CollectionUtils.isNonEmpty (directoryNameList)) {
        	
        	fileNameList = new StringList ();
        	
        	for (String directoryName: directoryNameList) {
        		
        		fileNameList.add (FileUtils.getFileName (directoryName, fileName));
        	}
        	
            return fileNameList;

        }
        else {

            return null;

        }
	}


	public static String getExtension (String fileName) {
		

		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		int		extensionSepIndex	= 0;
		
		String	fileExtension			= null;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////

		if (fileName != null) {
			
			extensionSepIndex = fileName.lastIndexOf (IFileConst.FILE_EXTENSION_SEPARATOR);
			
			if (extensionSepIndex != -1 && extensionSepIndex < fileName.length() - 1) {
				
				fileExtension = fileName.substring (extensionSepIndex + 1);
			}
		}

		return fileExtension;
	}
	
	
	public static String getFileName (String directoryName, String fileName) {
		
		return directoryName + getFileSeparator () + fileName;
	}
	

	/**
	 * Returns the file name without the path.
	 * 
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String getFileNameNoPath (String fullFileName) throws IOException {

		// ///////////////////////////////
		// Declarations:
		// ///////////////////////////////

		File	file		= null;


		// ///////////////////////////////
		// Code:
		// ///////////////////////////////

		file	= new File (fullFileName);
		
		return file.getName ();
	}
	
	
	/**
	 * Returns the file path without the file name.
	 * 
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String getFilePath (String fullFileName) throws IOException {

		// ///////////////////////////////
		// Declarations:
		// ///////////////////////////////

		File	file		= null;


		// ///////////////////////////////
		// Code:
		// ///////////////////////////////

		file	= new File (fullFileName);
		
		return file.getParent ();
	}	


	public static char getFileSeparator () {

		return System.getProperty ("file.separator").charAt (0);
	}


	public static String getParentPath (String path) throws IOException {

		// ///////////////////////////////
		// Declarations:
		// ///////////////////////////////

		File currentFile = null;
		String currentPath = null;

		File parentFile = null;

		// ///////////////////////////////
		// Code:
		// ///////////////////////////////

		currentFile = new File (path);
		currentPath = currentFile.getCanonicalPath ();

		currentFile = new File (currentPath);
		parentFile = currentFile.getParentFile ();

		if (parentFile != null) {

			return parentFile.getCanonicalPath ();
		}
		else {

			return null;
		}
	}


	public static final boolean isAbsolutePath (String path) {

		if (path != null && path.length () > 0) {

			if (isUnixFileSystem ()) {

				// Check unix file name...

				if (IFileConst.FILE_SEPARATOR_UNIX == path.charAt (0)) {

					return true;
				}
			}
			else {

				// Check dos file name...

				if (IFileConst.FILE_SEPARATOR_DOS == path.charAt (0)) {

					return true;
				}
				else if (path.length () > 2 && ":".equals (path.substring (1, 2))) {

					return true;
				}

			}

		}

		return false;
	}


	public static boolean isDosFileSystem () {

		return (IFileConst.FILE_SEPARATOR_DOS == getFileSeparator ());
	}


	public static boolean isFileExists (String fileName) {

		return (new File (fileName)).exists ();
	}


	public static boolean isUnixFileSystem () {

		return (IFileConst.FILE_SEPARATOR_UNIX == getFileSeparator ());
	}
}
