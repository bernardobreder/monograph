package web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;

public class IndexServlet extends JspServlet {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String action(Map<String, String> in, Map<String, Object> out)
			throws ServletException, IOException {
		out.put("title", "Main");
		out.put("subtitle", "Breder Language");
		return "index";
	}

}
