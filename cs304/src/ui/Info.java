package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import database.DataBaseConnection;

public class Info extends JPanel {

	protected JPanel panel;
	BorrowerInfo borrowerInfo;
	ClerkInfo clerkInfo;
	LibrarianInfo librarianInfo;
	DataBaseConnection db;

	public Info (String string, DataBaseConnection db) {
		this.db = db;
		setPanelSpecs();
		
		if (string.contains("Login"))
			loginPanel();
		else if (string.contains("Search"))
			searchPanel();
		else if (string.contains("Hold"))
			holdPanel();
		else if (string.contains("Account"))
			accountPanel();
		else if (string.contains("Fine"))
			finePanel();
		else if (string.contains("Add Borrower"))
			addBorrowerPanel();
		else if (string.contains("Check Out"))
			checkOutItemsPanel();
		else if (string.contains("Returns"))
			returnsPanel();
		else if (string.contains("Overdue"))
			overduePanel();
		else if (string.contains("Add Book"))
			addBookPanel();
		else if (string.contains("Book Report"))
				bookReportPanel();
		else if (string.contains("Popular Items Report"))
					popularReportPanel();
	}

	public void loginPanel() {
		borrowerInfo = new BorrowerInfo(db);
		this.add(borrowerInfo.loginPanel());
	}
	
	public void searchPanel() {
		borrowerInfo = new BorrowerInfo(db);
		this.add(borrowerInfo.searchPanel());
	}

	public void holdPanel() {
		borrowerInfo = new BorrowerInfo(db);
		this.add(borrowerInfo.holdPanel(), BorderLayout.CENTER);
	}

	public void accountPanel() {
		borrowerInfo = new BorrowerInfo(db);
		this.add(borrowerInfo.accountPanel(), BorderLayout.CENTER);
	}

	public void finePanel() {
		borrowerInfo = new BorrowerInfo(db);
		this.add(borrowerInfo.finePanel(), BorderLayout.CENTER);
	}

	private void addBookPanel() {
		librarianInfo = new LibrarianInfo(db);
		this.add(librarianInfo.addBookPanel(), BorderLayout.CENTER);
	}


	private void bookReportPanel() {
		librarianInfo = new LibrarianInfo(db);
		this.add(librarianInfo.bookReportPanel(), BorderLayout.CENTER);
	}

	private void popularReportPanel() {
		librarianInfo = new LibrarianInfo(db);
		this.add(librarianInfo.popularReportPanel(), BorderLayout.CENTER);
	}

	private void addBorrowerPanel() {
		clerkInfo = new ClerkInfo(db);
		this.add(clerkInfo.addBorrowerPanel(), BorderLayout.CENTER);
	}

	private void returnsPanel() {
		clerkInfo = new ClerkInfo(db);
		this.add(clerkInfo.returnsPanel(), BorderLayout.CENTER);
	}

	private void checkOutItemsPanel() {
		clerkInfo = new ClerkInfo(db);
		this.add(clerkInfo.checkOutItemsPanel(), BorderLayout.CENTER);
	}

	private void overduePanel() {
		clerkInfo = new ClerkInfo(db);
		this.add(clerkInfo.overduePanel(), BorderLayout.CENTER);
	}

	protected void setPanelSpecs() {
		this.setPreferredSize(new Dimension(300, getHeight()));		
		this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
		this.setBackground(new Color(59,67,103));
	}

	public JPanel getPanel() {
		return this;
	}

}
