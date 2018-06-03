package ua.core.util;

import java.util.ArrayList;

public class StringParser {

	/*
	 
	// Example escape characters...
	
	public static	char		FILE_ESCAPE_CHAR	= '/';		
	
	public static UaList <NVStringPair> FILE_ESCAPE_NVPAIR_LIST = new UaList <NVStringPair> (new NVStringPair[] {
		new NVStringPair ("/", "/"),
		new NVStringPair ("\\", "\\"),
		new NVStringPair (",", ","),
	});
	
	*/
	
	public static String				CLASS_NAME			= StringParser.class.getName();
	
	private	boolean						isSkipEmptySegments	= true;	// Skip segments that are 0 length.
	private char						escapeCharacter		= 0;
	private ArrayList <NVStringPair>	escapeNVStringList	= null;
	private StringList					separatorStringList	= null;
	
	public StringParser (StringList separatorStringList) {
		
		this.separatorStringList = separatorStringList;
	}

	public StringParser (StringList separatorStringList, boolean isSkipEmptySegments) {
		
		this.separatorStringList	= separatorStringList;
		this.isSkipEmptySegments	= isSkipEmptySegments;
	}

	public StringParser (StringList separatorStringList, char escapeCharacter, ArrayList <NVStringPair> escapeNVStringList) {
		
		this.separatorStringList	= separatorStringList;
		this.escapeCharacter		= escapeCharacter;
		this.escapeNVStringList		= escapeNVStringList;
	}

	public StringParser (StringList separatorStringList, char escapeCharacter, ArrayList <NVStringPair> escapeNVStringList, boolean isSkipEmptySegments) {
		
		this.separatorStringList	= separatorStringList;
		this.escapeCharacter		= escapeCharacter;
		this.escapeNVStringList		= escapeNVStringList;
		this.isSkipEmptySegments	= isSkipEmptySegments;
	}
	
	/**
	 * Parse the text using the parse parameters used to instantiate the parse class.
	 * The results are returned as a StringList.
	 * 
	 * Note: A string list will always be returned.
	 * 
	 * @param text
	 * @return
	 */
	public StringList parse (String text) {
		
		/////////////////////////////////////////////////////////
		// Declarations:
		/////////////////////////////////////////////////////////

		StringList		resultStringList		= null;
		StringBuilder	segmentStringBuilder	= null;
		int				segmentIndexCurrent		= 0;
		boolean			isEscapeSequence		= false;
		boolean			isSeparator				= false;


		/////////////////////////////////////////////////////////
		// Code:
		/////////////////////////////////////////////////////////

		resultStringList	= new StringList();
		
		if (StringUtils.isNonEmpty (text)) {
			
			segmentStringBuilder = new StringBuilder();
			
			// Loop through all characters...
			
			while (segmentIndexCurrent < text.length()) {
				
				if (this.escapeCharacter > 0 && this.escapeCharacter == text.charAt (segmentIndexCurrent) && segmentIndexCurrent < text.length() -1) {
					
					// Escape character found...
					
					// Check is escape sequence...

					isEscapeSequence = false;
					
					for (NVStringPair escapeNVPair: this.escapeNVStringList) {
						
						if (StringUtils.isStartsWith (text.substring (segmentIndexCurrent + 1), escapeNVPair.name)) {
							
							// Match found...
							
							// Add unescaped string...
							segmentStringBuilder.append (escapeNVPair.value);
							
							// Skip to the next character...
							segmentIndexCurrent = segmentIndexCurrent + escapeNVPair.name.length() + 1;
							
							isEscapeSequence = true;
							
							break;
						}
					}
					
					if (! isEscapeSequence) {
					
						// Not an escape sequence.
						
						// Add as normal character...
						segmentStringBuilder.append (text.charAt (segmentIndexCurrent));
						
						// Increment counter...
						segmentIndexCurrent ++;
						
					}
					
				}
				else {
					
					// Check if separator string...
					
					isSeparator = false;
					
					for (String separator: separatorStringList) {
						
						if (StringUtils.isStartsWith (text.substring (segmentIndexCurrent), separator)) {
							
							isSeparator = true;
							break;
						}
					}
					
					if (isSeparator) {
						
						// Segment Separator...
						
						// Save current segment...
						
						if (segmentStringBuilder.length() > 0) {
							
							resultStringList.add (segmentStringBuilder.toString());
							segmentStringBuilder = new StringBuilder();
						}
						else if (! this.isSkipEmptySegments) {
							
							resultStringList.add ((String) null);
						}
						
						// Increment counter...
						segmentIndexCurrent ++;
						
					}
					else {
						
						// Normal character.
						
						// Add...
						segmentStringBuilder.append (text.charAt (segmentIndexCurrent));
						
						// Increment counter...
						segmentIndexCurrent ++;
					}
				}
			}
			
			if (segmentStringBuilder.length() > 0) {
				
				resultStringList.add (segmentStringBuilder.toString());
			}
		}
		

		return resultStringList;
	}

}
