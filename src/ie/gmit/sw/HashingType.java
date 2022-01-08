package ie.gmit.sw;

import java.util.List;
import java.util.Set;

/**
 * Interface for all hashing methods. All classes which implement
 * this interface provide a method of hashing a document for the jaccard 
 * calculations and also for creating a Document object which will be stored
 * in the database. This interface follows the SRP.
 */
public interface HashingType {
	/**
	 * Takes a list of words and applies a hashing function to them.
	 * 
	 * @param shingles - List of shingled words to be hashed
	 * @return a set of hashes
	 */
	public Set<Integer> hash(List<String> shingles);
}
