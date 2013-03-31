package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import database.DataBaseConnection;

public class LibrarianInfo {
	private JPanel finalPanel;
	private Text txt;
	private JButton button;
	private GridBagConstraints c;
	private Color txtBackColor = Color.white;
	private Color panelBackColor = new Color(59,67,103);
	private Insets bottom = new Insets(0,0,20,0);
	private static Font font = new Font("Times New Roman", Font.BOLD, 15);
	private DataBaseConnection db;
	private JTextField[] fields;

	public LibrarianInfo(DataBaseConnection db) {
		this.db = db;
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
		Label labelSubject = new Label("Subject: ");
		Label labelAddAuthors = new Label("Other Authors: ");
		Label labelCopies = new Label("Copies: ");

		//Fields
		final JTextField fieldCallNumber = new JTextField(14);
		final JTextField fieldISBN = new JTextField(14);
		final JTextField fieldTitle = new JTextField(14);
		final JTextField fieldAuthor = new JTextField(14);
		final JTextField fieldPublisher = new JTextField(14);
		final JTextField fieldYear = new JTextField(14);
		final JTextField fieldSubject = new JTextField(14);
		final JTextField fieldAddAuthors = new JTextField(14);
		final JTextField fieldCopies = new JTextField(14);
		
		//Add fields to an array to check validate input
		fields = new JTextField[8];
		fields[0] = fieldCallNumber;
		fields[1] = fieldISBN;
		fields[2] = fieldTitle;
		fields[3] = fieldAuthor;
		fields[4] = fieldPublisher;
		fields[5] = fieldYear;
		fields[6] = fieldSubject;
		fields[7] = fieldCopies;

		//Set up labels with fields
		labelCallNumber.setLabelFor(fieldCallNumber);
		labelISBN.setLabelFor(fieldISBN);
		labelTitle.setLabelFor(fieldTitle);
		labelAuthor.setLabelFor(fieldAuthor);
		labelPublisher.setLabelFor(fieldPublisher);
		labelYear.setLabelFor(fieldYear);
		labelSubject.setLabelFor(fieldSubject);
		labelAddAuthors.setLabelFor(fieldAddAuthors);
		labelCopies.setLabelFor(fieldCopies);

		//Add Book Button
		button = new JButton("Add Book");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				boolean fieldsFilledOut = true;
				//iterate through each field to make sure something is
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
				//Validate the field entry
				if(fieldYear.getText().length() < 2 || fieldYear.getText().length() > 4 || !fieldYear.getText().matches("^[0-9]*"))
				{
					JOptionPane.showMessageDialog(null, "Please enter a valid year");
					fieldsFilledOut = false;
				}
				if(fieldsFilledOut)
				{
					String subject = fieldSubject.getText();
					String[] subjects = subject.split("[,]");
					for(int i = 1; i < subjects.length; i++)
						subjects[i] = subjects[i].replaceFirst(" ", "");
					int copies = Integer.parseInt(fieldCopies.getText().trim());
					
					if(fieldAddAuthors.getText().isEmpty())
					{
						db.insertBook(fieldCallNumber.getText(), fieldISBN.getText(), fieldTitle.getText(), 
								fieldAuthor.getText(), fieldPublisher.getText(), fieldYear.getText());
						for(int i=0; i<copies; i++){
							db.insertCopy(fieldCallNumber.getText());
						}
						db.insertSubject(subjects, fieldCallNumber.getText());
					}
					else {
						String author = fieldAddAuthors.getText();
						String authors[] = author.split(",");
						for(int i = 1; i < authors.length; i++)
							authors[i] = authors[i].replaceFirst(" ", "");
						db.insertBook(fieldCallNumber.getText(), fieldISBN.getText(), fieldTitle.getText(), 
								fieldAuthor.getText(), fieldPublisher.getText(), fieldYear.getText());
						for(int i=0; i<copies; i++){
							db.insertCopy(fieldCallNumber.getText());
						}
						db.insertSubject(subjects, fieldCallNumber.getText());
						db.insertAuthors(authors,fieldCallNumber.getText());
					}
					//Empty all fields
					for(int i = 0; i < fields.length; i++)
					{
						fields[i].setText("");
					}
					fieldAddAuthors.setText("");
				}
			}

		});

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
		c.insets = bottom;
		finalPanel.add(labelSubject, c);
		c.gridx = 1;
		c.gridy = 7;
		c.insets = bottom;
		finalPanel.add(fieldSubject, c);
		c.gridx = 0;
		c.gridy = 8;
		c.insets = bottom;
		finalPanel.add(labelAddAuthors, c);
		c.gridx = 1;
		c.gridy = 8;
		c.insets = bottom;
		finalPanel.add(fieldAddAuthors, c);
		c.gridx = 0;
		c.gridy = 9;
		c.insets = bottom;
		finalPanel.add(labelCopies, c);
		c.gridx = 1;
		c.gridy = 9;
		c.insets = bottom;
		finalPanel.add(fieldCopies, c);
		c.gridx = 0;
		c.gridy = 10;
		c.gridwidth = 2;
		finalPanel.add(button, c);
		finalPanel.setBackground(panelBackColor);

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
		final JTextField fieldSubject = new JTextField(17);

		//Set up labels with fields
		labelSubject.setLabelFor(fieldSubject);

		//Book Report Button
		button = new JButton("Book Report");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(fieldSubject.getText().trim().isEmpty())
					db.bookReport();
				else
					db.bookReport(fieldSubject.getText());
			}

		});

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
		finalPanel.setBackground(panelBackColor);

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
		final JTextField fieldYear = new JTextField(14);
		final JTextField fieldN = new JTextField(14);

		//Set up labels with fields
		labelYear.setLabelFor(fieldYear);
		labelN.setLabelFor(fieldN);

		//Book Report Button
		button = new JButton("Popular Report");
		button.addActionListener(new ActionListener() {
			String year;

			@Override
			public void actionPerformed(ActionEvent e) {
				//Check all fields filled out
				if(fieldYear.getText().isEmpty() || fieldN.getText().isEmpty())
					JOptionPane.showMessageDialog(null,"Please fill in both boxes");
				//Validate year
				if(!fieldYear.getText().matches("^[0-9]*") || !fieldN.getText().matches("^[0-9]*") 
						|| fieldYear.getText().length() < 2 || fieldYear.getText().length() > 4)
					JOptionPane.showMessageDialog(null, "Please enter a valid year and number of results");
				else
				{
					if(fieldYear.getText().length() == 4)
						year = fieldYear.getText().substring(2);
					else
						year = fieldYear.getText();
					db.popularReport(year, fieldN.getText());
					fieldYear.setText("");
					fieldN.setText("");
				}
			}
		});

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
		finalPanel.setBackground(panelBackColor);

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