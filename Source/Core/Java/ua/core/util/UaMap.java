package ua.core.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Creation date: (2009.08.10)
 * 
 * @author: Tim O'Connell
 * @version: 1.00.00
 * 
 * This is a standard HashMap where the keys are strings only.
 */

public class UaMap <ValueType> extends HashMap <String, ValueType> {
	
	// This is a standard HashMap where the keys are strings only.

	private static final long serialVersionUID = -2670524593128733048L;
	

	public UaMap() {
		
		super();
	}
	

	public UaMap (int initialCapacity, float loadFactor) {
		
		super (initialCapacity, loadFactor);
	}
	

	public UaMap (int initialCapacity) {
		
		super (initialCapacity);
	}
	

	public UaMap (Map <? extends String, ? extends ValueType> map) {
		
		super();
		
		this.putAll (map);
	}
	
	
	public UaMap (IPropertyBridgeName <ValueType> instanceName, ValueType[] valueArray) {
		
		super (valueArray.length);
		
		for (ValueType item: valueArray) {
				
			add (instanceName.getName (item), item);	
		}
	}
	
	
	public ValueType add (String key, ValueType value) {
		
		return this.put (key, value);
	}
	
	
	public void addAll (Map <? extends String, ? extends ValueType> map) {
		
		this.putAll (map);
	}
	

	public boolean containsKey (String key) {
		
		return (super.containsKey (getKeyActual (key)));
	}
	
	
	public ValueType get (String key) {
		
		return super.get (getKeyActual (key));
	}
	
	
	private String getKeyActual (String key) {
		
		return StringUtils.toLowerCase (key);
	}
	
	
	public StringList getKeyList() {
		
		///////////////////////////////////////////////////////////////////////
		// Declarations:
		///////////////////////////////////////////////////////////////////////

		StringList keyList = null;
		
		
		///////////////////////////////////////////////////////////////////////
		// Code:
		///////////////////////////////////////////////////////////////////////

		keyList = new StringList();
		
		for (String key : this.keySet()) {
			
			keyList.add (key);
		}
		
		return keyList;
	}
	
	
	public int getKeyMaxLength() {
		
		int maxLength = 0;
		
		for (String key: this.keySet()) {
			
			if (key != null && key.length() > maxLength) {
				
				maxLength = key.length();
			}
		}
		
		return maxLength;
	}
	
	
	public StringList getSortedKeyList() {
		
		///////////////////////////////////////////////////////////////////////
		// Declarations:
		///////////////////////////////////////////////////////////////////////

		StringList keyList = null;
		
		
		///////////////////////////////////////////////////////////////////////
		// Code:
		///////////////////////////////////////////////////////////////////////

		keyList = this.getKeyList();
		
		keyList.sort();
		
		return keyList;
	}
	
	
	public boolean hasKey (String key) {
		
		return containsKey (key);
	}
	
	
	public ValueType put (String key, ValueType value) {
		
		return super.put (getKeyActual (key), value);
	}
	
	
	public void putAll (Map <? extends String, ? extends ValueType> map) {
		
		for (String key: map.keySet()) {
			
			put (key, map.get (key));
		}
	}
}
