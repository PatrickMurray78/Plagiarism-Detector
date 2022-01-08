package ie.gmit.sw;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Hasher is a gateway method which is used to invoke an algorithm.
 * This will allow me to use the strategy pattern in other classes by passing
 * an instance of the HashingType interface into the hash function.
 */
public class Hasher {
	// List of shingles
	List<String> shingles = new ArrayList<String>();

	/**
	 * Default Constructor.
	 */
	public Hasher() {

	}
	
	/**
	 * Constructor with Fields.
	 * 
	 * @param shingles - A list of shingled words to hash
	 */
	public Hasher(List<String> shingles) {
		this.shingles = shingles;
	}

	/**
	 * The function hash will hash the list of shingles above
	 * using an instance of HashingType. This is an implementation of
	 * the strategy pattern.
	 * 
	 * @param ht - Instance of HashingType
	 * @return a set of hashes
	 */
	public Set<Integer> hash(HashingType ht) {
		// Delegate the hashing to the hashing algorithm and return
		// its result
		return ht.hash(shingles);
	}
}
