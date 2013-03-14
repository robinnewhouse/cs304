package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

public class UserToolbar extends JToolBar{
	
	private MainWindow main;
	private JMenuBar menuBar;
	private JMenu userTypeMenu;
	private JMenuItem menuItem;
	
	public UserToolbar(MainWindow mainWindow) {
		
		this.main = mainWindow;
		main.setJMenuBar(createMenuBar());
	}

	/**
	 * creates the toolbar to be used by borrowers
	 * 
	 * @return borrowerToolbar
	 */
	public JToolBar borrowerToolbar() {
		JToolBar toolbar = new JToolBar();
		toolbar.setName("Borrower");
		JButton button;
		
		//Toolbar buttons
		button = new JButton("Search");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				main.getSession().loadEditPanel("Search");
			}
			
		});
		toolbar.add(button);
		
		button = new JButton("Hold Request");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				main.getSession().loadEditPanel("Hold");
			}
			
		});
		toolbar.add(button);

		button = new JButton("Check Account");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				main.getSession().loadEditPanel("Account");
			}
			
		});
		toolbar.add(button);
		
		button = new JButton("Pay Fine");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				main.getSession().loadEditPanel("Fine");
			}
			
		});
		toolbar.add(button);
		
		toolbar.setMinimumSize(new Dimension(getWidth(), 40));
		toolbar.setPreferredSize(new Dimension(getWidth(), 40));
		
		return toolbar;
	}
	
	private JToolBar librarianToolbar() {
		JToolBar toolbar = new JToolBar();
		toolbar.setName("Librarian");
		JButton button;
		
		//Toolbar buttons
		button = new JButton("Add Book/Copy");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				main.getSession().loadEditPanel("Add Book");
			}
			
		});
		toolbar.add(button);
		
		button = new JButton("Book Report");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				main.getSession().loadEditPanel("Book Report");
			}
			
		});
		toolbar.add(button);

		button = new JButton("Popular Items Report");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				main.getSession().loadEditPanel("Popular Items Report");
			}
			
		});
		toolbar.add(button);
		
		toolbar.setMinimumSize(new Dimension(getWidth(), 40));
		toolbar.setPreferredSize(new Dimension(getWidth(), 40));
		
		return toolbar;
	}
	
	private JToolBar clerkToolbar() {
		JToolBar toolbar = new JToolBar();
		toolbar.setName("Clerk");
		JButton button;
		
		//Toolbar buttons
		button = new JButton("Add Borrower");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				main.getSession().loadEditPanel("Add Borrower");
			}
			
		});
		toolbar.add(button);
		
		button = new JButton("Check out Items");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				main.getSession().loadEditPanel("Check Out");
			}
			
		});
		toolbar.add(button);

		button = new JButton("Returns");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				main.getSession().loadEditPanel("Returns");
			}
			
		});
		toolbar.add(button);
		
		button = new JButton("Check Overdue Items");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				main.getSession().loadEditPanel("Overdue");
			}
			
		});
		toolbar.add(button);
		toolbar.setMinimumSize(new Dimension(getWidth(), 40));
		toolbar.setPreferredSize(new Dimension(getWidth(), 40));
		
		return toolbar;
	}

	private JMenuBar createMenuBar() {
		menuBar = new JMenuBar();
		userTypeMenu = new JMenu("Type of User");
		userTypeMenu.setForeground(Color.white);
		userTypeMenu.setBorder(BorderFactory.createLineBorder(Color.white));
		userTypeMenu.getAccessibleContext().setAccessibleDescription("A menu representing which type of user is going to use it");
		
		//Menu Items
		menuItem = new JMenuItem("Clerk");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				main.getSession().loadToolbar(clerkToolbar());
				main.getSession().loadEditPanel("Add Borrower");
			}
			
		});
		userTypeMenu.add(menuItem);
		
		menuItem = new JMenuItem("Borrower");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				main.getSession().loadToolbar(borrowerToolbar());
				main.getSession().loadEditPanel("Search");
			}
			
		});
		userTypeMenu.add(menuItem);
		
		menuItem = new JMenuItem("Librarian");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				main.getSession().loadToolbar(librarianToolbar());
				main.getSession().loadEditPanel("Add Book");
			}
			
		});
		userTypeMenu.add(menuItem);
		
		//Background Color
		menuBar.setBackground(Color.black);
		
		menuBar.add(userTypeMenu);
		return menuBar;
	}

}
