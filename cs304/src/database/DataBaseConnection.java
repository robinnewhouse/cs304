package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

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
	private String globalbID = null;

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
		//"ora_e2n7", "a36106094"
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
			String query = "SELECT * FROM book WHERE call_number = '" + varargs[0] + "'";
			ps = con.prepareStatement(query);
			ResultSet result = ps.executeQuery();
			boolean bookExists = false;
			if(result.next())
				bookExists = true;
			
			// Don't insert book if already exists in book table
			if(bookExists == false){
				query = "INSERT INTO book VALUES (?,?,?,?,?,?)";
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertCopy(String callNumber){	
		PreparedStatement ps;
		try{
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
			ps.setString(2, "c" + String.valueOf(copyNumber));
			ps.setString(3, "in");
			rowCount += ps.executeUpdate();

			if(rowCount == 1)
				JOptionPane.showMessageDialog(null, "Added entry to Book_Copy table");
			else if(rowCount > 1)
				JOptionPane.showMessageDialog(null, "Added " + rowCount + " entries to Book_Copy table");
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
				JOptionPane.showMessageDialog(null,"Successfully added " + rowCount + "values to Has_Author table");
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
			ps = con.prepareStatement("SELECT * FROM borrower WHERE sinOrStNo = " + varargs[6]);
			ResultSet result = ps.executeQuery();
			boolean borrowerExists = false;
			if(result.next()){
				borrowerExists = true;
				JOptionPane.showMessageDialog(null, "Borrower with sinOrStNo " + varargs[6] + " already exists");
			}
			
			if(borrowerExists == false){
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
				result = ps.executeQuery();
				result.next();
				int bid = 0;
				bid = result.getInt(1);
				if(bid != 0)
					JOptionPane.showMessageDialog(null,"Borrower's BID is: " + bid);
				
				//Commit changes
				con.commit();
	
				//Close prepared statement
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void checkOverdueItems() {
		Calendar calendar = Calendar.getInstance();
		long today = calendar.getTimeInMillis();
		Date todaySQL = new Date(today);
		System.out.println(todaySQL);
		try {
			Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet rs;
			rs = stm.executeQuery("SELECT bid, call_number FROM borrowing WHERE inDate < to_date('"+todaySQL+"','yyyy-mm-dd')");
			Result r = new Result(rs);
			session.loadResultPanel(r);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void overdueEmails(String borrowers) {
		Calendar calendar = Calendar.getInstance();
		long today = calendar.getTimeInMillis();
		Date todaySQL = new Date(today);
		try {
			String query = "SELECT bid, emailAddress FROM borrower WHERE bid IN ";

			if(borrowers.equals("()"))
				query +="(SELECT bid FROM borrowing WHERE inDate < '" + todaySQL + "')";
			else
				query += borrowers;

			Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet rs;
			rs = stm.executeQuery(query);

			if(rs.next())
				JOptionPane.showMessageDialog(null, "Sent automated emails to the following borrowers:");
			else
				JOptionPane.showMessageDialog(null, "No borrowers with those ids.  No emails sent");
			Result r = new Result(rs);
			session.loadResultPanel(r);

			//Close statement
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Place a hold request for a book that is out. When the item is returned,
	 * the system sends an email to the borrower and informs the library clerk
	 * to keep the book out of the shelves.
	 */	
	public void insertHold(String callNumStr) {

		String bIDstr = null;

		if (null == globalbID){
			JOptionPane.showMessageDialog(null, "Please log in first");
			return;
		}else{
			bIDstr = globalbID;
		}

		PreparedStatement ps;
		try{

			// get issueDate
			Calendar calendar = Calendar.getInstance();
			long jDate = calendar.getTimeInMillis();
			Date issueDate = new Date(jDate);

			String query1 = "SELECT copy_no FROM book_copy " +
					"WHERE call_number = '" + callNumStr + "'";
			ps = con.prepareStatement(query1);
			ResultSet result = ps.executeQuery();
			boolean copyExists = false;
			if(result.next()){
				copyExists = true;
			}
			if(false == copyExists){ // If book doesn't exist
				JOptionPane.showMessageDialog(null, "Call number does not exist");
				return;
			}

			
			String query2 = "SELECT hid FROM hold_request " +
					"WHERE call_number = '" + callNumStr + "'" +
							"AND bid = '" + bIDstr +"'";
			ps = con.prepareStatement(query2);
			ResultSet result2 = ps.executeQuery();
			boolean isHeld = false;
			if(result2.next()){
				isHeld = true;
			} 
				if(true == isHeld){ // If hold already exists
				JOptionPane.showMessageDialog(null, "Hold already exists for bid call_number combination");
				return;
			}
			// if others pass
			String query3 = "INSERT INTO hold_request (hid,bid,call_number,issuedDate) " +
					"VALUES (hid_counter.nextval,?,?,?)";
			ps = con.prepareStatement(query3);
			ps.setString(1, bIDstr);
			ps.setString(2, callNumStr);
			ps.setDate(3, issueDate);
			int rs2 = ps.executeUpdate();
			if(rs2 > 0)
				JOptionPane.showMessageDialog(null, "Added " + rs2 + " rows to Hold table");

			con.commit();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Inserting tuple in borrowing didn't work during iteration ");
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

				//Close statement
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
		try {
			Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet rs;

			rs = stm.executeQuery("select * from (select call_number, count(call_number) as times_rented from borrowing " +
					"where outDate like '%" + year + "%' group by call_number) where rownum <= " +
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
		int first = 0;
		int bid = Integer.parseInt(cardnum.trim());

		for (int i = 0; i < callnums.length; i++) {
			PreparedStatement ps;
			ResultSet result;
			try {
				boolean copyIn = true;
				// Get copy number of an available copy
				String query = "SELECT copy_no FROM book_copy " +
						"WHERE call_number = '" + callnums[i] + "' AND status = 'in'";
				ps = con.prepareStatement(query);
				result = ps.executeQuery();
				String copy = "";
				if(result.next()){
					copy = result.getString(1);
				}
				if(copy.isEmpty()){ // If all copies out
					JOptionPane.showMessageDialog(null, "No copies are available");
					copyIn = false;
				}

				if(copyIn == true){
					// Get borrower's type
					query = "SELECT type FROM borrower WHERE bid = " + bid;
					ps = con.prepareStatement(query);
					result = ps.executeQuery();
					String type = "";
					if(result.next()){
						type = result.getString(1);
					}
					if(!type.isEmpty())
					{

						// Get borrower's loan time limit
						query = "SELECT book_time_limit FROM borrower_type WHERE type = '" + type + "'";
						System.out.println(query);
						ps = con.prepareStatement(query);
						result = ps.executeQuery();
						int weeks = 0;

						// Changed to if/else statements for 1.6 Compatibility
						if(type.contentEquals("student"))
							weeks = 2;
						else if (type.contentEquals("faculty"))
							weeks = 12;
						else if (type.contentEquals("staff"))
							weeks = 6;
						System.out.println(weeks);

						// Create checkout date and due date according to borrower type
						Calendar calendar = Calendar.getInstance();
						long jDate = calendar.getTimeInMillis();
						Date outDate = new Date(jDate);
						calendar.add(Calendar.DAY_OF_YEAR, weeks*7);
						jDate = calendar.getTimeInMillis();
						Date dueDate = new Date(jDate);

						// Checkout copy if there is an available copy
						query = "INSERT INTO borrowing (borid,bid,call_number,copy_no,outDate,inDate) VALUES (borid_counter.nextval,?,?,?,?,?)";
						ps = con.prepareStatement(query);
						ps.setInt(1, bid);
						ps.setString(2, callnums[i]);
						ps.setString(3, copy);
						ps.setDate(4, outDate);
						ps.setDate(5, dueDate);
						int rs2 = ps.executeUpdate();
						if(rs2 > 0)
							JOptionPane.showMessageDialog(null, "Added " + rs2 + " rows to Borrowing table");

						if(i == 0){ // For first book
							query = "SELECT borid_counter.currval FROM dual";
							ps = con.prepareStatement(query);
							result = ps.executeQuery();
							if(result.next())
								first = result.getInt(1);
						}

						// Update book copy status to out
						query = "UPDATE book_copy SET status = 'out' WHERE copy_no = '" + copy + "'";
						ps.execute(query);

						// Check to see if there is a hold request on this copy from this borrower
						query = "SELECT * FROM hold_request WHERE call_number = '"+ callnums[i] + "' and bid = '" + bid + "'";
						result = ps.executeQuery(query);
						if (result.next()) {
							System.out.println("Did BID placed a hold request on this book.");
							//Delete the hold request
							Statement st4 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
							int rs4 = st4.executeUpdate("DELETE FROM hold_request WHERE call_number = '" + callnums[i] + "' and bid = '" + bid + "'");

							if (rs4 > 0) {
								JOptionPane.showMessageDialog(null, "You placed a hold request on " + callnums[i] + ". It is now deleted.");
							} 
							con.commit();
						}
					}
					else 
						JOptionPane.showMessageDialog(null, "BID does not exist. Please try again");
				}

				con.commit();
				ps.close();
			} catch (SQLException e) {
				System.out.println("Inserting tuple in borrowing didn't work during iteration ");
				e.printStackTrace();
			}
		}

		// Display all books checked out this session
		if(first != 0){
			try{
				Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				String query = "SELECT c.call_number, c.copy_no, b.title, r.inDate " +

							   "FROM book b, book_copy c, borrowing r " +
							   "WHERE ( (r.call_number = c.call_number) AND (r.copy_no = c.copy_no) AND " +
							   "(b.call_number = c.call_number AND r.borid >=" + first + "))";
				ResultSet result = st.executeQuery(query);
				Result showrs = new Result(result);
				session.loadResultPanel(showrs);
				con.commit();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	/**
	 * Processes a return. When  an item is returned, the clerk records the return by providing the item's
	 * catalogue number. The system determines the borrower who had borrowed the item and records that the
	 * the item is "in".  If the item is overdue, a fine is assessed for the borrower.  If there is a hold
	 * request for this item by another borrower, the item is registered as "on hold" and a message is send
	 * to the borrower who made the hold request.
	 */
	public void processReturn(String[] callnum) {
		if (callnum.length != 2) {
			JOptionPane.showMessageDialog(null, "Please add the callnumber as well as the copy number at the same time");
		}
		else {
			try {
				Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = st.executeQuery("SELECT * FROM book_copy WHERE call_number = '" + callnum[0] + "' and copy_no = '" 
						+ callnum[1] +"' and status = 'out'");
				con.commit();

				if (rs.next()) { // if the above query returns a valid tuple
					Statement st2 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					int rs2 = st2.executeUpdate("UPDATE book_copy SET status = 'in' WHERE call_number = '" + callnum[0] + "' and copy_no = '" + callnum[1] +"'");

					String notifyClerk;
					if (rs2 > 0) {
						notifyClerk = "Processed return for book: " + callnum[0] + " copy no: " + callnum[1];
					} else {
						notifyClerk = "Return did not process successfully";
					}

					JOptionPane.showMessageDialog(null, notifyClerk);

					// Assess fine
					Calendar calendar = Calendar.getInstance();
					long today = calendar.getTimeInMillis();
					Date returnDate = new Date(today);

					Statement st3 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					ResultSet rs3 = st3.executeQuery("select borid, inDate from (select * from borrowing where call_number = '" 
							+ callnum[0] + "' and copy_no = '" + callnum[1] + "' order by borid desc) where rownum <= 1");
					if (rs3.next()) {
						Date dueDate = rs3.getDate(2);
						if (returnDate.after(dueDate)) {
							// fine this person
							JOptionPane.showMessageDialog(null, "You will get fined");
							PreparedStatement ps = con.prepareStatement("Insert into FINE values (fine_counter.nextval,20,?,null,?)");
							ps.setDate(1, returnDate);
							ps.setInt(2, rs3.getInt(1));
							int rowsUpdated = ps.executeUpdate();
							System.out.println(rowsUpdated);
						}
					}
					// TODO check for hold on the book
					Statement st4 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					ResultSet rs4 = st4.executeQuery("SELECT * FROM hold_request WHERE call_number = '" + callnum[0] + "'");
					//Result r3 = new Result(rs3);

					//session.loadResultPanel(r3);
					con.commit();

					if (rs4.next()) {
						JOptionPane.showMessageDialog(null, "There is a hold request on this book, sending an email to the person who placed the request");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Book is already checked in or not a valid copy no/call no");
				}
				// Close statement
				st.close();	
			} catch (SQLException e) {
				System.out.println("Processing return failed");
				e.printStackTrace();
			}
		}
	}


	/**
	 * Search for books using keyword search on titles, authors and subjects. The result is a list of books
	 * that match the search together with the number of copies that are in and out.
	 * @param keyword
	 * @param author
	 * @param subject
	 */

	public void searchForItem(String keyword, String author, String subject){
		try {
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT b.call_number, c.copy_no, b.isbn, b.title, b.main_author, b.publisher, b.year, c.status " +
					"FROM book b, book_copy c " +
					"WHERE b.call_number = c.call_number AND b.call_number IN " +
					"(SELECT b.call_number FROM book b, has_subject s WHERE ";

			if(!keyword.isEmpty())
				query += "(lower(b.title) LIKE lower('%" + keyword + "%')) ";

			/* Can't get it to also search additional authors*/
			if(!author.isEmpty() && !keyword.isEmpty())
				query += "AND (lower(b.main_author) LIKE lower('%" + author + "%')) " ;
			//"(b.call_number = s.call_number AND lower(a.name) LIKE lower('%" + author + "%')))";
			else if(!author.isEmpty() && keyword.isEmpty())
				query += "(lower(b.main_author) LIKE lower('%" + author + "%')) ";
			//"(b.call_number = a.call_number AND lower(a.name) LIKE lower('%" + author + "%'))) ";

			if(!subject.isEmpty() && (!keyword.isEmpty() || !author.isEmpty()))
				query += "AND (b.call_number = s.call_number AND lower(s.subject) LIKE lower('%" + subject + "%'))";
			else if (!subject.isEmpty() && (keyword.isEmpty() && author.isEmpty()))
				query += "(b.call_number = s.call_number AND (lower(s.subject) LIKE lower('%" + subject + "%')))";
			query += ")";

			System.out.println(query);
			ResultSet result = st.executeQuery(query);
			Result showrs = new Result(result);
			if(result.next())
				System.out.println("JFKLDSJ:FKSL:JSDF");
			session.loadResultPanel(showrs);
			con.commit();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check borrowers account. The system will display the items the borrower has
	 * currently borrowed and not yet returned, any outstanding fines and the
	 * hold requests that have been placed by the borrower.
	 */	
	public void checkAccount() {

		String bIDstr = null;

		if (null == globalbID){
			JOptionPane.showMessageDialog(null, "Please log in first");
			return;
		}else{
			bIDstr = globalbID;
		}


		try {
			Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			Calendar calendar = Calendar.getInstance();
			long today = calendar.getTimeInMillis();
			Date todaySQL = new Date(today);
			ResultSet rs;

			String bookQuery = " SELECT 'out' type, bing.call_number, bk.title, 0 amount " +
					" FROM book bk, borrowing bing, fine f " +
					" WHERE bing.bid = " + bIDstr + 
					" AND bk.call_number = bing.call_number AND bing.inDate < to_date('"+todaySQL+"','yyyy-mm-dd') "+ 
					// add part that restricts listing only once if there is a
					// fine and is borrowed
					" UNION " +
					" SELECT  'fine' type, bing.call_number, bk.title, f.amount " +
					" FROM fine f, borrowing bing, book bk" +
					" WHERE f.borid = bing.borid AND bing.call_number = bk.call_number AND bing.bid = "  + bIDstr + 
					" AND f.paidDate is NULL " +
					" UNION " +
					" SELECT  'hold' type, bk.call_number, bk.title, 0 amount " +
					" FROM hold_request h, book bk " +
					" where h.call_number = bk.call_number AND h.bid = " + bIDstr;

			rs = stm.executeQuery(bookQuery);
			Result r = new Result(rs);
			session.loadResultPanel(r);
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param username
	 * @param password
	 */
	public void payFine(String paymentString){
		String bIDstr = null;

		if (null == globalbID){
			JOptionPane.showMessageDialog(null, "Please log in first");
			return;
		}else{
			bIDstr = globalbID;
		}
		
		Integer paymentInt = null;
		try { 
			paymentInt = Integer.parseInt(paymentString); 
		} catch(NumberFormatException e) { 
			JOptionPane.showMessageDialog(null, "Fine must be an integer");
			return; 
		}
		if (paymentInt == null){
			JOptionPane.showMessageDialog(null, "Fine must be an integer");
			return;
		}

		// get issueDate
		Calendar calendar = Calendar.getInstance();
		long jDate = calendar.getTimeInMillis();
		java.sql.Date dateNow = new java.sql.Date(jDate);

		Integer rowCount = 0;

		System.out.println("fineint: " + paymentInt.toString());

		try {
			Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			ResultSet rs;

			String fineQuery = " SELECT * " +
					" FROM fine f, borrowing bing, book bk" +
					" WHERE f.borid = bing.borid AND bing.call_number = bk.call_number AND bing.bid = "  + bIDstr + 
					" AND f.paidDate is NULL ";

			PreparedStatement ps;

			rs = stm.executeQuery(fineQuery);
			while (rs.next()){
				Integer fineAmount = rs.getInt("amount");
				Integer fineId = rs.getInt("fid");
				Date issuedDate = rs.getDate("issuedDate");
				if (fineAmount < paymentInt){
					paymentInt = paymentInt - fineAmount;
					System.out.println("dateNow: " + dateNow);
					System.out.println("issuedDate: " +  issuedDate);

					String updateQuery = " UPDATE fine " +
							" SET paidDate = " + dateNow +
							" WHERE fid = " + fineId.toString();

					String query = "UPDATE fine SET paidDate = ? where fid = ? ";
					ps = con.prepareStatement(query);

					ps.setDate(1, dateNow);
					ps.setInt(2, fineId);
					rowCount += ps.executeUpdate();
					System.out.println(rowCount);

				}
				else{ 
					checkFines();
					break;
				}

			}

			if(rowCount > 0)
			{
				JOptionPane.showMessageDialog(null,"Successfully paid " + rowCount + "fines");
			}


			//			rs.first();
			//			Result r = new Result(rs);
			//			rs.getInt("amount");
			//			Result r = new Result(rs);
			//			session.loadResultPanel(r);
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

	public void checkFines() {
		String bIDstr = null;

		if (null == globalbID){
			JOptionPane.showMessageDialog(null, "Please log in first");
			return;
		}else{
			bIDstr = globalbID;
		}


		try {
			Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			ResultSet rs;

			String fineQuery = " SELECT bing.call_number, bk.title, f.amount, f.issueddate, f.paiddate " +
					" FROM fine f, borrowing bing, book bk" +
					" WHERE f.borid = bing.borid AND bing.call_number = bk.call_number AND bing.bid = "  + bIDstr + 
					" AND f.paidDate is NULL ";

			rs = stm.executeQuery(fineQuery);
			Result r = new Result(rs);
			session.loadResultPanel(r);
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void login(String username, String password) {

		Integer usernameInt = null;
		try { 
			usernameInt = Integer.parseInt(username); 
		} catch(NumberFormatException e) { 
			JOptionPane.showMessageDialog(null, "SIN or Student Number must be an integer");
			return; 
		}
		if (usernameInt == null){
			JOptionPane.showMessageDialog(null, "SIN or Student Number must be an integer");
			return;
		}

		String sqlPassword = "\'" + password + "\'";
		System.out.println(sqlPassword);
		try {

			Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			ResultSet rs;
			String bID = null;

			String userQuery = " SELECT bid " +
					" FROM borrower " +
					" WHERE sinOrStNo = " + usernameInt.toString() + 
					" AND password = " + sqlPassword ;

			rs = stm.executeQuery(userQuery);
			if(rs!=null && rs.next()){
				bID=rs.getString("bid");
				globalbID  = bID;
				JOptionPane.showMessageDialog(null, "Logged in. borrower ID = " + bID);

			}else{
				JOptionPane.showMessageDialog(null, "Username/password combination does not exist");
			}
			stm.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void logout() {
		JOptionPane.showMessageDialog(null, "Logged out");
		//TODO clear results on logout
		globalbID = null;
	}

	public Session getSession() {
		return session;
	}
}
