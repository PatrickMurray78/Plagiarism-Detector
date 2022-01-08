package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * Parserator is an interface which describes each parserator
 * object. Parserator objects are used to parse documents.
 * Follows the SRP.
 */
public interface Parserator {
	/**
	 * This method parses the contents of the document which is now in the 
	 * form of a BufferedReader and produces a list of words separated
	 * by spaces.
	 * 
	 * @param br - BufferedReader containing the document contents
	 * @return a list of words
	 * @throws IOException - File handling
	 */
	public List<String> parse(BufferedReader br) throws IOException;
}
