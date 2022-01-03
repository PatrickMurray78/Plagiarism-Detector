module gmit.software {
	requires java.desktop;
	requires javafx.base;
	requires javafx.graphics;
	requires javafx.fxml;
	requires transitive javafx.controls;
	requires storage.embedded;
	requires storage.embedded.configuration;
	requires storage;
	requires persistence;
	requires persistence.binary;
	exports ie.gmit.sw;
	opens ie.gmit.sw to javafx.fxml;
}