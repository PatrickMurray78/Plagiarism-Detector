package ie.gmit.sw;

import java.util.ArrayList;
import java.util.List;

// This class simply creates shingles of size n from
// the parsed text document.
/**
 * Implementation of Shingleator. This class simply creates
 * shingles of size k. Shingler is-a Shingleator (Inheritance).
 * Follows the SRP.
 */
public class Shingler implements Shingleator{
	// Constant shingle size 3
	private final int SHINGLE_SIZE = 3;
		
	// List of shingled words
	private List<String> shingles;	public Shingler() {

	}
	
	@Override
	public List<String> getShingles(List<String> words) {
		// Instantiate shingles
		shingles = new ArrayList<String>();
		// Create a temp blank string to store each shingle
		String shingle = "";
		// Set the count to 0
		int count = 0;
		// Loop over the list of words
		for (int i = 0; i < words.size(); i++) {
			// Add each word to the shingle
			shingle += words.get(i);
			// Increment count
			count++;
			// If the count is the same size as the shingle size
			if (count == SHINGLE_SIZE) {
				// Add the shingle to shingles
				shingles.add(shingle);
				// Reset string
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
