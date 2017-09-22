package breder.org.lang.gui.task;

import breder.org.lang.gui.gui.login.LoginFrame;
import breder.util.task.LocalTask;

public class LogoutTask extends LocalTask {

	@Override
	public void updateUI() {
		new LoginFrame().setVisible(true);
	}

}
