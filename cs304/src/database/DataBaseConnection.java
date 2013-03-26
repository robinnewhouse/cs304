package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	public void insertBook(String... varargs) {
		try {
			Statement stm = con.createStatement();
			int rowCount = stm.executeUpdate("INSERT INTO book VALUES (");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	/**
	 * Check-out items borrowed by a borrower. To borrow items, borrowers provide their card number
	 * and a list with the call numbers of the items they want to check out. The system determines
	 * if the borrower's account is valid and if the library items are available for borrowing. 
	 * Then it creates one or more borrowing records and prints a note with the items and their 
	 * due day (which is giver to the borrower)
	 */
	public void checkOutItems (String cardnum, String[] callnums) {
		try {
			Statement st = con.createStatement();
			String sql = "SELECT name FROM borrower WHERE bid = " + cardnum;
			ResultSet rs = st.executeQuery(sql);
//			boolean b = rs.getBoolean(1);
//			if (b) {
//				System.out.println(b);
//			} else {
//				System.out.println("Cardnumber or call number list is invalid");
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
