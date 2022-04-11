package application;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControllerDiv extends Controller implements Initializable{
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
	int quotient;
	int divisor;
	int dividend;
	int timeStart;
	int timeFinish;
	double time;
	DecimalFormat format = new DecimalFormat("0.#");

	public void setDigit(int digit1, int digit2) {
		if (digit2 > digit1) {
			int temp;
			temp = digit2;
			digit2 = digit1;
			digit1 = temp;
		}
		dig1 = digit1;
		dig2 = digit2;
		quotient = getRandomNumber((digit1 - digit2)) + 1;
		divisor = getDivisor(quotient, digit2, digit1);
		dividend = divisor * quotient;
		factor1.setText(Integer.toString(dividend));
		factor2.setText(Integer.toString(divisor));
		timeStart = (int)System.currentTimeMillis();
	}

	public static int getDivisor(int quo, int digits, int digitsUpper) {
		int div = 0;
		while ((div * quo < (int)(Math.pow(10, digitsUpper - 1)))) {
			div = getRandomNumber(digits);
		}
		return div;
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
		int answer = dividend / divisor;
		if (answer == guess ) {
			timeFinish = (int)System.currentTimeMillis();
			time = ((Math.round((timeFinish - timeStart) / 100.0)) / 10.0);
			labelCorrect.setVisible(true);
			tfieldAnswerM.setEditable(false);
			timeElapsed.setText(String.format("Time Elasped: %s seconds\n",(format.format(time))));
			timeElapsed.setVisible(true);
		} else {
			labelCorrect.setVisible(false);
		}
	}

	@FXML
	void newProblem() {
		tfieldAnswerM.clear();
		setDigit(dig1, dig2);
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
