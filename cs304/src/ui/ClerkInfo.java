package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import database.DataBaseConnection;

public class ClerkInfo {

	private JPanel labelPanel;
	private JPanel fieldPanel;
	private JPanel finalPanel;
	private JTextArea txt;
	private JButton button;
	private GridBagConstraints c;
	private Color txtBackColor = Color.white;
	private Color panelBackColor = new Color(59,67,103);
	private static Font font = new Font("Times New Roman", Font.BOLD, 16);
	private DataBaseConnection db;
	private JTextField[] fields;
	
	public ClerkInfo(DataBaseConnection db) {
		this.db = db;
	}

	public JPanel addBorrowerPanel(){

		//Labels
		Label labelName = new Label("Name: ");
		Label labelPassword = new Label("Password: ");
		Label labelAddress = new Label("Address: ");
		Label labelPhone = new Label("Phone: ");
		Label labelEmail = new Label("Email: ");
		Label labelNumber = new Label("SIN or St#: ");
		Label labelExDate = new Label("Expiry Date: ");
		Label labelType = new Label("Type: ");

		//Fields
		final JTextField fieldName = new JTextField(14);
		final JTextField fieldPassword = new JTextField(14);
		final JTextField fieldAddress = new JTextField(14);
		final JTextField fieldPhone = new JTextField(14);
		final JTextField fieldEmail = new JTextField(14);
		final JTextField fieldNumber = new JTextField(14);
		final JTextField fieldExDate = new JTextField(14);
		final JTextField fieldType = new JTextField(14);
		
		//Add fields to an array to check validate input
		final JTextField[] fields = new JTextField[8];
		fields[0] = fieldName;
		fields[1] = fieldPassword;
		fields[2] = fieldAddress;
		fields[3] = fieldPhone;
		fields[4] = fieldEmail;
		fields[5] = fieldNumber;
		fields[6] = fieldExDate;
		fields[7] = fieldType;

		//Set up fields with labels
		labelName.setLabelFor(fieldName);
		labelPassword.setLabelFor(fieldPassword);
		labelAddress.setLabelFor(fieldAddress);
		labelPhone.setLabelFor(fieldPhone);
		labelEmail.setLabelFor(fieldEmail);
		labelNumber.setLabelFor(fieldNumber);
		labelExDate.setLabelFor(fieldExDate);
		labelType.setLabelFor(fieldType);

		//Label Panel
		labelPanel = new JPanel(new GridLayout(9,1,0,25));
		labelPanel.add(labelName);
		labelPanel.add(labelPassword);
		labelPanel.add(labelAddress);
		labelPanel.add(labelPhone);
		labelPanel.add(labelEmail);
		labelPanel.add(labelNumber);
		labelPanel.add(labelExDate);
		labelPanel.add(labelType);
		labelPanel.setBackground(panelBackColor);

		//Field Panel
		fieldPanel = new JPanel(new GridLayout(9,1,0,16));
		fieldPanel.add(fieldName);
		fieldPanel.add(fieldPassword);
		fieldPanel.add(fieldAddress);
		fieldPanel.add(fieldPhone);
		fieldPanel.add(fieldEmail);
		fieldPanel.add(fieldNumber);
		fieldPanel.add(fieldExDate);
		fieldPanel.add(fieldType);
		fieldPanel.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
		fieldPanel.setBackground(panelBackColor);

		//Add Borrower Button
		button = new JButton("Add Borrower");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				boolean fieldsFilledOut = true;
				//Iterate through each field to make sure something is
				//in each field
				for(int i = 0; i < fields.length; i++)
				{
					if(fields[i].getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Please fill out all fields.");
						fieldsFilledOut = false;
						break;
					}
				}
				if(fieldsFilledOut) 
				{
					db.insertBorrower(fieldName.getText(), fieldPassword.getText(), fieldAddress.getText(), 
							fieldPhone.getText(), fieldEmail.getText(),fieldNumber.getText(), fieldExDate.getText(), 
							fieldType.getText());
					
					//Empty all fields
					for(int i = 0; i < fields.length; i++)
					{
						fields[i].setText("");
					}
				}
			}
		});

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
		finalPanel.setBackground(panelBackColor);

		return finalPanel;
	}

	public JPanel checkOutItemsPanel() {

		txt = new Text();
		txt.setText("Please enter the SIN or Student Number of the Borrower " +
		"and enter the callnumbers they want to borrow, each callnumber followed by a ','");
		txt.setPreferredSize(new Dimension(250,110));

		//Labels
		Label labelNumber = new Label("BID: ");
		Label labelCallNumbers = new Label("CallNumbers: ");

		//Fields
		final JTextField fieldNumber = new JTextField(15);
		final JTextField fieldCallNumbers = new JTextField(15);
		
		fields = new JTextField[2];
		fields[0] = fieldNumber;
		fields[1] = fieldCallNumbers;

		//Set up labels with fields
		labelNumber.setLabelFor(fieldNumber);
		labelCallNumbers.setLabelFor(fieldCallNumbers);

		//Check Out Button
		button = new JButton("Check Out Items");
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Check to make sure fields are filled out
				if(fieldNumber.getText().isEmpty() || fieldCallNumbers.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Please fill out each field");
				//pass card number and call number values
				String callnum = fieldCallNumbers.getText();
				String[] callnums = callnum.split(", ");
				db.checkOutItems(fieldNumber.getText(), callnums);
				
				//Empty all fields
				for(int i = 0; i < fields.length; i++)
					fields[i].setText("");
			}
		});

		//Label Panel
		labelPanel = new JPanel(new GridLayout(2,1,0,19));
		labelPanel.add(labelNumber);
		labelPanel.add(labelCallNumbers);
		labelPanel.setBackground(panelBackColor);

		//Field Panel
		fieldPanel = new JPanel(new GridLayout(2,1,0,16));
		fieldPanel.add(fieldNumber);
		fieldPanel.add(fieldCallNumbers);
		fieldPanel.setBackground(panelBackColor);

		finalPanel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.insets = new Insets(0,0,20,0);
		finalPanel.add(txt, c);
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		finalPanel.add(labelPanel, c);
		c.gridx = 1;
		c.gridy = 1;
		finalPanel.add(fieldPanel, c);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		finalPanel.add(button, c);
		finalPanel.setBackground(panelBackColor);

		return finalPanel;		
	}

	public JPanel returnsPanel() {

		//Labels
		Label labelReturn = new Label("Callnumber: ");

		//Fields
		final JTextField fieldReturn = new JTextField(15);

		//Text
		txt = new Text();
		txt.setText("Please enter the call number of the book to return, followed" +
				" by copy number and click return.");
		txt.setPreferredSize(new Dimension(250,70));

		//Set up labels with fields
		labelReturn.setLabelFor(fieldReturn);

		//Return Button
		button = new JButton("Return");
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//pass card number and call number values
				String callnum = fieldReturn.getText();
				callnum = callnum.replaceAll(" ", "");
				String[] callnums = callnum.split(",");
				db.processReturn(callnums);
			}
		});
		
		//Label Panel
		labelPanel = new JPanel(new GridLayout(1,1,0,16));
		labelPanel.add(labelReturn);
		labelPanel.setBackground(panelBackColor);

		//Field Panel
		fieldPanel = new JPanel(new GridLayout(1,1,0,14));
		fieldPanel.add(fieldReturn);
		fieldPanel.setBackground(panelBackColor);

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
		c.insets = new Insets(0,0,20,0);
		finalPanel.add(txt, c);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.insets = new Insets(20,0,0,0);
		finalPanel.add(button, c);
		finalPanel.setBackground(panelBackColor);

		return finalPanel;		
	}

	public JPanel overduePanel() {
		
		//Text
		txt = new Text();
		txt.setText("Please click 'Overdue Items' button to see all items that are overdue \n\n" +
					"To send emails to borrowers reminding them of their overdue items, enter their " +
					"BIDs and click the 'Send Email' button.  Leaving the field blank will send an email " +
					"to all overdue borrowers");
		txt.setPreferredSize(new Dimension(250, 200));
		
		Label labelBorrowers = new Label("CallNumbers: ");
		final JTextArea fieldBorrowers = new JTextArea(8, 20);
		fieldBorrowers.setLineWrap(true);
		labelBorrowers.setLabelFor(fieldBorrowers);
		
		//Check overdue items button
		button = new JButton("Overdue Items");
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				db.checkOverdueItems();
			}
		});
		
		JButton email = new JButton("Send Email");
		email.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Convert input into form (101,102,103,...)
				String input = fieldBorrowers.getText();
				String[] bor = input.split(", ");
				String borrowers = "(";
				for(int i=0; i<bor.length-1; i++)
					borrowers += bor[i] + ",";
				borrowers += bor[bor.length-1] + ")";
				
				db.overdueEmails(borrowers);
			}
		});
			
		//Panel
		fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.insets = new Insets(0,0,20,0);
		fieldPanel.add(txt, c);
		c.fill = 0;
		c.gridy = 1;
		fieldPanel.add(button, c);
		c.gridx = 1;
		c.gridy = 10;
		c.insets = new Insets(0,0,20,0);
		fieldPanel.add(fieldBorrowers, c);
		c.gridx = 1;
		c.gridy = 12;
		c.insets = new Insets(0,0,20,0);
		fieldPanel.add(email, c);
		
		fieldPanel.setBackground(panelBackColor);

		return fieldPanel;
	}

	private class Label extends JLabel {

		private Label(String str) {
			super(str);
			setFont(font);
			setForeground(Color.black);
		}		
	}

	private class Text extends JTextArea {

		private Text() {
			super();
			setFont(font);
			setLineWrap(true);
			setWrapStyleWord(true);
			setOpaque(false);
			setBackground(txtBackColor);
			setForeground(Color.BLACK);
		}
	}
}
