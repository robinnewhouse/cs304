package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {	

	Connection con;

	public DataBaseConnection() {

	}

	public void connectToDB() {

		//Register the driver
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//Get the Connection
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1521:ug", "username", "password");
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

}
