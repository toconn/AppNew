/*
 * Created on May 12, 2004
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
public class StringTable {
	
	public static String CLASS_NAME = "StringTable";
	
	
	int			colSize;
	StringList	colTitleList		= null;
	String[][]	stringTableArray	= null;
	
	private int	defaultRowCapacity	= 10;
	
	int			rowSize;
	
	StringList	rowTitleList		= null;
	
	
	/**
	 * @param colSize, The column size to set.
	 */
	public StringTable (int newColumnSize) {

		super ();

		colSize			= newColumnSize;
		rowSize			= defaultRowCapacity;
		
		colTitleList	= new StringList (newColumnSize);
		rowTitleList	= new StringList (defaultRowCapacity);
		
		expandColumnTitleList(newColumnSize);
		expandRowTitleList(defaultRowCapacity);

		stringTableArray	= new String[rowSize][colSize];
	}
	/**
	 * @param rowSize, The row size to set.
	 * @param colSize, The column size to set.
	 */
	public StringTable (int newRowSize, int newColumnSize) {

		super ();

		colSize			= newColumnSize;
		rowSize			= newRowSize;
		
		colTitleList	= new StringList(newColumnSize);
		rowTitleList	= new StringList(newRowSize);

		expandColumnTitleList (newColumnSize);
		expandRowTitleList (newRowSize);

		stringTableArray	= new String[rowSize][colSize];
	}

private void checkRange(int rowIndex, int colIndex) {
	
	if (rowIndex >= rowSize || rowIndex < 0) {
		
	    throw new IndexOutOfBoundsException ("Error: " + CLASS_NAME + ".checkRange: " + "Row index out of range (0 <-> size - 1)" + ". " + "Row Index" + ": " + rowIndex + ", " + "Size" + ": " + rowSize);
	}

	if (colIndex >= colSize || colIndex < 0) {
		
	    throw new IndexOutOfBoundsException ("Error: " + CLASS_NAME + ".checkRange: " + "Column index out of range (0 <-> size - 1)" + ". " + "Column Index" + ": " + colIndex + ", " + "Size" + ": " + colSize);
	}

}
	 /* Increases the capacity of this <tt>StringList</tt> instance, if
	 * necessary, to ensure  that it can hold at least the number of elements
	 * specified by the minimum capacity argument. 
	 *
	 * @param   minCapacity   the desired minimum capacity.
	 */
public void ensureRowCapacity (int minCapacity) {

	// Declarations:

	int		oldCapacity;
	int		newCapacity;

	String	oldArray[][];


	// Code:
	
	
	oldCapacity = stringTableArray.length;
	
	if (minCapacity > oldCapacity) {
		
	    oldArray = stringTableArray;
	    
	    newCapacity = (oldCapacity * 3) / 2 + 1;
	    
		if (newCapacity < minCapacity) {
		    
			newCapacity = minCapacity;
		}
		
		stringTableArray = new String[newCapacity][colSize];
	    
	    System.arraycopy (oldArray, 0, stringTableArray, 0, rowSize);
	    
	}
	
}	/**
	 * @return Returns the columnSize.
	 */
	public int getColumnSize () {

		return colSize;
	}
	/**
	 * @return Returns the columnTitleList.
	 */
	public String getColumnTitle (int index) {

		return colTitleList.get (index);
	}
	public String getItem (int rowIndex, int colIndex){
	
		checkRange (rowIndex, colIndex);
		
		return stringTableArray [rowIndex][colIndex];
	}
	/**
	 * @return Returns the rowSize.
	 */
	public int getRowSize () {

		return rowSize;
	}
	/**
	 * @return Returns the rowTitleList.
	 */
	public String getRowTitle (int index) {

		return rowTitleList.get (index);
	}
	/**
	 * @param columnTitleList The columnTitleList to set.
	 */
	public void setColumnTitle (int index, String columnTitle) {

		this.colTitleList.set (index, columnTitle);
	}
	public void setItem (int rowIndex, int colIndex, String newItem){
	
		checkRange (rowIndex, colIndex);
		
		stringTableArray [rowIndex][colIndex] = newItem;
	}
	/**
	 * @param rowTitleList The rowTitleList to set.
	 */
	public void setRowTitle (int index, String rowTitle) {

		this.rowTitleList.set (index, rowTitle);
	}
	public String toString() {
		
		/////////////////////////////////
		// Declarations:
		/////////////////////////////////
		
		StringBuffer stringOutput = null;
		
		int rowIndex;
		int colIndex;
		
		/////////////////////////////////
		// Code:
		/////////////////////////////////
		
		stringOutput = new StringBuffer (256);
		
		stringOutput.append ("Columns: ");
		stringOutput.append (colTitleList.toString());
		stringOutput.append ("\n");
		
		for (rowIndex = 0; rowIndex < rowSize; rowIndex++) {
			
			if (! StringUtils.isEmpty (rowTitleList.get(rowIndex))) {
				stringOutput.append (rowTitleList.get(rowIndex));
				stringOutput.append (": ");
			}
			
			for (colIndex = 0; colIndex < colSize; colIndex++) {
				
				stringOutput.append (stringTableArray[rowIndex][colIndex]);
				
				if (colIndex < colSize - 1) {
					stringOutput.append(", ");
				}

			}
			
			if (rowIndex < rowSize - 1) {
				
				stringOutput.append ("\n");
			}
		}

		return stringOutput.toString();

	}
	public void addRow() {
	
		ensureRowCapacity (rowSize + 1);
		rowSize++;
		rowTitleList.add ((String) null);
	};
	public void addRow (String newRowTitle, String[] columnData) {
		
		if (columnData != null && columnData.length != colSize) {
			
		    throw new IndexOutOfBoundsException ("Error: " + CLASS_NAME + ".addRow: " + "Column data count does not match column size of table. " + "column data size" + ": " + columnData.length + ", " + " table column size" + ": " + colSize);
		}
		
		ensureRowCapacity (rowSize + 1);
		rowSize++;
		rowTitleList.add (newRowTitle);
		
		if (columnData != null) {
			stringTableArray[rowSize -1] = columnData;
		}
		
	}
	private void expandColumnTitleList (int columnSize) {
		
		int colIndex;
		
		for (colIndex = colTitleList.size(); colIndex < columnSize; colIndex++) {
			
			colTitleList.add((String) null);
		}
	}
	private void expandRowTitleList (int rowSize) {
		
		int rowIndex;
		
		for (rowIndex = rowTitleList.size(); rowIndex < rowSize; rowIndex++) {
			
			rowTitleList.add((String) null);
		}
	}
	
}
