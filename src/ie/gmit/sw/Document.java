package ie.gmit.sw;

import java.util.Set;
import java.util.TreeSet;

// Document template to store on database
public class Document {
	private String title;
	private Set<Integer> hashes = new TreeSet<Integer>();

	// Default constructor
	public Document() {
		
	}
	
	// Document Constructor
	public Document(String title, Set<Integer> hashes) {
		super();
		this.title = title;
		this.hashes = hashes;
	}
	
	// Get Title
	public String getTitle() {
		return title;
	}
	
	// Set Title
	public void setTitle(String title) {
		this.title = title;
	}

	// Get Hashes
	public Set<Integer> getHashes() {
		return hashes;
	}
	
	// Set Hashes
	public void setHashes(Set<Integer> hashes) {
		this.hashes = hashes;
	}
}
