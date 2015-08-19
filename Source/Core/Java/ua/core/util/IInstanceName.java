package ua.core.util;

/**
 * Returns the name (id) of the object. This interface allows the actual name attribute to
 * be defined separately from the object and leaves the work of retrieving it 
 * to the class implementing this interface.
 * 
 * The class retrieving the name will pass an instance of the object
 * to this class and allow it to retrieve the actual name.
 * 
 * Used primarily to initialize collections data defined using generics.
 *
 * @param <ValueType>
 */
public interface IInstanceName <ValueType> {
	
	public String getName (ValueType item);
}
