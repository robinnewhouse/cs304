package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import connection.Session;
import connection.listener.SessionListener;

public class MainWindow extends JFrame implements SessionListener {
	
	private Session session;
	private JLabel currentUserTypeLabel;
	private JScrollPane scrollPane;
	private JPanel container, currentEditPanel, resultsContainer;
	private UserToolbar userToolbar;
	private JToolBar currentToolbar;
	private Image image;
	Font font;
	
	
	public MainWindow() {
		
		super("Database GUI");
		session = new Session(this);
		session.addSessionListener(this);
		container = new JPanel(new BorderLayout());
		container.setPreferredSize(new Dimension(900,500));
		try {
			image = ImageIO.read(new File("images/BookCase.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		userToolbar = new UserToolbar(this);
		userToolbar.setBackground(Color.black);
		currentToolbar = userToolbar.borrowerToolbar();
		
		font = new Font("Times New Roman", Font.PLAIN, 30);
		resultsContainer = new JPanel(new BorderLayout());
		JLabel txt = new JLabel("Results will go here");
		JLabel imagePanel = new JLabel();
		imagePanel.setIcon(new ImageIcon(image));
		txt.setFont(font);
		txt.setForeground(Color.blue);
		resultsContainer.add(imagePanel);
		resultsContainer.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		currentEditPanel = new JPanel();
		currentEditPanel.setMaximumSize(new Dimension(300, getHeight()));
		currentEditPanel.setMinimumSize(new Dimension(300, getHeight()));
		currentEditPanel.setPreferredSize(new Dimension(300, getHeight()));		
		currentEditPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		currentUserTypeLabel = new JLabel();
		currentUserTypeLabel.setText("Current User: " + currentToolbar.getName());
		currentUserTypeLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
		currentUserTypeLabel.setPreferredSize(new Dimension(50, 30));
		currentUserTypeLabel.setMaximumSize(new Dimension(50, 30));
		currentUserTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		currentUserTypeLabel.setOpaque(true);
		currentUserTypeLabel.setBackground(Color.black);
		currentUserTypeLabel.setForeground(Color.white);
		
		session.loadEditPanel("Add");
		
		container.add(userToolbar, BorderLayout.PAGE_START);
		container.add(currentToolbar, BorderLayout.PAGE_START);
		container.add(resultsContainer, BorderLayout.CENTER);
		container.add(currentEditPanel, BorderLayout.WEST);
		container.add(currentUserTypeLabel, BorderLayout.PAGE_END);
		
		scrollPane = new JScrollPane(container);
		
		//Main window specs
		this.add(scrollPane);	
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1000, 600);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new MainWindow();
	}
	
	/**
	 * Updates the User input Info panel according to which user is 
	 * currently selected and which operation they which to perform.
	 */
	@Override
	public void updateEditPanel(JPanel panel) {
		
		container.remove(currentEditPanel);
		container.add(panel, BorderLayout.WEST);
		currentEditPanel = panel;
		container.revalidate();
	}
	
	public Session getSession() {
		return session;
	}

	@Override
	public void updateToolbar(JToolBar toolbar) {
		
		if(currentToolbar != null)
			container.remove(currentToolbar);
		currentToolbar = toolbar;
		container.add(currentToolbar, BorderLayout.PAGE_START);
		currentUserTypeLabel.setText("Current User: " + currentToolbar.getName());
		container.revalidate();		
	}

	@Override
	public void updateResultsPanel(JLabel label) {
		// TODO Auto-generated method stub
		
	}
}
