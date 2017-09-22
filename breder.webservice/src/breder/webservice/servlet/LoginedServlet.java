package breder.webservice.servlet;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class LoginedServlet extends Servlet {

	private final static Map<String, Entity> users = new Hashtable<String, Entity>();

	private static int MAX = 5 * 60 * 1000;

	static {
		Timer timer = new Timer("LoginedServlet");
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				synchronized (users) {
					String[] keys = users.keySet().toArray(new String[0]);
					for (String key : keys) {
						Long timer = users.get(key).timer;
						if (System.currentTimeMillis() - timer > MAX) {
							users.remove(key);
						}
					}
				}
			}
		}, 0, 60 * 1000);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.req = req;
		this.resp = resp;
		HttpSession session = req.getSession(true);
		if (req.getServletPath().startsWith("/login")) {
			super.service(req, resp);
		} else if (req.getServletPath().startsWith("/logout")) {
			users.remove(session.getId());
			String path = req.getParameter("path");
			if (path == null) {
				throw new ServletException("not found path parameter");
			}
			resp.sendRedirect(path);
		} else {
			boolean logined;
			{
				Entity entity = users.get(session.getId());
				if (entity == null) {
					logined = false;
				} else {
					if (System.currentTimeMillis() - entity.timer > MAX) {
						users.remove(session.getId());
						logined = false;
					} else {
						entity.timer = System.currentTimeMillis();
						logined = true;
					}
				}
			}
			if (!logined) {
				StringBuilder sb = new StringBuilder("");
				for (Object key : req.getParameterMap().keySet()) {
					sb.append(String.format("%s=%s&", key, req.getParameter(key
							.toString())));
				}
				String page = String.format("login?%spath=%s", sb, req
						.getRequestURI());
				resp.sendRedirect(page);
			} else {
				super.service(req, resp);
			}
		}
	}

	public String getUsername() {
		Entity entity = users.get(this.req.getSession(true).getId());
		if (entity == null) {
			return null;
		} else {
			return entity.username;
		}
	}

	protected void setUser(String username) {
		Entity entity = new Entity();
		entity.username = username;
		entity.timer = System.currentTimeMillis();
		users.put(this.req.getSession(true).getId(), entity);
	}

	private class Entity {

		public String username;

		public Long timer;

	}

}
