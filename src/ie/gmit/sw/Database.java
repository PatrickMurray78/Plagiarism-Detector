package ie.gmit.sw;

import java.nio.file.Paths;
import java.util.*;

import one.microstream.storage.types.EmbeddedStorage;
import one.microstream.storage.types.EmbeddedStorageManager;


// This class focuses handles saving documents to the 
// database and retrieving them
public class Database {
	// ArrayList of Document Objects
	private ArrayList<Document> documents;
	private EmbeddedStorageManager db = null;
	// The instance of the database
	private static Database instance = null;
	
	// Default constructor
	public Database() {

	}

	// Creates an instance of a database if one doesn't already exist.
	/*public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
			System.out.println("Created a database instance");
		}
		// return the single instance
		return instance;
	}*/
	
	// Add document to database
	public boolean addDocument(Document d) {
		try {
			db = EmbeddedStorage.start(d, Paths.get("./data"));
			db.storeRoot();
			System.out.println("Added to database");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			db.shutdown();
		}
	}
	
	public void getAllDocuments() {
		System.out.println("Get all documents");
		db = EmbeddedStorage.start(Paths.get("./data"));
		if(db.root() == null)
		{
		    System.out.println("Database is empty");
		}
		else
		{    
		    //MyRoot root = (MyRoot) db.root();
			System.out.println(db.root().toString());
		}
	}
}
