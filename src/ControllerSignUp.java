package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ControllerSignUp {
	@FXML
	private Stage stage;
	@FXML
	private Scene scene;
	@FXML
	private TextField TFieldEmail;
	@FXML
	private TextField TFieldUsernameCreate;
	@FXML
	private PasswordField PFieldPasswordCreate;
	@FXML
	private PasswordField PFieldPasswordVerify;
	@FXML
	private Label SignUpLabel = new Label("SignUpLabel");
	
	String email;
	String username;
	String password;
	ActionEvent event;
	
	public void cancel(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void returnToLogin(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void createAccount(ActionEvent event) throws IOException {
		this.event = event;
		email = TFieldEmail.getText();
		username = TFieldUsernameCreate.getText();
		password = PFieldPasswordCreate.getText();
		String passwordVerify = PFieldPasswordVerify.getText();
		int key = 1;
		VerifyEmail verifyEmail = new VerifyEmail(email);
		
		
		if (!password.equals(passwordVerify)) {
			SignUpLabel.setText("Password do not match. Please try again.");
			SignUpLabel.setTextFill(Color.web("#FD5555"));
		} else if (!verifyEmail.validEmail) {
			SignUpLabel.setText("Invalid e-mail address. Please try again.");
			SignUpLabel.setTextFill(Color.web("#FD5555"));
		} else {
			DatabaseConnection connect = new DatabaseConnection();
			Connection connectDB = connect.getConnection();
			
			String checkUsername = "SELECT count(1) FROM users WHERE username = '" + username + "';";
			try {
				Statement statement = connectDB.createStatement();
				ResultSet queryResult = statement.executeQuery(checkUsername);
				
				while(queryResult.next()) {
					if (queryResult.getInt(1) == 1) {
						SignUpLabel.setText("Username has been taken. Please try again.");
						SignUpLabel.setTextFill(Color.web("#FD5555"));
						key = 0;
						break;
					} 
				}
			}catch (Exception e) {
				
			} finally {
				if (key == 1) {
					try {
						String checkEmail = "SELECT count(1) FROM users WHERE email = '" + email + "';";
						
						Statement statement = connectDB.createStatement();
						ResultSet queryResult = statement.executeQuery(checkEmail);
						
						while(queryResult.next()) {
							if (queryResult.getInt(1) == 1) {
								SignUpLabel.setText("Email is already in use. Please try again.");
								SignUpLabel.setTextFill(Color.web("#FD5555"));
								key = 0;
								break;
							}
						}
					} catch (Exception e) {
						
					} finally {
						if (key == 1) {
							try {
								String addAccount = "INSERT INTO users(email, username, password, levelM, levelA, levelD, levelS) VALUES ('" + email + "', '" + username + "', '" + password + "', '2', '2', '2', '2');";
								//System.out.println(addAccount);
								Statement statement = connectDB.createStatement();
								statement.executeUpdate(addAccount);
								
								String checkEmail = "SELECT count(1) FROM users WHERE email = '" + email + "';";
								
								statement = connectDB.createStatement();
								ResultSet queryResult = statement.executeQuery(checkEmail);
								
								int key2 = 0;
								while(queryResult.next()) {
									if (queryResult.getInt(1) == 1) {
										SignUpLabel.setText("Account successfully created! Redirecting to login page...");
										SignUpLabel.setTextFill(Color.web("#409E6C"));
										key2 = 1;
										try {
											Timer timer = new Timer();
											timer.schedule(new TimerTask() {
												@Override
												public void run() {
													Platform.runLater(new Runnable() {
														@Override
														public void run() {
															try {
																returnToLogin(event);
															} catch (IOException e) {
																// TODO Auto-generated catch block
																e.printStackTrace();
															}
														}
													});
												}
											}, 3000);
										} catch (Exception e) {
											
										}
										break;
									}
								}
								if (key2 == 0) {	
									SignUpLabel.setText("Error signing up. Please try again later.");
									SignUpLabel.setTextFill(Color.web("#FD5555"));
									}
								
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							SignUpLabel.setText("Email is already in use. Please try again.");
							SignUpLabel.setTextFill(Color.web("#FD5555"));
						}
					}
				}
			}
		}
	}
	
}
