package ie.gmit.sw;

import java.nio.file.Paths;
import java.util.*;

import one.microstream.storage.types.EmbeddedStorage;
import one.microstream.storage.types.EmbeddedStorageManager;

// This class focuses handles saving documents to the 
// database and retrieving them
public class Database {
	// ArrayList of Document Objects
	private List<Document> root = new ArrayList<>();
	private EmbeddedStorageManager db = null;
	// The instance of the database
	private static Database instance = null;
	
	// Default constructor
	public Database() {

	}

	// Creates an instance of a database if one doesn't already exist.
	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
			System.out.println("Created a database instance");
		}
		// return the single instance
		return instance;
	}
	
	// Add document to database
	public void addDocument(Document d) {
		query();
		root.add(d);
		// If there is no active database, initialise one
		if(db == null) {
			try {
				db = EmbeddedStorage.start(root, Paths.get("./storage"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Added to database");
		db.storeRoot();
	}
	
	private void query() {
		System.out.println("\n[Query] Show all documents");
		root.stream()
			.forEach(System.out::println);
	}
	
	// Get amount of documents in database
	public int getCount() {
		return root.size();
	}
	
	// Iterates through the database and compares the most recently
	// stored document against the rest to check for plagiarism and
	// returns a list of plagiarism results
	public List<PlagiarismResult> compare() {
		List<PlagiarismResult> results = new ArrayList<>();
		Jaccard j = new Jaccard();
		Set<Integer> hashToCompare = new TreeSet<Integer>();
		Set<Integer> hashToCompareAgainst = new TreeSet<Integer>();
		double result;
		double totalResult = 0;
		double noPlagiarismResult = 100;
		
		if(getCount() == 1) {
			results.add(new PlagiarismResult("Not Plagiarised", 100));
		}
		
		// Get document to compare
		Document doc = root.get(getCount() - 1);
		
		hashToCompare = doc.getHashes();
		
		// Loop through all documents except the most recently added one
		for(int i = 0; i < getCount() - 1; i++) {
			// Get the hashedShingles set
			hashToCompareAgainst = root.get(i).getHashes();
			
			// Call the compareSimilarity method in the jaccard class to compare both hashedShingles
			result = j.compareSimilarity(hashToCompare, hashToCompareAgainst) * 100;
			
			System.out.println(doc.getTitle() + " plagiarises " + root.get(i).getTitle() + " by " + result + "%");
			
			// Only show result if plagiarism is above 0.5%
			if(result > 0.5) {
				totalResult += result;
				results.add(new PlagiarismResult(root.get(i).getTitle(), result));
			}
			
			// If the document has 100% plagiarism with another, remove it
			// from the database as one already exists there
			if(result == 100) {
				root.remove(getCount() - 1);
				db.storeRoot();
			}
		}
		
		// If the total plagiarism result was less than 100 and
		// there is more than one document in the database
		// Then calculate the percentage that is not plagiarised
		if(totalResult < 100 && getCount() != 1) {
			noPlagiarismResult -= totalResult;
			System.out.println(noPlagiarismResult + "% of the document is not plagiarised");
			results.add(new PlagiarismResult("Not Plagiarised", noPlagiarismResult));
		}
		
		return results;
	}
}
