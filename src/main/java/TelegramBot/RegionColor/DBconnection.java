package TelegramBot.RegionColor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//classe di settaggio per stabilire la connessione al nostro db mySql

public class DBconnection {


	// variabili di configurazione
	private static String dbhost = "jdbc:mysql://connectionHostAddress <!-- ex localhost -->:yourPortNumber <!-- ex 3306 -->/yourDatabaseName";
	private static String username = "username";
	private static String password = "password";
	private static Connection conn;

	
	
	// metodo da richiamare per effettuare la connessione al db
	@SuppressWarnings("finally")
	public static Connection createNewDBconnection() {
		try  {	
			conn = DriverManager.getConnection(
					dbhost, username, password);	
		} catch (SQLException e) {
			System.out.println("Cannot create database connection");
			e.printStackTrace();
		} finally {
			return conn;	
		}		
	}
	
}
