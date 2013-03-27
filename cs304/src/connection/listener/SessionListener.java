package connection.listener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

public interface SessionListener {

	public void updateToolbar(JToolBar toolbar);

	public void updateEditPanel(JPanel panel);

	public void updateResultsPanel(JScrollPane panel);

}
