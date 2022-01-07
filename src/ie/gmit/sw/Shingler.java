package ie.gmit.sw;

import java.util.ArrayList;
import java.util.List;

// This class simply creates shingles of size n from
// the parsed text document.
public class Shingler implements Shingleator{
	// Constant shingle size 3
	private final int SHINGLE_SIZE = 2;
		
	// List of shingled words
	private List<String> shingles = new ArrayList<String>();
	
	// Default constructor
	public Shingler() {

	}
	
	// Using list of words, create shingles of given size
	@Override
	public List<String> getShingles(List<String> words) {
		String shingle = "";
		// Set the count to 0
		int count = 0;
		
		for (int i = 0; i < words.size(); i++) {
			// Add each word to the shingle
			shingle += words.get(i);
			// Increment count
			count++;
			// If the count is the same size as the shingle size
			if (count == SHINGLE_SIZE) {
				// Add the temp string to shingles list
				shingles.add(shingle);
				shingle = "";
				// Reset count
				count = 0;
			}
			// If we are at the end of the loop and the count is less than the shingle size, add
			// the shingle anyway.
			if (count < SHINGLE_SIZE && i == words.size() - 1) {
				shingles.add(shingle);
			}
		}
		
		// Return the shingles
		return shingles;
	}
}
