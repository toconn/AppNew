package ua.core.util;


public interface IUtilConst {
	
	public StringParser	COMMA_PARSER		= new StringParser (new StringList (new String [] {","}));
	public StringParser	TAB_PARSER			= new StringParser (new StringList (new String [] {"\t"}));
	
	public String		CHARSET_ISO_8859	= new String ("ISO-8859-1");
	public String		CHARSET_UTF8		= new String ("UTF-8");

}
