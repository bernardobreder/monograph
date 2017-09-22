package breder.org.lang.gui.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;

import breder.webservice.servlet.Servlet;

public class IndexServlet extends Servlet {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String action(Map<String, String> in, Map<String, Object> out)
			throws ServletException, IOException {
		out.put("title", "Principal");
		out.put("subtitle", "Instituto Nacional de Proteção Animal");
		return "index";
	}

}
