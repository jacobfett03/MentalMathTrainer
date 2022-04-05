package application;
	//Really important update, if you are using javaSE-16 you NEED to add the javaFX library under the MODULE-PATH instead of under the class-path.
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.image.*;
//import javafx.scene.text.*;
//import javafx.scene.text.Font;
//import javafx.scene.text.Text;
//import javafx.scene.shape.*;
import javafx.fxml.*;
import javafx.scene.Parent;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			//BorderPane root = new BorderPane();
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root,Color.BEIGE);
			
			//Font.loadFont(getClass().getResourceAsStream("file:/resources/cute_notes.ttf"), 30);
			
			stage.setTitle("Mental Math Trainer");
			Image icon = new Image("brain.png");
			stage.getIcons().add(icon);
			stage.setResizable(false);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
