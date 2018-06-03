/*
 * Created on Jun 16, 2005
 * 
 * Non case sensitive properties class with some extras.
 */
package ua.core.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import ua.core.exceptions.ExceptionEmptyValue;
import ua.core.exceptions.ExceptionItemNotFound;
import ua.core.exceptions.ExceptionRuntime;
import ua.core.exceptions.IEncryptionController;
import ua.core.exceptions.IExceptionConst;



/**
 * This implementation of Properties is based off of the standard Properties
 * class. This implementation makes some enhancements...
 * 
 *     Keys are case insensitive
 *     Can set or retrieve encrypted data types
 * 
 * @author TOCONNEL
 */
public class UaProperties implements Iterable <NVStringPair> {

    private static final String[]	BOOLEAN_FALSE_STRING_ARRAY	= new String[] {"No",  "n", "f", "false"};
    private static final String[]	BOOLEAN_TRUE_STRING_ARRAY	= new String[] {"Yes", "y", "t", "true"};

    private static final String		CHARACTERS_WHITE_SPACE		= " \t\r\n\f";
    private static final String		SEPARATORS_KEY_VALUE		= "= \t\r\n\f";
    private static final String 	SEPARATORS_KEY_VALUE_STRICT	= "=";
	
	UaProperties					defaultProperties			= null;
	UaMap  <NVStringPair>			propertyMap					= null;
	
