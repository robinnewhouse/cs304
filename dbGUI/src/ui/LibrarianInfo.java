package ui;

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

public class LibrarianInfo {
	
	private JPanel labelPanel;
	private JPanel fieldPanel;
	private JPanel finalPanel;
	private Text txt;
	private JButton button;
	private GridBagConstraints c;
	private Color txtBackColor = Color.LIGHT_GRAY;
	private Insets bottom = new Insets(0,0,20,0);
	private static Font font = new Font("Times New Roman", Font.BOLD, 15);

	public LibrarianInfo() {
		
	}
	
	public JPanel addBookPanel() {
		
		//Text
		txt = new Text();
		txt.setText("Please enter the Book info below and click 'Add Book' button.");
		txt.setPreferredSize(new Dimension(250, 50));
		
		//Labels
		Label labelCallNumber = new Label("CallNumber:");
		Label labelISBN = new Label("ISBN:");
		Label labelTitle = new Label("Title:");
		Label labelAuthor = new Label("Main Author:");
		Label labelPublisher = new Label("Publisher:");
		Label labelYear = new Label("Year:");
		
		//Fields
		JTextField fieldCallNumber = new JTextField(14);
		JTextField fieldISBN = new JTextField(14);
		JTextField fieldTitle = new JTextField(14);
		JTextField fieldAuthor = new JTextField(14);
		JTextField fieldPublisher = new JTextField(14);
		JTextField fieldYear = new JTextField(14);
		
		//Set up labels with fields
		labelCallNumber.setLabelFor(fieldCallNumber);
		labelISBN.setLabelFor(fieldISBN);
		labelTitle.setLabelFor(fieldTitle);
		labelAuthor.setLabelFor(fieldAuthor);
		labelPublisher.setLabelFor(fieldPublisher);
		labelYear.setLabelFor(fieldYear);
				
		//Add Book Button
		button = new JButton("Add Book");
	
		//Final Panel
		finalPanel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = bottom;
		finalPanel.add(txt, c);
		c.fill = 0;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = bottom;
		finalPanel.add(labelCallNumber, c);
		c.gridx = 1;
		c.gridy = 1;
		c.insets = bottom;
		finalPanel.add(fieldCallNumber, c);
		c.gridx = 0;
		c.gridy = 2;
		c.insets = bottom;
		finalPanel.add(labelISBN, c);
		c.gridx = 1;
		c.gridy = 2;
		c.insets = bottom;
		finalPanel.add(fieldISBN, c);
		c.gridx = 0;
		c.gridy = 3;
		c.insets = bottom;
		finalPanel.add(labelTitle, c);
		c.gridx = 1;
		c.gridy = 3;
		c.insets = bottom;
		finalPanel.add(fieldTitle, c);
		c.gridx = 0;
		c.gridy = 4;
		c.insets = bottom;
		finalPanel.add(labelAuthor, c);
		c.gridx = 1;
		c.gridy = 4;
		c.insets = bottom;
		finalPanel.add(fieldAuthor, c);
		c.gridx = 0;
		c.gridy = 5;
		c.insets = bottom;
		finalPanel.add(labelPublisher, c);
		c.gridx = 1;
		c.gridy = 5;
		c.insets = bottom;
		finalPanel.add(fieldPublisher, c);
		c.gridx = 0;
		c.gridy = 6;
		c.insets = bottom;
		finalPanel.add(labelYear, c);
		c.gridx = 1;
		c.gridy = 6;
		c.insets = bottom;
		finalPanel.add(fieldYear, c);
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 2;
		finalPanel.add(button, c);
		
		return finalPanel;
	}
	

	public JPanel bookReportPanel() {
		
		//Text
		txt = new Text();
		txt.setText("Click 'Book Report' to see all Books that are currently out. Include a " +
		"subject to narrow the results");
		txt.setPreferredSize(new Dimension(250, 70));
		
		//Label
		Label labelSubject = new Label("Subject: ");
		
		//Field
		JTextField fieldSubject = new JTextField(17);
		
		//Set up labels with fields
		labelSubject.setLabelFor(fieldSubject);
		
		//Book Report Button
		button = new JButton("Book Report");
		
		//finalPanel
		finalPanel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.insets = bottom;
		finalPanel.add(txt, c);
		c.fill = 0;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		finalPanel.add(labelSubject, c);
		c.gridx = 1;
		c.gridy = 1;
		finalPanel.add(fieldSubject, c);
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(20,0,0,0);
		c.gridwidth = 2;
		finalPanel.add(button, c);
		
		return finalPanel;
	}
	
	public JPanel popularReportPanel() {
		
		//Text
		txt = new Text();
		txt.setText("Enter a year and the number of popular books and Click 'Popular Report' " +
				"to see the most popular books in a given year.");
		txt.setPreferredSize(new Dimension(250, 90));

		//Label
		Label labelYear = new Label("Year: ");
		Label labelN = new Label("How many: ");

		//Field
		JTextField fieldYear = new JTextField(14);
		JTextField fieldN = new JTextField(14);

		//Set up labels with fields
		labelYear.setLabelFor(fieldYear);
		labelN.setLabelFor(fieldN);

		//Book Report Button
		button = new JButton("Popular Report");

		//finalPanel
		finalPanel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.insets = bottom;
		finalPanel.add(txt, c);
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		finalPanel.add(labelYear, c);
		c.gridx = 1;
		c.gridy = 1;
		finalPanel.add(fieldYear, c);
		c.gridx = 0;
		c.gridy = 2;
		finalPanel.add(labelN, c);
		c.gridx = 1;
		c.gridy = 2;
		finalPanel.add(fieldN, c);
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.insets = new Insets(20,0,0,0);
		finalPanel.add(button, c);

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
