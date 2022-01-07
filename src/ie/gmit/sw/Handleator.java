package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public interface Handleator {
	public void parseDocument(String title, BufferedReader br) throws IOException;
	public void processDocument(HashingMethod hashingMethod);
	public List<String> displayDocument();
	public void addDocument();
	public List<PlagiarismResult> compareSim();
	public boolean checkForFullPlagiarism();
	public List<PlagiarismResult> process(String fileName, HashingMethod hashingMethod) throws IOException;
}
