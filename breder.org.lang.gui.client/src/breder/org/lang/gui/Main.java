package breder.org.lang.gui;

import breder.org.lang.gui.gui.login.BasicLoginFrame;
import breder.org.lang.gui.gui.login.LoginFrame;
import breder.org.lang.gui.service.user.ILangAuthenticationService;
import breder.util.lookandfeel.LookAndFeel;
import breder.webservice.IServerService;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Locale.setDefault(new Locale("pt", "PT", ""));
		if (false) {
			IServerService.DEFAULT.getConfig().setTomcatUrl(
					"http://www.breder.org");
		}
		IServerService.DEFAULT.getConfig().setSessionTimeout(60 * 1000);
		IServerService.DEFAULT.setLoginFrameClass(BasicLoginFrame.class);
		IServerService.DEFAULT
				.setAuthenticationServiceClass(ILangAuthenticationService.class);
		IServerService.DEFAULT.getConfig().setServletUrl("langui/service");
		LookAndFeel.getInstance().installSeaglass();
		new LoginFrame().setVisible(true);
	}

}
