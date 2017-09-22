package web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;

public class LogoServlet extends JspServlet {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String action(Map<String, String> in, Map<String, Object> out) throws ServletException,
			IOException {
		return "logo";
	}
	
}
