package breder.org.lang.gui.task;


import java.awt.AWTException;
import java.awt.Component;
import java.awt.SystemTray;

import javax.swing.JOptionPane;

import breder.org.lang.gui.gui.trayicon.LangTrayIcon;
import breder.org.lang.gui.service.ServiceLocator;
import breder.webservice.ILoginFrame;

public class LoginTask extends BasicLoginTask {

	public LoginTask(ILoginFrame frame, Component... components) {
		super(frame, components);
	}

	@Override
	public void updateUI() {
		if (ServiceLocator.getInstance() != null) {
			try {
				SystemTray.getSystemTray().add(new LangTrayIcon());
			} catch (AWTException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				System.exit(0);
			}
			this.getFrame().dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Usuário ou Senha inválida");
		}
	}
}
