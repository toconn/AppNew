package ua.shared.appgenerator;

import ua.shared.appgenerator.beans.AppGenerator;
import ua.core.exceptions.ExceptionValidation;
import ua.core.exceptions.ValidationUtils;
import ua.core.util.CollectionUtils;
import ua.core.util.IUtilConst;
import ua.core.util.Message;
import ua.core.util.NVStringPair;
import ua.core.util.StringList;
import ua.core.util.StringUtils;
import ua.core.util.filereader.IDataLineReader;


public class AppTemplateConfigLineReader implements IDataLineReader <AppGenerator> {
	
	public final static String	COMMENT_1			= "#";
	public final static String	COMMENT_2			= "//";
	public final static String	COMMENT_3			= "REM";

	public final static char	HEADER_OPEN_CHAR	= '[';
	public final static char	HEADER_CLOSE_CHAR	= ']';
	
	
	private long				lineNumber			= 0;
	
	private	String				currentSectionName	= null;
	private boolean				hasError			= false;
	private ExceptionValidation	exceptionVal		= null;
	
	
	
	public AppTemplateConfigLineReader () {
		
		this.hasError = false;
	}
	


	public boolean processLine (AppGenerator data, String lineString) {

		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		boolean	hasData	= true;	// always true. Even if empty, data object will be returned.


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
		
		incrementLineNumber ();
		

		if (isIgnoreLine (lineString)) {
			
			// #, //, Comment, Blank
			
			// Ignore.
		}
		else if (isSectionHeader (lineString)) {
			
			// new section...
			
			setSectionName (lineString);

		}
		else {
			
			// Process line...
			
			if (StringUtils.isEqual		 (getSectionName (), IAppGeneratorConst.CONFIG_SECTION_COPY_DIR)) {
				
				addCopyDirectory (data, lineString);
			}
			else if (StringUtils.isEqual (getSectionName (), IAppGeneratorConst.CONFIG_SECTION_CREATE_DIR)) {
				
				addCreateDirectory (data, lineString);
			}
			else if (StringUtils.isEqual (getSectionName (), IAppGeneratorConst.CONFIG_SECTION_PROCESS_TEMPLATES)) {
				
				addProcessTemplate (data, lineString);
			}
			else if (StringUtils.isEqual (getSectionName (), IAppGeneratorConst.CONFIG_SECTION_TAGS)) {
				
				addTag (data, lineString);
			}

		}
		
		return hasData;
	}
	
	
	private void addCopyDirectory (AppGenerator data, String lineString) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		NVStringPair	nvStringPair	= null;

		
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		
		nvStringPair = getLineNVPair (lineString);

		if (nvStringPair != null)
			
			data.getDirectoryCopyNVList ().add (nvStringPair);
		
		else
			
			reportError (IAppGeneratorConst.MESSAGE_INVALID_CONFIGURATION.cloneMe (getSectionName(), getLineCount()));
	}
	
	
	private void addCreateDirectory (AppGenerator data, String lineString) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		StringList		lineList 	= null;

		
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		
		lineList = IUtilConst.TAB_PARSER.parse (lineString);

		if (CollectionUtils.isNonEmpty (lineList))
			
			data.getDirectoryCreateStringList ().add (lineList.get (0));
			
		else
			
			reportError (IAppGeneratorConst.MESSAGE_INVALID_CONFIGURATION.cloneMe (getSectionName(), getLineCount()));
	}
	
	
	private void addProcessTemplate (AppGenerator data, String lineString) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		NVStringPair	nvStringPair	= null;

		
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		
		nvStringPair = getLineNVPair (lineString);

		if (nvStringPair != null)
			
			data.getTemplateNVList ().add (nvStringPair);
		
		else
			
			reportError (IAppGeneratorConst.MESSAGE_INVALID_CONFIGURATION.cloneMe (getSectionName(), getLineCount()));
	}
	
	
	private void addTag (AppGenerator data, String lineString) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		NVStringPair	nvStringPair	= null;

		
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		
		nvStringPair = getLineNVPair (lineString);

		if (nvStringPair != null)
			
			data.getTagProperties().setProperty (nvStringPair);
		
		else
			
			reportError (IAppGeneratorConst.MESSAGE_INVALID_CONFIGURATION.cloneMe (getSectionName(), getLineCount()));
	}
	
	
	public ExceptionValidation getExceptionValidation () {
		
		return this.exceptionVal;
	}
	
	
	public long getLineCount () {
		
		return this.lineNumber;
	}
	
	
	/**
	 * Returns the current line in a name value pair.
	 * Pairs must be separated by a tab.
	 * 
	 * if there is only one item, null will be returned.
	 * if there are more than two items on the line, the remainder will be ignored. This allows for commenting.
	 * 
	 * @param lineString
	 * @return
	 */
	private NVStringPair getLineNVPair (String lineString) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		StringList		lineList 		= null;

		NVStringPair	nvStringPair	= null;

		
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		
		// Parse tabs...
		
		lineList = IUtilConst.TAB_PARSER.parse (lineString);

		if (CollectionUtils.isNonEmpty (lineList))
			
			if (lineList.size () >= 2)

				// If 2 or more, return nv pair. Ignore extras.
				
				nvStringPair = new NVStringPair (lineList.get (0), lineList.get (1));

			// if less than 2, ignore and return a null...

		return nvStringPair;
	}
	
	
	/**
	 * Returns the current section name.
	 * 
	 * @return
	 */
	private String getSectionName () {
		
		return this.currentSectionName;
	}
	
	
	/**
	 * Returns true if an error was encountered during the file processing.
	 * Check the validation exception for specific messages.
	 * 
	 * @return
	 */
	public boolean hasError () {
		
		return this.hasError;
	}
	
	
	private void incrementLineNumber () {
		
		this.lineNumber ++;
	}
	
	
	
	/**
	 * Tests to see if the line is a section header.
	 * 
	 * @param lineString
	 * @return
	 */
	private boolean isSectionHeader (String lineString) {
		
		return (StringUtils.isStartsWith (lineString, HEADER_OPEN_CHAR) && StringUtils.isEndsWith (lineString, HEADER_CLOSE_CHAR) && lineString.length () > 2);
	}
	
	
	/**
	 * Checks to see if this is a blank line. Returns true if line is empty / to be ignored.
	 * 
	 * @param lineString
	 * @return
	 */
	private boolean isIgnoreLine (String lineString) {
		
		return (StringUtils.isEmpty (lineString) || StringUtils.isStartsWith (lineString, COMMENT_1) || StringUtils.isStartsWith (lineString, COMMENT_2) || StringUtils.isStartsWith (lineString, COMMENT_3));
	}
	
	
	private void reportError (Message message) {
		
		this.hasError		= true;
		this.exceptionVal	= ValidationUtils.addMessage (this.exceptionVal, message);
	}
	
	
	/**
	 * Sets the current section header.
	 */
	private void setSectionName (String lineString) {
		
		this.currentSectionName = lineString.substring (1, lineString.length () -1);
	}
}
