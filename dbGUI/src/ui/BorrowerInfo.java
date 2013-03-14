package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BorrowerInfo {
	
	private JPanel labelPanel;
	private JPanel fieldPanel;
	private static Font font = new Font("Times New Roman", Font.BOLD, 20);

	public BorrowerInfo() {
	}
	
	public JPanel[] searchPanel() {
		
		//Labels
		JLabel labelTitle = new JLabel("Title: ");
		labelTitle.setFont(font);
		JLabel labelAuthor = new JLabel("Author: ");
		labelAuthor.setFont(font);
		JLabel labelSubject = new JLabel("Subject: ");
		labelSubject.setFont(font);

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
		
		JPanel[] panels = new JPanel[2];
		panels[0] = labelPanel;
		panels[1] = fieldPanel;
		
		return panels;
	}
	
	public JPanel holdPanel() {
		
		//Labels
		JLabel labelBook = new JLabel("Call number of book");
		JLabel labelBookCont = new JLabel("to put on hold:");
		labelBook.setFont(font);
		labelBookCont.setFont(font);
		
		//Fields
		JTextField fieldBook = new JTextField(5);
		fieldBook.setColumns(15);
		
		//Set up labels with fields
		labelBook.setLabelFor(fieldBook);
		
		//LabelPanel
		labelPanel = new JPanel(new GridLayout(3,1,0,14));
		labelPanel.add(labelBook);
		labelPanel.add(labelBookCont);
		labelPanel.add(fieldBook);
				
		return labelPanel;
	}
	
	public JPanel accountPanel() {
		
		//Labels
		JLabel labelAccount = new JLabel("Account Number: ");
		labelAccount.setFont(font);
		
		//Fields
		JTextField fieldAccount = new JTextField(20);
		
		//Set up labels with fields
		labelAccount.setLabelFor(fieldAccount);
		
		//Labels Panel
		labelPanel = new JPanel(new GridLayout(3,1,0,14));
		labelPanel.add(labelAccount);
		labelPanel.add(fieldAccount);
		
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

}
