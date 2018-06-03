package ua.core.service.shell;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.core.util.CollectionUtils;
import ua.core.service.environment.EnvironmentService;
import ua.core.util.StringUtils;

public class ParameterUtils {
	
	
	/**
	 * Splits a parameter string into it's parts. Spaces are part separators.
	 * 
	 * Items enclosed in quotes are kept together.
	 * 
	 * Null safe (returns empty list).
	 * 
	 * @param paramString
	 * @return
	 */
	public static List <String> asParameterList (String paramString) {
		
		// DECLARATIONS:
		
		List <String>	list;
		Matcher			matcher;
		
		
		// MAIN:

		list	= new ArrayList <String>();
		matcher	= Pattern.compile ("([^\"]\\S*|\".+?\")\\s*").matcher (paramString);
		
		if (StringUtils.isNonEmpty (paramString)) {
			
			while (matcher.find()) {
				list.add (matcher.group (1).replace ("\"", "")); 			// Removes surrounding quotes if any.
			}
		}
		
		return list;
	}
	
	
	/**
	 * Returns true if a file exists, otherwise false.
	 * 
	 * This will strip external quotes and expand environment variables.
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isFileExists (String fileName) {
		
		// DECLARATIONS:
		
		String fileNameStripped;
		String fileNameExpanded;
		
		
		// MAIN:
		
		fileNameStripped	= stripOuterQuotes (fileName);
		fileNameExpanded	= EnvironmentService.expandEnvironmentString (fileNameStripped);
		
		return (new File (fileNameExpanded)).exists();
	}
	
	
	/**
	 * Returns true if a file does not exist, otherwise false.
	 * 
	 * This will strip external quotes and expand environment variables.
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isNotFileExists (String fileName) {
		
		return ! isFileExists (fileName);
	}

	
	/**
	 * Returns a list of strings with the outer quotes stripped if present.
	 * 
	 * Null safe (returns empty list).
	 * 
	 * @param textList
	 * @return
	 */
	public static List <String> stripOuterQuotes (List <String> textList) {
		
		// DECLARATIONS:
		
		List <String>	list;
		
		
		// MAIN:
		
		list = new ArrayList <String>();
		
		if (CollectionUtils.isNonEmpty (textList)) {
			
			for (String text : textList) {
				list.add (stripOuterQuotes (text));
			}
		}
		
		return list;
	}
	
	
	/**
	 * Strips the outer quotes of a string if present.
	 * 
	 * Null safe (returns null).
	 * 
	 * @param text
	 * @return
	 */
	public static String stripOuterQuotes (String text) {
		
		if (StringUtils.getLength (text) >= 2) {
			
			if (text.charAt(0) == '"' && text.charAt(text.length() - 1) == '"') {
				
				return text.substring (1, text.length() - 1);
			}
			else {
				return text;
			}
		}
		else {
			return text;
		}
	}
}
