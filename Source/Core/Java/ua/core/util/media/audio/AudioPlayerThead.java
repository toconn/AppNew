package ua.core.util.media.audio;

import java.io.File;

import ua.core.exceptions.ExceptionRuntime;


class AudioPlayerThead extends Thread {
	
	private File	file = null;
	
	
	public AudioPlayerThead (File file) {
		
		this.file = file;
	}
	
	
	public AudioPlayerThead (String fileName) {
		
		this.file = new File (fileName);
	}
	
	
	public void run () {
		
		try {
			
			(new AudioPlayer()).play (this.file);
		}
		catch (ExceptionRuntime e) {

			// Ignore
		}
	}

}
