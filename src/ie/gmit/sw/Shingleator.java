package ie.gmit.sw;

import java.util.List;

// Interface which describes each shingleator object.
// Shinglator objects are used to create shingles from
// parsed documents.
public interface Shingleator {
	// Creates a list of shingles from a list of words and returns the
	// created list
	public List<String> getShingles(List<String> words);
}
