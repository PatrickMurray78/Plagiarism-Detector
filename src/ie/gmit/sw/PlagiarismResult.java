package ie.gmit.sw;

public class PlagiarismResult {
	private String title;
	private double result;
	
	public PlagiarismResult(String title, double result) {
		this.title = title;
		this.result = result;
	}

	public String getTitle() {
		return title;
	}

	public double getResult() {
		return result;
	}
}
