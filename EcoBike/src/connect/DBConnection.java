package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import utils.Utils;

public class DBConnection {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL_DATABASE = "jdbc:mysql://localhost:3306/ecobike";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "";
	private static Logger LOGGER = Utils.getLogger(Connection.class.getName());
	public static Connection getConnection() {
		Connection connect = null;
		try {
			 // Initialize all the information regarding
	        // Database Connection
	        Class.forName(DRIVER);
	        connect =  DriverManager.getConnection(URL_DATABASE,USERNAME, PASSWORD);
			LOGGER.info("Connect success");
		} catch (SQLException ex) {
			LOGGER.info("Cannot get connection: " + ex);
		} catch (Throwable te) {
			te.printStackTrace();
		}
		return connect;
	}

    public static void main(String[] args) {
        DBConnection.getConnection();
    }
}
