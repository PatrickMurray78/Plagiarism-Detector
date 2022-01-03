package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller {
	/* The following instance variables use the @FXML annotation to bind to the controls
	 * declared in the FXML file. The name of the control must match the fx:id attribute 
	 * of an FXML element, e.g. the VBox container is bound to the root VBox declared as
	 * fx:id="container" in the file window.xml.
	 */
	@FXML private VBox container;
	@FXML private VBox chartArea;
	@FXML private TextField txtFile;
	@FXML private Button btnClose;
	@FXML private Button btnOpen;
	@FXML private Button btnCompare;
	
	
	@FXML public void initialize() {
		/*
		 * Plant an observer on the button btnClose. The event dispatch thread (the
		 * application thread in JavaFX) will call the action specified by the lambda
		 * expression. 
		 */
		btnClose.setOnAction(e -> ((Stage) btnClose.getScene().getWindow()).close());
		
		/*
		 * Plant an observer on the button btnCompare. The action or call-back method
		 * should compute the Jaccard similarity of the text file specified from the
		 * FileChooser against the subject document(s) stored by the application. When
		 * the Jaccard similarity has been computed, the application should display the
		 * result in some way.
		 */
		btnCompare.setOnAction(e -> {
			/*
			 * Do the comparing with Jaccard Similarity and MinHash and compute the data
			 * for the Pie Chart. *** Do not hard-code these values *** as shown below.
			 * The data needs to be computed dynamically.
			 */
			
			DocumentParser dp = new DocumentParser();
			
			BufferedReader br = null;
			List<String> words = new ArrayList<String>();
			List<String> shingles = new ArrayList<String>();
			
			// Try to read file path and create a buffered reader if found
			try {
				br = new BufferedReader(new FileReader(txtFile.getText()));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
			// Parse the document
			try {
				words = dp.readDocument(br);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			System.out.println(words.size());
			
			// Add shingles
			Shingleator s = new Shingleator();
			
			shingles = s.shingler(words);
			
			// I used a shingle size of 3 and this list is 1/3 the size of
			// words, so it is working as expected
			System.out.println(shingles.size());
			
			// Hash each shingle
			HashCoder hc = new HashCoder();
			Set<Integer> hashShingles = new TreeSet<Integer>();
			
			hashShingles = hc.hash(shingles);
			
			// Parse file title from path
			File f = new File(txtFile.getText());
			String title = f.getName();
			int size = title.length();
			title = title.substring(0, (size - 4));
			
			// Create a document object with the hashShingles and title
			Document d = new Document(title, hashShingles);
			
			Database db = new Database();
			
			db.addDocument(d);
			
			
			ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
		            new PieChart.Data("War and Peace", 20),
		            new PieChart.Data("De Bello Gallico", 10),
		            new PieChart.Data("The Divine Comedy", 10),
		            new PieChart.Data("Not Plagiarised", 60)
			);
			
			
			//Display the results in a pie chart
			PieChart chart = new PieChart(data); //Use PieChart from javafx.scene.chart
			chartArea.getChildren().clear(); //Remove any existing chart
			chartArea.getChildren().add(chart); //Add the new chart to the tree
		});
		
		//The class FileChooser is not a control and cannot be specified in FXML
		FileChooser fc = new FileChooser(); //A leaf node
		fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt")); //Filter for TXT files
		btnOpen.setOnAction(e -> { //Plant an observer on the button btnOpen
			File f = fc.showOpenDialog(container.getScene().getWindow());
			
			txtFile.setText(f.getAbsolutePath());
		});
	}
}