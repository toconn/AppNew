package ua.shared.freemarker;

import ua.core.util.Message;


public interface IFreeMarkerConst {
	
	public String	TAG_CURRENT_DATE	= "CurrentDate";
	public String	TAG_DAY				= "CurrentDay";
	public String	TAG_TIME			= "CurrentTime";

	public String	TAG_ISO_DATE		= "ISODate";
	public String	TAG_ISO_DOT_DATE	= "ISODotDate";

	
	public Message	MESSAGE_TEMPLATE_FILE_NOT_FOUND	= new Message ("To Do: Message Id",			"Template file \"{0}\" not found.");
	public Message	MESSAGE_TEMPLATE_FILE_INVALID	= new Message ("To Do: Message Id",			"Template file \"{0}\" is formatted incorrectly.");
	public Message	MESSAGE_TEMPLATE_INVALID		= new Message ("To Do: Message Id",			"Template invalid.");
}
