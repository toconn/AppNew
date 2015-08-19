package ua.core.util.file;

import java.io.File;
import java.io.FilenameFilter;

import ua.core.util.StringUtils;


public class FilenameFilterStartsWith implements FilenameFilter {
	
	
	private String	nameStart	= null;
	
	
	public FilenameFilterStartsWith (String nameStart) {
		
		this.nameStart = nameStart;
	}


	public boolean accept (File directory, String fileName) {
		
		return StringUtils.isStartsWith (fileName, this.nameStart);
	}
}
