package ua.core.service.shell;

import java.util.ArrayList;

import ua.core.util.CollectionUtils;
import ua.core.util.NVStringPair;
import ua.core.util.StringList;
import ua.core.util.StringUtils;
import ua.core.util.UaProperties;


public class Shell {
	
	public String		shellConfigFileName			= null;
	
	public UaProperties	shellCommandProperties		= new UaProperties();
	public UaProperties	shellExtensionProperties	= new UaProperties();
	public UaProperties	shellLeadProperties			= new UaProperties();
	
	
	public Shell (String shellConfigFileName, UaProperties shellCommandProperties, UaProperties shellExtensionProperties, UaProperties shellLeadProperties) {
		
		this.shellConfigFileName		= shellConfigFileName;
		
		this.shellCommandProperties		= shellCommandProperties;
		this.shellExtensionProperties	= shellExtensionProperties;
		this.shellLeadProperties		= shellLeadProperties;
	}
	
	
	public String getConfigFileName() {
		
		return this.shellConfigFileName;
	}
	
	
	public String getExtensionCommandType (String extension) {
		
		return this.shellExtensionProperties.getProperty (extension);
	}
	
	
	public ArrayList <StringList> getExtensionCommandTypeStringListList() {
		
		return CollectionUtils.toStringListArrayList (this.shellExtensionProperties.getPropertyNVList());
	}
	
	
	public String getLeadCommandType (String text) {
		

		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		String	leadActual	= null;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////

		for (NVStringPair leadNV : this.shellLeadProperties) {
		
			if (StringUtils.isStartsWith (text, leadNV.name)) {
				
				leadActual = leadNV.value;
				break;
			}
		}		
		
		return leadActual;
	}


	public ArrayList <StringList> getLeadCommandTypeStringListList() {

		return CollectionUtils.toStringListArrayList (this.shellLeadProperties.getPropertyNVList());
	}


	/**
	 * Looks up the command type and returns the actual command. 
	 *
	 * Ex: command Type "Text", command "Notepad.exe".
	 * 
	 * @param commandType
	 * @return
	 */
	public String getTypeCommand (String commandType) {
		
		return this.shellCommandProperties.getProperty (commandType);
	}
	
	
	public ArrayList <StringList> getTypeCommandStringListList() {
		
		return CollectionUtils.toStringListArrayList (this.shellCommandProperties.getPropertyNVList());
	}
	
	
	/**
	 * Tests to see if the commandType has been defined.
	 * 
	 * Ex: Is the type "Text" defined?
	 * 
	 * @param commandType
	 * @return
	 */
	public boolean hasTypeCommand (String commandType) {
		
		return this.shellCommandProperties.hasProperty (commandType);
	}
	
	
	public boolean isKnownExtension (String extension) {
		
		return this.shellExtensionProperties.hasProperty (extension);
	}

	
	
	public void setConfigFileName (String fileName) {

		this.shellConfigFileName = fileName;
		
	}

}
