package ie.gmit.sw;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SimilarityCalculator {
	// A list of doubles that represents the similarity of all objects
	private List<Double> allCalculations = new ArrayList<Double>();
	// A composition of a comparator
	private SimilarityAlgo similarityAlgo;
	
	public double calculateAllDocs(List<Document> documents, Set<Integer> a, SimilarityAlgo sa) {
		// StrategyPattern-esque behavior dynamically setting the algorithm to
		// be used.
		similarityAlgo = sa;

		// If it's the first document in the documents, return 0 as there is
		// nothing to compare against
		if (documents.size() == 1) {
			return 0;
		} else {
			// Loop over the size of documents - 1 (avoid comparing against
			// itself)
			for (int i = 0; i < documents.size() - 1; i++) {
				// Add the result of the delegated similarity function to
				// allCalculations
				addJaccardIndex(similarityAlgo.compareSimilarity(a, documents.get(i).getHashes()));
			}
		}
		// Delegate the avg calculation and return it's response.
		return calculateAvg();
	}
	
	public void addJaccardIndex(double ji) {
		allCalculations.add(ji);
	}
	
	public double calculateAvg() {
		// Set a temp double to 0
		double sum = 0;
		// Enhanced loop to loop over each double and add it onto sum.
		for (double i : allCalculations) {
			sum += i;
		}
		// Console log the similarity level.
		System.out.println("Similarity % = " + (sum / allCalculations.size()) * 100);
		// Return the average (sum/size) and multiplied by 100 (100/1) to present a precentile.
		return (sum / allCalculations.size()) * 100;
	}
	
	
}
