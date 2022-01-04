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
			.sorted((s, t) -> s.getTitle().compareTo(t.getTitle()))
			.forEach(System.out::println);
	}
	
	public int getCount() {
		return root.size();
	}
}
