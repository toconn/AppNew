package ua.core.ui.console;

import java.util.Locale;

import ua.core.ui.all.*;


public class ConsoleUI extends UIBase implements IUI {
	
	public ConsoleUI (Locale locale) {
		
		setLocale (locale);
	}
	
	public void print() {
		
		System.out.println ("");
	}
	
	public void print (String text) {
	
		System.out.println (text);
	}
}
