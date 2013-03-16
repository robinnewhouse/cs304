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
	private Text txt;
	private GridBagConstraints c;
	private Color txtBackColor = Color.lightGray;
	private Insets bottom = new Insets(0,0,20,0);
	private static Font font = new Font("Times New Roman", Font.BOLD, 20);

	public BorrowerInfo() {
	}
	
	public JPanel searchPanel() {
		
		//Text
		txt = new Text();
		txt.setText("Enter the information to search on and then click 'Search'.");
		txt.setPreferredSize(new Dimension(250, 60));
		
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
		
		//Search Button
		button = new JButton("Search");
		
		finalPanel = new JPanel();
		finalPanel.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.insets = bottom;
		finalPanel.add(txt, c);
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = bottom;
		finalPanel.add(labelTitle, c);
		c.gridx = 1;
		c.gridy = 1;
		c.insets = bottom;
		finalPanel.add(fieldTitle, c);
		c.gridx = 0;
		c.gridy = 2;
		c.insets = bottom;
		finalPanel.add(labelAuthor, c);
		c.gridx = 1;
		c.gridy = 2;
		c.insets = bottom;
		finalPanel.add(fieldAuthor, c);
		c.gridx = 0;
		c.gridy = 3;
		c.insets = bottom;
		finalPanel.add(labelSubject, c);
		c.gridx = 1;
		c.gridy = 3;
		c.insets = bottom;
		finalPanel.add(fieldSubject, c);
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;
		finalPanel.add(button, c);
		
		return finalPanel;
	}
	
	public JPanel holdPanel() {
		
		//Labels
		txt = new Text();
		txt.setText("Please enter the callnumber of the book to put on hold.");
		txt.setPreferredSize(new Dimension(250,70));
		
		//Fields
		JTextField fieldBook = new JTextField(15);
				
		//Hold Button
		button = new JButton("Place Hold Request");
		
		//LabelPanel
		labelPanel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = bottom;
		labelPanel.add(txt, c);
		c.gridy = 1;
		c.insets = bottom;
		labelPanel.add(fieldBook, c);
		c.gridy = 2;
		labelPanel.add(button, c);
				
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
				
		//Fields
		JTextField fineField = new JTextField(20);
		
		//Buttons
		JButton fineButton = new JButton("Pay Fine");
		JButton checkFineButton = new JButton("Check Fines");
		
		//Text
		txt = new Text();
		txt.setText("Please click on 'Check Fines' to see outstanding fines " +
		"and enter the amount you wish to pay");
		txt.setPreferredSize(new Dimension(250,110));
		
		//Panel
		fieldPanel = new JPanel(new GridLayout(3,1,0,14));
		fieldPanel.add(checkFineButton);
		fieldPanel.add(fineField);
		fieldPanel.add(fineButton);
		
		finalPanel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill= GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,20,0);
		finalPanel.add(txt, c);
		c.fill = 0;
		c.gridy = 1;
		finalPanel.add(fieldPanel, c);
		
		return finalPanel;
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
