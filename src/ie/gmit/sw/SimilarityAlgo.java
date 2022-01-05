package ie.gmit.sw;

import java.util.Set;

// Interface which describes  all comparator objects.
// A comparator object is used to compare two documents
// and compare them for similarity.
public interface SimilarityAlgo {
	// Compares two sets
	public double compareSimilarity(Set<Integer> a, Set<Integer> b);
}
