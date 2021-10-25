package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

	private static String url = "jdbc:sqlite:db/tierraMedia.db";
	private static Connection connection;
	
	public static Connection getConnection() throws SQLException {
		if (connection == null) {
			connection = DriverManager.getConnection(url);
		}
		
		return connection;
	}
}