package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServiceHandler implements Handleator {
	// List of words which make up document
	private List<String> words;
	// List of plagiarism results - title and result
	private List<PlagiarismResult> plagiarismResults;
	private BufferedReader br;
	// Compositions of classes
	private Parserator parserator;
	private Database db;
	private Shingleator shingleator;
	private Hasher hasher;
	private SimilarityCalculator sc;
	private Document d;
	
	public ServiceHandler() {
		parserator = new Parser();
		db = Database.getInstance();
		shingleator = new Shingler();
		sc = new SimilarityCalculator();
	}
	
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
			
			// Set the words to the return of parse
			words = parserator.parse(br);
			// Set the title of the document
			d.setTitle(title);
		} catch (IOException e) {
			System.out.println(e);
		}	
	}

	@Override
	public void processDocument(HashingMethod hashingMethod) {
		hasher = new Hasher(shingleator.getShingles(words));
		d.setHashes(
				hasher.hash(hashingMethod == hashingMethod.MINHASH ? new MinHasher() : new MinHasher())
		);
		
	}

	@Override
	public List<String> displayDocument() {
		return words;
	}

	@Override
	public void addDocument() {
		db.addDocument(d);
	}

	@Override
	public List<PlagiarismResult> compareSim() {
		return sc.calculateAllDocs(new ArrayList<Document>(db.getAllDocuments()), d.getHashes()
				, new Jaccard());
	}
	
	@Override
	public boolean checkForFullPlagiarism() {
		for (PlagiarismResult result : plagiarismResults) {
			if(result.getTitle().equalsIgnoreCase("Not Plagiarised")) {
				if(result.getResult() != 0) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public List<PlagiarismResult> process(String filePath, HashingMethod hashingMethod) throws IOException {
		d = new Document();
		plagiarismResults = new ArrayList<PlagiarismResult>();
		// Using the file path, read the document and add it to a
		// buffered reader.
		System.out.println();
		//System.out.println("Read in " + filePath);
		readDocument(filePath);
		// Then parse the file and get a list
		// of words.
		//System.out.println("Then Parse " + filePath);
		parseDocument(filePath, br);
		// Using these words I can now shingle and hash them
		processDocument(hashingMethod);
		
		db.query();
		/*List<Document> ds = db.getAllDocuments();
		for (Document document : ds) {
			System.out.println("Title is " + document.getTitle());
		}*/
		// Compare the hashed document with all other in database
		plagiarismResults = compareSim();
		// Check if any of the document does not plagiarise any other document by 100%, if
		// it doesnt then save it to database
		if(!checkForFullPlagiarism()) {
			System.out.println("Adding document");
			db.addDocument(d);
			db.query();
		}
		return plagiarismResults;
	}

}
