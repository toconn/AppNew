/*
 * Created on May 26, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package ua.core.util;


/**
 * @author TOCONNEL
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class NVIntPair implements IName {
	
	private String	name	= null;
	private int		value;

	
	public NVIntPair	cloneMe () {
		
		return new NVIntPair (this.name, this.value);
	}
	
	
	/**
	 * @return Returns the name.
	 */
	public String getName () {

		return name;
	}
	
	
	/**
	 * @param name The name to set.
	 */
	public void setName (String name) {

		this.name = name;
	}
	
	
	/**
	 * @return Returns the value.
	 */
	public int getValue () {

		return value;
	}
	
	
	/**
	 * @param value The value to set.
	 */
	public void setValue (int value) {

		this.value = value;
	}
	
	
	/**
	 * @param name
	 * @param value
	 */
	public NVIntPair (String name, int value) {

		super ();
		this.name = name;
		this.value = value;
	}
}
