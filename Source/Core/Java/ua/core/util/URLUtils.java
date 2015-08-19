package ua.core.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;


public class URLUtils {
	
	public static final String	URL_ENCODING_DEFAULT	= "UTF-8";
	
	public static String decode (String urlString) {
		
		try {
			
			return URLDecoder.decode (urlString, URL_ENCODING_DEFAULT);
		}
		catch (UnsupportedEncodingException e) {

			return urlString;
		}
	}
	
	public static String encode (String urlString) {
		
		try {
			
			return URLEncoder.encode (urlString, URL_ENCODING_DEFAULT);
		}
		catch (UnsupportedEncodingException e) {
			
			return urlString;
		}
	}

}
