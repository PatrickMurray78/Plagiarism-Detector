package ie.gmit.sw;

/**
 * PlagiarismResult is an abstract template of what each plagiarism result
 * should look like. By templating objects, we provide some general OOP.
 */
public class PlagiarismResult {
	// Title of document
	private String title;
	// Result of how much a document plagiarises this documennt
	private double result;
	
	/**
	 * Default Constructor.
	 */
	public PlagiarismResult() {
		
	}
	
	/**
	 * Constructor with Fields.
	 * 
	 * @param title - Title of document
	 * @param result - Result of plagiarism
	 */
	public PlagiarismResult(String title, double result) {
		this.title = title;
		this.result = result;
	}

	/**
	 * Get the title of the document.
	 * 
	 * @return title of document
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Get the result of the plagiarism.
	 * 
	 * @return plagiarism result
	 */
	public double getResult() {
		return result;
	}
}
