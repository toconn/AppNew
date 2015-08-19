package ua.core.ui.all;

import java.util.Locale;

import ua.core.exceptions.ExceptionRuntime;
import ua.core.exceptions.ExceptionValidation;
import ua.core.util.Message;


public interface ICompUI {
	
	public Locale	getLocale				();
	public void		updateStatusItem		(String item);
	public void		updateStatus			(Message message);
	public void		reportError				(ExceptionRuntime e);
	public void		reportValidationError	(ExceptionValidation e);
}
