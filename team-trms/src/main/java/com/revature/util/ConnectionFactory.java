package com.revature.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	private static String url;
	
	private static String user;
	
	private static String password;
	
	private static final String PROPERTIES_FILE = "/database.properties";
	
	private static ConnectionFactory cf;
	
	public static Connection getConnection() {
		if (cf == null) {
			cf = new ConnectionFactory();
		}
		
		return cf.createConnection();
	}
	
	private ConnectionFactory() {
		Properties prop = new Properties();
		
		try {
			prop.load(ConnectionFactory.class.getResourceAsStream(PROPERTIES_FILE));
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	private Connection createConnection() {
		Connection conn = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch(SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
