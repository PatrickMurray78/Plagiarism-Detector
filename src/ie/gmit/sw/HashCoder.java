package ie.gmit.sw;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

// This class just hashes each shingle and adds them to a tree set
public class HashCoder implements HashingType {
	// Store hashed shingles in a tree set
	private Set<Integer> hashedShingles = new TreeSet<Integer>();

	@Override
	public Set<Integer> hash(List<String> shingles) {
		for (String shingle : shingles) {
			// Get hash code of each shingle and add it to hashedShingles
			hashedShingles.add(shingle.hashCode());
		}
		return hashedShingles;
	}
}
