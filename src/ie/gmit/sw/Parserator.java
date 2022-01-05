package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

// Interface which describes each parserator object.
// Parserator objects are used to parse documents.
public interface Parserator {
	// Reads the contents of a document and parses its contents into
	// a list of words excluding spaces
	public List<String> parse(BufferedReader br) throws IOException;
}
