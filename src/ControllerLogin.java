package application;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;


public class ControllerLogin{

	@FXML
	private Button ButtonLogin;
	@FXML
	private Button ButtonContiueWithout;
	@FXML
	private Button ButtonSignUp;
	@FXML
	private PasswordField PFieldPassword;
	@FXML
	private TextField TFieldUsername;
	@FXML
	private Label LabelLoginMessage;
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	Node node;
	//ActionEvent event;
	
	public void checkEnter(ActionEvent event) throws IOException {
			node = (Node)(event.getSource());
	}
	
	public void checkEnter2(KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER)) {
			validate();
		}
	}
	
	
	public void continueWithoutLogin(ActionEvent event) throws IOException {
		
		CurrentUser currentUser = new CurrentUser();
		currentUser.loggedIn = false;
		currentUser.username = null;
		try { //save
			FileOutputStream fos = new FileOutputStream("C:\\Program Files (x86)\\MentalMathTrainer\\temp.dat");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(currentUser);
			oos.close();
				} catch(IOException e) {
			System.out.println(e);
				}
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();	
	}
	
	public void loginButton(ActionEvent event) {
		//System.out.println(event.getSource());
		node = (Node)event.getSource();
		if (TFieldUsername.getText().isBlank() == false && PFieldPassword.getText().isBlank() == false) {
			//LabelLoginMessage.setText("You try to login");
			validate();
		}
		else {
			LabelLoginMessage.setText("Please enter username and password.");
		}
	}
	
	public void validate() {
		DatabaseConnection connect = new DatabaseConnection();
		Connection connectDB = connect.getConnection();
		
		String verifyLogin = "SELECT count(1) FROM users WHERE username = '" + TFieldUsername.getText() + "' AND password = '" + PFieldPassword.getText() + "';";
		//System.out.println(verifyLogin);
		try {
			
			Statement statement = connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(verifyLogin);
			
			while(queryResult.next()) {
				if (queryResult.getInt(1) == 1) {
					CurrentUser currentUser = new CurrentUser();
					currentUser.loggedIn = true;
					currentUser.username = TFieldUsername.getText();
					try { //save
						FileOutputStream fos = new FileOutputStream("C:\\Program Files (x86)\\MentalMathTrainer\\temp.dat");
						BufferedOutputStream bos = new BufferedOutputStream(fos);
						ObjectOutputStream oos = new ObjectOutputStream(bos);
						oos.writeObject(currentUser);
						oos.close();
							} catch(IOException e) {
						System.out.println(e);
							}
					goToMain(node);
				} else {
					LabelLoginMessage.setText("Invalid Login. Please try again.");
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void goToMain(Node node) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		stage = (Stage)node.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();	
	}
	
	public void goToMain(KeyEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();	
	}
	public void signUp(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
