package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Planner");
        primaryStage.setScene(new Scene(root, 1280, 585));
        primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
