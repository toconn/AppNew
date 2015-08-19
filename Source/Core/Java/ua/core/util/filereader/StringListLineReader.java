package ua.core.util.filereader;

import ua.core.util.StringList;
import ua.core.util.StringUtils;


public class StringListLineReader implements IDataLineReader <StringList> {
	
	private static final String	COMMENT_1	= "//";
	private static final String	COMMENT_2	= "#";
	
	
	
	private boolean				ignoreComments	= false;


	public StringListLineReader (boolean ignoreComments) {
		
		this.ignoreComments = ignoreComments;
	}
	
	
	public boolean processLine (StringList data, String lineString) {

		if (ignoreComments) {
			
			if (StringUtils.isNonEmpty (lineString))
				
				if (! (StringUtils.isStartsWith (lineString, COMMENT_1) || StringUtils.isStartsWith (lineString, COMMENT_2)))
						
					data.add (lineString);
		}
		else
			
			data.add (lineString);
		
		return true;
	}
}
