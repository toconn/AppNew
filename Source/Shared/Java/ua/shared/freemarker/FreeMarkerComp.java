package ua.shared.freemarker;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;

import ua.core.exceptions.ExceptionValidation;
import ua.core.exceptions.IExceptionConst;
import ua.core.util.NVStringPair;
import ua.core.util.UaProperties;
import ua.core.util.dates.DateUtils;
import ua.core.util.file.FileUtils;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;


public class FreeMarkerComp {
	
	
	@SuppressWarnings ("rawtypes")
	public void processToFile (String templateFileName, String destinationFileName, HashMap templateDataMap) throws ExceptionValidation {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		Configuration		fmConfiguration		= null;
		Template			fmTemplate			= null;
		Writer				writer				= null;
		
		ExceptionValidation	exceptionValidation	= null;

		
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		try {
			
			// Setup FreeMarker...

			fmConfiguration = new Configuration();
			fmConfiguration.setDirectoryForTemplateLoading (new File (FileUtils.getFilePath (templateFileName)));
			fmConfiguration.setObjectWrapper (new DefaultObjectWrapper());
			
			fmTemplate = fmConfiguration.getTemplate (FileUtils.getFileNameNoPath (templateFileName));
			
			
			// write to destination file...

			writer = new OutputStreamWriter (new FileOutputStream (destinationFileName));
			fmTemplate.process (templateDataMap, writer);
			writer.flush();
		}
		catch (TemplateException e) {
			
			exceptionValidation = new ExceptionValidation (IFreeMarkerConst.MESSAGE_TEMPLATE_INVALID);
			exceptionValidation.addMessage (IExceptionConst.MESSAGE_UNFORMATTED.cloneMe (e.getMessage()));
			exceptionValidation.addMessage (IExceptionConst.MESSAGE_FILE_NAME.cloneMe (templateFileName));

			throw exceptionValidation;
		}
		catch (IOException e) {

			exceptionValidation = new ExceptionValidation (IFreeMarkerConst.MESSAGE_TEMPLATE_INVALID);
			exceptionValidation.addMessage (IExceptionConst.MESSAGE_UNFORMATTED.cloneMe (e.getMessage()));
			exceptionValidation.addMessage (IExceptionConst.MESSAGE_FILE_NAME.cloneMe (templateFileName));

			throw exceptionValidation;
		}
		
	}
	
	
	@SuppressWarnings ("rawtypes")
	public String processToString (String templateString, HashMap templateDataMap) throws ExceptionValidation {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		Configuration	fmConfiguration	= null;
		
		Template		fmTemplate		= null;
		Writer			writer			= null;

		
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		// Setup Free Maker...

		try {
			
			fmConfiguration = new Configuration();
			fmConfiguration.setObjectWrapper (new DefaultObjectWrapper());

			fmTemplate		= new Template ("name", new StringReader (templateString),  fmConfiguration);
			writer			= new StringWriter();
			
			fmTemplate.process (templateDataMap, writer);
		}
		catch (TemplateException e) {

			throw new ExceptionValidation (IFreeMarkerConst.MESSAGE_TEMPLATE_INVALID);
		}
		catch (IOException e) {

			throw new ExceptionValidation (IFreeMarkerConst.MESSAGE_TEMPLATE_INVALID);
		}
		
		return writer.toString();
	}
	
	
	/**
	 * Returns a data hash from a UaProperties instance suitable to pass to FreeMarker.
	 * 
	 * @param properties
	 * @return
	 */
	@SuppressWarnings ({ "rawtypes", "unchecked" })
	public HashMap getTemplateDataMap (UaProperties properties) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		HashMap		dataMap		= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
		
		dataMap = new HashMap();
		
		for (NVStringPair nvPair: properties) {
			
			dataMap.put (nvPair.getName(), nvPair.getValue());
		}
		
		
		return dataMap;
	}
	
	
	/**
	 * Adds default tags to the data Hash
	 * 
	 * Date, Day, Time, ISODate, ISODotDate
	 * 
	 * @param dataHash
	 */
	@SuppressWarnings ({ "rawtypes", "unchecked" })
	public void setDefaultDataTags (HashMap dataMap) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		Date	currentDate	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		currentDate	= new Date();
		

		// Add defaults...
		
		dataMap.put (IFreeMarkerConst.TAG_DAY,			DateUtils.getDay (currentDate));
		dataMap.put (IFreeMarkerConst.TAG_TIME,			DateUtils.getTime (currentDate));
		dataMap.put (IFreeMarkerConst.TAG_CURRENT_DATE,	currentDate);
		
		dataMap.put (IFreeMarkerConst.TAG_ISO_DATE, 	DateUtils.getISODate (currentDate));
		dataMap.put (IFreeMarkerConst.TAG_ISO_DOT_DATE,	DateUtils.getISODotDate (currentDate));
	}
}
