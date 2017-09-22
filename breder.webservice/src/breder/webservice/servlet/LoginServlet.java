package breder.webservice.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;

public abstract class LoginServlet extends LoginedServlet {
	
	protected abstract String login(String username, char[] password) throws Exception;
	
	public abstract String actionPage(Map<String, String> in, Map<String, Object> out)
			throws ServletException, IOException;
	
	@Override
	public final String action(Map<String, String> in, Map<String, Object> out)
			throws ServletException, IOException {
		String username = in.get("username");
		String password = in.get("password");
		if (username != null && password != null) {
			try {
//				if (this.login(username, password.toCharArray())) {
//					this.setUser(username);
//					return "*" + in.get("path");
//				}
			} catch (Exception e) {
			}
		}
		for (String key : in.keySet()) {
			out.put(key, in.get(key));
		}
		out.put("path", in.get("path"));
		return this.actionPage(in, out);
	}
}
