package brederweb.servlet;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import brederweb.util.FieldUtil;


public abstract class Servlet extends HttpServlet {

	public abstract String action(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String page = this.request(req, resp);
		req.getRequestDispatcher("WEB-INF/pag/" + page + ".jsp").forward(req,
				resp);
	}

	protected String request(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		for (Field field : FieldUtil.getAllFields(Servlet.class, this
				.getClass())) {
			String value = req.getParameter(field.getName());
			try {
				if (value != null) {
					setField(field, value);
				} else {
					field.set(this, null);
				}
			} catch (Exception e) {
			}
		}
		String page = this.action(req, resp);
		return page;
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
