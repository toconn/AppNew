package ua.core.util;

import java.util.*;

/**
 * This is an implementation of the standard iterator. It is a generic
 * iterator class that can be used by other classes that do not want
 * to create their own. This class does not support exception handling
 * around changes in the original collection. 
 * 
 * Creation date: (2002/10/28 10:49:57 AM)
 */
public class UaIterator <ValueType> implements java.util.Iterator <ValueType> {

	public static String	CLASS_NAME	= "Iterator";

	ArrayList <ValueType>	itemList	= null;
	int						cursor;

	
	/**
	 * UAIterator constructor comment.
	 */
	public UaIterator() {
		super();
	
		init (10);
	}
	
	
	/**
	 * 
	 * 
	 * Creation date: (2002/10/28 5:53:59 PM)
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @param size int
	 */
	public UaIterator (int size) {
	
		init (size);
	}
	
	
	/**
	 * 
	 * 
	 * Creation date: (2002/10/28 10:50:52 AM)
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @param opItem java.lang.Object
	 */
	public void add (ValueType item) {
	
		if (item != null) {
	
			itemList.add (item);
		}
	
	}
	
	
	/**
	 * 
	 * 
	 * Creation date: (2002/10/28 12:00:11 PM)
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @param aopItemList java.util.ArrayList
	 */
	public void addAll (List <ValueType> newItemList) {
	
		/////////////////////////////////
		// Declarations:
		/////////////////////////////////
	
		java.util.Iterator <ValueType> iterator	= null;
	
			
		/////////////////////////////////
		// Code:
		/////////////////////////////////
	
		if (newItemList != null) {
		
			itemList.ensureCapacity (itemList.size() + newItemList.size());
			
			iterator = newItemList.iterator();
	
			while (iterator.hasNext()) {
				
				this.itemList.add (iterator.next());
			}
		}
	}
	
	
	/**
	 * 
	 * 
	 * Creation date: (2002/10/28 12:13:47 PM)
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @param hopItemHash java.util.Hashtable
	 */
	public void addAll (Dictionary <?, ValueType> dictionary) {
		
		/////////////////////////////////
		// Declarations:
		/////////////////////////////////
	
		Enumeration <?>	enumeration	= null;
	
			
		/////////////////////////////////
		// Code:
		/////////////////////////////////
	
		if (dictionary != null) {
		
			itemList.ensureCapacity (itemList.size() + dictionary.size());
			
			enumeration = dictionary.keys();
	
			while (enumeration.hasMoreElements()) {
				
				itemList.add (dictionary.get (enumeration.nextElement()));
			}
		}
	}
	
	
	public void addAll (Map <?, ValueType> map) {
		
		itemList.ensureCapacity (itemList.size() + map.size());
		
		for (ValueType item: map.values()) {
			
			itemList.add (item);
		}
	}
	
	
	/**
	 * Returns <tt>true</tt> if the iteration has more elements. (In other
	 * words, returns <tt>true</tt> if <tt>next</tt> would return an element
	 * rather than throwing an exception.)
	 *
	 * @return <tt>true</tt> if the iterator has more elements.
	 */
	public boolean hasNext() {
		
		return cursor < itemList.size();
	}
	
	
	/**
	 * 
	 * 
	 * Creation date: (2002/10/28 5:54:28 PM)
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @param size int
	 */
	private void init (int size) {
	
		itemList	= new ArrayList <ValueType> (size);
		cursor		= 0;
		
	}

	
	/**
	 * Returns the next element in the interation.
	 *
	 * @returns the next element in the interation.
	 * @exception NoSuchElementException iteration has no more elements.
	 */
	public ValueType next() {
	
		/////////////////////////////////
		// Code:
		/////////////////////////////////
	
		if (cursor < itemList.size()) {
	
			return itemList.get (cursor ++);
		}
		else {
	
			throw new NoSuchElementException();
		}
	}
	
	
	/**
	 *
	 * NOT IMPLEMENTED!
	 *
	 * Removes from the underlying collection the last element returned by the
	 * iterator (optional operation).  This method can be called only once per
	 * call to <tt>next</tt>.  The behavior of an iterator is unspecified if
	 * the underlying collection is modified while the iteration is in
	 * progress in any way other than by calling this method.
	 *
	 * @exception UnsupportedOperationException if the <tt>remove</tt>
	 *		  operation is not supported by this Iterator.
	 
	 * @exception IllegalStateException if the <tt>next</tt> method has not
	 *		  yet been called, or the <tt>remove</tt> method has already
	 *		  been called after the last call to the <tt>next</tt>
	 *		  method.
	 */
	public void remove() {
	
		throw new UnsupportedOperationException();	
	}
	
	
	/**
	 * 
	 * 
	 * Creation date: (2002/10/28 5:57:09 PM)
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 */
	public void reset() {
	
		cursor = 0;
	}
}
