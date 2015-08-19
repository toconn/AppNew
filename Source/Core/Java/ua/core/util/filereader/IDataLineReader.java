package ua.core.util.filereader;


public interface IDataLineReader <E> {
	
	/**
	 * Processes the line of data.
	 * 
	 * Returns false - no data present.
	 * Returns true - data present.
	 * 
	 * @param data
	 * @param line
	 * @return
	 */
	public boolean processLine (E data, String lineString);
}
