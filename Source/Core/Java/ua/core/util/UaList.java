package ua.core.util;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Same as Java's ArrayList but adds constructor for array of objects.
 * 
 * @author Tadhg
 *
 * @param <E>
 */
public class UaList <E> extends ArrayList <E> {


	private static final long serialVersionUID = 1540097668081830055L;

	
	public UaList() {
		
		super();
	}

	
	public UaList (Collection <? extends E> collection) {
		
		super (collection);
	}

	
	public UaList (int size) {
		
		super (size);
	}
	
	
	public UaList (E[] dataArray) {
		
		int	index	= 0;
		
		for (index = 0; index < dataArray.length; index++) {
			
			this.add (dataArray [index]);
		}
	}
	
	
	public UaList <E> cloneMe() {
		
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		UaList<E>	cloneList	= null;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////

		cloneList = new UaList <E>(this);
		
		return cloneList;
	}

}
