package ie.gmit.sw;

import java.io.File;
import java.util.Set;
import java.util.TreeSet;

/**
 * Document is an abstract template of what each document should look
 * like. By templating objects, we provide some general OOP.
 */
public class Document {
	// Title of document
	private String title;
	// Set of hashes, which contain the contents of the document
	private Set<Integer> hashes = new TreeSet<Integer>();

	/**
	 * Default Constructor.
	 */
	public Document() {
		
	}
	
	/**
	 * Constructor with Fields.
	 * 
	 * @param title - Title of document
	 * @param hashes - Set of hashes
	 */
	public Document(String title, Set<Integer> hashes) {
		super();
		this.title = title;
		this.hashes = hashes;
	}
	
	/**
	 * Get the title of document.
	 * 
	 * @return title of document
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Set the title of document.
	 * 
	 * @param filePath - Manipulated to get document title
	 */
	public void setTitle(String filePath) {
		// Create temp file using filePath
		File f = new File(filePath);
		// Get the "name.txt" from the filePath
		String tempTitle = f.getName();
		int size = tempTitle.length();
		// Using the size of the string, remove the last 4 characters
		// which will remove the ".txt"
		tempTitle = tempTitle.substring(0, (size - 4));
		// The documents also contain the author name separated by a "-"
		// so I will split this string by the "-" character to be left with
		// the title followed by the author name so set the title to the
		// first element of splitTitle array as this is the title post split
		String[] splitTitle = tempTitle.split("-");
		// Set title
		this.title = splitTitle[0];
	}

	/**
	 * Get the set of hashes.
	 * 
	 * @return set of hashes
	 */
	public Set<Integer> getHashes() {
		return hashes;
	}
	
	/**
	 * Set the hashes
	 * 
	 * @param hashes - Used to compare against other documents
	 */
	public void setHashes(Set<Integer> hashes) {
		this.hashes = hashes;
	}
	
	/**
	 * Override the toString method to provide a more
	 * detailed response when called.
	 */
	@Override
	public String toString() {
		return "Text: " + title;
	}
}
