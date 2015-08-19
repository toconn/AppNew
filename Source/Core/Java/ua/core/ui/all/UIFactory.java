package ua.core.ui.all;

import java.util.Locale;

import ua.core.ui.console.ConsoleCompUI;
import ua.core.ui.console.ConsoleUI;
import ua.core.ui.swing.SwingCompUI;
import ua.core.ui.swing.SwingUI;

public class UIFactory {
	
	public static ICompUI getCompUIConsole (Locale locale) {
		
		return new ConsoleCompUI (locale);
	}

	public static ICompUI getCompUISwing (Locale locale) {
		
		return new SwingCompUI (locale);
	}

	public static IUI getUIConsole (Locale locale) {
		
		return new ConsoleUI (locale);
	}

	public static IUI getUISwing (Locale locale) {
		
		return new SwingUI (locale);
	}

}
