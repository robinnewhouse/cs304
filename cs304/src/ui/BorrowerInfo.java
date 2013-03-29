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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import database.DataBaseConnection;

public class BorrowerInfo {

	private JPanel labelPanel;
	private JPanel fieldPanel;
	private JPanel finalPanel;
	private JButton button;
	private Text txt;
	private GridBagConstraints c;
	private Color txtBackColor = Color.white;
	private Color panelBackColor = new Color(59,67,103);
	private Insets bottom = new Insets(0,0,20,0);
	private static Font font = new Font("Times New Roman", Font.BOLD, 20);
	private DataBaseConnection db;
	private JTextField[] fields;

	public BorrowerInfo(DataBaseConnection db) {
		this.db = db;
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
		final JTextField fieldTitle = new JTextField(15);
		final JTextField fieldAuthor = new JTextField(15);
		final JTextField fieldSubject = new JTextField(15);
		
		fields = new JTextField[3];
		fields[0] = fieldTitle;
		fields[1] = fieldAuthor;
		fields[2] = fieldSubject;

		//Set up fields with labels
		labelTitle.setLabelFor(fieldTitle);
		labelAuthor.setLabelFor(fieldAuthor);
		labelSubject.setLabelFor(fieldSubject);

		//Search Button
		button = new JButton("Search");
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean fieldsFilledOut = false;
				
				for(JTextField f : fields){
					if(!f.getText().isEmpty()){
						System.out.println("Field filled");
						fieldsFilledOut = true;
						break;
					}		
				}
				
				if(fieldsFilledOut == false)
					JOptionPane.showMessageDialog(null, "At least one field must be filled.");
				else{
					System.out.println("Keyword: " + fieldTitle.getText() + " Author: " + fieldAuthor.getText() + " Subject: " + fieldSubject.getText());
					db.searchForItem(fieldTitle.getText(), fieldAuthor.getText(), fieldSubject.getText());
				}
				
				
			}
		});

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
		finalPanel.setBackground(panelBackColor);

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
		labelPanel.setBackground(panelBackColor);

		return labelPanel;
	}

	public JPanel accountPanel() {

		//Text
		txt = new Text();
		txt.setText("Please enter the account number below and click 'Check Account' " +
		"to get account details");
		txt.setPreferredSize(new Dimension(250,110));
		txt.setAlignmentX(SwingConstants.CENTER);

		//Fields
		JTextField fieldAccount = new JTextField(15);

		//Check Account Button
		button = new JButton("Check Account");

		//Labels Panel
		labelPanel = new JPanel();
		labelPanel.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = bottom;
		labelPanel.add(txt, c);
		c.gridx = 0;
		c.gridy = 1;
		c.insets = bottom;
		labelPanel.add(fieldAccount, c);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		labelPanel.add(button, c);
		labelPanel.setBackground(panelBackColor);

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
		fieldPanel.setBackground(panelBackColor);

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
