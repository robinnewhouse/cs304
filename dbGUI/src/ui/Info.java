package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Info extends JPanel {
	
	protected JPanel panel;
	BorrowerInfo borrowerInfo;
	ClerkInfo clerkInfo;
	
	public Info (String string) {
		setPanelSpecs();
		
		switch (string) {
		//BorrowerInfo Panels
		case "Search": { 
			searchPanel();
			break;
		}
		case "Hold": {
			holdPanel();
			break;
		}
		case "Account": {
			accountPanel();
			break;
		}
		case "Fine": {
			finePanel();
			break;
		}
		//ClerkInfo Panels
		case "Add Borrower": {
			addBorrowerPanel();
			break;
		}
		case "Check Out": {
			checkOutItemsPanel();
			break;
		}
		case "Returns": { 
			returnsPanel();
			break;
		}
		case "Overdue": {
			overduePanel();
			break;
		}
		//LibrarianInfo Panels
		case "Add Book": {
			addBookPanel();
			break;
		}
		case "Book Report": {
			bookReportPanel();
			break;
		}
		case "Popular Items Report": {
			popularReportPanel();
			break;
		}
		default: { searchPanel(); }
		}
	}
	
	public void searchPanel() {
		borrowerInfo = new BorrowerInfo();
		this.add(borrowerInfo.searchPanel());
	}
	
	public void holdPanel() {
		borrowerInfo = new BorrowerInfo();
		this.add(borrowerInfo.holdPanel(), BorderLayout.CENTER);
	}
	
	public void accountPanel() {
		borrowerInfo = new BorrowerInfo();
		this.add(borrowerInfo.accountPanel(), BorderLayout.CENTER);
	}
	
	public void finePanel() {
		borrowerInfo = new BorrowerInfo();
		this.add(borrowerInfo.finePanel(), BorderLayout.CENTER);
	}
	
	private void popularReportPanel() {
		panel = new JPanel();
	}

	private void bookReportPanel() {
		panel = new JPanel();
	}

	private void addBookPanel() {
		panel = new JPanel();
	}
	
	private void addBorrowerPanel() {
		clerkInfo = new ClerkInfo();
		this.add(clerkInfo.addBorrowerPanel(), BorderLayout.CENTER);
	}

	private void returnsPanel() {
		clerkInfo = new ClerkInfo();
		this.add(clerkInfo.returnsPanel(), BorderLayout.CENTER);
	}

	private void checkOutItemsPanel() {
		clerkInfo = new ClerkInfo();
		this.add(clerkInfo.checkOutItemsPanel(), BorderLayout.CENTER);
	}
	
	private void overduePanel() {
		clerkInfo = new ClerkInfo();
		this.add(clerkInfo.overduePanel(), BorderLayout.CENTER);
	}

	

	protected void setPanelSpecs() {
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		this.setMaximumSize(new Dimension(300, getHeight()));
		this.setMinimumSize(new Dimension(300, getHeight()));
		this.setPreferredSize(new Dimension(300, getHeight()));		
		this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
	}
	
	public JPanel getPanel() {
		return this;
	}

}