	IEncryptionController			encryptionController		= null;
	
	
	/**
	 * 
	 */
	public UaProperties() {
		
		super();
		propertyMap	 = new UaMap <NVStringPair>();
	}
	
	
	/*
	 * Converts encoded &#92;uxxxx to unicode chars
	 * and changes special saved chars to their original forms
	 */
	public String convertEscapedCharacters (String originalString) {
		
	
		///////////////////////////////////////////
		// Declarations:
		///////////////////////////////////////////
	
		// final String	METHOD_NAME	= "loadConvert (String)";
	
		
	    int				stringLength;
	    StringBuffer	convertedStringBuffer	= null;
	
	    int				i;
	    int				j;
	    char			currentCharacter;
	    int				charValue;
	
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
	
	    stringLength = originalString.length();
	    convertedStringBuffer = new StringBuffer (stringLength);
	    
	    for (i = 0; i < stringLength; ) {
	    	
	    	currentCharacter = originalString.charAt (i);
	    	i ++;
	
	    	if (currentCharacter == '\\') {
	    		
	    		// Process escape characters...
			
	    		currentCharacter = originalString.charAt (i);
	    		i++;
			
	    		if(currentCharacter == 'u') {
			
	    			// Read hex characters...
	    			// form \\uxxxx
	    			
	    			charValue=0;
	    			
	    			for (j = 0; j < 4; j++) {
	    				
	    				currentCharacter = originalString.charAt (i);
	    				i++;
	    				
	    				switch (currentCharacter) {
	    				
	    					case '0':
	    					case '1':
	        				case '2':
	        				case '3':
	            			case '4':
	                    	case '5':
	                    	case '6':
	                    	case '7':
	                    	case '8':
	                        case '9':
	                        	
	                        	charValue = (charValue << 4) + currentCharacter - '0';
	                        	break;
	                        	
	                        case 'a':
	                        case 'b':
	                        case 'c':
	                        case 'd':
	                        case 'e':
	                        case 'f':
	                        	
	                        	charValue = (charValue << 4) + 10 + currentCharacter - 'a';
	                        	break;
	                        	
	                        case 'A':
	                        case 'B':
	                        case 'C':
	                        case 'D':
	                        case 'E':
	                        case 'F':
	                        	
	                        	charValue = (charValue << 4) + 10 + currentCharacter - 'A';
	                        	break;
	                        	
	                        default:
	                        	
	                        	throw new IllegalArgumentException ("Malformed \\uxxxx encoding.");
	    				}
	    			}
	    			
	    			convertedStringBuffer.append ((char)charValue);
	    		}
	    		else {
	    			
	    			// Process other escape characters...
	    			
	    			// If not a special character, add as is.
	    			
	    			if (currentCharacter == 't') {
	    				
	    				currentCharacter = '\t';
	    			}
	    			else if (currentCharacter == 'r') {
	    				
	    				currentCharacter = '\r';
	    			}
	    			else if (currentCharacter == 'n') {
	    				
	    				currentCharacter = '\n';
	    			}
	    			else if (currentCharacter == 'f') {
	    				
	    				currentCharacter = '\f';
	    			}
	    			else if (currentCharacter == 's') {
	    				
	    				currentCharacter = ' ';
	    			}
	    			
	    			convertedStringBuffer.append (currentCharacter);
	    		}
	    	}
	    	else {
	    		
	    		convertedStringBuffer.append (currentCharacter);
	    	}
	    }
	    
	    return convertedStringBuffer.toString();
	}
	
	
	/**
	 * @return Returns the defaultProperties.
	 */
	public UaProperties getDefaultProperties() {
	
		return defaultProperties;
	}
	
	
	/**
	 * @return Returns the encryptionController.
	 */
	public IEncryptionController getEncryptionController() {
	
		return encryptionController;
	}
	
	
	/**
	 * Returns a Name Value pair.
	 * 
	 * @param name
	 * @return
	 */
	protected NVStringPair getItem (String name) {
		
		///////////////////////////////////////////
		// Declarations:
		///////////////////////////////////////////
	
		NVStringPair	nvString	= null;
	
		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
	
		if (name != null) {
		
			nvString = (NVStringPair) propertyMap.get (name);
			
			if (nvString == null && defaultProperties != null) {
				
				// Get default property if no actual value
				// and defaults exist.
				
				nvString = defaultProperties.getItem (name);
			}
		}
		
		return nvString;
	}
	
	
	public int getKeyMaxSize() {
		
		return propertyMap.getKeyMaxLength();
	}
	
	
	/**
	 * Returns the string value of a property.
	 * 
	 * @param name
	 * @return
	 */
	public String getProperty (String name) {
		
		///////////////////////////////////////////
		// Declarations:
		///////////////////////////////////////////
	
		NVStringPair	nvString	= null;
	
		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
	
		nvString = getItem (name);
			
		if (nvString != null) {
	
			return nvString.getValue();
		}
		else {
			
			return null;
		}
	}
	
	
	public boolean getPropertyBoolean (String name)
		throws NumberFormatException, ExceptionEmptyValue {
		
		///////////////////////////////////////////
		// Declarations:
		///////////////////////////////////////////
	
		NVStringPair	nvString	= null;
	
		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
		
		nvString	= getItem (name);
	
		if (nvString != null) {
			
			if (isTrue (nvString.getValue())) {
				
				return true;
			}
			else if (isTrue (nvString.getValue())) {
				
				return false;
			}
			else {
				
				throw new NumberFormatException();
			}
		}
		
		throw new ExceptionEmptyValue (new Message ("10175", "Empty Value"));
		
	}
	
	
	public boolean getPropertyBoolean (String name, boolean defaultValue) {
		
		///////////////////////////////////////////
		// Declarations:
		///////////////////////////////////////////
		
		NVStringPair	nvString	= null;
		
		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
		
		nvString	= getItem (name);
		
		if (nvString != null) {
			
			if (isTrue (nvString.getValue())) {
				
				return true;
			}
			else if (isTrue (nvString.getValue())) {
				
				return false;
			}
		}
		
		return defaultValue;
	
	}
	
	
	/** 
	 * Returns the property value after decrypting it.
	 * 
	 * @param name
	 * @return
	 */
	public String getPropertyDecrypt (String name) {
		
		///////////////////////////////////////////
		// Declarations:
		///////////////////////////////////////////
	
		NVStringPair	nvString	= null;
	
		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
	
		nvString = getItem (name);
			
		if (nvString != null && encryptionController != null) {
	
			return encryptionController.decrypt (nvString.getValue());
		}
		else {
			
			return null;
		}
	}
	
	
	public int getPropertyInt (String name)
		throws NumberFormatException, ExceptionEmptyValue {
		
		///////////////////////////////////////////
		// Declarations:
		///////////////////////////////////////////
	
		NVStringPair	nvString	= null;
		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
		
		nvString	= getItem (name);
	
		if (nvString != null) {
				
			return Integer.parseInt (nvString.getValue());
		}
		
		throw new ExceptionEmptyValue(new Message ("10175", "Empty Value"));
		
	}
	
	
	public int getPropertyInt (String name, int defaultValue) {
		
		///////////////////////////////////////////
		// Declarations:
		///////////////////////////////////////////
		
		NVStringPair	nvString	= null;
		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
		
		nvString	= getItem (name);
		
		try {
			
			if (nvString != null) {
					
				return Integer.parseInt (nvString.getValue());
			}
		}
		catch (Exception e) {
			
			// Ignore any errors.
		}
	
		return defaultValue;
	
	}
	
	
	public ArrayList <NVStringPair> getPropertyNVList() {
		
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		ArrayList <NVStringPair>	nvPairList		= null;
		
		
		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////


		// Collect Command Data...
		
		nvPairList = new ArrayList <NVStringPair>();
		
		for (NVStringPair nvPair : this) {
			
			nvPairList.add (nvPair.cloneMe());
		}
		
		return nvPairList;		
	}
	
	
	public UaProperties getPropertiesSubset (String subName) {
	
		///////////////////////////////////////////
		// Declarations:
		///////////////////////////////////////////
		
		UaProperties	subsetProperties	= null;
		NVStringPair	nvString			= null;
		String			subNameLowerCase	= null;
		int				subNameLength		= 0;
	
		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
		
		subsetProperties	= new UaProperties();
		
		
		if (subName != null) {
			
			subNameLowerCase	= subName.toLowerCase();
			subNameLength		= subName.length();

			for (String propertyKey : propertyMap.getSortedKeyList()) {
				
				if (propertyKey.length() > subNameLength && subNameLowerCase.equals (propertyKey.substring (0, subNameLength))) {
					
					nvString = propertyMap.get (propertyKey);
					subsetProperties.setProperty (nvString.getName().substring (subNameLength), nvString.getValue());
				}
			}
		}
		
		return subsetProperties;
	}
	
	
	/**
	 * Reads a property from the properties class that may or may not
	 * be encrypted. The encrypted form will be 'propertyname.encrypted'. 
	 * 
	 * @param properties
	 * @param name
	 */
	public String getSpecialProperty (String name) {
		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
		
		String	value	= null;
	
		///////////////////////////////////////////
		// Error Handling:
		///////////////////////////////////////////
	
		value = getPropertyDecrypt (name + ".encrypted");
		
		if (value == null) {
			
			value = getProperty (name);
		}
		
		return value;
	}
	
	
	public boolean hasProperty (String name) {
		
		return (propertyMap.get (name) != null);
	}
	
	
	private boolean isFalse (String value) {
		
		///////////////////////////////////////////
		// Declarations:
		///////////////////////////////////////////
	
		int	i;
	
		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
	
		if (value != null) {
		
			for (i = 0; i < BOOLEAN_FALSE_STRING_ARRAY.length; i++) {
				
				if (StringUtils.isEqual (value, BOOLEAN_FALSE_STRING_ARRAY[i])) {
					
					return true;
				}	
			}
		}
		
		return false;
	}
	
	
	/*
	 * Returns true if the given line is a line that must
	 * be appended to the next line
	 */
	private boolean isMultiline (String line) {
		
		///////////////////////////////////////////
		// Declarations:
		///////////////////////////////////////////
	
		int	slashCount;
		int	index;
	
		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
	
		slashCount = 0;
		index = line.length() - 1;
	
		while ((index >= 0) && (line.charAt (index--) == '\\')) {
			
	        slashCount++;
		}
		
	    return (slashCount % 2 == 1);
	}
	
	
	public boolean isPropertyBoolean (String name) {
		
		///////////////////////////////////////////
		// Declarations:
		///////////////////////////////////////////
	
		NVStringPair	nvString	= null;
		boolean			isBoolean;
		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
		
		isBoolean	= false;
		
		nvString	= getItem (name);
	
		if (nvString != null) {
			
			if (isTrue (nvString.getValue())) {
				
				isBoolean = true;
			}
			else if (isFalse (nvString.getValue())) {
				
				isBoolean = true;
			} 
		}
		
		return isBoolean;
	}
	
	
	public boolean isPropertyInt (String name) {
		
		///////////////////////////////////////////
		// Declarations:
		///////////////////////////////////////////
	
		NVStringPair	nvString	= null;
		boolean		isBoolean;
		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
		
		isBoolean	= false;
		
		nvString	= getItem (name);
	
		if (nvString != null) {
			
			if (StringUtils.isInt (nvString.getValue())) {
				
				isBoolean = true;
			}
		}
		
		return isBoolean;
		
	}
	
	
	private boolean isTrue (String value) {
		
		///////////////////////////////////////////
		// Declarations:
		///////////////////////////////////////////
	
		int	i;
	
		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
	
		if (value != null) {
		
			for (i = 0; i < BOOLEAN_TRUE_STRING_ARRAY.length; i++) {
				
				if (StringUtils.isEqual (value, BOOLEAN_TRUE_STRING_ARRAY[i])) {
					
					return true;
				}	
			}
		}
		
		return false;
	}
	
	
	public UaIterator <NVStringPair> iterator() {
		

		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		UaIterator <NVStringPair>	iterator	= null;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////

		iterator = new UaIterator <NVStringPair>();
		
		for (String propertyKey : propertyMap.getSortedKeyList()) {
			
			iterator.add (propertyMap.get (propertyKey).cloneMe());
		}
		
		return iterator;
	}
	
	
	public void load (String fileName) throws ExceptionItemNotFound, ExceptionRuntime {
		
		try {
			
			load (new FileInputStream (fileName));
		}
		catch (FileNotFoundException e) {
			
			throw new ExceptionItemNotFound (IExceptionConst.MESSAGE_FILE_NOT_FOUND.cloneMe (fileName));
		}
		catch (IOException e) {

			throw new ExceptionRuntime (IExceptionConst.MESSAGE_RUNTIME_ERROR.cloneMe ("IOException", e.getMessage()));
		}
	}

	
	
