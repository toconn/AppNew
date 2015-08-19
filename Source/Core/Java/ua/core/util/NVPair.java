package ua.core.util;

public class NVPair <ValueType> implements IName {
	
	private String		name	= null;
	private ValueType	value	= null;

	
	public NVPair <ValueType> cloneMe () {
		
		return new NVPair <ValueType> (this.name, this.value);
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
	public ValueType getValue () {

		return value;
	}
	
	/**
	 * @param value The value to set.
	 */
	public void setValue (ValueType value) {

		this.value = value;
	}
	
	/**
	 * @param name
	 * @param value
	 */
	public NVPair (String name, ValueType value) {

		super ();
		this.name = name;
		this.value = value;
	}
	
}
