package ua.core.util.file;

import java.io.File;
import java.io.FileFilter;


public class FileFilterDirectory implements FileFilter {
	
	public boolean accept (File file) {
		
		return file.isDirectory();
	}
}