	public void load (InputStream inStream) throws IOException {
	
		///////////////////////////////////////////
		// Declarations:
		///////////////////////////////////////////
	
		BufferedReader	inputReader		= null;
		
		String			inputLine		= null;
		String			inputLineNext	= null;
		String			loppedLine		= null;
		
		int				whiteSpaseIndex;
		int				inputLineLength;
		int				keyIndex;
		int				separatorIndex;
		int				valueIndex;
	
		char			firstChar;
		char			currentChar;
		
		String			propertyName	= null;
		String			propertyValue	= null;
		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
	
		
		inputReader	= new BufferedReader (new InputStreamReader (inStream, "8859_1"));
		
		do {
			
	        // Get next line...
			
			inputLine = inputReader.readLine();
			
	        if (inputLine != null && inputLine.length() > 0) {
	        	
	            // Continue lines that end in slashes if they are not comments
	        	
	            firstChar = inputLine.charAt(0);
	            
	            if ((firstChar != '#') && (firstChar != '!')) {
	            	
	                while (isMultiline (inputLine)) {
	                	
	                	inputLineNext = inputReader.readLine();
	                	
	                    if (inputLineNext == null) {
	                    	
	                    	inputLineNext = "";
	                    }
	                    
	                    loppedLine = inputLine.substring (0, inputLine.length()-1);
	                    
	                    // Advance beyond whitespace on new line...
	                    
	                    for (whiteSpaseIndex = 0; whiteSpaseIndex < inputLineNext.length(); whiteSpaseIndex++){
	                  
	                        if (CHARACTERS_WHITE_SPACE.indexOf (inputLineNext.charAt (whiteSpaseIndex)) == -1) {
	                        	
	                            break;
	                        }
	                    }
	                    
	                    inputLineNext = inputLineNext.substring (whiteSpaseIndex, inputLineNext.length());
	                    inputLine = new String(loppedLine + inputLineNext);
	                }
	
	                // Find start of key
	                
	                inputLineLength = inputLine.length();
	
	                for (keyIndex = 0; keyIndex < inputLineLength; keyIndex++) {
	                	
	                    if (CHARACTERS_WHITE_SPACE.indexOf (inputLine.charAt (keyIndex)) == -1) {
	                    	
	                        break;
	                    }
	                }
	
	                // Blank lines are ignored...
	                
	                if (keyIndex == inputLineLength) {
	                	
	                    continue;
	                }
	
	                // Find separation between key and value...
	
	                for (separatorIndex = keyIndex; separatorIndex < inputLineLength; separatorIndex++) {
	                	
	                    currentChar = inputLine.charAt (separatorIndex);
	                    
	                    if (currentChar == '\\') {
	                    	
	                        separatorIndex++;
	                    }
	                    else if (SEPARATORS_KEY_VALUE.indexOf (currentChar) != -1) {
	                    	
	                        break;
	                    }
	                }
	
	                // Skip over whitespace after key if any
	
	                for (valueIndex=separatorIndex; valueIndex<inputLineLength; valueIndex++) {
	                	
	                    if (CHARACTERS_WHITE_SPACE.indexOf (inputLine.charAt(valueIndex)) == -1) {
	                    	
	                        break;
	                    }
	                }
	                
	                // Skip over one non whitespace key value separators if any...
	                    
	                if (valueIndex < inputLineLength) {
	                	
	                    if (SEPARATORS_KEY_VALUE_STRICT.indexOf (inputLine.charAt(valueIndex)) != -1) {
	                    	
	                        valueIndex++;
	                    }
	                }
	
	                // Skip over white space after other separators if any...
	                    
	                while (valueIndex < inputLineLength) {
	                	
	                    if (CHARACTERS_WHITE_SPACE.indexOf (inputLine.charAt (valueIndex)) == -1) {
	                    	
	                        break;
	                    }
	                    
	                    valueIndex++;
	                }
	
	                propertyName	= inputLine.substring (keyIndex, separatorIndex);
	                propertyValue	= (separatorIndex < inputLineLength) ? inputLine.substring (valueIndex, inputLineLength) : "";
	
	                // Convert key and value...
	                
	                // propertyName	= convertEscapedCharacters (propertyName);
	                // propertyValue	= convertEscapedCharacters (propertyValue);
	                
	                setProperty (propertyName, propertyValue);
	            }
	        }
		}
		while (inputLine != null);
	}
	
	
	public void removeProperty (String name) {
		
		if (name != null) {
		
			propertyMap.remove (name);		
		}
	}
	
	
	/**
	 * @param defaultProperties The defaultProperties to set.
	 */
	public void setDefaultProperties (UaProperties defaultProperties) {
		
		this.defaultProperties = defaultProperties;
	}
	
	
	/**
	 * @param encryptionController The encryptionController to set.
	 */
	public void setEncryptionController (
			IEncryptionController encryptionController) {
		
		this.encryptionController = encryptionController;
	}
	
	
	/** Add properties from another property object to the current
	 * collection of properties
	 * 
	 * @param properties
	 */
	public void setProperties (UaProperties properties) {
		
		for (NVStringPair nvString: properties) {
			
			setProperty (nvString.name, nvString.value);
		}
		
	}
	
	
	public void setProperty (String name, boolean value) {
		
		///////////////////////////////////////////
		// Declarations:
		///////////////////////////////////////////
	
		NVStringPair	nvString	= null;
	
		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
		
		if (name != null) {
			
			// Add property...
			
			if (value) {
				
				nvString = new NVStringPair (name, BOOLEAN_TRUE_STRING_ARRAY[0]);
			}
			else {
				
				nvString = new NVStringPair (name, BOOLEAN_FALSE_STRING_ARRAY[0]);
			}
				
			propertyMap.put (name, nvString);
		}
	}
	
	
	public void setProperty (String name, int value) {
		
		///////////////////////////////////////////
		// Declarations:
		///////////////////////////////////////////
	
		NVStringPair	nvString	= null;
	
		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
		
		if (name != null) {
			
			// Add property...
			
			nvString = new NVStringPair (name, Integer.toString (value));
			propertyMap.put (name, nvString);
		}
	}
	
	
	public void setProperty (String name, String value) {
		
		///////////////////////////////////////////
		// Declarations:
		///////////////////////////////////////////
	
		NVStringPair	nvString	= null;
	
		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
		
		if (name != null) {
	
			// Add property...
			
			nvString = new NVStringPair (name, value);
			propertyMap.put (name, nvString);
		}
	}
	
	
	public void setProperty (NVStringPair nvStringPair) {
		
		propertyMap.put (nvStringPair.getName(), nvStringPair.cloneMe());
	}
	
	
	public void setPropertyEncrypt (String name, String value) {
		
		///////////////////////////////////////////
		// Declarations:
		///////////////////////////////////////////
	
		NVStringPair	nvString	= null;
	
		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
		
		if (name != null) {
	
			// Add property...
			
			nvString = new NVStringPair (name, encryptionController.encrypt (value));
			propertyMap.put (name, nvString);
		}
	}
	
	
	public int size() {
		
		return propertyMap.size();
	}
	
	
	public StringList toStringList() {
		
		return toStringList (this.getKeyMaxSize() + 2);
	}
	
	
	public StringList toStringList (int keyPaddingLength) {
		
		//////////////////////////////////////////////////////////////////
		// Declarations:
		//////////////////////////////////////////////////////////////////
		
		StringList		toStringList		= null;
    	
    	StringList		propertyNameList	= null;
    	NVStringPair	itemNVPair			= null;
		
		
		//////////////////////////////////////////////////////////////////
		// Code:
		//////////////////////////////////////////////////////////////////

    	propertyNameList = propertyMap.getSortedKeyList();
    	

    	// Create toStringList...
    	
    	toStringList = new StringList();
    	
    	for (String paramName: propertyNameList) {
    		
    		itemNVPair = propertyMap.get (paramName);
    		
    		toStringList.add (StringUtils.padString (itemNVPair.name, ' ', keyPaddingLength) + itemNVPair.value);
    	}   	

		return toStringList;
	}
}