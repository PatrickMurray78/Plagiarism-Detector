module gmit.software {
	requires java.desktop;
	requires javafx.base;
	requires javafx.graphics;
	requires javafx.fxml;
	requires transitive javafx.controls;
	
	exports ie.gmit.sw;
	opens ie.gmit.sw to javafx.fxml;
}