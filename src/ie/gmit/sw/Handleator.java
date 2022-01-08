package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * Interface which states what a Handleator should do. This interface follows the 
 * facade design pattern as it is composed of other interfaces which allows us to handle
 * all functionality from the one place. As this application is quite complicated with
 * the amount of classes that all abide by the SRP. I felt it would be beneficial to use
 * an interface like this to connect everything together.
 */
public interface Handleator {
	/**
	 * Reads document and adds it to a BufferedReader using filePath.
	 * 
	 * @param filePath - String containing the path to file
	 */
	public void readDocument(String filePath);
	/**
	 * Delegates work to the Parserator which parses the file using the 
	 * BufferedReader.
	 * 
	 * @param title - Used to add title to document
	 * @param br - BufferedReader that contains file contents
	 * @throws IOException - File handling
	 */
	public void parseDocument(String title, BufferedReader br) throws IOException;
	/**
	 * Responsible for delegating work to the Shingler and Hasher to 
	 * shingle and hash the document.
	 * 
	 * @param hashingMethod - Enum which states the hashing method to use
	 */
	public void processDocument(HashingMethod hashingMethod);
	/**
	 * Responsible for getting the set of words that has been parsed.
	 * 
	 * @return list of words
	 */
	public List<String> displayDocument();
	/**
	 * Delegates work to Database to add a document.
	 */
	public void addDocument();
	/**
	 * Delegates work to the SimilarityCalculator where the similarity 
	 * of the document compared to all other documents is calculated.
	 * 
	 * @return list of PlagiarismResults
	 */
	public List<PlagiarismResult> compareSimilarity();
	/**
	 * Checks if the document fully plagiarises any other document in the
	 * database. If it does we do not want to store it.
	 * 
	 * @return boolean depending on if there is full plagiarism or not
	 */
	public boolean checkForFullPlagiarism();
	/**
	 * Responsible for calling all the other methods. In controller.java, the client can
	 * simply call the Process method instead of having to call each method individually.
	 * This helps the Handleator follow the facade pattern.
	 * 
	 * @param fileName - String containing the path to file
	 * @param hashingMethod - Enum which states the hashing method to use
	 * 
	 * @return list of PlagiarismResults
	 * @throws IOException - File handling
	 */
	public List<PlagiarismResult> process(String fileName, HashingMethod hashingMethod) throws IOException;
}
