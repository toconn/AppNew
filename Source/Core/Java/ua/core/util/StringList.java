package ua.core.util;

import java.util.*;
/*
 * @(#)src/classes/sov/java/util/StringList.java, hs122, hs122, 20001020 1.4.1.2
 * ===========================================================================
 * Licensed Materials - Property of IBM
 * IBM Java(tm)2 SDK, Standard Edition, v 1.2
 *
 * (C) Copyright IBM Corp. 1999 All Rights Reserved.
 * Copyright 1997-1999 by Sun Microsystems, Inc.,
 * ===========================================================================
 */
 
/*
 * @(#)StringList.java	1.19 99/04/22
 *
 */

/**
 * Resizable-array implementation of the <tt>List</tt> interface.  Implements
 * all optional list operations, and permits all elements, including
 * <tt>null</tt>.  In addition to implementing the <tt>List</tt> interface,
 * this class provides methods to manipulate the size of the array that is
 * used internally to store the list.  (This class is roughly equivalent to
 * <tt>Vector</tt>, except that it is unsynchronized.)<p>
 *
 * The <tt>size</tt>, <tt>isEmpty</tt>, <tt>get</tt>, <tt>set</tt>,
 * <tt>iterator</tt>, and <tt>listIterator</tt> operations run in constant
 * time.  The <tt>add</tt> operation runs in <i>amortized constant time</i>,
 * that is, adding n elements requires O(n) time.  All of the other operations
 * run in linear time (roughly speaking).  The constant factor is low compared
 * to that for the <tt>LinkedList</tt> implementation.<p>
 *
 * Each <tt>StringList</tt> instance has a <i>capacity</i>.  The capacity is
 * the size of the array used to store the elements in the list.  It is always
 * at least as large as the list size.  As elements are added an StringList,
 * its capacity grows automatically.  The details of the growth policy are not
 * specified beyond the fact that adding an element has constant amortized
 * time cost.<p> 
 *
 * An application can increase the capacity of an <tt>StringList</tt> instance
 * before adding a large number of elements using the <tt>ensureCapacity</tt>
 * operation.  This may reduce the amount of incremental reallocation.<p>
 *
 * <strong>Note that this implementation is not synchronized.</strong> If
 * multiple threads access an <tt>StringList</tt> instance concurrently, and at
 * least one of the threads modifies the list structurally, it <i>must</i> be
 * synchronized externally.  (A structural modification is any operation that
 * adds or deletes one or more elements, or explicitly resizes the backing
 * array; merely setting the value of an element is not a structural
 * modification.)  This is typically accomplished by synchronizing on some
 * object that naturally encapsulates the list.  If no such object exists, the
 * list should be "wrapped" using the <tt>Collections.synchronizedList</tt>
 * method.  This is best done at creation time, to prevent accidental
 * unsynchronized access to the list:
 * <pre>
 *	List list = Collections.synchronizedList(new StringList(...));
 * </pre><p>
 *
 * The iterators returned by this class's <tt>iterator</tt> and
 * <tt>listIterator</tt> methods are <i>fail-fast</i>: if list is structurally
 * modified at any time after the iterator is created, in any way except
 * through the iterator's own remove or add methods, the iterator will throw a
 * ConcurrentModificationException.  Thus, in the face of concurrent
 * modification, the iterator fails quickly and cleanly, rather than risking
 * arbitrary, non-deterministic behavior at an undetermined time in the
 * future.
 *
 * @author  Josh Bloch
 * @version 1.19 04/22/99
 * @see	    Collection
 * @see	    List
 * @see	    LinkedList
 * @see	    Vector
 * @see	    Collections#synchronizedList(List)
 * @since JDK1.2
 */

public class StringList implements Cloneable, java.io.Serializable, Iterable <String> {
						            
	private static final long 	serialVersionUID = 3266314499844219017L;

	public static String		CLASS_NAME		= StringList.class.getName();

	/**
	 * The array buffer into which the elements of the StringList are stored.
	 * The capacity of the StringList is the length of this array buffer.
	 */
	private transient String[]	elementArray;

	/**
	 * The size of the StringList (the number of elements it contains).
	 *
	 * @serial
	 */
	private int					size;

