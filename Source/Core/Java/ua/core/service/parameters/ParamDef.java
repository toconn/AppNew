package ua.core.service.parameters;

import ua.core.util.Message;

public class ParamDef {

	public String 	name        		= "";
	public int    	subParamCount    	= 0;
	public boolean	subParamsOptional	= false;
	
	public Message descriptionMessage	= null;
	public Message usageMessage      	= null;
	
	
	public ParamDef (String name, int count, Message descriptionMessage) {
		
		this.name 					= name;
		this.subParamCount 			= count;
		this.descriptionMessage		= descriptionMessage;
	}
	
	
	public ParamDef (String name, int count, boolean subParamsOptional, Message descriptionMessage) {
		
		this.name 					= name;
		this.subParamCount 			= count;
		this.subParamsOptional		= subParamsOptional;
		this.descriptionMessage		= descriptionMessage;
	}
	
	
	public ParamDef (String name, int count, Message descriptionMessage, Message usageMessage) {
		
		this.name 					= name;
		this.subParamCount 			= count;
		this.descriptionMessage 	= descriptionMessage;
		this.usageMessage 			= usageMessage;
	}
	
	
	public ParamDef (String name, int count, boolean subParamsOptional, Message descriptionMessage, Message usageMessage) {
		
		this.name 					= name;
		this.subParamCount 			= count;
		this.subParamsOptional		= subParamsOptional;
		this.descriptionMessage 	= descriptionMessage;
		this.usageMessage 			= usageMessage;
	}
}
