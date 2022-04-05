package application;

import java.io.BufferedOutputStream;
//import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
					
				try {
					Thread.sleep(500);
				} catch (InterruptedException ie) {
					
				}
				
				resetLabel.setVisible(true);
				
				oos.writeObject(data);
				oos.close();
			} catch(IOException e) {
				e.printStackTrace();
			} 
			
				
		}
		
}
