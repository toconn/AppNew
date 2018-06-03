/*
 * Created on Jun 30, 2005
 */
package ua.core.service.message;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

import ua.core.exceptions.*;
import ua.core.util.*;


/**
 * @author TOCONNEL
 */
public class MessageService {

	
    public MessageService() {
    	
    	super();
    }
    
    
    public static String getFormattedMessage (Locale locale, Message message) {
    	
    	/////////////////////////////////
    	// Declarations:
    	/////////////////////////////////
    	
    	// String			METHOD_NAME		= "getFormattedMessage (Locale, Message, boolean)";
    	
    	MessageFormat	messageFormat	= null;
    	
    	
    	/////////////////////////////////
    	// Code:
    	/////////////////////////////////
    	
    	if (message != null && message.getFormat() != null) {
    		
    		// Create formatter...
    		
    		if (locale != null) {
    			
    			messageFormat = new MessageFormat (message.getFormat(), locale);
    		}
    		else {
    			
    			messageFormat = new MessageFormat (message.getFormat());
    		}
    		
    		// Format message...
    		
    		return messageFormat.format (message.getParameters());
    	}
    	else {
    		
    		return null;
    	}		
    	
    	///////////////////////////////////////////
    	// Error Handling:
    	///////////////////////////////////////////
    	
    	// No error handling. Let the error percolate up to the next level.
    
    }
    
    
    public static StringList getFormattedMessageList (Locale locale, ArrayList <Message> messageList) {
    	
    	/////////////////////////////////
    	// Declarations:
    	/////////////////////////////////
    	
        // String	METHOD_NAME		= "getFormattedMessageList (Locale, ArrayList)";
    	
    	StringList			messageTextList	= null;
    	
    	MessageFormat		messageFormat	= null;
    	Object				object			= null;
    	Message				message			= null;
    	
    	Iterator <Message>	iterator		= null;
    	
    	
    	/////////////////////////////////
    	// Code:
    	/////////////////////////////////
    	
    	messageTextList = new StringList();
    	
    	// Create formatter...
    	
    	if (locale != null) {
    		
    		messageFormat = new MessageFormat ("", locale);
    	}
    	else {
    		
    		messageFormat = new MessageFormat ("");
    	}
    	
    	// Loop through messages
    	
    	iterator = messageList.iterator();
    	
    	while (iterator.hasNext()) {
    	
    		object = iterator.next();
    		
    		if (object == null) {
    			
    			messageTextList.add ("");
    		}
    		else if (object instanceof Message) {
    			
    			// Format message...
    			
    			message = (Message) object;
    
    			if (message.getFormat() != null) {
    					
    				messageFormat.applyPattern (message.getFormat());
    				messageTextList.add (messageFormat.format (message.getParameters()));
    			}
    		}
    		else if (object instanceof String) {
    			
    			// Copy string as is...
    		
    			messageTextList.add ((String) object);
    		}
    	}
    	
    	return messageTextList;
    
    	///////////////////////////////////////////
    	// Error Handling:
    	///////////////////////////////////////////
    	
    	// No error handling. Let the error percolate up to the next level.
    }
    
    
    public static ArrayList <Object> parseTextFormatting (String messageText)
    	throws ExceptionRuntime {
    	
    	///////////////////////////////////////////
    	// Declarations:
    	///////////////////////////////////////////
    	
    	ArrayList <Object>	parsedFormatList	= null;
    	NVStringPair		tokenNVPair			= null;
    	
    	int				index;
    	int				tokenPosStart;
    	int				tokenPosEnd;
    	int				tokenPosSplit;
    	int				currentTextStartPos;
    	
    	
    	///////////////////////////////////////////
    	// Code:
    	///////////////////////////////////////////
    	
    	try {
    	
    		parsedFormatList	= new ArrayList <Object>();
    		
    	
    		if (! StringUtils.isEmpty (messageText)) {
    			
    			///////////////////////////////////////////
    			//Process text, look for tokens...
    			///////////////////////////////////////////
    			
    			currentTextStartPos	= 0;
    			
    			// Scan for escape characters...
    			
    			for (index = 0; index < messageText.length();) {
    				
    				// Find start of next token "["...
    				
    				tokenPosStart = messageText.indexOf ("[", index);
    				
    				if (tokenPosStart == -1) {
    					
    					break;
    				}
    					
    				// Find the end of the token "]"...
    				
    				tokenPosEnd = messageText.indexOf ("]", tokenPosStart + 1);
    				
    				if (tokenPosEnd == -1) {
    					
    					break;
    				}
    				
    				if (tokenPosStart < tokenPosEnd - 1) {
    					
    					///////////////////////////////////////////
    					// Process the token:
    					///////////////////////////////////////////
    					
    					// Find the split "=", if any...
    					tokenPosSplit = messageText.indexOf ("=", tokenPosStart + 1);
    							
    					tokenNVPair =  new ua.core.util.NVStringPair();
    	
    					// Create the token...
    					
    					if (tokenPosSplit == -1) {
    						
    						tokenNVPair.setName (messageText.substring(tokenPosStart + 1, tokenPosEnd));	
    					}
    					else {
    						
    						tokenNVPair.setName (messageText.substring(tokenPosStart + 1, tokenPosSplit));
    						tokenNVPair.setValue (messageText.substring(tokenPosSplit + 1, tokenPosEnd));
    					}
    						
    						
    					///////////////////////////////////////////
    					// Process remainder:
    					///////////////////////////////////////////
    					
    					// Copy the leading text...
    					
    					if (currentTextStartPos < tokenPosStart) {
    						
    						parsedFormatList.add (messageText.substring (currentTextStartPos, tokenPosStart));
    					}
    					
    					// Add the token...
    					
    					parsedFormatList.add (tokenNVPair);
    					
    					// Update pointers...
    					
    					index = tokenPosEnd + 1;
    					currentTextStartPos = index;
    				}
    				else {
    					
    					// "[]", skip two spots
    					
    					index = index + 2;
    				}
    			}
    			
    			// Add the remaining string...
    			
    			if (currentTextStartPos < messageText.length()) {
    				
    				parsedFormatList.add (messageText.substring (currentTextStartPos));
    			}
    		}
    		
    		return parsedFormatList;
    	
    	}
    
    	///////////////////////////////////////////
    	// Error Handling:
    	///////////////////////////////////////////
    
    	catch (Exception e) {
    		
    		throw new ExceptionRuntime (e);
    	}	
    }
}
