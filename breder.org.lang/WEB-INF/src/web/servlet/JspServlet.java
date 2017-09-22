package web.servlet;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class JspServlet extends HttpServlet {

	public abstract String action(Map<String, String> in,
			Map<String, Object> out) throws ServletException, IOException;

	protected String url;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, Object> out = new HashMap<String, Object>();
		Map<String, String> in = new HashMap<String, String>();
		{
			for (Object key : req.getParameterMap().keySet()) {
				in.put(key.toString(), req.getParameter(key.toString()));
			}
		}
		this.url = req.getRequestURI();
		String page = this.action(in, out);
		for (String key : out.keySet()) {
			req.setAttribute(key, out.get(key));
		}
		req.getRequestDispatcher("WEB-INF/jsp/" + page + ".jsp").forward(req,
				resp);
	}

	public void setField(Field field, String value) throws Exception {
		Class<?> c = field.getType();
		if (c.equals(String.class)) {
			field.set(this, value);
		} else if (c.equals(Integer.class)) {
			field.set(this, new Integer(value));
		}
	}

	public String getUrl() {
		return url;
	}

}
