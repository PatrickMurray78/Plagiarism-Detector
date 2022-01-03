package ie.gmit.sw;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppWindow extends Application{
    @Override
    public void start(Stage stage) throws Exception {
    	//Load the GUI declared in the FXML file window.fxml
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/fxml/window.fxml"));
    	Parent root = loader.load(); //Load the GUI

    	Scene scene = new Scene(root); //Create a scene to display
        stage.setTitle("GMIT - B.Sc. in Computing (Software Development)");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}