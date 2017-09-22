package breder.webservice.servlet;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import breder.webservice.util.FieldUtil;

public abstract class Servlet extends HttpServlet {

	protected HttpServletRequest req;

	protected HttpServletResponse resp;

	public abstract String action(Map<String, String> in,
			Map<String, Object> out) throws ServletException, IOException;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.req = req;
		this.resp = resp;
		Map<String, Object> out = new HashMap<String, Object>();
		Map<String, String> in = new HashMap<String, String>();
		{
			for (Object key : req.getParameterMap().keySet()) {
				in.put(key.toString(), req.getParameter(key.toString()));
			}
		}
		for (Field field : FieldUtil.getAllFields(Servlet.class, this
				.getClass())) {
			String value = in.get(field.getName());
			try {
				if (value != null) {
					setField(field, value);
				} else {
					field.set(this, null);
				}
			} catch (Exception e) {
			}
		}
		String page = this.action(in, out);
		for (String key : out.keySet()) {
			req.setAttribute(key, out.get(key));
		}
		if (page.startsWith("*")) {
			resp.sendRedirect(page.substring(1));
		} else {
			req.getRequestDispatcher("WEB-INF/pag/" + page + ".jsp").forward(
					req, resp);
		}
	}

	public void setField(Field field, String value) throws Exception {
		Class<?> c = field.getType();
		if (c.equals(String.class)) {
			field.set(this, value);
		} else if (c.equals(Integer.class)) {
			field.set(this, new Integer(value));
		}
	}

}
