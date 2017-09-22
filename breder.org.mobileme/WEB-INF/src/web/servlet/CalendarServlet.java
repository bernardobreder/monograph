package web.servlet;

import java.io.IOException;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import breder.jsql.ITransaction;
import breder.jsql.access.MySqlConnection;
import breder.jsql.generic.Connection;

public class CalendarServlet extends HttpServlet {

	private Connection connection;

	public static final String CALENDAR = "iCal";

	@Override
	public void init() throws ServletException {
		try {
			connection = new MySqlConnection(null, "breder.org", "bernardobreder", "tepeguei".toCharArray());
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getRequestURI();
		ITransaction t = connection.start();
		try {
			this.doDelete(req, resp);
			PreparedStatement pt = t.getConnection().prepareCall("insert into storedb (name,content) values (?,?)");
			pt.setString(1, name);
			pt.setBlob(2, req.getInputStream());
			pt.execute();
			t.commit();
		} catch (Exception e) {
			t.rollback();
			throw new ServletException(e);
		}
		resp.getOutputStream().println();
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getRequestURI();
		ITransaction t = connection.start();
		boolean found = t.nativeSql("select 1 from storedb where name = ?", name).length == 1;
		if (found) {
			t.nativeSql("delete from storedb where name = ?", name);
		}
	}

}
