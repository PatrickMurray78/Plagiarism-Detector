package ie.gmit.sw;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

// This class just hashes each shingle by getting minimal hashcodes for each
// one
public class MinHasher implements HashingType {
	// Stores the hashed value of shingles
	private Set<Integer> hashedShingles = new TreeSet<Integer>();
	// Stores the random hashes
	private Set<Integer> hashes = new TreeSet<Integer>();
	// Number of min hashes K
	private final int K = 250;
	// Seed for random integer generation
	private final int SEED = 40;

	// Default constructor
	public MinHasher() {
		
	}

	@Override
	public Set<Integer> hash(List<String> shingles) {
		// Get sequence of random numbers
		Random r = new Random(SEED);
		// Generate K number of hashes to be added to the set
		for (int i = 0; i < K; i++) {
			// Each hash is the next integer in the random sequence.
			hashes.add(r.nextInt());
		}
		// For each integer in hashes.
		for (Integer hash : hashes) {
			// Set the minimum to the maximum as if we are decreasing
			// its value, this must ne default
			int min = Integer.MAX_VALUE;
			// For each string in shingles
			for (String word : shingles) {
				// Compute each strings hash code by using a hash function, which
				// simple is the strings hash code to the power of a random hash
				int minHash = word.hashCode() ^ hash;
				// If the result of the above function is smaller than the min (MAX_VALUE),
				// then set that as the new min
				if (minHash < min)
					min = minHash;
			}
			// Add the min to the set
			hashedShingles.add(min);
		}
		return hashedShingles;
	}
}