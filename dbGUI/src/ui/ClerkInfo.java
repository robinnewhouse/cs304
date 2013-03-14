package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClerkInfo {
	
	private JPanel labelPanel;
	private JPanel fieldPanel;
	private JPanel finalPanel;
	private JTextArea txt;
	private JButton button;
	private GridBagConstraints c;
	private static Font font = new Font("Times New Roman", Font.BOLD, 15);

	public ClerkInfo() {

	}
	
	public JPanel addBorrowerPanel(){
		
		//Labels
		Label labelBid = new Label("Bid: ");
		Label labelPassword = new Label("Password: ");
		Label labelName = new Label("Name: ");
		Label labelAddress = new Label("Address: ");
		Label labelPhone = new Label("Phone: ");
		Label labelEmail = new Label("Email: ");
		Label labelNumber = new Label("SIN or St#: ");
		Label labelExDate = new Label("Expiry Date: ");
		Label labelType = new Label("Type: ");
		
		//Fields
		JTextField fieldBid = new JTextField(12);
		JTextField fieldPassword = new JTextField(12);
		JTextField fieldName = new JTextField(12);
		JTextField fieldAddress = new JTextField(12);
		JTextField fieldPhone = new JTextField(12);
		JTextField fieldEmail = new JTextField(12);
		JTextField fieldNumber = new JTextField(12);
		JTextField fieldExDate = new JTextField(12);
		JTextField fieldType = new JTextField(12);
		
		//Set up fields with labels
		labelBid.setLabelFor(fieldBid);
		labelPassword.setLabelFor(fieldPassword);
		labelName.setLabelFor(fieldName);
		labelAddress.setLabelFor(fieldAddress);
		labelPhone.setLabelFor(fieldPhone);
		labelEmail.setLabelFor(fieldEmail);
		labelNumber.setLabelFor(fieldNumber);
		labelExDate.setLabelFor(fieldExDate);
		labelType.setLabelFor(fieldType);
		
		//Label Panel
		labelPanel = new JPanel(new GridLayout(9,1,0,18));
		labelPanel.add(labelBid);
		labelPanel.add(labelPassword);
		labelPanel.add(labelName);
		labelPanel.add(labelAddress);
		labelPanel.add(labelPhone);
		labelPanel.add(labelEmail);
		labelPanel.add(labelNumber);
		labelPanel.add(labelExDate);
		labelPanel.add(labelType);
				
		//Field Panel
		fieldPanel = new JPanel(new GridLayout(9,1,0,16));
		fieldPanel.add(fieldBid);
		fieldPanel.add(fieldPassword);
		fieldPanel.add(fieldName);
		fieldPanel.add(fieldAddress);
		fieldPanel.add(fieldPhone);
		fieldPanel.add(fieldEmail);
		fieldPanel.add(fieldNumber);
		fieldPanel.add(fieldExDate);
		fieldPanel.add(fieldType);
		fieldPanel.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
		
		//Add Borrower Button
		button = new JButton("Add Borrower");
		
		finalPanel = new JPanel();
		finalPanel.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		finalPanel.add(labelPanel, c);
		c.gridx = 1;
		c.gridy = 0;
		finalPanel.add(fieldPanel, c);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.insets = new Insets(20,0,0,0);
		finalPanel.add(button, c);
		
		return finalPanel;
	}
	
	public JPanel checkOutItemsPanel() {
		
		txt = new JTextArea();
		txt.setText("Please enter the SIN or Student Number of the Borrower " +
		"and enter the callnumbers they want to borrow, each callnumber followed by a ','");
		txt.setFont(font);
		txt.setLineWrap(true);
		txt.setWrapStyleWord(true);
		txt.setOpaque(false);
		txt.setMinimumSize(new Dimension(250, 20));
		txt.setForeground(Color.BLACK);
		
		//Labels
		Label labelNumber = new Label("SIN or ST#: ");
		Label labelCallNumbers = new Label("CallNumbers: ");
		
		//Fields
		JTextField fieldNumber = new JTextField(15);
		JTextField fieldCallNumbers = new JTextField(15);
		
		//Set up labels with fields
		labelNumber.setLabelFor(fieldNumber);
		labelCallNumbers.setLabelFor(fieldCallNumbers);
		
		//Label Panel
		labelPanel = new JPanel(new GridLayout(2,1,0,18));
		labelPanel.add(labelNumber);
		labelPanel.add(labelCallNumbers);
		
		//Field Panel
		fieldPanel = new JPanel(new GridLayout(2,1,0,16));
		fieldPanel.add(fieldNumber);
		fieldPanel.add(fieldCallNumbers);
		
		JPanel textPanel = new JPanel(new GridLayout(1,1,0,14));
		textPanel.add(txt);
		
		JPanel semiFinalPanel = new JPanel();
		semiFinalPanel.add(labelPanel);
		semiFinalPanel.add(fieldPanel);
		
		JPanel finalPanel = new JPanel(new GridLayout(2,1,0,8));
		finalPanel.add(textPanel, BorderLayout.CENTER);
		finalPanel.add(semiFinalPanel);
		
		return finalPanel;		
	}
	
	public JPanel returnsPanel() {
		
		//Labels
		Label labelReturn = new Label("Callnumber: ");
		
		//Fields
		JTextField fieldReturn = new JTextField(15);
		
		//Text
		txt = new JTextArea();
		txt.setText("Please enter the call number of the book to return and click return.");
		txt.setOpaque(false);
		txt.setLineWrap(true);
		txt.setFont(font);
		txt.setForeground(Color.black);
		txt.setWrapStyleWord(true);
		
		//Set up labels with fields
		labelReturn.setLabelFor(fieldReturn);
		
		//Label Panel
		labelPanel = new JPanel(new GridLayout(1,1,0,16));
		labelPanel.add(labelReturn);
		
		//Label Panel
		fieldPanel = new JPanel(new GridLayout(1,1,0,14));
		fieldPanel.add(fieldReturn);
		
		finalPanel = new JPanel();
		finalPanel.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		finalPanel.add(labelPanel, c);
		c.gridx = 1;
		c.gridy = 1;
		finalPanel.add(fieldPanel, c);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,20,0);
		finalPanel.add(txt, c);
		
		return finalPanel;		
	}
	
	public JPanel overduePanel() {
		
		//Text
		txt = new JTextArea();
		txt.setFont(font);
		txt.setForeground(Color.black);
		txt.setOpaque(false);
		txt.setLineWrap(true);
		txt.setWrapStyleWord(true);
		txt.setText("Please click 'Overdue Items' button to see all items that are overdue");
		txt.setPreferredSize(new Dimension(250,50));
		
		//Check overdue items button
		button = new JButton("Overdue Items");
		
		//Panel
		fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		fieldPanel.add(txt, c);
		c.gridy = 1;
		fieldPanel.add(button, c);
	
		return fieldPanel;
	}
	
	private class Label extends JLabel {
		
		private Label(String str) {
			super(str);
			setFont(font);
			setForeground(Color.black);
		}		
	}
}
