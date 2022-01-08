package ie.gmit.sw;

import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import one.microstream.storage.types.EmbeddedStorage;
import one.microstream.storage.types.EmbeddedStorageManager;

/**
 * Database provides a way of accessing the database for all other classes.
 * A database should only be instantiated once, so for this reason I made it a 
 * singleton. This will ensure we can access the same database from anywhere.
 * This class follows the SRP.
 */
public class Database {
	// ArrayList of Document Objects
	private List<Document> root = new ArrayList<>();
	// The EmbeddedStorageManager
	private EmbeddedStorageManager db = null;
	// The instance of the database
	private static Database instance = null;
	
	/**
	 * Default Constructor which is private as we instantiate
	 * Database in the getInstance method below.
	 */
	private Database() {

	}

	/**
	 * Creates an instance of the Database if one doesn't exist.
	 * Singleton pattern.
	 * 
	 * @return an instance of the Database
	 */
	public static Database getInstance() {
		// Create one instance if one does not exist. (Singleton behaviour)
		if (instance == null) {
			instance = new Database();
		}
		// Return the instance
		return instance;
	}
	
	/**
	 * Adds a document to the microstream database.
	 * 
	 * @param d - Document object to be added to database
	 */
	public void addDocument(Document d) {
		// Add Document d to the root
		root.add(d);
		// If there is no active database, initialise one
		if(db == null) {
			try {
				db = EmbeddedStorage.start(root, Paths.get("./storage"));
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		// Store root in database
		db.storeRoot();
	}
	
	/**
	 * Displays the titles of all documents in the database.
	 */
	public void query() {
		System.out.println("[Query] Show all documents");
		
		Collection<String> titles = root.stream()
		.map(d ->d.getTitle())
		.collect(Collectors.toCollection(LinkedList::new));

		System.out.println(titles);
	}
	
	/**
	 * Gets a list of all the documents in database.
	 * 
	 * @return list of documents
	 */
	public List<Document> getAllDocuments() {
		return root;
	}
}
