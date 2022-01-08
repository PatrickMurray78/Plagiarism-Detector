package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Parserator. This class parses the document that we
 * are going to compare. Parser is-a Parserator (Inheritance). This class also
 * follows the SRP.
 */
public class Parser implements Parserator {
	// List of words which make up document
	private List<String> words;
	
	/**
	 * Default Constructor.
	 */
	public Parser() {
		
	}
	
	@Override
	public List<String> parse(BufferedReader br) throws IOException {
		// Instantiate words
		words = new ArrayList<String>();
		// Create a temp blank string
		String line = "";
		try {
			// While there are lines to be read, read them
			while((line = br.readLine()) != null) {
				// Everytime a space is encountered, split the line
				String[] split = line.split(" ");
				// For each split, add it to words
				for(int i = 0; i < split.length; i++) {
					words.add(split[i]);
				}
			}
		} finally {
			// Close buffered reader
			br.close();
		}
		// Return the list of words
		return words;
	}
}
