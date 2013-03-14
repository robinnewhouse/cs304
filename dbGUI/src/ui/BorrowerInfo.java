package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BorrowerInfo {
	
	private JPanel labelPanel;
	private JPanel fieldPanel;
	private JPanel finalPanel;
	private JButton button;
	private GridBagConstraints c;
	private static Font font = new Font("Times New Roman", Font.BOLD, 20);

	public BorrowerInfo() {
	}
	
	public JPanel searchPanel() {
		
		//Labels
		Label labelTitle = new Label("Title: ");
		Label labelAuthor = new Label("Author: ");
		Label labelSubject = new Label("Subject: ");

		//Fields
		JTextField fieldTitle = new JTextField(15);
		JTextField fieldAuthor = new JTextField(15);
		JTextField fieldSubject = new JTextField(15);

		//Set up fields with labels
		labelTitle.setLabelFor(fieldTitle);
		labelAuthor.setLabelFor(fieldAuthor);
		labelSubject.setLabelFor(fieldSubject);

		//Label Panel
		labelPanel = new JPanel(new GridLayout(3,1,0,14));
		labelPanel.add(labelTitle);
		labelPanel.add(labelAuthor);
		labelPanel.add(labelSubject);

		//Field Panel
		fieldPanel = new JPanel(new GridLayout(3,1,0,18));
		fieldPanel.add(fieldTitle);
		fieldPanel.add(fieldAuthor);
		fieldPanel.add(fieldSubject);
		
		//Search Button
		button = new JButton("Search");
		
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
		c.gridy = 2;
		c.gridwidth = 2;
		c.insets = new Insets(20,0,0,0);
		c.ipadx = 20;
		finalPanel.add(button, c);
		
		return finalPanel;
	}
	
	public JPanel holdPanel() {
		
		//Labels
		Label labelBook = new Label("Call number of book");
		Label labelBookCont = new Label("to put on hold:");
		
		//Fields
		JTextField fieldBook = new JTextField(5);
		fieldBook.setColumns(15);
		
		//Set up labels with fields
		labelBook.setLabelFor(fieldBook);
		
		//Hold Button
		button = new JButton("Place Hold Request");
		
		//LabelPanel
		labelPanel = new JPanel(new GridLayout(4,1,0,14));
		labelPanel.add(labelBook);
		labelPanel.add(labelBookCont);
		labelPanel.add(fieldBook);
		labelPanel.add(button);
				
		return labelPanel;
	}
	
	public JPanel accountPanel() {
		
		//Labels
		Label labelAccount = new Label("Account Number: ");
		
		//Fields
		JTextField fieldAccount = new JTextField(12);
		
		//Set up labels with fields
		labelAccount.setLabelFor(fieldAccount);
		
		//Check Account Button
		button = new JButton("Check Account");
		
		//Labels Panel
		labelPanel = new JPanel();
		labelPanel.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		labelPanel.add(labelAccount, c);
		c.gridx = 1;
		c.gridy = 0;
		labelPanel.add(fieldAccount, c);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.insets = new Insets(20,0,0,0);
		labelPanel.add(button, c);
		
		return labelPanel;
	}
	
	public JPanel finePanel() {
		JTextArea txt = new JTextArea();
		txt.setText("Please click on 'Check Fines' to see outstanding fines " +
		"and enter the amount you wish to pay");
		txt.setFont(font);
		txt.setWrapStyleWord(true);
		txt.setLineWrap(true);
		txt.setMaximumSize(new Dimension(250, 20));
		txt.setForeground(Color.black);
		txt.setOpaque(false);
		
		//Fields
		JTextField fineField = new JTextField(20);
		
		//Buttons
		JButton fineButton = new JButton("Pay Fine");
		JButton checkFineButton = new JButton("Check Fines");
		
		//Panel
		JPanel textPanel = new JPanel(new GridLayout(1,1,0,14));
		textPanel.add(txt);
		JPanel fieldPanel = new JPanel(new GridLayout(3,1,0,14));
		fieldPanel.add(checkFineButton);
		fieldPanel.add(fineField);
		fieldPanel.add(fineButton);
		JPanel finalPanel = new JPanel(new GridLayout(2,1,0,8));
		finalPanel.add(textPanel, BorderLayout.CENTER);
		finalPanel.add(fieldPanel, BorderLayout.PAGE_END);
		
		return finalPanel;
	}
	
	private class Label extends JLabel {

		private Label(String str) {
			super(str);
			setFont(font);
			setForeground(Color.black);
		}		
	}

}
