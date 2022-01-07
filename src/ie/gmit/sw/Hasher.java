package ie.gmit.sw;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Hasher {
	List<String> shingles = new ArrayList<String>();

	public Hasher() {

	}

	public Hasher(List<String> shingles) {
		// Set the shingles
		this.shingles = shingles;
	}

	public Set<Integer> hash(HashingType hs) {
		// return the set by delegating the work to the hashing algorithm and
		// returning what it returns
		return hs.hash(shingles);
	}
}
