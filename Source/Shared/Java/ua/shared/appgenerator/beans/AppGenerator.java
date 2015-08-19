package ua.shared.appgenerator.beans;

import java.util.ArrayList;
import ua.core.util.NVStringPair;
import ua.core.util.StringList;
import ua.core.util.UaProperties;


public class AppGenerator {
	
	private String						appName						= null;

	private String						generatorName				= null;
	private String						generatorDir				= null;

	private StringList					directoryCreateStringList	= null;
	private ArrayList <NVStringPair>	directoryCopyNVList			= null;
	private ArrayList <NVStringPair>	templateNVList				= null;
	
	private UaProperties				tagProperties				= null;
	private UaProperties				processedTagProperties		= null;
	private UaProperties				appTagProperties			= null;
	

	public AppGenerator () {
		
		this.directoryCopyNVList		= new ArrayList <NVStringPair>();
		this.directoryCreateStringList	= new StringList();
		this.templateNVList				= new ArrayList <NVStringPair>();
		
		this.tagProperties				= new UaProperties();
		this.processedTagProperties		= new UaProperties();
	}

	
	public String getAppName () {
	
		return appName;
	}


	public UaProperties getAppTagProperties () {
	
		return appTagProperties;
	}
	
	
	public ArrayList <NVStringPair> getDirectoryCopyNVList () {
	
		return directoryCopyNVList;
	}
	
	
	public StringList getDirectoryCreateStringList () {
	
		return directoryCreateStringList;
	}
	
	
	public String getGeneratorDir () {
	
		return generatorDir;
	}


	public String getGeneratorName () {
	
		return generatorName;
	}

	
	public UaProperties getProcessedTagProperties () {
	
		return processedTagProperties;
	}

	
	public UaProperties getTagProperties () {
	
		return tagProperties;
	}


	public ArrayList <NVStringPair> getTemplateNVList () {
	
		return templateNVList;
	}

	
	public void setAppName (String appName) {
	
		this.appName = appName;
	}
	
	
	public void setAppTagProperties (UaProperties appTagProperties) {
	
		this.appTagProperties = appTagProperties;
	}
	
	
	public void setDirectoryCopyNVList (ArrayList <NVStringPair> directoryCopyNVList) {
	
		this.directoryCopyNVList = directoryCopyNVList;
	}
	
	
	public void setDirectoryCreateStringList (StringList directoryCreateStringList) {
	
		this.directoryCreateStringList = directoryCreateStringList;
	}

	
	public void setGeneratorDir (String generatorDir) {
	
		this.generatorDir = generatorDir;
	}


	public void setGeneratorName (String generatorName) {
	
		this.generatorName = generatorName;
	}


	public void setProcessedTagProperties (UaProperties processedTagProperties) {
	
		this.processedTagProperties = processedTagProperties;
	}
	
	
	public void setTagProperties (UaProperties tagProperties) {
	
		this.tagProperties = tagProperties;
	}
	
	
	public void setTemplateNVList (ArrayList <NVStringPair> templateNVList) {
	
		this.templateNVList = templateNVList;
	}
}
