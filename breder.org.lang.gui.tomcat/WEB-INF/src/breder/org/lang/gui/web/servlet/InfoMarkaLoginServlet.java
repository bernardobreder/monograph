package breder.org.lang.gui.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;

import breder.org.lang.gui.service.user.ILangAuthenticationService;
import breder.org.lang.gui.service.user.LangAuthenticationService;
import breder.webservice.servlet.LoginServlet;

public class InfoMarkaLoginServlet extends LoginServlet {

	@Override
	protected String login(String username, char[] password) throws Exception {
		ILangAuthenticationService service = new LangAuthenticationService();
		return service.login(username, password);
	}

	@Override
	public String actionPage(Map<String, String> in, Map<String, Object> out)
			throws ServletException, IOException {
		out.put("title", "Logar");
		out.put("subtitle", "Paciente");
		return "login";
	}

}
