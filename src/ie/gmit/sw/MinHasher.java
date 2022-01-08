package ie.gmit.sw;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * Implementation of HashingType. MinHasher is a hashing function which hashes each
 * shingle by getting their minimal hashcodes and storing the first 250.
 * HashCoder is-a HashingType (Inheritance). This class also follows the SRP.
 */
public class MinHasher implements HashingType {
	// Stores the hashed value of shingles
	private Set<Integer> hashedShingles;;
	// Stores the random hashes
	private Set<Integer> hashes;
	// Number of min hashes K. Although K can be computed, I chose
	// to use a fixed size.
	private final int K = 250;
	// Seed for random integer generation so that each shingle gets hashed
	// the same way.
	private final int SEED = 40;

	/**
	 * Default Constructor.
	 */
	public MinHasher() {
		
	}

	@Override
	public Set<Integer> hash(List<String> shingles) {
		// Instantiate sets
		hashedShingles = new TreeSet<Integer>();
		hashes = new TreeSet<Integer>();
		
		System.out.println("Using minhasher");
		// Get sequence of random numbers
		Random r = new Random(SEED);
		// Generate K number of hashes to be added to the set
		for (int i = 0; i < K; i++) {
			// Each hash is the next integer in the random sequence.
			hashes.add(r.nextInt());
		}
		// For each integer in hashes.
		for (Integer hash : hashes) {
			// Set the minimum to the maximum int value as if we are decreasing
			// its value, this must be default to avoid the risk of error
			int min = Integer.MAX_VALUE;
			// For each shingle in shingles
			for (String shingle : shingles) {
				// Compute each shingles hash code by using a hash function, which
				// is just the shingles hash code to the power of a random hash
				int minHash = shingle.hashCode() ^ hash;
				// If the result of the above function is smaller than the min (MAX_VALUE),
				// then set that as the new min
				if (minHash < min)
					min = minHash;
			}
			// Add the min to the set
			hashedShingles.add(min);
		}
		// Return hashedShingles
		return hashedShingles;
	}
}