package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {

		  try {
			  
			AnchorPane root = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
			Scene scene = new Scene(root);
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("fond.jpg")));
			//primaryStage.initStyle(StageStyle.DECORATED);
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
	  }
	}

	public static void main(String[] args) {
		launch(args);
	}
}