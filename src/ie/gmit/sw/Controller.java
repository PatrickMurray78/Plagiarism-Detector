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
	
	private ServiceHandler sh = new ServiceHandler();
	List<PlagiarismResult> results = new ArrayList<>();
	
	@FXML public void initialize() {
		//ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
		
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
			// Call the process method of ServiceHandler sh to handle all processing
			// of the document. This will return a list of results to be displayed.
			try {
				results = sh.process(txtFile.getText(), HashingMethod.MINHASH);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			
			// Instantiate an ObservableList of PieChart Data
			ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
			
			// For each result in the results of plagiarism calculations
			for (PlagiarismResult result : results) {
				// Add a new PieChart.Data object with the document name and the plagiarism
				// percentage to the data ObservableList
				data.add(new PieChart.Data(result.getTitle(), result.getResult()));
			}
		
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