	/**
	 * The initial default capacity of the StringList.
	 *
	 */
	private static final int	defaultInitalCapacity	= 10;

	
	public StringList() {
		
		init (defaultInitalCapacity);
	
	}
	
	/**
	 * Constructs an empty list with the specified initial capacity.
	 *
	 * @param   initialCapacity   the initial capacity of the list.
	 */
	public StringList (int initialCapacity) {
		
		super();
	
		init (initialCapacity);
	
	}
	
	
	public StringList (String stringArray[]) {
		
		init (defaultInitalCapacity);
		add (stringArray);
	}
	
	/**
	 * Inserts the specified element at the specified position in this
	 * list. Shifts the element currently at that position (if any) and
	 * any subsequent elements to the right (adds one to their indices).
	 *
	 * @param index index at which the specified element is to be inserted.
	 * @param element element to be inserted.
	 * @throws    IndexOutOfBoundsException if index is out of range
	 *		  <tt>(index &lt; 0 || index &gt; size())</tt>.
	 */
	public void add (int index, String element) {
		
		if (index > size || index < 0) {
	
		    throw new IndexOutOfBoundsException ("Index out of range (0 <-> size)" + "." + "Index" + ": " + index + ", " + "Size" + ": " + size);
		}
	
		ensureCapacity (size + 1);
		
		System.arraycopy (elementArray, index, elementArray, index + 1, size - index);
		
		elementArray[index] = element;
		
		size ++;
	}
	/**
	 * Appends the specified element to the end of this list.
	 *
	 * @param o element to be appended to this list.
	 * @return <tt>true</tt> (as per the general contract of Collection.add).
	 */
	public boolean add (String element) {
	
		ensureCapacity (size + 1); 
		
		elementArray[size] = element;
		size ++;
		
		return true;
	}
	
	
	/**
	 * 
	 * 
	 * Creation date: (2002/04/02 5:55:01 PM)
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @param newStringList tadhg.util.StringList
	 */
	public void add(String stringArray[]) {
	
		/////////////////////////////////
		// Declarations:
		/////////////////////////////////
	
		int	i;
	
			
		/////////////////////////////////
		// Code:
		/////////////////////////////////
		
		if (stringArray != null) {
		
			ensureCapacity (size + stringArray.length);
		
			for (i = 0; i < stringArray.length; i ++) {
				
				elementArray[size] = stringArray[i];
				size ++;
			}
		}
	}

	
	/**
	 * 
	 * 
	 * Creation date: (2002/04/02 5:55:01 PM)
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @param newStringList tadhg.util.StringList
	 */
	public void add(StringList newList) {
	
		/////////////////////////////////
		// Declarations:
		/////////////////////////////////
	
		int	i;
	
			
		/////////////////////////////////
		// Code:
		/////////////////////////////////
		
		if (newList != null) {
		
			ensureCapacity (size + newList.size);
		
			for (i = 0; i < newList.size; i ++) {
				
				elementArray[size] = newList.elementArray[i];
				size ++;
			}
		}
	}
	
	
	/**
	 * Check if the given index is in range.  If not, throw an appropriate
	 * runtime exception.
	 */
	private void checkRange(int index) {
		
		if (index >= size || index < 0) {
			
		    throw new IndexOutOfBoundsException ("Error: StringList.checkRange: " + "Index out of range (0 <-> size - 1)" + "." + "Index" + ": " + index + ", " + "Size" + ": " + size);
		}
	
	}
	
	
	/**
	 * Removes all of the elements from this list.  The list will
	 * be empty after this call returns.
	 */
	public void clear() {
	
		// Declarations:
	
		int	index;
	
	
		// Code:
		
		// Clear out old strings.
		for (index = 0; index < size; index++) {
			
		    elementArray[index] = null;
		}
	
		size = 0;
	}
	
