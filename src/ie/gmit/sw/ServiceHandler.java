package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Handleator. This is the main handler that connects
 * all other classes together to allow the whole application to be ran from this
 * class alone. The client sees nothing going on in this class as it is abstracted. This 
 * class follows the facade pattern.
 */
public class ServiceHandler implements Handleator {
	// List of words which make up document
	private List<String> words;
	// List of plagiarism results - title and result
	private List<PlagiarismResult> plagiarismResults;
	// BufferedReader
	private BufferedReader br;
	// Compositions of classes
	private Parserator parserator;
	private Database db;
	private Shingleator shingleator;
	private Hasher hasher;
	private SimilarityCalculator sc;
	private Document d;
	
	/**
	 * Default Constructor which creates instances of other classes.
	 * If the ServiceHandler goes down, so does all the instantiated classes.
	 */
	public ServiceHandler() {
		parserator = new Parser();
		db = Database.getInstance();
		shingleator = new Shingler();
		sc = new SimilarityCalculator();
	}
	
	@Override
	public void readDocument(String filePath) {
		// Try to read file path and create a buffered reader if found
		try {
			br = new BufferedReader(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void parseDocument(String title, BufferedReader br) throws IOException {
		try {
			// Set the words to the list parse returns
			words = parserator.parse(br);
			// Set the title of the document
			d.setTitle(title);
		} catch (IOException e) {
			System.out.println(e);
		}	
	}

	@Override
	public void processDocument(HashingMethod hashingMethod) {
		// Delegate work to shingler to shingle the words which is then
		// hashed using the hasher class
		hasher = new Hasher(shingleator.getShingles(words));
		// Using a ternary operator, create a new instance of HashCoder or MinHasher.
		// This gets passed into the hash function which then returns a set of hashed
		// shingles which is then passed to the document and set as its hashes.
		d.setHashes(
				hasher.hash(hashingMethod == hashingMethod.HASHCODE ? new HashCoder() : new MinHasher())
		);
	}

	@Override
	public List<String> displayDocument() {
		// Return the words from document
		return words;
	}

	@Override
	public void addDocument() {
		// Add a document to the database
		db.addDocument(d);
	}

	@Override
	public List<PlagiarismResult> compareSimilarity() {
		// Delegate the calculation to the SimilarityCalculator by passing it a new
		// ArrayList of documents along with the hashes of the document we wish to compare and a new
		// instance of Jaccard. Enum would be used instead of Jaccard if I had more than one
		// SimilarityAlgo
		return sc.getResults(new ArrayList<Document>(db.getAllDocuments()), d.getHashes()
				, new Jaccard());
	}
	
	@Override
	public boolean checkForFullPlagiarism() {
		// For each result in plagiarismResults
		for (PlagiarismResult result : plagiarismResults) {
			// Check if one of the results has the title of "Not Plagiarised"
			if(result.getTitle().equalsIgnoreCase("Not Plagiarised")) {
				// If it does and it's value is not equal to 0
				if(result.getResult() != 0) {
					// Return false as this document is not fully plagiarised
					return false;
				}
			}
		}
		// Return true as Document is fully plagiarised
		return true;
	}

	@Override
	public List<PlagiarismResult> process(String filePath, HashingMethod hashingMethod) throws IOException {
		// Instantiate new Document
		d = new Document();
		// Instantiate new ArrayList of PlagiarismResults
		plagiarismResults = new ArrayList<PlagiarismResult>();
		// Using the file path, read the document and add it to a
		// buffered reader.
		readDocument(filePath);
		// Then parse the file and get a list
		// of words.
		parseDocument(filePath, br);
		// Using these words I can now shingle and hash them
		processDocument(hashingMethod);
		
		// Compare the hashed document with all other in database
		plagiarismResults = compareSimilarity();
		// Check if any of the document does not plagiarise any other document by 100%, if
		// it doesnt then save it to database
		if(!checkForFullPlagiarism()) {
			db.addDocument(d);
		}
		// Return the plagiarismResults
		return plagiarismResults;
	}

}
