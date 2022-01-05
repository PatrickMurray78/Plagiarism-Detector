package ie.gmit.sw;

import java.util.List;
import java.util.Set;

// This interface is for all hashing methods.
public interface HashingType {
	// Takes a list of words and applies a hashing function to them
	// returning a set of hashes.
	public Set<Integer> hash(List<String> shingles);
}
