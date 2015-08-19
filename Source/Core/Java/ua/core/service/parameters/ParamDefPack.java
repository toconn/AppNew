package ua.core.service.parameters;

import ua.core.service.message.MessageService;
import ua.core.util.Message;
import ua.core.util.StringList;
import ua.core.util.StringUtils;
import ua.core.util.UaMap;


public class ParamDefPack {
	
	public Message				paramDescriptionMessage	= null;
	public ParamDef				paramDefArray[]			= null;
	public String				paramIdentifier			= null; // '-' or '/' that establishes a named parameter.
	public boolean				defaultShowHelp; 				// Show help if there are no default options (default option)

	private UaMap <ParamDef>	paramDefMap				= null;
	
	
	
	public ParamDefPack (Message paramDescriptionMessage, ParamDef paramDefArray[], String paramIdentifier, boolean defaultShowHelp) {
		
		this.paramDescriptionMessage	= paramDescriptionMessage;
		this.paramDefArray				= paramDefArray;
		this.paramIdentifier			= paramIdentifier;
		this.defaultShowHelp			= defaultShowHelp;
		
		setParamDefMap ();
	}
	
	
	public ParamDefPack (Message paramDescriptionMessage, ParamDef paramDefArray1[], ParamDef paramDefArray2[], String paramIdentifier, boolean defaultShowHelp) {
		
		this.paramDescriptionMessage	= paramDescriptionMessage;
		this.paramIdentifier			= paramIdentifier;
		this.defaultShowHelp			= defaultShowHelp;
		
		
		// Copy param def arrays to pack (presumably Standard help and App Help)...

		if (paramDefArray1 != null && paramDefArray2 != null) {
		
    		this.paramDefArray = new ParamDef [paramDefArray1.length + paramDefArray2.length + 1];
    		this.paramDefArray [paramDefArray1.length] = null;
    		
    		System.arraycopy (paramDefArray1, 0, this.paramDefArray, 0,                         paramDefArray1.length);
    		System.arraycopy (paramDefArray2, 0, this.paramDefArray, paramDefArray1.length + 1, paramDefArray2.length);
		}
		else {
			
			if (paramDefArray1 != null) {
				
				this.paramDefArray = paramDefArray1;
			}

			if (paramDefArray2 != null) {
				
				this.paramDefArray = paramDefArray2;
			}
		}
		
		setParamDefMap ();
	}
	
	
	public int getParamDefCount () {
		
		return paramDefMap.size();
	}
	
	public UaMap <ParamDef> getParamDefMap () {
		
		return paramDefMap;
	}
	
	
	private void setParamDefMap () {
			
		//////////////////////////////////////////////////////////////////
		// Declarations:
		//////////////////////////////////////////////////////////////////

    	int			i;
		
    	
		//////////////////////////////////////////////////////////////////
		// Code:
		//////////////////////////////////////////////////////////////////
		
    	paramDefMap = new UaMap <ParamDef> ();
    	
    	if (paramDefArray != null) {
    		
        	for (i = 0; i < paramDefArray.length; i++) {
        		
        		if (paramDefArray[i] != null) {
        			
        			paramDefMap.put (paramDefArray [i].name, paramDefArray [i]);
        		}
        	}
    	}
	}
	
	
	public StringList toStringListParamDefs () {
	
		return toStringListParamDefs (paramDefMap.getKeyMaxLength() + 2);
	}
	
	
	public StringList toStringListParamDefs (int keyPaddingLength) {

		//////////////////////////////////////////////////////////////////
		// Declarations:
		//////////////////////////////////////////////////////////////////
		
    	int			i;
    	
    	StringList	toStringList	= null;
		
		//////////////////////////////////////////////////////////////////
		// Code:
		//////////////////////////////////////////////////////////////////

    	// Create toStringList...
    	
    	toStringList = new StringList ();
    	
    	for (i = 0; i < paramDefArray.length; i++) {
    		
    		if (paramDefArray[i] == null) {
    			
    			toStringList.add ("");
    		}
    		else {
    			
    			toStringList.add (StringUtils.padString (paramDefArray[i].name, ' ', keyPaddingLength) + MessageService.getFormattedMessage (null, paramDefArray[i].descriptionMessage));
    		}
    	}   	
    	
    	return toStringList;
	}
}
