package ua.core.util.xml;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import ua.core.util.NVStringPair;
import ua.core.util.StringUtils;


public class XmlUtils {

	public static NVStringPair[]	XmlEscapeCharacterNVArray = {
		
		new NVStringPair ("<",	"&lt;"),
		new NVStringPair (">",	"&gt;"),
		new NVStringPair ("\"",	"&quot;"),
		new NVStringPair ("\"",	"&apos;"),
	};

	
	public static String decode (String encodedString) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////
	
		String		decodedString	= null;
	
	
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
	
		
		if (StringUtils.isNonEmpty (encodedString)) {
		
			decodedString = encodedString;
			
			for (NVStringPair nvPair: XmlEscapeCharacterNVArray) {
				
				decodedString = decodedString.replace (nvPair.value, nvPair.name);
			}
		}
		
		
		return decodedString;
	}

	
	public static String getFormattedXml (String xmlString) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

        Source				xmlInput			= null;
        StringWriter		stringWriter		= null;
        StreamResult		xmlOutput			= null;
        
        TransformerFactory	transformerFactory	= null;
        Transformer			transformer			= null;
        
        String				formattedXml		= null;

        
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

        try {
        	
	        xmlInput		= new StreamSource (new StringReader (xmlString));
	        stringWriter	= new StringWriter();
	        xmlOutput		= new StreamResult (stringWriter);
	        
	        transformerFactory = TransformerFactory.newInstance();
	        transformerFactory.setAttribute ("indent-number", 4);
	        
	        transformer = transformerFactory.newTransformer(); 
	        transformer.setOutputProperty (OutputKeys.INDENT, "yes");
	        transformer.transform (xmlInput, xmlOutput);
	        
	        formattedXml = xmlOutput.getWriter().toString();
	    }
	    catch (Exception e) {

	    	// To Do: Handle Exception..
	    }
        
        return formattedXml; 
	}
}
