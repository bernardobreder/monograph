package breder.org.lang.gui.service;

import javax.swing.SwingUtilities;

import breder.org.lang.gui.gui.login.BasicLoginFrame;
import breder.org.lang.gui.service.document.IDocumentService;
import breder.org.lang.gui.service.user.ILangAuthenticationService;
import breder.org.lang.gui.service.user.ILogService;
import breder.org.lang.gui.service.user.IUserService;
import breder.org.lang.gui.user.User;
import breder.util.lookandfeel.LookAndFeel;
import breder.util.swing.BInternalFrame;
import breder.webservice.IServerService;
import breder.webservice.exception.AuthenticationException;

public class ServiceLocator {

	private static ServiceLocator instance;

	private final User user;

	private final ILogService logService;

	private final IUserService userService;

	private final IDocumentService docService;

	public ServiceLocator(User user, ILogService logService,
			IUserService userService, IDocumentService docService) {
		super();
		this.user = user;
		this.logService = logService;
		this.userService = userService;
		this.docService = docService;
	}

	public static ServiceLocator getInstance() {
		return instance;
	}

	public static void setInstance(ServiceLocator instance) {
		ServiceLocator.instance = instance;
	}

	public User getUser() {
		return user;
	}

	public ILogService getLogService() {
		return logService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public IDocumentService getDocService() {
		return docService;
	}

	public static void startDefaultLogin() throws AuthenticationException {
		startDefaultLogin("admin", "");
	}

	public static void startDefaultLogin(String username, String password)
			throws AuthenticationException {
		if (ServiceLocator.getInstance() != null) {
			return;
		}
		IServerService.DEFAULT.getConfig()
				.setTomcatUrl("http://localhost:8080");
		IServerService.DEFAULT.getConfig().setServletUrl("inpa/service");
		IServerService.DEFAULT.setLoginFrameClass(BasicLoginFrame.class);
		IServerService.DEFAULT
				.setAuthenticationServiceClass(ILangAuthenticationService.class);
		IServerService.DEFAULT.init(username, password.toCharArray());
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
	}

	public static void finishDefaultLogin() {
		IUserService service = IServerService.DEFAULT
				.lookup(IUserService.class);
		service.logout();
		IServerService.DEFAULT.finish();
	}

	public static void startDefaultGui() {
		LookAndFeel.getInstance().installNimbus();
	}

	public static void startDefaultLogin(final BInternalFrame frame)
			throws AuthenticationException {
		ServiceLocator.startDefaultLogin();
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				ServiceLocator.startDefaultGui();
				BInternalFrame.main(frame);
			}
		});
	}

}
