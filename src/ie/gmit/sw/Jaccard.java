package ie.gmit.sw;

import java.util.Set;
import java.util.TreeSet;

public class Jaccard implements SimilarityAlgo {
	private Set<Integer> n;
	// | A N B |
	private int intersectionCardinality;
	// | A U B |
	private int unionCardinality;

	// Default Constructor
	public Jaccard() {

	}

	@Override
	public double compareSimilarity(Set<Integer> a, Set<Integer> b) {
		// Unioncardinatlity is size of a and b
		unionCardinality = a.size() + b.size();
		// Create treeset with integers from set a
		n = new TreeSet<Integer>(a);
		// Keep only corresponding integers
		n.retainAll(b);
		// The cardinality of intersection is the size of n after retaining b
		intersectionCardinality = n.size();
		// Return calculation (| A n B | / | A | + | B | - | A n B |)
		return Double.valueOf(intersectionCardinality)
				/ (Double.valueOf(unionCardinality) - Double.valueOf((intersectionCardinality)));
	}
}
