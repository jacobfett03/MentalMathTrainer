package application;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.Node;


public class Controller implements Initializable {	
	
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
	@FXML 
	private Button mainMult;
	@FXML 
	private Button mainAdd;
	@FXML 
	private Button mainDiv;
	@FXML 
	private Button mainSub;
	@FXML
	private TextField tfieldAnswerM;
	@FXML
	private Label LabelWelcome;
	boolean digit1;
	boolean digit2;
	int fac1digits;
	int fac2digits;
	int dig1;
	int dig2; 
	

	public int getDigits(int number) {
		if (number == 1) {
			return dig1;
		} else {
			return dig2;
		} 
	}
	
	public void setDigit1(int number) {
		fac1digits = number;
	}
	
	public void setDigit2(int number) {
		fac2digits = number;
	}

	public void multiplication(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("multiplication.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();	
	}
	public void division(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("division.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void addition(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("addition.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();	
	}
	public void subtraction(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("subtraction.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void mainMenu(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();	
	}
	public void express(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("express.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void settings(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public static int getRandomNumber(int digits) {
    	Random rand = new Random();
    	int number = (int)Math.pow(10, digits - 1) + rand.nextInt((int)(Math.pow(10, digits)) - (int)Math.pow(10, digits - 1));
    	return number;
    }
	
	public void goToMultProblem(ActionEvent event) throws IOException {
		
		int digits1 = fac1digits;
		int digits2 = fac2digits;
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("multProblem.fxml"));
		root = loader.load();
		
		ControllerMult controllerMult = loader.getController();
		controllerMult.setDigit1(digits1);
		controllerMult.setDigit2(digits2);
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void multiplicationCheckBox(ActionEvent event) throws IOException {	
		
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
			digit1 = true;
			setDigit1(2);
		}
		else if (fac1_3.isSelected()) {
			fac1_1.setDisable(true);
			fac1_2.setDisable(true);
			fac1_4.setDisable(true);
			fac1_5.setDisable(true);
			fac1_6.setDisable(true);
			digit1 = true;
			setDigit1(3);
		}
		else if (fac1_4.isSelected()) {
			fac1_1.setDisable(true);
			fac1_3.setDisable(true);
			fac1_2.setDisable(true);
			fac1_5.setDisable(true);
			fac1_6.setDisable(true);
			digit1 = true;
			setDigit1(4);
		}
		else if (fac1_5.isSelected()) {
			fac1_1.setDisable(true);
			fac1_3.setDisable(true);
			fac1_4.setDisable(true);
			fac1_2.setDisable(true);
			fac1_6.setDisable(true);
			digit1 = true;
			setDigit1(5);
		}
		else if (fac1_6.isSelected()) {
			fac1_1.setDisable(true);
			fac1_3.setDisable(true);
			fac1_4.setDisable(true);
			fac1_5.setDisable(true);
			fac1_2.setDisable(true);
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
			
			if (((!fac2_1.isSelected()) && (!fac2_2.isSelected())&& (!fac2_3.isSelected())&& (!fac2_4.isSelected()) && (!fac2_5.isSelected())&& (!fac2_6.isSelected()))) {
				fac2_1.setDisable(false);
				fac2_2.setDisable(false);
				fac2_3.setDisable(false);
				fac2_4.setDisable(false);
				fac2_5.setDisable(false);
				fac2_6.setDisable(false);
				digit2 = false;		
		}
			
			if (digit1 && digit2) {
				buttonContinue.setDisable(false);
			} else { 
				buttonContinue.setDisable(true);
			}
	}
	
	int guess;
	int timeStart;
	int timeFinish;
	int num1;
	int num2;
	double time;
	@FXML
	private Label labelCorrect;
	@FXML
	private Label timeElapsed;
	DecimalFormat format = new DecimalFormat("0.#");
	
	@FXML
	void checkAnswer(KeyEvent event) throws InterruptedException {
		String text = tfieldAnswerM.getCharacters().toString();
		tfieldAnswerM.positionCaret(text.length());
		
		if (text.contains("?")) {
			String textA = event.getText();
			if (!(textA.matches("[0-9]+"))){
				tfieldAnswerM.clear();
				tfieldAnswerM.setText("?");
			}
			else {
				tfieldAnswerM.clear();
				String firstNum = event.getText();
				tfieldAnswerM.setText(firstNum);
				tfieldAnswerM.deleteNextChar();
			}
		} else if (!(text.matches("[0-9]+"))) {
			tfieldAnswerM.deletePreviousChar();
		}
		
		if (event.getCode().equals(KeyCode.ENTER)) {
			newProblem();
		}
		guess = Integer.parseInt(tfieldAnswerM.getText());
	}	
	 void newProblem() {
		 
	 }

	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			FileInputStream fis = new FileInputStream("C:\\Program Files (x86)\\MentalMathTrainer\\temp.dat");
			BufferedInputStream bis = new BufferedInputStream(fis);
			ObjectInputStream ois = new ObjectInputStream(bis);
			
			CurrentUser currentUser = new CurrentUser();
			currentUser = (CurrentUser)ois.readObject();
			
			if (currentUser.loggedIn) {
				LabelWelcome.setText("Welcome, " + currentUser.username);
			}
		
	} catch (Exception e) {
	}
  }
}
