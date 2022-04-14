package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
//REMINDER: SHOW SOLUTION BUTTON IS INVISIBLE-------------------------------------------------------------------------------------------------------------------------------------------------
public class ControllerExpress extends Controller implements Initializable{
	@FXML
	private Label factor1;
	@FXML
	private Label factor2;
	@FXML
	private TextField tfieldAnswerM;
	@FXML
	private Text symbol;
	@FXML
	private BorderPane frameBorderPane;
	@FXML
	private ScrollPane solutionBox;
	@FXML
	Label timeElapsed;
	@FXML
	Label labelCorrect;
	int guess;
	static int dig1;
	static int dig2;
	int correctInARowM = 0;
	int correctInARowA = 0;
	int correctInARowS = 0;
	int correctInARowD = 0;
	int fac1;
	int fac2;
	int timeStart = (int) System.currentTimeMillis();
	int timeFinish;
	double time;
	int level = 2;
	int maxLevel = 2;
	int backLevel = 2;
	int answer;
	int type;
	int problemType; // 0=mult 1=add 2=sub 3=div
	boolean loggedIn = false;
	Data data = new Data();
	DecimalFormat format = new DecimalFormat("0.#");
	CurrentUser currentUser = new CurrentUser();
	DatabaseConnection connect = new DatabaseConnection();
	Connection connectDB = connect.getConnection();
	
	public boolean getLoginStatus() {
		try {
			FileInputStream fis = new FileInputStream("C:\\Program Files (x86)\\MentalMathTrainer\\temp.dat");
			BufferedInputStream bis = new BufferedInputStream(fis);
			ObjectInputStream ois = new ObjectInputStream(bis);
			
			currentUser = (CurrentUser)ois.readObject();
			
			if (currentUser.loggedIn) {
				loggedIn = true;
			} else {
				loggedIn = false;
			}	
		} catch (Exception e) {
			
		}
		return loggedIn;		
	}
	
	public void setDigits(int complexity) {
		if (problemType == 0) {    //multiplication
			symbol.setText("X");
			if (complexity > 6) {
				complexity = 6;
			}
			dig2 = (int)Math.floor(complexity / 2.0);
			dig1 = complexity - dig2;
			fac1 = getRandomNumber(dig1);
			fac2 = getRandomNumber(dig2);
			answer = fac1 * fac2;
			factor1.setText(Integer.toString(fac1));
			factor2.setText(Integer.toString(fac2));
		}
		if (problemType == 1) {
			symbol.setText("+");
			if (complexity > 10) {
				complexity = 10;
			}
			dig2 = (int)Math.floor(complexity / 2.0);
			dig1 = complexity - dig2;
			fac1 = getRandomNumber(dig1);
			fac2 = getRandomNumber(dig2);

			answer = fac1 + fac2;

			factor1.setText(Integer.toString(fac1));
			factor2.setText(Integer.toString(fac2));
		}
		if (problemType == 2) {
			symbol.setText("-");
			if (complexity > 10) {
				complexity = 10;
			}
			dig2 = (int)Math.floor(complexity / 2.0);
			dig1 = complexity - dig2;
			fac1 = getRandomNumber(dig1);
			fac2 = getRandomNumber(dig2);
			if (fac2 > fac1) {
				int temp = fac1;
				fac1 = fac2;
				fac2 = temp;
			}
			answer = fac1 - fac2;
			factor1.setText(Integer.toString(fac1));
			factor2.setText(Integer.toString(fac2));
		}
		if (problemType == 3) {
			++complexity;
			if (complexity > 6) {
				complexity = 6;
			}
			symbol.setText("÷");
			dig2 = 1;
			if (complexity > 4) {
				Random rand = new Random();
				dig2 = rand.nextInt(1,3);
			}
			dig1 = complexity - dig2;
			if (dig2 > dig1) {
				int temp = dig1;
				dig1 = dig2;
				dig2 = temp;
			}
			answer = getRandomNumber((dig1 - dig2)) + 1;
			fac2 = ControllerDiv.getDivisor(answer, dig2, dig1);	
			fac1 = answer * fac2;
			factor1.setText(Integer.toString(fac1));
			factor2.setText(Integer.toString(fac2));
		}	
	}
	
	public void getProblemType() {
		problemType = (int)Math.floor(Math.random()*(3-0+1)+0);
	}
	
