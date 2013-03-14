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
		JPanel[] panels = borrowerInfo.searchPanel();
		
		//Search Button
		JButton searchButton = new JButton("Search");
		
		this.add(panels[0], BorderLayout.CENTER);
		this.add(panels[1], BorderLayout.LINE_END);
		this.add(searchButton, BorderLayout.PAGE_END);
	}
	
	public void holdPanel() {
		borrowerInfo = new BorrowerInfo();
		JPanel panel = borrowerInfo.holdPanel();
		
		//Hold Button
		JButton holdButton = new JButton("Place Hold Request");
		
		this.add(panel, BorderLayout.CENTER);
		this.add(holdButton, BorderLayout.PAGE_END);
	}
	
	public void accountPanel() {
		borrowerInfo = new BorrowerInfo();
		
		//Check Account Button
		JButton checkAccountButton = new JButton("Check Account");
		
		this.add(borrowerInfo.accountPanel(), BorderLayout.CENTER);
		this.add(checkAccountButton, BorderLayout.PAGE_END);
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

	private void overduePanel() {
		panel = new JPanel();		
	}

	private void returnsPanel() {
		panel = new JPanel();		
	}

	private void checkOutItemsPanel() {
		panel = new JPanel();		
	}

	private void addBorrowerPanel() {
		panel = new JPanel();		
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
