package ie.gmit.sw;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Implementation of HashingType. HashCoder is a hashing function which performs
 * a .hashCode() representation of shingles. HashCoder is-a HashingType (Inheritance).
 * This class also follows the SRP.
 */
public class HashCoder implements HashingType {
	// Store hashed shingles in a tree set
	private Set<Integer> hashedShingles = new TreeSet<Integer>();

	@Override
	public Set<Integer> hash(List<String> shingles) {
		// For each shingle in shingles
		System.out.println("Using hashcode");
		for (String shingle : shingles) {
			// Get hash code of each shingle and add it to hashedShingles
			hashedShingles.add(shingle.hashCode());
		}
		// Return the hashedShingles
		return hashedShingles;
	}
}
