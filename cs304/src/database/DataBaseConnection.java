package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import connection.Session;

import ui.Result;

/**
 * Class creates the connection to the Oracle SQL database and 
 * is called for all queries, inserts and deletes in database.
 * 
 * @author Abe Friesen
 *
 */

public class DataBaseConnection {	

	Connection con;
	Session session;

	public DataBaseConnection(Session session) {
		this.session = session;
		connectToDB();
	}

	/**
	 * Connects to the oracle database
	 */
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
	
	/**
	 * Inserts an entry into the Book table in the database
	 * 
	 * @param varargs
	 * 		a list of strings containing the callNumber, ISBN, Title,
	 * 		MainAuthor, Publisher and year of a book, in that order. 
	 */
	public void insertBook(String... varargs) {
		
		PreparedStatement ps,ps2;
		int rowCount = 0;
		try {
			ps = con.prepareStatement("INSERT INTO book VALUES (?,?,?,?,?,?)");
			ps.setString(1,varargs[0]);
			ps.setString(2,varargs[1]);
			ps.setString(3,varargs[2]);
			ps.setString(4,varargs[3]);
			ps.setString(5,varargs[4]);
			ps.setInt(6,Integer.parseInt(varargs[5]));
			//Execute the insert
			rowCount += ps.executeUpdate();
			
			ps2 = con.prepareStatement("INSERT INTO book_copy VALUES(?,?,?)");
			ps2.setString(1, varargs[0]);
			ps2.setString(2, varargs[0]);
			ps2.setString(3, "in");
			
			rowCount += ps2.executeUpdate();
			if(rowCount > 0)
				JOptionPane.showMessageDialog(null, "Added entry to Book table");
			//Commit changes and close prepared statements
			con.commit();
			ps.close();
			ps2.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Inserts a subject entry for a particular callNumber
	 * 
	 * @param subjects
	 * 		The subjects associated with the callNumber;
	 * @param
	 * 		The callNumber for the book;
	 */
	public void insertSubject(String[] subjects, String callNumber) {
		
		PreparedStatement ps;
		int rowCount = 0;
		try {
			ps = con.prepareStatement("INSERT INTO has_subject VALUES (?,?)");
			for(int i = 0; i < subjects.length; i++)
			{
				ps.setString(1, callNumber);
				ps.setString(2, subjects[i]);
				rowCount += ps.executeUpdate();
			}
			if(rowCount > 0)
			{
				JOptionPane.showMessageDialog(null,"Successfully added values to Subject table");
			}
			
			//Commit and close prepared statement
			con.commit();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Inserts an Author entry for a particular callNumber
	 * 
	 * @param varargs
	 * 		The callNumber and Author name associated with it, in that order
	 */
	public void insertAuthors(String[] authors, String callNumber) {
		
		PreparedStatement ps;
		int rowCount = 0;
		try {
			ps = con.prepareStatement("INSERT INTO has_author VALUES (?,?)");
			for(int i = 0; i < authors.length; i++)
			{
				ps.setString(1, callNumber);
				ps.setString(2, authors[i]);
				rowCount += ps.executeUpdate();
			}
			if(rowCount > 0)
			{
				JOptionPane.showMessageDialog(null,"Successfully added values to Has_Author table");
			}
			
			//Commit and close prepared statement
			con.commit();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Inserts an entry into the Borrower table in the database
	 * 
	 * @param varargs
	 * 		a list of strings containing the bid, password, name, address,
	 * 		phone number, email address, sin or student #, expiry date, and type
	 * 		of a borrower, in that order
	 */
	public void insertBorrower(String... varargs) {
		
		PreparedStatement ps;
		
		//Format date and phone fields
		String strDate = varargs[7];
		String phone = varargs[4].replaceAll("[\\.-]", "");
		java.util.Date jDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			jDate = sdf.parse(strDate);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(jDate);
		Date sqlDate = new Date(calendar.getTimeInMillis());
				
		try {
			ps = con.prepareStatement("INSERT INTO borrower VALUES (?,?,?,?,?,?,?,?,?)");
			ps.setString(1,varargs[0]);
			ps.setString(2,varargs[1]);
			ps.setString(3,varargs[2]);
			ps.setString(4,varargs[3]);
			ps.setLong(5,Long.parseLong(phone));
			ps.setString(6,varargs[5]);
			ps.setLong(7,Long.parseLong(varargs[6]));
			ps.setDate(8,sqlDate);
			ps.setString(9,varargs[8]);
			
			//Execute the statement
			int rowCount = ps.executeUpdate();
			if(rowCount > 0)
				JOptionPane.showMessageDialog(null,"Added " + rowCount + " rows to Borrower Table");
			
			//Commit changes
			con.commit();
			
			//Close prepared statement
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Lists all the books that are currently checked out. Optional parameter can include a 
	 * subject to narrow down the results.
	 * 
	 * @param subject
	 * 		Possible parameter to search subject from has_subject table
	 */
	public void bookReport(String... varargs) {
		
		//Check if optional parameter is there
		if(varargs.length != 0)
		{
			try {
				Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				ResultSet rs;
				rs = stm.executeQuery("SELECT call_number, outDate, inDate FROM borrowing " +
						"WHERE call_number IN (SELECT call_number FROM book_copy WHERE status = 'out' INTERSECT " +
						"SELECT call_number FROM has_subject WHERE subject = '" + varargs[0] +"') ORDER BY call_number");
				Result r = new Result(rs);
				session.loadResultPanel(r);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else
		{		
			try {
				Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				ResultSet rs;
				rs = stm.executeQuery("SELECT call_number, outDate, inDate FROM borrowing " +
						"WHERE call_number IN (SELECT call_number FROM book_copy WHERE status = 'out') ORDER BY call_number");
				Result r = new Result(rs);
				session.loadResultPanel(r);
				
				//Close statment
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Generates a report for the popular items borrowed in a given year
	 * 
	 * @param varargs
	 * 		Strings containing the year, and the number of results to query for, in that order.
	 */
	public void popularReport(String... varargs) {
		
		/*
		 * Need to implement this correctly, however, as far as I know there is no way
		 * of querying the database for how often a book has been borrowed. Also, what is the 
		 * purpose of the borrowing table?
		 */
		try {
			Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet rs;
			rs = stm.executeQuery("SELECT call_number FROM book_copy WHERE status = 'out' ORDER BY call_number");
			Result r = new Result(rs);
			session.loadResultPanel(r);
			
			//Close the statement
			stm.close();
		} catch (SQLException e) {
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
	
	public Session getSession() {
		return session;
	}
	
}
