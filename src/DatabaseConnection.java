package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	public Connection dbLink;
	
	public Connection getConnection() {
		String dbName = "HIDDEN";
		String dbUser = "HIDDEN";
		String dbPassword = "HIDDEN";
		String url = "HIDDEN" + dbName;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			dbLink = DriverManager.getConnection(url, dbUser, dbPassword);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return dbLink; 
	}
}
