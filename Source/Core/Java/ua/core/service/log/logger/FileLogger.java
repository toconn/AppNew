/*
 * @author TOCONNEL
 * Created on Jul 27, 2004
 *
 */
package ua.core.service.log.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ua.core.exceptions.ExceptionRuntime;
import ua.core.service.log.ILogConst.LogLevelEnum;
import ua.core.util.ClassUtils;
import ua.core.util.StringList;
import ua.core.util.StringUtils;
import ua.core.util.file.DirectoryUtils;
import ua.core.util.file.FileUtils;

/**
 * @author TOCONNEL Created on Jul 27, 2004
 * 					Redone	on Jan 30, 2011
 * 
 */
public class FileLogger implements ILogger {

	public static final String	FILE_DATE_FORMAT	= "yyyy/MM/dd hh:mm:ss  ";
	public static final int		TEXT_PADDING_SIZE	= 36;							// Used to report errors when the log craps out.

	private String				name;
	private LogLevelEnum		logLevel;
	private boolean				trace;
	private Locale				locale;
	
	private String				fileName;
	private SimpleDateFormat	dateFormat;
	

	
	public FileLogger (String newName, LogLevelEnum newLogLevel, boolean newTrace, Locale newLocale, String fileName) throws ExceptionRuntime {

		// ///////////////////////////////
		// Declarations:
		// ///////////////////////////////

		String fileDirectoryPath = null;



		// ///////////////////////////////
		// Code:
		// ///////////////////////////////

		try {

			this.name		= newName;
			this.logLevel	= newLogLevel;
			this.trace		= newTrace;
			this.locale		= newLocale;
			
			fileDirectoryPath = FileUtils.getParentPath(fileName);
			DirectoryUtils.createDirectory(fileDirectoryPath);

			dateFormat = new SimpleDateFormat (FILE_DATE_FORMAT);

		}
		catch (Exception e) {

			throw new ExceptionRuntime (e);
		}
	}

	
	public Locale getLocale () {
		
		return locale;
	}

	
	/**
	 * @return Returns the name.
	 */
	public LogLevelEnum getLogLevel() {

		return logLevel;
	}
	
	
	/**
	 * @return Returns the name.
	 */
	public String getName() {

		return name;
	}

	
	/**
	 * @return Returns the trace.
	 */
	public boolean isTrace() {

		return trace;
	}
	
	
	private void fileWrite (String text) {
		

		// ///////////////////////////////
		// Declarations:
		// ///////////////////////////////

		BufferedWriter fileWriter = null;


		// ///////////////////////////////
		// Code:
		// ///////////////////////////////

		try {

			fileWriter = new BufferedWriter (new FileWriter(fileName, true));
			
			fileWriter.write (dateFormat.format (new Date()));
			fileWriter.write (text);
			fileWriter.newLine();
			
			fileWriter.close();
		}

		// ///////////////////////////////
		// Standard Error Handling:
		// ///////////////////////////////

		catch (Exception e) {

			writeToConsoleErr (e, "Error writing to file.");
		}		
	}

	
	private void fileWrite (StringList textList) {
		

		// ///////////////////////////////
		// Declarations:
		// ///////////////////////////////

		BufferedWriter fileWriter = null;


		// ///////////////////////////////
		// Code:
		// ///////////////////////////////

		try {

			fileWriter = new BufferedWriter (new FileWriter(fileName, true));
			
			for (String text: textList) {
				
				fileWriter.write (dateFormat.format (new Date()));
				fileWriter.write (text);
				fileWriter.newLine();
			}
			
			fileWriter.close();
		}

		// ///////////////////////////////
		// Standard Error Handling:
		// ///////////////////////////////

		catch (Exception e) {

			writeToConsoleErr (e, "Error writing to file.");
		}		
	}

	
	public void write (LogLevelEnum logLevel, String text) {

		fileWrite (text);
	}

	
	public void write (LogLevelEnum logLevel, StringList textStringList) {
		
		fileWrite (textStringList);
	}	

	
	public void write (String text) {

		fileWrite (text);
	}

	
	public void write (StringList textStringList) {

		fileWrite (textStringList);
	}
	
	
	private void writeToConsoleErr (Exception e, String comment) {
		
		// Used to report errors when the log really craps out...
		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////

		writeToConsoleErr ("Exception ",	e.getClass().getName());
		writeToConsoleErr ("   Message  ",	e.getMessage());
		writeToConsoleErr ("   Class  ",	ClassUtils.getUpperClassName());
		writeToConsoleErr ("   Method  ",	ClassUtils.getUpperMethodName());
		
		if (comment != null) {
			
			writeToConsoleErr ("   comment  ",	comment);
		}
		
		System.err.println ("");
	}
	
	
	private void writeToConsoleErr (String text1, String text2) {
		
		// Used to report errors when the log really craps out...
		
		System.err.println (StringUtils.padString (text1, " ", TEXT_PADDING_SIZE) + ": " + text2);

	}
}
