package ua.core.util.media.audio;

import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;


public class AudioListener implements LineListener {
	
	private boolean done	= false;

	
	public synchronized void update (LineEvent event) {
	    	
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		Type eventType	= null;
		    
		
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		eventType = event.getType();

		if (eventType == Type.STOP || eventType == Type.CLOSE) {
		   	
			this.done = true;
			notifyAll();
		}
	}

	
    public synchronized void waitUntilDone() {
    	
    	while (! this.done) {
    		
    		try {
    			
				wait();
			}
			catch (InterruptedException e) {

				// Ignore.
				
				this.done = true;
			}
    			
    	}   	
    }
}
