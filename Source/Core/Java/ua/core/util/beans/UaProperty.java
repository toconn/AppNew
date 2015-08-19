package ua.core.util.beans;

import ua.core.util.beans.IBeanConst.PropertyType;

public class UaProperty {
	
	
	String			name	= null;
	PropertyType	type	= PropertyType.None;
	int				length	= 0;
	
	
	UaProperty (String name, PropertyType type) {
		
		this.name	= name;
		this.type	= type;
		this.length	= 0;
	}
	
	UaProperty (String name, PropertyType type, int length) {
		
		this.name	= name;
		this.type	= type;
		this.length	= length;
	}
	
	
	public int getLength() {
		return length;
	}
	
	
	public void setLength(int length) {
		this.length = length;
	}

	
	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public PropertyType getType() {
		return type;
	}
	
	
	public void setType(PropertyType type) {
		this.type = type;
	}
	
	
}
