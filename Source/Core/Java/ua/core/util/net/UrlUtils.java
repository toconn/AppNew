package ua.core.util.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class UrlUtils {
	
	
	public static URLConnection getReadWriteConnection (String UrlString) throws MalformedURLException, IOException {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		URLConnection	urlConnection		= null;
		

		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////
		
		urlConnection = (new URL (UrlString)).openConnection();
		
		urlConnection.setDoInput	(true);
		urlConnection.setDoOutput	(true);
		urlConnection.setUseCaches	(false);
		
		return urlConnection;
	}
	
	
	public static String connectionRead (URLConnection urlConnection) throws IOException {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		BufferedReader	connectionReader		= null;

		StringBuilder	dataBuilder				= null;
		String			dataLine				= null;
		
		
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		connectionReader = new BufferedReader (new InputStreamReader (urlConnection.getInputStream()));

		
		dataBuilder = new StringBuilder();
	
		while ((dataLine = connectionReader.readLine()) != null) {

			dataBuilder.append (dataLine);
		}
		
		return dataBuilder.toString();
	}

	
	
	public static void connectionWrite (URLConnection urlConnection, String data) throws IOException {

		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		BufferedWriter	connectionWriter		= null;

		
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		connectionWriter = new BufferedWriter (new OutputStreamWriter (urlConnection.getOutputStream()));
		connectionWriter.write (data);
		connectionWriter.flush();
		connectionWriter.close();
	}

}
