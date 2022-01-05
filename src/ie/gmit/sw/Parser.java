package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Purpose of this class is to parse the uploaded text document into a
// list of words which are then returned back to controller.
public class Parser implements Parserator {
	// List of words which make up document
	private List<String> words = new ArrayList<String>();
	
	// Default Constructor
	public Parser() {
		
	}
	
	// Reads text file and returns list of words
	@Override
	public List<String> parse(BufferedReader br) throws IOException {
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
		
		return words;
	}
}
