package web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;

public class PaperServlet extends JspServlet {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String action(Map<String, String> in, Map<String, Object> out) throws ServletException, IOException {
		out.put("subtitle", "Paper");
		out.put("title", out.get("subtitle"));
		return "paper";
	}

}
