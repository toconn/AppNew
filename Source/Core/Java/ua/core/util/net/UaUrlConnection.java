package ua.core.util.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class UaUrlConnection {
	
	private String			urlString		= null;
	private URLConnection	urlConnection	= null;

	
	public UaUrlConnection (String urlString) throws MalformedURLException, IOException {
		
		this.urlString		= urlString;
		
		this.urlConnection	= (new URL (urlString)).openConnection();
		
		this.urlConnection.setDoInput	(true);
		this.urlConnection.setDoOutput	(true);
		this.urlConnection.setUseCaches	(false);
	}


	
	public String getUrlString() {
	
		return urlString;
	}
	
	
	public String read() throws IOException {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		BufferedReader	connectionReader		= null;

		StringBuilder	dataBuilder				= null;
		String			dataLine				= null;
		
		
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		connectionReader = new BufferedReader (new InputStreamReader (this.urlConnection.getInputStream()));

		
		dataBuilder = new StringBuilder();
	
		while ((dataLine = connectionReader.readLine()) != null) {

			dataBuilder.append (dataLine);
		}
		
		return dataBuilder.toString();
	}
	
	
	public void write (String data) throws IOException {

		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		BufferedWriter	connectionWriter		= null;

		
		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		connectionWriter = new BufferedWriter (new OutputStreamWriter (this.urlConnection.getOutputStream()));
		connectionWriter.write (data);
		connectionWriter.flush();
		connectionWriter.close();
	}
}
