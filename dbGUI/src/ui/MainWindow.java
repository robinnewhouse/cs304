package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import connection.Session;
import connection.listener.SessionListener;

public class MainWindow extends JFrame implements SessionListener {
	
	private Session session;
	private JLabel currentUserTypeLabel;
	JPanel currentEditPanel, resultsPanel;
	private UserToolbar userToolbar;
	private JToolBar currentToolbar;
	Font font;
	
	
	public MainWindow() {
		
		super("Database GUI");
		session = new Session(this);
		session.addSessionListener(this);
		
		userToolbar = new UserToolbar(this);
		currentToolbar = userToolbar.borrowerToolbar();
		
		font = new Font("Times New Roman", Font.PLAIN, 30);
		resultsPanel = new JPanel();
		JLabel txt = new JLabel("Results will go here");
		txt.setVerticalAlignment(SwingConstants.CENTER);
		txt.setHorizontalAlignment(SwingConstants.CENTER);
		txt.setFont(font);
		txt.setForeground(Color.blue);
		resultsPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		resultsPanel.add(txt, BorderLayout.CENTER);
		resultsPanel.setBackground(Color.white);
		
		currentEditPanel = new JPanel();
		currentEditPanel.setMaximumSize(new Dimension(250, getHeight()));
		currentEditPanel.setMinimumSize(new Dimension(250, getHeight()));
		currentEditPanel.setPreferredSize(new Dimension(250, getHeight()));		
		currentEditPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		currentUserTypeLabel = new JLabel();
		currentUserTypeLabel.setText("Current User: " + currentToolbar.getName());
		currentUserTypeLabel.setPreferredSize(new Dimension(50, 30));
		currentUserTypeLabel.setMaximumSize(new Dimension(50, 30));
		currentUserTypeLabel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		currentUserTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		currentUserTypeLabel.setOpaque(true);
		currentUserTypeLabel.setBackground(Color.black);
		currentUserTypeLabel.setForeground(Color.white);
		
		this.add(userToolbar, BorderLayout.PAGE_START);
		this.add(currentToolbar, BorderLayout.PAGE_START);
		getContentPane().add(resultsPanel, BorderLayout.CENTER);
		getContentPane().add(currentEditPanel, BorderLayout.WEST);
		
		getContentPane().add(currentUserTypeLabel, BorderLayout.PAGE_END);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.white);
		
		this.setSize(1000, 500);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		new MainWindow();
	}
	
	@Override
	public void updateEditPanel(JPanel panel) {
		
		this.remove(currentEditPanel);
		this.add(panel, BorderLayout.WEST);
		currentEditPanel = panel;
		this.revalidate();
	}
	
	public Session getSession() {
		return session;
	}

	@Override
	public void updateToolbar(JToolBar toolbar) {
		
		if(currentToolbar != null)
			this.remove(currentToolbar);
		currentToolbar = toolbar;
		this.add(currentToolbar, BorderLayout.PAGE_START);
		currentUserTypeLabel.setText("Current User: " + currentToolbar.getName());
		this.revalidate();		
	}

	@Override
	public void updateResultsPanel(JLabel label) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserType(String string) {
				
	}
	

}
