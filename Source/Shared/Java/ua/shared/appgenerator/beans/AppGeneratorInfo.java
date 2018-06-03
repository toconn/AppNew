package ua.shared.appgenerator.beans;

import ua.core.util.UaProperties;

public class AppGeneratorInfo {

	String			appName			= null;
	String			templateName	= null;

	UaProperties	appProperties	= null;


	public AppGeneratorInfo() {

		super();
	}


	public AppGeneratorInfo (String appName, String templateName, UaProperties appProperties) {

		super();
		this.appName = appName;
		this.templateName = templateName;
		this.appProperties = appProperties;
	}


	public String getAppName() {

		return appName;
	}


	public UaProperties getAppProperties() {

		return appProperties;
	}


	public String getTemplateName() {

		return templateName;
	}


	public void setAppName (String appName) {

		this.appName = appName;
	}


	public void setAppProperties (UaProperties appProperties) {

		this.appProperties = appProperties;
	}


	public void setTemplateName (String templateName) {

		this.templateName = templateName;
	}

}