	/**
	 * Returns a shallow copy of this <tt>StringList</tt> instance.  (The
	 * elements themselves are not copied.)
	 *
	 * @return  a clone of this <tt>StringList</tt> instance.
	 */
	public Object clone() {
	
		return cloneMe();
	}
	
	
	public StringList cloneMe () {
		
		
		// Declarations:
		
		StringList	newList;
	
	
		// Code:
		
		try {
			
		    newList = (StringList) super.clone();
		    
		    newList.elementArray = new String[size];
		    newList.size = size;
		    System.arraycopy (elementArray, 0, newList.elementArray, 0, size);
	
		    return newList;
		    
		}
		catch (CloneNotSupportedException e) {
	
		    // this shouldn't happen, since we are Cloneable
		    throw new InternalError();
		}		
	}
	
	
	/**
	 * Returns <tt>true</tt> if this list contains the specified element.
	 *
	 * @param o element whose presence in this List is to be tested.
	 */
	public boolean contains(String element) {
		
		return indexOf(element) >= 0;
	}
	
	
	/**
	 * Increases the capacity of this <tt>StringList</tt> instance, if
	 * necessary, to ensure  that it can hold at least the number of elements
	 * specified by the minimum capacity argument. 
	 *
	 * @param   minCapacity   the desired minimum capacity.
	 */
	public void ensureCapacity (int minCapacity) {
	
		// Declarations:
	
		int		oldCapacity;
		int		newCapacity;
	
		String[]	oldArray;
	
	
		// Code:
		
		
		oldCapacity = elementArray.length;
		
		if (minCapacity > oldCapacity) {
			
		    oldArray = elementArray;
		    
		    newCapacity = (oldCapacity * 3) / 2 + 1;
		    
			if (newCapacity < minCapacity) {
			    
				newCapacity = minCapacity;
			}
			
		    elementArray = new String[newCapacity];
		    
		    System.arraycopy (oldArray, 0, elementArray, 0, size);
		    
		}
		
	}
	// Positional Access Operations
	
	/**
	 * Returns the element at the specified position in this list.
	 *
	 * @param  index index of element to return.
	 * @return the element at the specified position in this list.
	 * @throws    IndexOutOfBoundsException if index is out of range <tt>(index
	 * 		  &lt; 0 || index &gt;= size())</tt>.
	 */
	
