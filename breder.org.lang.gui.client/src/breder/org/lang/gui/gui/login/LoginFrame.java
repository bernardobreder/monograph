package breder.org.lang.gui.gui.login;


import java.awt.Component;

import breder.org.lang.gui.task.LoginTask;
import breder.util.task.Task;
import breder.webservice.ILoginFrame;

public class LoginFrame extends BasicLoginFrame {
	
	@Override
	public Task getLoginTask(ILoginFrame frame, Component... components) {
		return new LoginTask(this, components);
	}
	
}
