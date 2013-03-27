package connection;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JToolBar;

import ui.Info;
import ui.MainWindow;
import ui.Result;
import connection.listener.SessionListener;
import database.DataBaseConnection;


public class Session {

	private Info info;
	private MainWindow main;
	private DataBaseConnection db;

	private Set<SessionListener> sessionListeners = new HashSet<SessionListener>();

	public Session(MainWindow mainWindow) {
		this.main = mainWindow;
		db = new DataBaseConnection(this);
	}

	public synchronized void addSessionListener(SessionListener listener) {
		sessionListeners.add(listener);
	}

	public void removeSessionListener(SessionListener listener) {
		sessionListeners.remove(listener);
	}

	public void loadEditPanel(String string) {
		info = new Info(string, db);
		for (SessionListener listener : sessionListeners)
			listener.updateEditPanel(info.getPanel());
	}

	public void loadToolbar(JToolBar toolbar) {
		for (SessionListener listener : sessionListeners)
			listener.updateToolbar(toolbar);
	}
	
	public void loadResultPanel(Result result) {
		for (SessionListener listener: sessionListeners)
			listener.updateResultsPanel(result.getPanel());
	}

}
