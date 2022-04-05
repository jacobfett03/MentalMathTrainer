package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import javafx.scene.Node;

public class ControllerFactorsDiv extends Controller {
	
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private CheckBox fac1_1;
	@FXML
	private CheckBox fac1_2;
	@FXML
	private CheckBox fac1_3;
	@FXML
	private CheckBox fac1_4;
	@FXML
	private CheckBox fac1_5;
	@FXML
	private CheckBox fac1_6;
	@FXML
	private CheckBox fac2_1;
	@FXML
	private CheckBox fac2_2;
	@FXML
	private CheckBox fac2_3;
	@FXML
	private CheckBox fac2_4;
	@FXML
	private CheckBox fac2_5;
	@FXML
	private CheckBox fac2_6;
	@FXML
	private Button buttonContinue;
	boolean digit1;
	boolean digit2;
	int fac1digits;
	int fac2digits;
	int dig1;
	int dig2; 
	public void mainMenu(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();	
	}
	public int getDigits(int number) {
		if (number == 1) {
			return dig1;
		} else {
			return dig2;
		} 
	}
	
	public void setDigit1(int number) {
		fac1digits = number;
		//System.out.println(fac1digits);
	}
	public void setDigit2(int number) {
		fac2digits = number;
	}
	
public void goToDivProblem(ActionEvent event) throws IOException {
		
		int digits1 = fac1digits;
		int digits2 = fac2digits;
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("divProblem.fxml"));
		root = loader.load();
		
		ControllerDiv ControllerDiv = loader.getController();

		ControllerDiv.setDigit(digits1, digits2);
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}



public void divisionCheckBox(ActionEvent event) throws IOException {
	//System.out.println(settings.getSettingOp());	
	
	if (fac1_1.isSelected()) {
		fac1_2.setDisable(true);
		fac1_3.setDisable(true);
		fac1_4.setDisable(true);
		fac1_5.setDisable(true);
		fac1_6.setDisable(true);
		digit1 = true;
		setDigit1(1);
		
	} else if (fac1_2.isSelected()) {
		fac1_1.setDisable(true);
		fac1_3.setDisable(true);
		fac1_4.setDisable(true);
		fac1_5.setDisable(true);
		fac1_6.setDisable(true);
		
		fac2_1.setDisable(false);
		fac2_2.setDisable(true);
		fac2_3.setDisable(true);
		fac2_4.setDisable(true);
		fac2_5.setDisable(true);
		fac2_6.setDisable(true);
		digit1 = true;
		setDigit1(2);
	}
	else if (fac1_3.isSelected()) {
		fac1_1.setDisable(true);
		fac1_2.setDisable(true);
		fac1_4.setDisable(true);
		fac1_5.setDisable(true);
		fac1_6.setDisable(true);
		
		fac2_1.setDisable(false);
		fac2_2.setDisable(false);
		fac2_3.setDisable(true);
		fac2_4.setDisable(true);
		fac2_5.setDisable(true);
		fac2_6.setDisable(true);
		digit1 = true;
		setDigit1(3);
	}
	else if (fac1_4.isSelected()) {
		fac1_1.setDisable(true);
		fac1_3.setDisable(true);
		fac1_2.setDisable(true);
		fac1_5.setDisable(true);
		fac1_6.setDisable(true);
		
		fac2_1.setDisable(false);
		fac2_2.setDisable(false);
		fac2_3.setDisable(false);
		fac2_4.setDisable(true);
		fac2_5.setDisable(true);
		fac2_6.setDisable(true);
		digit1 = true;
		setDigit1(4);
	}
	else if (fac1_5.isSelected()) {
		fac1_1.setDisable(true);
		fac1_3.setDisable(true);
		fac1_4.setDisable(true);
		fac1_2.setDisable(true);
		fac1_6.setDisable(true);
		
		fac2_1.setDisable(false);
		fac2_2.setDisable(false);
		fac2_3.setDisable(false);
		fac2_4.setDisable(false);
		fac2_5.setDisable(true);
		fac2_6.setDisable(true);
		digit1 = true;
		setDigit1(5);
	}
	else if (fac1_6.isSelected()) {
		fac1_1.setDisable(true);
		fac1_3.setDisable(true);
		fac1_4.setDisable(true);
		fac1_5.setDisable(true);
		fac1_2.setDisable(true);
		
		fac2_1.setDisable(false);
		fac2_2.setDisable(false);
		fac2_3.setDisable(false);
		fac2_4.setDisable(false);
		fac2_5.setDisable(false);
		fac2_6.setDisable(true);
		digit1 = true;
		setDigit1(6);
	}
	
	if (((!fac1_1.isSelected()) && (!fac1_2.isSelected())&& (!fac1_3.isSelected())&& (!fac1_4.isSelected()) && (!fac1_5.isSelected())&& (!fac1_6.isSelected()))) {
		fac1_1.setDisable(false);
		fac1_2.setDisable(false);
		fac1_3.setDisable(false);
		fac1_4.setDisable(false);
		fac1_5.setDisable(false);
		fac1_6.setDisable(false);
		fac2_1.setDisable(false);
		fac2_2.setDisable(false);
		fac2_3.setDisable(false);
		fac2_4.setDisable(false);
		fac2_5.setDisable(false);
		fac2_6.setDisable(false);
		digit1 = false;
	}
		//--------------------------------Check for 2nd factor-----------------------
		if (fac2_1.isSelected()) {
			fac2_2.setDisable(true);
			fac2_3.setDisable(true);
			fac2_4.setDisable(true);
			fac2_5.setDisable(true);
			fac2_6.setDisable(true);
			digit2 = true;
			fac2digits = 1;
			
		} else if (fac2_2.isSelected()) {
			fac2_1.setDisable(true);
			fac2_3.setDisable(true);
			fac2_4.setDisable(true);
			fac2_5.setDisable(true);
			fac2_6.setDisable(true);
			digit2 = true;
			fac2digits = 2;
		}
		else if (fac2_3.isSelected()) {
			fac2_1.setDisable(true);
			fac2_2.setDisable(true);
			fac2_4.setDisable(true);
			fac2_5.setDisable(true);
			fac2_6.setDisable(true);
			digit2 = true;
			fac2digits = 3;
		}
		else if (fac2_4.isSelected()) {
			fac2_1.setDisable(true);
			fac2_3.setDisable(true);
			fac2_2.setDisable(true);
			fac2_5.setDisable(true);
			fac2_6.setDisable(true);
			digit2 = true;
			fac2digits = 4;
		}
		else if (fac2_5.isSelected()) {
			fac2_1.setDisable(true);
			fac2_3.setDisable(true);
			fac2_4.setDisable(true);
			fac2_2.setDisable(true);
			fac2_6.setDisable(true);
			digit2 = true;
			fac2digits = 5;
		}
		else if (fac2_6.isSelected()) {
			fac2_1.setDisable(true);
			fac2_3.setDisable(true);
			fac2_4.setDisable(true);
			fac2_5.setDisable(true);
			fac2_2.setDisable(true);
			digit2 = true;
			fac2digits = 6;
		}
		
		
		
		if (digit1 && digit2) {
			buttonContinue.setDisable(false);
			//dig1 = fac1digits;
			//dig2 = fac2digits;
		} else { 
			buttonContinue.setDisable(true);
		}
	
 }
}
