package ua.core.util;

/**
 * @author:
 */
public class SimpleObjectList {

	public static String	CLASS_NAME	= "SimpleObjectList";

	private int				size;
	private int				capacity;
	private Object[]		objectArray	= null;


	public SimpleObjectList () {

		super ();
		clear ();
	}


	public void add (Object[] newObjectArray) {

		// ///////////////////////////////
		// Code:
		// ///////////////////////////////

		if (newObjectArray != null && newObjectArray.length > 0) {

			checkCapacity (newObjectArray.length);
			System.arraycopy (newObjectArray, 0, objectArray, size, newObjectArray.length);
			size += newObjectArray.length;

		}

	}


	public void add (Object newObject) {

		checkCapacity (1);
		objectArray[size] = newObject;
		size++;

	}


	public void checkCapacity (int increment) {

		// ///////////////////////////////
		// Declarations:
		// ///////////////////////////////

		Object[] oldArray = null;

		// ///////////////////////////////
		// Code:
		// ///////////////////////////////

		if (size + increment > capacity) {

			// Increment by 3 if we are adding 1 record.
			capacity = size + (increment == 1 ? 3 : increment);

			oldArray = objectArray;
			objectArray = new Object[capacity];

			if (oldArray != null && size > 0) {
				System.arraycopy (oldArray, 0, objectArray, 0, size);
			}

		}

	}


	public void clear () {

		size = 0;
		capacity = 0;
		objectArray = null;

	}


	public Object get (int index) {

		if (index >= 0 && index < size) {

			return objectArray[index];
		}
		else {

			return null;
		}
	}


	public Object[] getArray () {

		// ///////////////////////////////
		// Declarations:
		// ///////////////////////////////

		Object[] newArray;

		// ///////////////////////////////
		// Code:
		// ///////////////////////////////

		newArray = new Object[size];

		System.arraycopy (objectArray, 0, newArray, 0, size);

		return newArray;

	}


	public int getCount () {

		return size;
	}
}
