package ie.gmit.sw;

import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

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
		}
		// return the single instance
		return instance;
	}
	
	// Add document to database
	public void addDocument(Document d) {
		//query();
		root.add(d);
		// If there is no active database, initialise one
		if(db == null) {
			try {
				db = EmbeddedStorage.start(root, Paths.get("./storage"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//System.out.println("\nAdd doc query is called");
		//query();
		db.storeRoot();
	}
	
	public void query() {
		System.out.println("[Query] Show all documents");
		
		/*root.stream()
			.forEach(System.out::println);*/
		
		// display all the titles
		Collection<String> titles = root.stream()
		.map(d ->d.getTitle())
		.collect(Collectors.toCollection(LinkedList::new));

		System.out.println(titles);
	}
	
	// Get amount of documents in database
	public int getCount() {
		return root.size();
	}
	
	// Returns a list of all documents in database
	public List<Document> getAllDocuments() {
		if(root.isEmpty()) {
			
		} 
		//System.out.println("\nGet all docs query called");
		//query();
		return root;
	}
}
