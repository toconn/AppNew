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
public class NVStringPair implements IName {
	
	public String	name	= null;
	public String	value	= null;
	
	
	public NVStringPair cloneMe() {

		return new NVStringPair (this.name, this.value);
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {

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
	public String getValue() {

		return value;
	}
	/**
	 * @param value The value to set.
	 */
	public void setValue (String value) {

		this.value = value;
	}
	/**
	 * @param name
	 * @param value
	 */
	public NVStringPair (String name, String value) {

		super();
		this.name = name;
		this.value = value;
	}
	public NVStringPair() {

		super();
	}

	@Override
	public String toString() {

		return "NVStringPair [name=" + name + ", value=" + value + "]";
	}
}
