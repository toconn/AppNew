package ua.core.util.dates;

import java.text.SimpleDateFormat;

public interface IDateConst {
	
	String				DATE_FORMAT_ISO				= "yyyy/MM/dd";
	String				DATE_FORMAT_ISO_DASH		= "yyyy-MM-dd";
	String				DATE_FORMAT_ISO_DOT			= "yyyy.MM.dd";
	String				DATE_FORMAT_ISO_FULL		= "yyyy/MM/dd HH:mm:ss";
	String				DATE_FORMAT_RFC822			= "E, dd MMM yyyy HH:mm:ss Z";
	
	SimpleDateFormat	DATE_FORMATTER_ISO			= new SimpleDateFormat (DATE_FORMAT_ISO);
	SimpleDateFormat	DATE_FORMATTER_ISO_DOT		= new SimpleDateFormat (DATE_FORMAT_ISO_DOT);
	SimpleDateFormat	DATE_FORMATTER_ISO_FULL		= new SimpleDateFormat (DATE_FORMAT_ISO_FULL);
	SimpleDateFormat	DATE_FORMATTER_RFC822		= new SimpleDateFormat (DATE_FORMAT_RFC822);

}
