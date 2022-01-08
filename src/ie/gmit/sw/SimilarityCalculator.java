package ie.gmit.sw;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * SimilarityCalculator is a gateway method which is used to invoke an algorithm.
 * This will allow me to use the strategy pattern in other classes by passing
 * an instance of the SimilarityAlgo interface into the getResukts function.
 */
public class SimilarityCalculator {
	// A list of plagiarism results that represents the comparison of all documents
	private List<PlagiarismResult> results;
	// A composition of a similarity algo
	private SimilarityAlgo similarityAlgo;
	
	/**
	 * Calculates the similarity of a set of hashes against all the other
	 * documents sets of hashes in the database.
	 * 
	 * @param documents - List of documents
	 * @param a - Set of hashes from the document we wish to compare
	 * @param sa - Instance of similarityAlgo;
	 * 
	 * @return a list of PlagiarismResults
	 */
	public List<PlagiarismResult> getResults(List<Document> documents, Set<Integer> a, SimilarityAlgo sa) {
		// Instantiate results
		results = new ArrayList<>();
		// Set similarityAlgo
		similarityAlgo = sa;
		// Stores jaccardIndex (0-1)
		double jaccardIndex;
		// Stores the total jaccardIndex of all documents
		double totalResult = 0;
		// Plagiarism Result (Starts at 1)
		double noPlagiarismResult = 1;
		
		// If there are no stored documents yet, then call the addTitleAndResult
		// method to add not plagiarised with a value of 1 as this document
		// we wish to compare cant be plagiarised as no documents against to compare
		// against
		if (documents.size() == 0) {
			addTitleAndResult("Not Plagiarised", 1);
		} else {
			// Loop over the size of documents
			for (int i = 0; i < documents.size(); i++) {
				// Get the jaccard index by calling the compareSimilarity method of similarityAlgo
				jaccardIndex = similarityAlgo.compareSimilarity(a, documents.get(i).getHashes());
				// If the jaccardIndex is greater than 0.02 (0.2% plagiarism), then
				// add it to results (I dont want to see very small figures)
				if(jaccardIndex > 0.02) {
					// Increment the totalResult when we have a valid plagiarism value
					totalResult += jaccardIndex;
					addTitleAndResult(documents.get(i).getTitle(), jaccardIndex);
				} 
			}
			// If the total plagiarism result was less than 1 and
			// there is a document or more in the database.
			// Then calculate the percentage that is not plagiarised
			if(totalResult < 1 && documents.size() > 0) {
				// 1 - totalResult
				noPlagiarismResult -= totalResult;
				addTitleAndResult("Not Plagiarised", noPlagiarismResult);
			}
		}
		// Return the results - list of plagiarism results
		return results;
	}
	
	/**
	 * Adds a PlagiarismResult to the list of results to
	 * be displayed to the client.
	 * 
	 * @param title - Title of the document
	 * @param jaccardIndex - Similarity amount (0-1)
	 */
	public void addTitleAndResult(String title, double jaccardIndex) {
		
		// The jaccard index is between 0 and 1. By multiplying it by 100, I will
		// get a percentile which will need to be used to display to the user
		double result = jaccardIndex * 100;
		// Add a new PlagiarismResult object to the results list
		results.add(new PlagiarismResult(title, jaccardIndex));
	}
}
