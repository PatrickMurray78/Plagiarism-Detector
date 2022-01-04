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
	private Jaccard jc = new Jaccard();
	
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
	/*public boolean addDocument(Document d) {
		root.add(d);
		System.out.println(root.size());
		try {
			db = EmbeddedStorage.start(root, Paths.get("./storage"));
			db.storeRoot();
			System.out.println("Added to database");
			query();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			db.shutdown();
		}
	}*/
	
	public boolean addDocument(Document d) {
		root.add(d);
		// If there is no active database, initialise one
		if(db == null) {
			try {
				db = EmbeddedStorage.start(root, Paths.get("./storage"));
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		System.out.println("Added to database");
		db.storeRoot();
		query();
		
		return true;
	}
	
	private void query() {
		System.out.println("\n[Query] Show all documents");
		root.stream()
			.forEach(System.out::println);
	}
	
	public int getCount() {
		return root.size();
	}
	
	public PlagiarismResult compare() {
		if(getCount() == 1) {
			return new PlagiarismResult("Not Plagiarised", 100);
		}
		
		Jaccard j = new Jaccard();
		Set<Integer> hashToCompare = new TreeSet<Integer>();
		Set<Integer> hashToCompareAgainst = new TreeSet<Integer>();
		
		hashToCompare = root.get(getCount() - 1).getHashes();
		hashToCompareAgainst = root.get(0).getHashes();
		
		double result = j.compareSimilarity(hashToCompare, hashToCompareAgainst);
		result = Math.round(result * 100.0);
	
		// Fully plagiarised
		if(result == 100 && getCount() > 1) {
			root.remove(getCount() - 1);
			db.storeRoot();
		}
		return new PlagiarismResult(root.get(0).getTitle(), result);
	}
}
