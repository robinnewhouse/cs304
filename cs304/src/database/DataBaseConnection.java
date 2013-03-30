package database;

import java.sql.Array;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
			con = DriverManager.getConnection("jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug", "ora_h7r8", "a10686129");
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
		PreparedStatement ps;
		int rowCount = 0;
		try {
			String query = "INSERT INTO book VALUES (?,?,?,?,?,?)";
			ps = con.prepareStatement(query);
			ps.setString(1,varargs[0]);
			ps.setString(2,varargs[1]);
			ps.setString(3,varargs[2]);
			ps.setString(4,varargs[3]);
			ps.setString(5,varargs[4]);
			ps.setInt(6,Integer.parseInt(varargs[5]));
			//Execute the insert
			rowCount += ps.executeUpdate();
			
			if(rowCount > 0)
				JOptionPane.showMessageDialog(null, "Added entry to Book table");
			//Commit changes and close prepared statements
			con.commit();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertCopy(String callNumber){	
		try{
			PreparedStatement ps;
			ResultSet result;
			int copyNumber = 1;
			int rowCount = 0;
			String query = "SELECT * FROM book_copy WHERE call_number LIKE '%" + callNumber + "%'";
			ps = con.prepareStatement(query);
			result = ps.executeQuery();
			
			while(result.next())
				copyNumber++;
			
			ps = con.prepareStatement("INSERT INTO book_copy (call_number, copy_no, status)" +
									  " VALUES(?, ?, ?)");
			ps.setString(1, callNumber);
			ps.setString(2, "c" + String.valueOf(copyNumber++));
			ps.setString(3, "in");
			rowCount += ps.executeUpdate();
			
			if(rowCount > 0)
				JOptionPane.showMessageDialog(null, "Added entry to Book_Copy table");
			//Commit changes and close prepared statements
			con.commit();
			ps.close();
		} catch (SQLException e){
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
		String strDate = varargs[6];
		String phone = varargs[3].replaceAll("[\\.-]", "");
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
			ps = con.prepareStatement("INSERT INTO borrower (bid, name, password, address, phone, " +
											"emailAddress, sinOrStNo, expiryDate, type) " +
									  "VALUES (bid.nextval,?,?,?,?,?,?,?,?)");
			ps.setString(1,varargs[0]);
			ps.setString(2,varargs[1]);
			ps.setString(3,varargs[2]);
			ps.setLong(4,Long.parseLong(phone));
			ps.setString(5,varargs[4]);
			ps.setLong(6,Long.parseLong(varargs[5]));
			ps.setDate(7,sqlDate);
			ps.setString(8,varargs[7]);
			
			//Execute the statement
			int rowCount = ps.executeUpdate();
			if(rowCount > 0){
				JOptionPane.showMessageDialog(null,"Added " + rowCount + " rows to Borrower Table");
			}
			
			ps = con.prepareStatement("SELECT bid.currval FROM dual");
			ResultSet result = ps.executeQuery();
			result.next();
			int bid = 0;
			bid = result.getInt(1);
			if(bid != 0)
				JOptionPane.showMessageDialog(null,"Borrower's BID is: " + bid);
			
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
	public void popularReport(String year, String n) {
		
		/*
		 * This is a weird bug. Executing the following query in the terminal database
		 * has the correct results, but for some reason while doing it from here
		 * it doesn't work. The culprit is the 'where outDate like '%" + year + "-%'
		 * part. If I take that out, the query returns results.
		 */
		try {
			Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet rs;
			
			rs = stm.executeQuery("select * from (select call_number, count(call_number) from borrowing " +
					"where outDate like '%" + year + "-%' group by call_number) where rownum <= " +
					n);
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
		int bid = Integer.parseInt(cardnum);
		
		PreparedStatement ps;
		for (int i = 0; i < callnums.length; i++) {
			try {
				boolean copyIn = true;
				// Get copy number of an available copy
				String query = "SELECT copy_no FROM book_copy WHERE status = 'in'";
				ps = con.prepareStatement(query);
				ResultSet result = ps.executeQuery(query);
				result.next();
				String copy = result.getString(1);
				if(copy.isEmpty()){ // If all copies out
					JOptionPane.showMessageDialog(null, "No copies are available");
					copyIn = false;
				}
					
				if(copyIn = true){
					// Get borrower's loan time limit
					query = "SELECT type FROM borrower WHERE bid = " + bid;
					ps = con.prepareStatement(query);
					result = ps.executeQuery();
					result.next();
					String type = result.getString(1);
					if(type.toLowerCase().equals("student")){
						
					}
					else if(type.toLowerCase().equals("faculty")){
						
					}
					else if(type.toLowerCase().equals("staff")){
						
					}
					
					// Create due date according to borrower type
					String strDate = "14/ 05/13";
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
					
					// Checkout copy if there is an available copy
					query = "INSERT INTO borrowing (borid,bid,call_number,copy_no,outDate) VALUES (borid_counter.nextval,?,?,?,?)";
					ps = con.prepareStatement(query);
					ps.setInt(1, bid);
					ps.setString(2, callnums[i]);
					ps.setString(3, copy);
					ps.setDate(4, sqlDate);
					int rs2 = ps.executeUpdate();
					if(rs2 > 0)
						JOptionPane.showMessageDialog(null, "Added " + rs2 + " rows to table");
					
					// Update book copy status to out
					query = "UPDATE book_copy SET status = 'out' WHERE copy_no = '" + copy + "'";
					ps.execute(query);
				}
				
				con.commit();
				ps.close();
			} catch (SQLException e) {
				System.out.println("Inserting tuple in borrowing didn't work during iteration ");
				e.printStackTrace();
			}
		}
	}
	
	public void searchForItem(String keyword, String author, String subject){
		try {
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT b.call_number, b.isbn, b.title, b.main_author, b.publisher, b.year, c.status " +
						   "FROM book b, book_copy c " +
						   "WHERE b.call_number IN " +
						   "	(SELECT b.call_number " +
						   "	 FROM book b, has_subject s " +
						   "	 WHERE ";
			
			if(!keyword.isEmpty())
				query += "(lower(b.title) LIKE lower('%" + keyword + "%'))";
			
			if(!author.isEmpty() && !keyword.isEmpty())
				query += "AND (lower(b.main_author) LIKE lower('%" + author + "%'))";
			else if(!author.isEmpty() && keyword.isEmpty())
				query += "(lower(b.main_author) LIKE lower('%" + author + "%'))";
			
			if(!subject.isEmpty() && (!keyword.isEmpty() || !author.isEmpty()))
				query += "AND (b.call_number = s.call_number AND lower(s.subject) LIKE lower('%" + subject + "%'))";
			else if (!subject.isEmpty() && (keyword.isEmpty() && author.isEmpty()))
				query += "(b.call_number = s.call_number AND (lower(s.subject) LIKE lower('%" + subject + "%')))";
			
			query += ")";
			System.out.println(query);
			ResultSet result = st.executeQuery(query);
			Result showrs = new Result(result);
			session.loadResultPanel(showrs);
			con.commit();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Session getSession() {
		return session;
	}
	
}