	public String get(int index) {
	
		checkRange (index);
	
		return elementArray[index];
	}
	
	
	public String[] getArray () {
		
		return toArray();
	}
	
	
	/**
	 * 
	 * 
	 * Creation date: (2002/10/29 3:32:37 PM)
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @return java.util.Iterator
	 */
	public Iterator <String> getIterator() {
	
		return iterator ();
	}
	
	
	/**
	 * Returns the list in reverse order.
	 * 
	 * @return
	 */
	public StringList getReverseStringList () {
		

		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		StringList	reverseList	= null;
		
		int			i;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		reverseList = new StringList ();
		
		for (i = size - 1; i > -1; i--) {
			
			reverseList.add (elementArray[i]);
		}
		
		
		return reverseList;
	}
	
	
	/**
	 * Searches for the first occurence of the given argument, testing 
	 * for equality using the <tt>equals</tt> method. 
	 *
	 * @param   elem   an object.
	 * @return  the index of the first occurrence of the argument in this
	 *          list; returns <tt>-1</tt> if the object is not found.
	 * @see     Object#equals(Object)
	 */
	public int indexOf (String element) {
		
		if (element == null) {
	
		    for (int i = 0; i < size; i++) {
			    
				if (elementArray[i]==null) {
			 	   return i;
				}
				
		    }
		
		}
		else {
			
		    for (int i = 0; i < size; i++) {
				if (element.equalsIgnoreCase (elementArray[i])) {
			    	return i;
				}
	
		    }
		   
		}
		
		return -1;
		
	}
	/**
	 * 
	 * 
	 * Creation date: (2002/10/14 2:45:12 PM)
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @param listSize int
	 */
	private void init(int ipInitialCapacity) {
	
		elementArray	= new String[ipInitialCapacity];
		size		= 0;
	
	}
		/**
		 * Tests if this list has no elements.
		 *
		 * @return  <tt>true</tt> if this list has no elements;
		 *          <tt>false</tt> otherwise.
		 */
	public boolean isEmpty() {
		
		return size == 0;
	}
	/**
	 * Returns the index of the last occurrence of the specified object in
	 * this list.
	 *
	 * @param   elem   the desired element.
	 * @return  the index of the last occurrence of the specified object in
	 *          this list; returns -1 if the object is not found.
	 */
	public int lastIndexOf (String element) {
		
		if (element == null) {
			
		    for (int i = size - 1; i >= 0; i--) {
				if (elementArray[i]==null) {
				    return i;
				}
			}    
		}
		else {
			
		    for (int i = size - 1; i >= 0; i--) {
				if (element.equalsIgnoreCase (elementArray[i])) {
				
			 	   return i;
				}
			}
		}
		
		return -1;
	}
	
	
	public void prependAll (String prependString) {
		
		int i;
		
		for (i = 0; i < size; i++) {
			
			elementArray [i] = prependString + elementArray [i];
		}
	}
	
	
	public void prependAllExceptBlanks (String prependString) {
		
		int i;
		
		for (i = 0; i < size; i++) {
			
			if (StringUtils.isNonEmpty (elementArray [i])) {
				
				elementArray [i] = prependString + elementArray [i];
			}
		}
	}
	
	
	/**
	 * Reconstitute the <tt>StringList</tt> instance from a stream (that is,
	 * deserialize it).
	 */
	private synchronized void readObject(java.io.ObjectInputStream s)
		throws java.io.IOException, ClassNotFoundException {
			
		// Read in size, and any hidden stuff
		s.defaultReadObject();
	
		// Read in array length and allocate array
		int arrayLength = s.readInt();
		elementArray = new String[arrayLength];
	
		// Read in all elements in the proper order.
		for (int i=0; i < size; i++) {
	
			elementArray[i] = (String) s.readObject();
		}
	
	}
	/**
	 * Removes the element at the specified position in this list.
	 * Shifts any subsequent elements to the left (subtracts one from their
	 * indices).
	 *
	 * @param index the index of the element to removed.
	 * @return the element that was removed from the list.
	 * @throws    IndexOutOfBoundsException if index out of range <tt>(index
	 * 		  &lt; 0 || index &gt;= size())</tt>.
	 */
	public void remove (int index) {
	
		// Declarations:
		int moveCount;
	
	
		// Code:
			
		checkRange (index);
	
	
		moveCount = size - index - 1;
	
		if (moveCount > 0) {
		    System.arraycopy (elementArray, index + 1, elementArray, index, moveCount);
		}
		
		// Delete old string for garbage collection.
		size --;
		elementArray[size] = null; // Let gc do its work
	
	}
	/**
	 * Removes from this List all of the elements whose index is between
	 * fromIndex, inclusive and toIndex, exclusive.  Shifts any succeeding
	 * elements to the left (reduces their index).
	 * This call shortens the list by <tt>(toIndex - fromIndex)</tt> elements.
	 * (If <tt>toIndex==fromIndex</tt>, this operation has no effect.)
	 *
	 * @param fromIndex index of first element to be removed.
	 * @param fromIndex index after last element to be removed.
	 */
	protected void removeRange (int indexFrom, int indexTo) {
	
		// Declarations
	
		int moveCount;
		int	newSize;
	
	
		// Code:
	
		moveCount = size - indexTo;
		System.arraycopy(elementArray, indexTo, elementArray, indexFrom, moveCount);
	
		// Delete old strings for garbage collection.
		newSize = size - (indexTo - indexFrom);
		
		while (size != newSize) {
	
			size --;
		    elementArray[size] = null;
		}
	}
		/**
		 * Replaces the element at the specified position in this list with
		 * the specified element.
		 *
		 * @param index index of element to replace.
		 * @param element element to be stored at the specified position.
		 * @return the element previously at the specified position.
		 * @throws    IndexOutOfBoundsException if index out of range
		 *		  <tt>(index &lt; 0 || index &gt;= size())</tt>.
		 */
	public void set(int index, String newString) {
			
		checkRange (index);
	
		elementArray[index] = newString;
		
	}
		/**
		 * Returns the number of elements in this list.
		 *
		 * @return  the number of elements in this list.
		 */
	public int size() {
		
		return size;
	}
	
	
	public void sort () {
		
		if (size > 0) {
			
		  Arrays.sort (elementArray, 0, size);
		}
	}
	
	
	/**
	 * Returns an array containing all of the elements in this list
	 * in the correct order.
	 *
	 * @return an array containing all of the elements in this list
	 * 	       in the correct order.
	 */
	public String[] toArray() {
	
		//////////////////////////////////////////////////////////////////
		// Declarations:
		//////////////////////////////////////////////////////////////////
		
		String[] stringListArray = null;
		
		
		//////////////////////////////////////////////////////////////////
		// Code:
		//////////////////////////////////////////////////////////////////
		
		stringListArray = new String [size];
		System.arraycopy (elementArray, 0, stringListArray, 0, size);
		
		return stringListArray;
	}
	
