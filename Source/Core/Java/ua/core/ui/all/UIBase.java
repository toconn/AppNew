package ua.core.ui.all;

import java.util.ArrayList;
import java.util.Locale;

import ua.core.util.StringList;
import ua.core.util.system.OSUtils;

public abstract class UIBase {
	
	private Locale	locale = null;
	
	
	public abstract void print (String text);

	
	public void print (String textArray []) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		StringBuilder	stringBuilder	= null;
		boolean			first;
		
		String			lineSeparator	= null;

		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		stringBuilder	= new StringBuilder ();
		lineSeparator	= OSUtils.getLineSeparator ();
		
		first = true;
		

		for (String text : textArray) {
			
			if (first) {
				
				first = false;
			}
			else {
				
				stringBuilder.append (lineSeparator);
			}
			
			stringBuilder.append (text);
		}
		
		print (stringBuilder.toString ());
	}

	
	public void print (StringList textList) {

		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		StringBuilder	stringBuilder	= null;
		boolean			first;
		
		String			lineSeparator	= null;

		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		stringBuilder	= new StringBuilder ();
		lineSeparator	= OSUtils.getLineSeparator ();
		
		first = true;
		

		for (String text : textList) {
			
			if (first) {
				
				first = false;
			}
			else {
				
				stringBuilder.append (lineSeparator);
			}
			
			stringBuilder.append (text);
		}
		
		print (stringBuilder.toString ());
	}

	
	public void print (ArrayList<String> textList) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		StringBuilder	stringBuilder	= null;
		boolean			first;
		
		String			lineSeparator	= null;

		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		stringBuilder	= new StringBuilder ();
		lineSeparator	= OSUtils.getLineSeparator ();
		
		first = true;
		

		for (String text : textList) {
			
			if (first) {
				
				first = false;
			}
			else {
				
				stringBuilder.append (lineSeparator);
			}
			
			stringBuilder.append (text);
		}
		
		print (stringBuilder.toString ());
	}

	
	public Locale getLocale() {
		
		return locale;
	}

	
	protected void setLocale (Locale locale) {
		
		this.locale = locale;
	}
}
