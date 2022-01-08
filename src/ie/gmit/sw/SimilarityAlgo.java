package ie.gmit.sw;

import java.util.Set;

/**
 * Interface which describes all SimilarityAlgo objects.
 * A SimilarityAlgo is used to compare two documents and
 * calculate their similarity. Follows the SRP.
 */
public interface SimilarityAlgo {
	/**
	 * Compares two sets for similarity.
	 * 
	 * @param a - Set a to be compared
	 * @param b - Set b to be compared
	 * 
	 * @return a double which represents the similarity value
	 */
	public double compareSimilarity(Set<Integer> a, Set<Integer> b);
}
