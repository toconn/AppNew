package ua.core.ui.all;

import java.util.ArrayList;
import java.util.Locale;

import ua.core.util.StringList;

public interface IUI {

	public void		print ();
	public void		print (String text);
	public void		print (String textArray []);
	public void		print (StringList textList);
	public void		print (ArrayList<String> textList);
	public Locale	getLocale ();
}
