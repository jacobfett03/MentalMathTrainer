package application;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ControllerSub extends Controller implements Initializable{
	@FXML
	private Label labelCorrect;
	@FXML
	private Label factor1;
	@FXML
	private Label factor2;
	@FXML
	private TextField tfieldAnswerM;
	@FXML
	private Label timeElapsed;
	int guess;
	static int dig1;
	static int dig2;
	int minuend;
	int subtrahend;
	int difference;
	int timeStart;
	int timeFinish;
	double time;
	boolean negative = false;
	DecimalFormat format = new DecimalFormat("0.#");
	private Stage stage;
	private Scene scene;
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
	public void setDigit(int digit1, int digit2, boolean allowNegative) {
		if (allowNegative) {
			negative = allowNegative;
			dig1 = digit1;
			dig2 = digit2;
			minuend = (getRandomNumber(dig1));
			subtrahend = (getRandomNumber(dig2));
			if (minuend < subtrahend) {
				int temp = minuend;
				minuend = subtrahend;
				subtrahend = temp;
			}
			factor1.setText(Integer.toString(minuend));
			factor2.setText(Integer.toString(subtrahend));
			timeStart = (int)System.currentTimeMillis();
		}
		else {
			dig1 = digit1;
			dig2 = digit2;
			minuend = (getRandomNumber(dig1));
			subtrahend = (getRandomNumber(dig2));
			factor1.setText(Integer.toString(minuend));
			factor2.setText(Integer.toString(subtrahend));
			timeStart = (int)System.currentTimeMillis();
		}
	}
	
	
	public static int getRandomNumber(int digits) {
    	Random rand = new Random();
    	int number = (int)Math.pow(10, digits - 1) + rand.nextInt((int)(Math.pow(10, digits)) - (int)Math.pow(10, digits - 1));
    	return number;
    }

	
	@FXML
	 void checkAnswer(KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER)) {
			newProblem();
		}
		guess = Integer.parseInt(tfieldAnswerM.getText());
		int answer = minuend - subtrahend;
		if (answer == guess ) {
			timeFinish = (int)System.currentTimeMillis();
			time = ((Math.round((timeFinish - timeStart) / 100.0)) / 10.0);
			labelCorrect.setVisible(true);
			tfieldAnswerM.setEditable(false);
			timeElapsed.setText(String.format("Time Elasped: %s seconds\n",(format.format(time))));
			//System.out.printf("Time Elasped: %s seconds\n",(format.format(time)));
			timeElapsed.setVisible(true);
		} else {
			labelCorrect.setVisible(false);
		}
	}
		//System.out.print("hi");
		
		/*void checkEnter(KeyEvent event) {
			//System.out.println("key pressed");
			if (event.getCode().equals(KeyCode.ENTER)) {
				newProblem();
			}
	}
	*/
	@FXML
	void newProblem() {
		tfieldAnswerM.clear();
		//tfieldAnswerM.setText("?");
		setDigit(dig1, dig2, negative);

		labelCorrect.setVisible(false);
		tfieldAnswerM.setEditable(true);
		timeElapsed.setVisible(false);
		
		
	}




	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		factor1.setText(Integer.toString(dig1));
		factor2.setText(Integer.toString(dig2));
	}
	
	
}
