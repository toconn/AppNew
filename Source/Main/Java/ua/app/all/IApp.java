package ua.app.all;

import ua.core.exceptions.ExceptionRuntime;
import ua.core.exceptions.ExceptionValidation;

public interface IApp {

	public void	init()		throws ExceptionRuntime, ExceptionValidation;
	public void	config()	throws ExceptionRuntime, ExceptionValidation;
	public void	run()		throws ExceptionRuntime, ExceptionValidation;
}
