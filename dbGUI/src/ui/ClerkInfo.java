package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClerkInfo {
	
	private JPanel labelPanel;
	private JPanel fieldPanel;
	private static Font font = new Font("Times New Roman", Font.BOLD, 15);

	public ClerkInfo() {

	}
	
	public JPanel addBorrowerPanel(){
		
		//Labels
		JLabel labelBid = new JLabel("Bid: ");
		labelBid.setFont(font);
		labelBid.setForeground(Color.black);
		JLabel labelPassword = new JLabel("Password: ");
		labelPassword.setFont(font);
		labelPassword.setForeground(Color.black);
		JLabel labelName = new JLabel("Name: ");
		labelName.setFont(font);
		labelName.setForeground(Color.black);
		JLabel labelAddress = new JLabel("Address: ");
		labelAddress.setFont(font);
		labelAddress.setForeground(Color.black);
		JLabel labelPhone = new JLabel("Phone: ");
		labelPhone.setFont(font);
		labelPhone.setForeground(Color.black);
		JLabel labelEmail = new JLabel("Email: ");
		labelEmail.setFont(font);
		labelEmail.setForeground(Color.black);
		JLabel labelNumber = new JLabel("SIN or St#: ");
		labelNumber.setFont(font);
		labelNumber.setForeground(Color.black);
		JLabel labelExDate = new JLabel("Expiry Date: ");
		labelExDate.setFont(font);
		labelExDate.setForeground(Color.black);
		JLabel labelType = new JLabel("Type: ");
		labelType.setFont(font);
		labelType.setForeground(Color.black);
		
		//Fields
		JTextField fieldBid = new JTextField(14);
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
		
		JPanel finalPanel = new JPanel();
		finalPanel.add(labelPanel, BorderLayout.WEST);
		finalPanel.add(fieldPanel, BorderLayout.CENTER);
		
		return finalPanel;
	}
	
	public JPanel checkOutItemsPanel() {
		
		JTextArea message = new JTextArea();
		message.setText("Please enter the SIN or Student Number of the Borrower " +
		"and enter the callnumbers they want to borrow, each callnumber followed by a ','");
		message.setFont(font);
		message.setLineWrap(true);
		message.setWrapStyleWord(true);
		message.setOpaque(false);
		message.setMinimumSize(new Dimension(250, 20));
		message.setForeground(Color.BLACK);
		
		//Labels
		JLabel labelNumber = new JLabel("SIN or ST#: ");
		labelNumber.setFont(font);
		labelNumber.setForeground(Color.black);
		JLabel labelCallNumbers = new JLabel("CallNumbers: ");
		labelCallNumbers.setFont(font);
		labelCallNumbers.setForeground(Color.black);
		
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
		textPanel.add(message);
		
		JPanel semiFinalPanel = new JPanel();
		semiFinalPanel.add(labelPanel);
		semiFinalPanel.add(fieldPanel);
		
		JPanel finalPanel = new JPanel(new GridLayout(2,1,0,8));
		finalPanel.add(textPanel, BorderLayout.CENTER);
		finalPanel.add(semiFinalPanel);
		
		return finalPanel;		
	}
	
	public JPanel returnsPanel() {
		return null;		
	}
	
	public JPanel overduePanel() {
		return null;		
	}
}
