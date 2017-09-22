package breder.org.lang.gui.task;

import java.awt.Component;

import javax.swing.JOptionPane;

import breder.org.lang.gui.service.ServiceLocator;
import breder.org.lang.gui.service.document.IDocumentService;
import breder.org.lang.gui.service.user.ILangAuthenticationService;
import breder.org.lang.gui.service.user.ILogService;
import breder.org.lang.gui.service.user.IUserService;
import breder.org.lang.gui.user.User;
import breder.util.task.RemoteTask;
import breder.webservice.ILoginFrame;
import breder.webservice.IServerService;
import breder.webservice.exception.AuthenticationException;

public class BasicLoginTask extends RemoteTask {

	private final ILoginFrame frame;
	private String username;
	private char[] password;

	public BasicLoginTask(ILoginFrame frame, Component... components) {
		super(components);
		this.frame = frame;
	}

	@Override
	public boolean preAction() {
		this.username = frame.getUsername();
		this.password = frame.getPassword();
		return true;
	}

	@Override
	public void perform() throws Throwable {
		IServerService.DEFAULT
				.setAuthenticationServiceClass(ILangAuthenticationService.class);
		IServerService.DEFAULT.finish();
		IServerService.DEFAULT.init(username, password);
		ServiceLocator.setInstance(null);
		User user = new User(username);
		ILogService logService = IServerService.DEFAULT
				.lookup(ILogService.class);
		IUserService userService = IServerService.DEFAULT
				.lookup(IUserService.class);
		IDocumentService docService = IServerService.DEFAULT
				.lookup(IDocumentService.class);
		ServiceLocator locator = new ServiceLocator(user, logService,
				userService, docService);
		ServiceLocator.setInstance(locator);
		synchronized (IServerService.DEFAULT) {
			IServerService.DEFAULT.notify();
		}
	}

	@Override
	public void updateUI() {
		this.getFrame().dispose();
	}

	@Override
	public void handler(Throwable t) {
		if (t instanceof AuthenticationException) {
			JOptionPane.showMessageDialog(null, "Usuário ou Senha inválida");
		} else {
			super.handler(t);
		}
	}

	public String getUsername() {
		return username;
	}

	public char[] getPassword() {
		return password;
	}

	public ILoginFrame getFrame() {
		return frame;
	}

}
