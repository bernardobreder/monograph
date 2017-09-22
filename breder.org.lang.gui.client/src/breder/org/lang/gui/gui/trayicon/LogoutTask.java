package breder.org.lang.gui.gui.trayicon;

import breder.org.lang.gui.gui.login.LoginFrame;
import breder.util.task.LocalTask;

public class LogoutTask extends LocalTask {

	@Override
	public void updateUI() {
		new QuitTask().updateUI();
		new LoginFrame().setVisible(true);
	}

}
