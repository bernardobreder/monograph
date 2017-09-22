package web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;

public class VersionServlet extends JspServlet {

	private Integer id;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String action(Map<String, String> in, Map<String, Object> out)
			throws ServletException, IOException {
		if (id != null) {
			out.put("subtitle", String.format("Version %d.0", id));
			out.put("title", out.get("subtitle"));
			return "version" + id;
		}
		out.put("subtitle", "Version");
		out.put("title", out.get("subtitle"));
		return "version";
	}

}
