package beni.momentum.bkalcul.controlers;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

	private static Connection connection;
	
	private static String dbUrl = "mysql-beni.alwaysdata.net/";
	private static String dbName = "beni_bkalcul";
	private static String dbUser = "beni_bkalcul";
	private static String dbPassword = "bkalcul_momentum";
	
	/*
	 * private static String dbUrl = "localhost:3306/"; 
	 * private static String dbName = "bkalcul"; 
	 * private static String dbUser = "root"; 
	 * private static String dbPassword = "";
	 */
	
	// Used to establish connection
	public static Connection getConnection() {

		if (connection == null) {

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://"+dbUrl+dbName, dbUser, dbPassword);
				System.out.println("Connexion Success");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return connection;
	}

}
