package ua.core.util;

/**
 * StringCarver is a string manipulation class that allows you to locate, slice and return portions
 * of a string.
 * 
 * @author toconnell
 *
 */
public class StringCarver {
	

	/**
	 * EMPTY_STRING is always returned when nothing is found.
	 */
	public static String	EMPTY_STRING	= "";
	
	private	int				indexStart		= 0;
	private int				indexEnd		= 0;
	
	private boolean			isEmpty			= false;
	
	private String			string			= null;
	
	
	public StringCarver (String string) {
		
		
		this.string = string;
		
		resetPointers();
		
		if (StringUtils.isEmpty (this.string))
			
			markEmpty();
	}
	
	
	public void endFindLast (String matchText) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		int	index	= 0;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
		
		if (! isEmpty()) {

			index = this.string.lastIndexOf (matchText);
			
			if ((index == -1) || isCrossed (this.indexStart, index))
					
				markEmpty();
			
			else
				
				this.indexEnd = index;
		}

	}
	
	
	public void endFindPrevious (String matchText) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		int	index	= 0;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		if (! isEmpty()) {

			index = this.string.lastIndexOf (matchText, this.indexEnd);
		
			if ((index == -1) || isCrossed (this.indexStart, index))
					
				markEmpty();
			
			else
				
				this.indexEnd = index;
		}

	}
	
	
	private boolean isCrossed (int startIndex, int endIndex) {
		
		return (startIndex >= endIndex - 1);
	}
	
	
	private void markEmpty() {
		
		this.isEmpty = true;
	}
	
	
	public void startFindFirst (String matchText) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		int	index	= 0;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		if (! isEmpty()) {

			index = this.string.indexOf (matchText);
		
			if (index == -1 || isCrossed (index + matchText.length(), this.indexEnd))
					
				markEmpty();
			
			else 
				
				this.indexStart = index + matchText.length();
		}
	}
		
	
	public void startFindNext (String matchText) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		int	index	= 0;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
		
		if (! isEmpty()) {

			index = this.string.indexOf (matchText, this.indexStart);
			
			if (index == -1 || isCrossed (index + matchText.length(), this.indexEnd))
					
				markEmpty();
			
			else 
				
				this.indexStart = index + matchText.length();
		}
	}
		
	
	public boolean isEmpty() {
		
		return this.isEmpty;
	}
	
	
	public void resetStartPointer() {
		
		this.indexStart = 0;
		this.isEmpty = false;
	}
	
	
	public void resetEndPointer() {
		
		this.indexEnd = StringUtils.getLength (this.string);
		this.isEmpty = false;
	}
	
	
	public void resetPointers() {
		
		resetStartPointer();
		resetEndPointer();
	}
	
	
	public String toString() {
		
		if (isEmpty())
		
			return StringCarver.EMPTY_STRING;
		
		else 
			
			return this.string.substring (this.indexStart, this.indexEnd);
	}
}
