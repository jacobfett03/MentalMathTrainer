package application;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
//import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControllerSettings extends Controller{
	@FXML
	private Label resetLabel;
	@FXML
	private Button resetButton;
		public void resetSettings()  {
			try {
				FileOutputStream fos = new FileOutputStream("C:\\Program Files (x86)\\MentalMathTrainer\\save.dat");
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				ObjectOutputStream oos = new ObjectOutputStream(bos);
				
				Data data = new Data();
				data.playerLevelM = 2;
				data.playerLevelA = 2;
				data.playerLevelS = 2;
				data.playerLevelD = 2;
				data.playerMaxLevelM = 2;
				data.playerMaxLevelA = 2;
				data.playerMaxLevelS = 2;
				data.playerMaxLevelD = 2;
					
				CurrentUser currentUser = new CurrentUser();
				DatabaseConnection connect = new DatabaseConnection();
				Connection connectDB = connect.getConnection();
				
				FileInputStream fis = new FileInputStream("C:\\Program Files (x86)\\MentalMathTrainer\\temp.dat");
				BufferedInputStream bis = new BufferedInputStream(fis);
				ObjectInputStream ois = new ObjectInputStream(bis);
				
				currentUser = (CurrentUser)ois.readObject();
				
				if (currentUser.loggedIn) {
					String resetData = "UPDATE users SET levelM = '2', levelA = '2', levelD = '2', levelS = 2 WHERE username='" + currentUser.username + "';";
					
					Statement statement = connectDB.createStatement();
					statement.executeUpdate(resetData);
				}
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					
				}
				
				resetLabel.setVisible(true);
				
				oos.writeObject(data);
				oos.close();
			} catch(Exception e) {
				e.printStackTrace();
			} 
			
				
		}
		
}
