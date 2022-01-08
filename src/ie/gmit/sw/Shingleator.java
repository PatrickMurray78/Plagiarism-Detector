package ie.gmit.sw;

import java.util.List;

/**
 * Interface which describes each Shingleator object.
 * Shingleator objects are used to create shingles from parsed documents.
 * Follows the SRP.
 */
public interface Shingleator {
	/**
	 * Takes a list of words and creates a list of shingles from them.
	 * 
	 * @param words - List of words to be shingled
	 * 
	 * @return list of shingled words
	 */
	public List<String> getShingles(List<String> words);
}
