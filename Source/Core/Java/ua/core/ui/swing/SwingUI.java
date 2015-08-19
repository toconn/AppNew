package ua.core.ui.swing;

import java.util.Locale;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import ua.core.exceptions.ExceptionRuntime;
import ua.core.service.log.LogService;
import ua.core.ui.all.IUI;
import ua.core.ui.all.UIBase;

public class SwingUI extends UIBase implements IUI {


	public SwingUI (Locale locale) {
		
		try {
			
			UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
			
			LogService.error (new ExceptionRuntime (e));
		}
		
		setLocale (locale);
	}
	
	
	public void print() {
		
		// Does nothing.
	}


	public void print(String text) {

		JOptionPane.showMessageDialog(null, text);
	}

}
