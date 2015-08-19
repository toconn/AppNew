package ua.core.util.filereader;

import ua.core.exceptions.ExceptionItemNotFound;
import ua.core.exceptions.ExceptionRuntime;
import ua.core.util.file.UaFileReader;


public class DataReader <E> {

	private IDataLineReader <E>	dataLineReader	= null;
	
	
	public DataReader (IDataLineReader <E> dataLineReader) {

		this.dataLineReader	= dataLineReader;
	}
	
	
	public E read (String fileName, E data) throws ExceptionItemNotFound, ExceptionRuntime {		

		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		UaFileReader	fileReader			= null;
		String			lineString			= null;
		
		boolean			hasData				= false;
		

		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		// Open File...
		
		try {
			
			fileReader = new UaFileReader (fileName);
			fileReader.open();
			
			lineString = fileReader.readLine();
			
			while (lineString != null) {
				
				if (this.dataLineReader.processLine (data, lineString)) {
					
					hasData = true;
				}
				
				// read next line...
				
				lineString = fileReader.readLine();
			}
		}
		finally {
			
			// Close file...
			
			if (fileReader != null) {
				
				fileReader.close();
			}
		}
		
		if (hasData) {
			
			return data;
		}
		else {
		
			return null;
		}

	}
}
