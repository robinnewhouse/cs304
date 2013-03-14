package connection.listener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public interface SessionListener {
	
	public void updateToolbar(JToolBar toolbar);
	
	public void updateEditPanel(JPanel panel);
	
	public void updateResultsPanel(JLabel label);
	
	public void updateUserType(String string);

}
