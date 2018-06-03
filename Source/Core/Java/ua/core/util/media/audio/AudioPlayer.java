package ua.core.util.media.audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import ua.core.exceptions.ExceptionRuntime;


public class AudioPlayer {
	
	public void play (File file) throws ExceptionRuntime {

		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		AudioListener		listener			= null;
		AudioInputStream	audioInputStream	= null;
		
		Clip				audioClip			= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		try {
		
			listener			= new AudioListener();
			audioInputStream	= AudioSystem.getAudioInputStream (file);
			
			audioClip			= AudioSystem.getClip();

			audioClip.addLineListener (listener);
			audioClip.open (audioInputStream);
			
		    try {
		    	
		    	audioClip.start();

		    	listener.waitUntilDone();
		    }
		    finally {
		    	
		    	audioClip.close();
		    }
		}
		catch (IOException e) {

			throw new ExceptionRuntime (e);
		}
		catch (UnsupportedAudioFileException e) {

			throw new ExceptionRuntime (e);
		}
		catch (LineUnavailableException e) {

			throw new ExceptionRuntime (e);
		}
		finally {
			
			if (audioInputStream != null) {

				try {
					
					audioInputStream.close();
				}
				catch (IOException e) {

					// Ignore.
				}

			}
		}
	}
	
	
	public void play (String fileName) throws ExceptionRuntime {

		
		play (new File (fileName));
	}
	
	
	public void playAsync (String fileName) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		AudioPlayerThead	thread	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		thread = new AudioPlayerThead (fileName);
		thread.start();
	}
	
	
	public void playAsync (File file) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		AudioPlayerThead	thread	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		thread = new AudioPlayerThead (file);
		thread.start();
	}

}
