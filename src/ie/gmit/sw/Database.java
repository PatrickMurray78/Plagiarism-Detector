package ie.gmit.sw;

import java.nio.file.Paths;
import java.util.*;

//import one.microstream.storage.types.EmbeddedStorage;
import one.microstream.storage.*;


// This class focuses handles saving documents to the 
// database and retrieving them
public class Database {
	// ArrayList of Document Objects
	private ArrayList<Document> documents;
	private EmbeddedStorageManager db = null;
	// The instance of the database
	private static Database instance = null;
	
	// Default constructor
	private Database() {

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
	public boolean addDocument(Document d) {
		System.out.println("Open Microsoft Stream");
		db = EmbeddedStorage.start(d, Paths.get("./data"));
		return false;
	}
	
	// Add document to database
	/*public boolean addDocument(Document d) {
		System.out.println("Open Microsoft Stream");
		try {
			db = EmbeddedStorage.start(d, Paths.get("./data"));
			db.storeRoot();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			db.shutdown();
		}
	}*/
	
	
}
