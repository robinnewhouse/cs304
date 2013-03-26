package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {	

	Connection con;

	public DataBaseConnection() {
		connectToDB();
	}

	private void connectToDB() {

		//Register the driver
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//Get the Connection
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug", "ora_j7p7", "a51712107");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void insert(String... varargs) {
		for(String str: varargs)
		{
			System.out.println(str);
		}
		
	}
	

}
