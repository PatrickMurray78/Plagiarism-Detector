package ie.gmit.sw;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SimilarityCalculator {
	// A list of plagiarism results that represents the comparison of all documents
	//private List<PlagiarismResult> results = new ArrayList<PlagiarismResult>();
	private List<PlagiarismResult> results;
	// A composition of a similarity algo
	private SimilarityAlgo similarityAlgo;
	// A list of results of type PlagiarismResult that holds the document title
	// and the result of its comparison
	
	public List<PlagiarismResult> calculateAllDocs(List<Document> documents, Set<Integer> a, SimilarityAlgo sa) {
		//System.out.println(results.size() + " in list before any calc");
		results = new ArrayList<>();
		
		/*if(results.size() > 0) {
			for (PlagiarismResult r : results) {
				System.out.println(r.getTitle() + " got " + r.getResult());
			}
			results.remove(0);
		}*/
		
		double jaccardIndex;
		double totalResult = 0;
		double noPlagiarismResult = 1;
		similarityAlgo = sa;

		// If it's the first document in the documents, return 100% not plagiarised
		// to be displayed
		if (documents.size() == 0) {
			addTitleAndResult("Not Plagiarised", 1);
		} else {
			System.out.println("There are " + documents.size() + " docs to loop through");
			// Loop over the size of documents
			for (int i = 0; i < documents.size(); i++) {
				// Get the jaccard index by calling the compareSimilarity() method
				System.out.println("Comparing this file with " + documents.get(i).getTitle());
				//System.out.println("Hash Set 1: " + a.size() + " Hash Set 2: " + documents.get(i).getHashes().size());
				jaccardIndex = sa.compareSimilarity(a, documents.get(i).getHashes());
				System.out.println("Comparison is " + (jaccardIndex*100));
				// If the jaccardIndex is greater than 0.05 (0.5% plagiarism), then
				// add it to results
				if(jaccardIndex > 0.05) {
					totalResult += jaccardIndex;
					System.out.println("Add it to results for " + documents.get(i).getTitle());
					addTitleAndResult(documents.get(i).getTitle(), jaccardIndex);
				} else {
					jaccardIndex = 0;
				}
			}
			// If the total plagiarism result was less than 100 and
			// there is more than one document in the database
			// Then calculate the percentage that is not plagiarised
			if(totalResult < 1 && documents.size() > 0) {
				noPlagiarismResult -= totalResult;
				System.out.println((noPlagiarismResult * 100) + "% of the document is not plagiarised");
				addTitleAndResult("Not Plagiarised", noPlagiarismResult);
			}
		}
		// Return the results - list of plagiarism results
		return results;
	}
	
	public void addTitleAndResult(String title, double jaccardIndex) {
		
		// The jaccard index is between 0 and 1. By multiplying it by 100, I will
		// get a percentile which will make sense for the user
		double result = jaccardIndex * 100;
		results.add(new PlagiarismResult(title, jaccardIndex));
	}
}
