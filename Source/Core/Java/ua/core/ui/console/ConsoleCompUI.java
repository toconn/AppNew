package ua.core.ui.console;

import java.util.Locale;

import ua.core.exceptions.ExceptionRuntime;
import ua.core.exceptions.ExceptionUtils;
import ua.core.exceptions.ExceptionValidation;
import ua.core.service.message.MessageService;
import ua.core.ui.all.ICompUI;
import ua.core.ui.all.IUI;
import ua.core.ui.all.UIFactory;
import ua.core.util.Message;


public class ConsoleCompUI implements ICompUI {
	

	IUI		ui		= null;
	
	
	public ConsoleCompUI (Locale locale) {
		
		this.ui		= UIFactory.getUIConsole (locale);
	}
	
	
	public Locale getLocale () {
		
		return this.ui.getLocale();
	}


	public void updateStatusItem (String item) {

		this.ui.print (item);
	}


	public void updateStatus (Message message) {

		this.ui.print (MessageService.getFormattedMessage (getLocale(), message));
	}


	public void reportError (ExceptionRuntime e) {

		this.ui.print (ExceptionUtils.getExceptionStringList (e, getLocale ()));
	}


	public void reportValidationError (ExceptionValidation e) {

		this.ui.print (MessageService.getFormattedMessageList (getLocale(), e.getMessageList ()));

	}

}
