package application;

import java.io.IOException;
import controller.mainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;

public class Main extends Application {
	
	public void start(Stage primaryStage) throws IOException {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("mainLayout.fxml"));
			TabPane mainLayout = (TabPane)loader.load();
			
			mainController mainController = loader.getController();
			mainController.setMain(this);
			
			Scene scene = new Scene(mainLayout);
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		launch(args);
	}

}
