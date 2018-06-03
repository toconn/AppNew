package ua.core.util.xml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPMessage;

import ua.core.util.IUtilConst;
import ua.core.util.StringUtils;


public class SoapUtils {
	
	
	public static SOAPElement addElement (SOAPMessage soapMessage, SOAPElement parentSoapElement, String elementName) throws SOAPException {
		
		return addElement (soapMessage, parentSoapElement, elementName, null);
	}
	
	
	public static SOAPElement addElement (SOAPMessage soapMessage, SOAPElement parentSoapElement, String elementName, String elementValue) throws SOAPException {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////
		
		Name					newSoapElementName	= null;
		SOAPElement				newSoapElement		= null;
		

		
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
		
		newSoapElementName	= soapMessage.getSOAPPart().getEnvelope().createName (elementName);
		newSoapElement		= parentSoapElement.addChildElement (newSoapElementName);
		
		if (StringUtils.isNonEmpty (elementValue)) {
			
			newSoapElement.addTextNode (elementValue);
		}
		
		return newSoapElement;
	}
	
	
	public static void addMimeHeader (SOAPMessage requestSoapMessage, String mimeName, String mimeValue) {
	
		requestSoapMessage.getMimeHeaders().addHeader (mimeName, mimeValue);
	}
	
	
	public static void addNamespaceDeclaration (SOAPMessage soapMessage, String prefix, String namespaceURI) throws SOAPException {
		
		soapMessage.getSOAPPart().getEnvelope().addNamespaceDeclaration (prefix, namespaceURI);
	}
	
	
	public static SOAPMessage callSoapService (SOAPMessage requestSoapMessage, String SoapUrl) throws UnsupportedOperationException, SOAPException, MalformedURLException {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////
	
		SOAPConnection	soapConnection		= null;
	
		SOAPMessage		responseSoapMessage	= null;
	
	
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
	
		// Send request...
	
		soapConnection		= SoapUtils.getSoapConnection();
		
		responseSoapMessage = soapConnection.call (requestSoapMessage, new URL (SoapUrl));
		soapConnection.close();
		
		return responseSoapMessage;
	}
	

	public static SOAPBodyElement getActionBodyElement (SOAPMessage soapMessage, String targetAction, String targetActionPrefix, String targetUri) throws SOAPException {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		SOAPFactory				soapFactory		= null;
		
		SOAPBody				soapBody		= null; 
		SOAPBodyElement			soapBodyElement	= null;

		Name					soapElementName	= null;

		
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
		
		soapFactory			= SOAPFactory.newInstance();
		
		if (StringUtils.isNonEmpty (targetUri))
			
			soapElementName		= soapFactory.createName (targetAction, targetActionPrefix, targetUri);
		
		else
			
			soapElementName		= soapFactory.createName (targetAction);
		
		soapBody			= soapMessage.getSOAPBody();
		soapBodyElement		= soapBody.addBodyElement (soapElementName);
		
		return soapBodyElement;
	}
	
	
	public static SOAPElement getNewElement (SOAPMessage soapMessage, SOAPElement parentSoapElement, String elementName) throws SOAPException {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////
		
		Name					newSoapElementName	= null;
		SOAPElement				newSoapElement		= null;
		

		
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
		
		newSoapElementName	= soapMessage.getSOAPPart().getEnvelope().createName (elementName);
		newSoapElement		= parentSoapElement.addChildElement (newSoapElementName);

		return newSoapElement;
	}
	
	
	public static SOAPConnection getSoapConnection() throws UnsupportedOperationException, SOAPException {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		SOAPConnectionFactory	soapConnectionFactory	= null;
		SOAPConnection			soapConnection			= null;

		
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
		
		soapConnectionFactory	= SOAPConnectionFactory.newInstance();
		soapConnection			= soapConnectionFactory.createConnection();

		
		return soapConnection;
	}
	
	
	public static SOAPMessage getSoapMessage() throws SOAPException {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		MessageFactory			messageFactory			= null;
		SOAPMessage				soapMessage				= null;		


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		messageFactory	= MessageFactory.newInstance();
		soapMessage		= messageFactory.createMessage();


		return soapMessage;
	}
	
	
	public static String getXml (SOAPMessage soapMessage) throws SOAPException, IOException {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		ByteArrayOutputStream	xmlByteArrayStream	= null;
		String					xmlString			= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		xmlByteArrayStream	= new ByteArrayOutputStream();
		
		soapMessage.writeTo (xmlByteArrayStream);

		xmlString			= new String (xmlByteArrayStream.toByteArray(), IUtilConst.CHARSET_ISO_8859);
		
		return xmlString;
	}
}
