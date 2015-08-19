package ua.core.util.file;

import java.io.File;
import java.io.FilenameFilter;

import ua.core.util.StringUtils;


public class FilenameFilterContains implements FilenameFilter {
	
	
	private String	nameContains	= null;
	
	
	public FilenameFilterContains (String nameContains) {
		
		this.nameContains = nameContains;
	}


	public boolean accept (File directory, String fileName) {
		
		return StringUtils.isContains (fileName, this.nameContains);
	}

}
