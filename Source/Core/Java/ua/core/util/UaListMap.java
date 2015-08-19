package ua.core.util;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Creation date: (12/11/2001)
 * 
 * @author: Tim O'Connell
 * @version: 1.00.00
 * 
 *           The HashList is a combination of a Hashtable and an ArrayList Items
 *           can be retrieved by their code or by index. The order of the items
 *           is always maintained (first in, first out).
 */
public class UaListMap <ValueType> {

	private static int						ITEM_NOT_FOUND	= -1;

	private ArrayList <NVPair <ValueType>>	itemList;				// Contains
																	// the
																	// values
																	// for
	// all items.
	private UaMap <Integer>					indexMap;				// Contains
																	// the array
																	// index
																	// number
																	// for


	// all items, stored by item name. It is
	// used to get the array number from the
	// item name.

	/**
	 * Hashray constructor comment.
	 */
	public UaListMap () {

		super ();

		init (20);
	}


	public final void add (String name, ValueType item) {

		int index;
		NVPair <ValueType> nvPair = null;

		// ** See if already exist
		index = findIndex (name);

		if (index != ITEM_NOT_FOUND) {

			remove (index);
		}

		nvPair = new NVPair <ValueType> (name, item);

		// Item does not exist. Add it.
		itemList.add (nvPair);

		// Update the indexes.
		indexMap.put (name, new Integer (itemList.size () - 1));

	}


	public final void Update (String name, ValueType item) {

		int index;
		NVPair <ValueType> nvPair = null;

		// ** See if already exist
		index = findIndex (name);

		if (index == ITEM_NOT_FOUND) {

			nvPair = new NVPair <ValueType> (name, item);

			// Item does not exist. Add it.
			itemList.add (nvPair);

			// Update the indexes.
			indexMap.put (name, new Integer (itemList.size () - 1));

		}
		else {

			// Item does exist. Update it.

			nvPair = getNVPair (index);
			nvPair.setValue (item);
		}

	}


	/**
	 * 
	 * 
	 * Creation date: (12/11/2001 5:23:58 PM)
	 * 
	 * @author: Tim O'Connell
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 */
	public void clear () {

		itemList.clear ();
		indexMap.clear ();
	}


	/**
	 * 
	 * 
	 * Creation date: (12/11/2001 5:54:52 PM)
	 * 
	 * @author: Tim O'Connell
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @return int
	 * @param itemCode
	 *            java.lang.String
	 */
	private int findIndex (String name) {

		Integer indexNumber = null;

		indexNumber = (Integer) indexMap.get (name);

		if (indexNumber != null) {

			return indexNumber.intValue ();
		}
		else {

			return ITEM_NOT_FOUND;
		}
	}


	/**
	 * 
	 * @return item
	 * @param index
	 *            int
	 */
	public ValueType get (int index) {

		// /////////////////////////////////////////
		// Declarations:
		// /////////////////////////////////////////

		NVPair <ValueType> nvPair = null;

		// /////////////////////////////////////////
		// Code:
		// /////////////////////////////////////////

		nvPair = getNVPair (index);

		if (nvPair != null) {

			return nvPair.getValue ();
		}
		else {

			return null;
		}

	}


	/**
	 * 
	 * @return aiu.emma.util.ReferenceItem
	 * @param ItemCode
	 *            java.lang.String
	 */
	public ValueType get (String itemName) {

		int itemNumber;

		itemNumber = findIndex (itemName);

		if (itemNumber == ITEM_NOT_FOUND) {

			return null;
		}
		else {

			return get (itemNumber);
		}
	}


	/**
	 * 
	 * 
	 * Creation date: (12/11/2001 6:48:21 PM)
	 * 
	 * @author: Tim O'Connell
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @return java.lang.String
	 * @param itemNumber
	 *            int
	 */
	public String getName (int index) {

		// /////////////////////////////////////////
		// Declarations:
		// /////////////////////////////////////////

		NVPair <ValueType> nvPair = null;

		// /////////////////////////////////////////
		// Code:
		// /////////////////////////////////////////

		if (index < itemList.size () && index > -1) {

			nvPair = getNVPair (index);
			return nvPair.getName ();
		}
		else {

			return null;
		}
	}


	/**
	 * 
	 * 
	 * Creation date: (12/11/2001 6:07:13 PM)
	 * 
	 * @author: Tim O'Connell
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @param itemNumber
	 *            int
	 */
	public void remove (int index) {

		// /////////////////////////////////////////
		// Declarations:
		// /////////////////////////////////////////

		NVPair <ValueType> nvPair = null;

		int i;

		// /////////////////////////////////////////
		// Code:
		// /////////////////////////////////////////

		if (index < itemList.size () && index > -1) {

			nvPair = getNVPair (index);

			// Remove the item from the list
			itemList.remove (index);
			indexMap.remove (nvPair.getName ());

			// Update the index values in hash.

			for (i = index; i < itemList.size (); i++) {

				indexMap.put (getNVPair (i).getName (), new Integer (i));
			}

		}

	}


	/**
	 * 
	 * 
	 * Creation date: (12/11/2001 6:05:34 PM)
	 * 
	 * @author: Tim O'Connell
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @param itemCode
	 *            java.lang.String
	 */
	public void remove (String itemName) {

		int itemNumber;

		itemNumber = findIndex (itemName);

		if (itemNumber != ITEM_NOT_FOUND) {

			remove (itemNumber);
		}
	}


	/**
	 * 
	 * @return int
	 */
	public int size () {

		return itemList.size ();
	}


	/**
	 * 
	 * 
	 * Creation date: (2002/10/28 1:09:43 PM)
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @param size
	 *            int
	 */
	public UaListMap (int ipSize) {

		init (ipSize);
	}


	public Iterator <ValueType> getIterator () {

		return this.iterator ();
	}


	/**
	 * 
	 * 
	 * Creation date: (2002/10/28 1:20:54 PM)
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @param size
	 *            int
	 */
	private void init (int ipSize) {

		itemList = new ArrayList <NVPair <ValueType>> (ipSize);
		indexMap = new UaMap <Integer> ((int) (ipSize * 1.333));
	}


	private NVPair <ValueType> getNVPair (int index) {

		return itemList.get (index);
	}


	public Iterator <ValueType> iterator () {

		// /////////////////////////////////////////
		// Declarations:
		// /////////////////////////////////////////

		UaIterator <ValueType> iterator = null;

		NVPair <ValueType> nvPair = null;
		int index;

		// /////////////////////////////////////////
		// Code:
		// /////////////////////////////////////////

		iterator = new UaIterator <ValueType> (itemList.size ());

		for (index = 0; index < itemList.size (); index++) {

			nvPair = getNVPair (index);
			iterator.add (nvPair.getValue ());
		}

		return iterator;
	}
}