	@FXML
	void newProblem() {
		tfieldAnswerM.setText(Integer.toString(answer));
		tfieldAnswerM.clear();
		tfieldAnswerM.setEditable(true);
		labelCorrect.setVisible(false);
		timeElapsed.setVisible(false);
		try {
			getProblemType();
			Random rand = new Random();
			try {
				if (problemType == 0) {
					level = rand.nextInt(data.playerMaxLevelM - 1, data.playerMaxLevelM + 1);
					maxLevel = data.playerMaxLevelM;
				} else if (problemType == 1) {
					level = rand.nextInt(data.playerMaxLevelA - 1, data.playerMaxLevelA + 1);
					maxLevel = data.playerMaxLevelS;
				} else if (problemType == 2) {
					level = rand.nextInt(data.playerMaxLevelS - 1, data.playerMaxLevelS + 1);
					maxLevel = data.playerMaxLevelS;
				} else if (problemType == 3) {
					level = rand.nextInt(data.playerMaxLevelD - 1, data.playerMaxLevelD + 1);
					maxLevel = data.playerMaxLevelD;
				}
			} catch (Exception e) {
			}	
			if (level < 2 ) {
				level = 2;
			}
			if (maxLevel < 2 ) {
				maxLevel = 2;
			}			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		setDigits(maxLevel);
		tfieldAnswerM.setEditable(true);			
	}
	
	@FXML
	void repositionCursor(MouseEvent event) {
		String text = tfieldAnswerM.getCharacters().toString();
		tfieldAnswerM.positionCaret(text.length());
	}
	
	@FXML
	void checkAnswer(KeyEvent event) throws InterruptedException {
		type = problemType;
		String text = this.tfieldAnswerM.getCharacters().toString();
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
		} else {  //only numbers
			guess = Integer.parseInt(tfieldAnswerM.getText());
			if (guess == answer) {
				timeFinish = (int)System.currentTimeMillis();
				time = ((Math.round((timeFinish - timeStart) / 100.0)) / 10.0);
				labelCorrect.setVisible(true);
				timeElapsed.setText(String.format("Time Elapsed: %s seconds\n", (format.format(time))));
				timeElapsed.setVisible(true);
				tfieldAnswerM.setEditable(false);

					if (type == 0) {
						++correctInARowM;
					}
					if (type == 1) {
						++correctInARowA;
					}
					if (type == 2) {
						++correctInARowS;
					}
					if (type == 3) {
						++correctInARowD;
					}
					if (correctInARowM >= 5) {
						correctInARowM = 0;
						data.playerMaxLevelM++;
						updateStats();
					}
					if (correctInARowA >= 5) {
						++backLevel;
						correctInARowA = 0;
						data.playerMaxLevelA++;
						updateStats();
					}
					if (correctInARowS >= 5) {
						++backLevel;
						correctInARowS = 0;
						data.playerMaxLevelS++;
						updateStats();
					}
					if (correctInARowD >= 5) {
						++backLevel;
						correctInARowD = 0;
						data.playerMaxLevelD++;
						updateStats();
					}

				try {
					Timer timer = new Timer();
					timer.schedule(new TimerTask() {
						@Override
						public void run() {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									newProblem();
								}
							});
						}
					}, 1000);
				} catch (Exception e) {
					
				}
				timeStart = (int)System.currentTimeMillis();
			}			
		}
	}

	public void sleep(int time) {
		try {
			
			Thread.sleep(time * 1000);
		} catch (Exception e) {
			
		}
		newProblem();
	}
	public void solution() {
		
	}
	
	public void updateStats() {
		try { //save to database
			FileInputStream fis = new FileInputStream("C:\\Program Files (x86)\\MentalMathTrainer\\temp.dat");
			BufferedInputStream bis = new BufferedInputStream(fis);
			ObjectInputStream ois = new ObjectInputStream(bis);
			
			currentUser = (CurrentUser)ois.readObject();
			
			if (currentUser.loggedIn) {
				
				String updateLevel = "UPDATE users SET levelM = '" + data.playerMaxLevelM + "', levelA = '" + data.playerMaxLevelA + "', levelD = '" 
				+ data.playerMaxLevelD + "', levelS = '" + data.playerMaxLevelS + "' WHERE username='" + currentUser.username + "';";
				
				Statement statement = connectDB.createStatement();
				statement.executeUpdate(updateLevel);
				System.out.println("level updated");
			} else {
				try { //save to machine
					FileOutputStream fos = new FileOutputStream("C:\\Program Files (x86)\\MentalMathTrainer\\save.dat");
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					ObjectOutputStream oos = new ObjectOutputStream(bos);
					oos.writeObject(data);
					oos.close();
						} catch(Exception e) {
					System.out.println(e);
						}
			}
		} catch (Exception e) {
			
		}
	}
	
	public void importStatsFromDatabase() {
		String getLevel = "SELECT levelM, levelD, levelA, levelS FROM users WHERE username='" + currentUser.username + "';";
		
		try {
			Statement statement = connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(getLevel);
			queryResult.next();
			data.playerMaxLevelM = queryResult.getInt("levelM");
			data.playerMaxLevelA = queryResult.getInt("levelA");
			data.playerMaxLevelD = queryResult.getInt("levelD");
			data.playerMaxLevelS = queryResult.getInt("levelS");
		} catch (Exception e) {
			System.out.println(e);
		}
		
		//SELECT levelM, levelD, levelA, levelS FROM users WHERE username='jacobfett21';
	}
	
	public void importStatsFromMachine() {
		try {
			FileInputStream fis = new FileInputStream("C:\\Program Files (x86)\\MentalMathTrainer\\save.dat");
			BufferedInputStream bis = new BufferedInputStream(fis);
			ObjectInputStream ois = new ObjectInputStream(bis);
			
			data = (Data)ois.readObject();
		} catch (Exception e) {
			
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		if (getLoginStatus()) {
			importStatsFromDatabase();
		} else {
			importStatsFromMachine();
		}
		newProblem();
	}
}