	/**
	 * Returns an array containing all of the elements in this list in the
	 * correct order.  The runtime type of the returned array is that of the
	 * specified array.  If the list fits in the specified array, it is
	 * returned therein.  Otherwise, a new array is allocated with the runtime
	 * type of the specified array and the size of this list.<p>
	 *
	 * If the list fits in the specified array with room to spare (i.e., the
	 * array has more elements than the list), the element in the array
	 * immediately following the end of the collection is set to
	 * <tt>null</tt>.  This is useful in determining the length of the list
	 * <i>only</i> if the caller knows that the list does not contain any
	 * <tt>null</tt> elements.
	 *
	 * @param a the array into which the elements of the list are to
	 *		be stored, if it is big enough; otherwise, a new array of the
	 * 		same runtime type is allocated for this purpose.
	 * @return an array containing the elements of the list.
	 * @throws ArrayStoreException if the runtime type of a is not a supertype
	 *         of the runtime type of every element in this list.
	 */
	
	public String[] toArray (String newArray[]) {
		
		if (newArray.length < size) {
			//newArray = (String[]) java.lang.reflect.Array.newInstance (newArray.getClass().getComponentType(), size);
			newArray = new String[size];
		}
	
		System.arraycopy (elementArray, 0, newArray, 0, size);
	
		if (newArray.length > size) {
			newArray[size] = null;
		}
	
		return newArray;
	}
	/**
	 * Trims the capacity of this <tt>StringList</tt> instance to be the
	 * list's current size.  An application can use this operation to minimize
	 * the storage of an <tt>StringList</tt> instance.
	 */
	public void trimToSize() {
	
		// Declarations:
	
		int		oldCapacity;
		String	oldArray[];
	
	
		// Code:
		
		oldCapacity = elementArray.length;
		
		if (size < oldCapacity) {
			
		    oldArray = elementArray;
		    elementArray = new String[size];
		    
		    System.arraycopy(oldArray, 0, elementArray, 0, size);
		}
		
	}
	/**
	 * Save the state of the <tt>StringList</tt> instance to a stream (that
	 * is, serialize it).
	 *
	 * @serialData The length of the array backing the <tt>StringList</tt>
	 *             instance is emitted (int), followed by all of its elements
	 *             (each an <tt>Object</tt>) in the proper order.
	 */
	private synchronized void writeObject (java.io.ObjectOutputStream s)
		throws java.io.IOException {
			
		// Write out element count, and any hidden stuff
		s.defaultWriteObject();
	
		// Write out array length
		s.writeInt (elementArray.length);
	
		// Write out all elements in the proper order.
		for (int i=0; i < size; i++) {
			
			s.writeObject (elementArray[i]);
		}
			
	}
	public String toString() {
		
		/////////////////////////////////
		// Declarations:
		/////////////////////////////////
		
		StringBuffer stringOutput = null;
		
		int rowIndex;
		
		/////////////////////////////////
		// Code:
		/////////////////////////////////
		
		stringOutput = new StringBuffer (128);
		
		for (rowIndex = 0; rowIndex < size; rowIndex++) {
			
			if (elementArray[rowIndex] != null) {
				stringOutput.append(elementArray[rowIndex]);
			}
			
			if (rowIndex < size - 1) {
				stringOutput.append(", ");
			}
	
		}
	
		return stringOutput.toString();
	
	}
	public java.util.Iterator <String> iterator() {
		
		//////////////////////////////////////////////////////////////////
		// Declarations:
		//////////////////////////////////////////////////////////////////
		
		int      			index;
		UaIterator <String> iterator;
		
		
		//////////////////////////////////////////////////////////////////
		// Code:
		//////////////////////////////////////////////////////////////////
		
		iterator = new UaIterator <String> (size);
		
		for (index = 0; index < size; index++) {
			
			iterator.add(elementArray [index]);
		}
	
		return iterator;
	}
}
