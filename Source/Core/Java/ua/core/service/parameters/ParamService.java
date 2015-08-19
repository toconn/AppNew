package ua.core.service.parameters;

import ua.core.util.*;


public class ParamService {
	
    public static UaMap <Param> getErrorParamMap (UaMap <Param> paramMap) {
    	
		//////////////////////////////////////////////////////////////////
		// Declarations:
		//////////////////////////////////////////////////////////////////
		
    	UaMap <Param>	errorParamMap	= null;
    	
    	
		//////////////////////////////////////////////////////////////////
		// Code:
		//////////////////////////////////////////////////////////////////
		
    	errorParamMap = new UaMap <Param> ();
    	
    	for (Param param : paramMap.values()) {
    		
    		if (param.hasError()) {
    			
    			errorParamMap.put (param.getName(), param);
    		}
    	}
    	
    	return errorParamMap;
    }
    
    
    public static boolean isNamedParam (String param, String paramIdentifier) {
    	
    	return (StringUtils.isStartsWith (param, paramIdentifier) && param.length() > paramIdentifier.length());
    }
    

    public static UaMap <Param> parse (ParamDefPack paramDefPack, String paramArray[]) {
    	
		//////////////////////////////////////////////////////////////////
		// Declarations:
		//////////////////////////////////////////////////////////////////
		
    	UaMap <ParamDef>	paramDefMap			= null;
    	UaMap <Param>		paramMap			= null;
    	
    	StringList			optionList			= null;
    	StringList			paramUnnamedList	= null;
    	StringList			paramInvalidList	= null;
    	
    	ParamDef			paramDef			= null;
    	Param				param				= null;
    	String				paramName			= null; // named parameter without the '/'.
    	
    	int					i;
    	int					j;
    	
		
		//////////////////////////////////////////////////////////////////
		// Code:
		//////////////////////////////////////////////////////////////////
    	
    	paramDefMap			= paramDefPack.getParamDefMap();
    	
    	paramMap			= new UaMap <Param> ();
    	
    	paramUnnamedList	= new StringList ();
    	paramInvalidList	= new StringList ();
    	
    	i = 0;
    	
    	while (i < paramArray.length) {
    		
    		if (isNamedParam (paramArray [i], paramDefPack.paramIdentifier)) {
    			
    			paramName = paramArray [i].substring (1);	// Trim the '/'.
    			paramDef  = paramDefMap.get (paramName);	// Lookup the definition.
    			
    			if (paramDef == null) {
    				
    				// Invalid parameter...
    				
    				paramInvalidList.add (paramArray [i]);
    				i++;
    			}
    			else {
    				
    				// Named parameter... find parameter options if any...
    				
    				j = 0;
    				i++;
    				
    				optionList = new StringList ();
    				
    				while (j < paramDef.subParamCount && i < paramArray.length && ! isNamedParam (paramArray [i], paramDefPack.paramIdentifier)){
    					
    					optionList.add(paramArray [i]);
    					
    					j++;
    					i++;
    				}
    				
    				
    				// Add options...
    				
    				if (j == 0) {
    					
    					param = new Param (paramName, null, paramDef);
    				}
    				else {
    					
    					param = new Param (paramName, optionList.getArray(), paramDef);
    				}
    				
    				if (! paramDef.subParamsOptional && paramDef.subParamCount != param.getSubparamCount ()) {
    					
    					param.setError(true);
    				}
    				
    				paramMap.put (paramName, param);
    			}
    		}
    		else {
    			
    			// Standard unnamed parameter.
    			
    			paramUnnamedList.add (paramArray [i]);
    			i++;
    		}
    	}
    	
    	// Add extra parameters...
    	
    	if (paramUnnamedList.size() > 0) {
    		
    		param = new Param (IParamConst.PARAM_DEFAULT, paramUnnamedList.getArray(), null);
    		paramMap.put (IParamConst.PARAM_DEFAULT, param);
    	}
    	
    	if (paramInvalidList.size() > 0) {
    		
    		param = new Param (IParamConst.PARAM_INVALID, paramInvalidList.getArray(), null);
    		paramMap.put (IParamConst.PARAM_INVALID, param);
    	}
    	
		
    	return paramMap;
	}
    
    
    public static StringList toStringListParamMap (UaMap <Param> paramMap, int keyPaddingLength) {
    	
		//////////////////////////////////////////////////////////////////
		// Declarations:
		//////////////////////////////////////////////////////////////////
    	
    	StringList	toStringList	= null;
    	StringList	paramNameList	= null;
    	Param		param			= null;
		
		//////////////////////////////////////////////////////////////////
		// Code:
		//////////////////////////////////////////////////////////////////
    	
    	paramNameList = paramMap.getSortedKeyList();
    	
    	
    	// Create toStringList...
    	
    	toStringList = new StringList ();
    	
    	for (String paramName: paramNameList) {
    		
    		if (! StringUtils.isEqual (paramName, IParamConst.PARAM_INVALID) && ! StringUtils.isEqual (paramName, IParamConst.PARAM_DEFAULT)) {
    		
	    		param = paramMap.get (paramName);
	    		
	    		toStringList.add (StringUtils.padString (param.getName(), ' ', keyPaddingLength) + StringUtils.expandArray (param.getSubparamStringArray(), " "));
    		}
    	}
    	
    	if (paramMap.containsKey (IParamConst.PARAM_DEFAULT) || paramMap.containsKey (IParamConst.PARAM_INVALID)) {
    		
    		toStringList.add ("");
    	}

    	if (paramMap.containsKey (IParamConst.PARAM_DEFAULT)) {
    		
    		param = paramMap.get (IParamConst.PARAM_DEFAULT);
    		toStringList.add (StringUtils.padString (param.getName(), ' ', keyPaddingLength) + StringUtils.expandArray (param.getSubparamStringArray(), " "));
    	}
    	
    	if (paramMap.containsKey (IParamConst.PARAM_INVALID)) {
    		
    		param = paramMap.get (IParamConst.PARAM_INVALID);
    		toStringList.add (StringUtils.padString (param.getName(), ' ', keyPaddingLength) + StringUtils.expandArray (param.getSubparamStringArray(), " "));
    	}
    	
    	return toStringList;
    }
}
