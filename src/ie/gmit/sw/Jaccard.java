package ie.gmit.sw;

import java.util.Set;
import java.util.TreeSet;

/**
 * Implementation of SimilarityAlgo. This class uses the Jaccard
 * index algorithm to calculate the jaccard index which informs us
 * of the similarity between two sets. The algorithm is |A n B| / |A u B|.
 * Jaccard is-a SimilarityAlgo (Inheritance). Upholds the SRP.
 */
public class Jaccard implements SimilarityAlgo {
	// Set of integers
	private Set<Integer> n;
	// | A n B |
	private int intersectionCardinality;
	// | A u B |
	private int unionCardinality;

	/**
	 * Default Constructor.
	 */
	public Jaccard() {

	}

	@Override
	public double compareSimilarity(Set<Integer> a, Set<Integer> b) {
		// Unioncardinatlity is size of a and b
		unionCardinality = a.size() + b.size();
		// Create treeset with integers from set a
		n = new TreeSet<Integer>(a);
		// Keep only common integers from both sets
		n.retainAll(b);
		// The cardinality of intersection is the size of n after retaining b
		intersectionCardinality = n.size();
		// Return calculation (| A n B | / | A | + | B | - | A n B |)
		return Double.valueOf(intersectionCardinality)
				/ (Double.valueOf(unionCardinality) - Double.valueOf((intersectionCardinality)));
	}
}
