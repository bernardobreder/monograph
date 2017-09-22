package breder.org.lang.gui.service;

import javax.servlet.ServletException;

import breder.jsql.IConnection;
import breder.jsql.access.MySqlConnection;
import breder.util.swing.BOptionPane;
import breder.webservice.IServerService;
import breder.webservice.jdbc.DbConnection;
import breder.webservice.servlet.ServiceServlet;

/**
 * Servlet que implementa o web server
 * 
 */
public class LangServiceServlet extends ServiceServlet {

	@Override
	public void init() throws ServletException {
		IServerService.DEFAULT.getConfig().setSessionTimeout(5000);
		super.init();
		try {
			IConnection c = new MySqlConnection("breder.org", "bernardobreder",
					BOptionPane.showPasswordDialog(null, "Password",
							"mysql.breder.org"));
			DbConnection.getInstance().put("", c);
		} catch (Exception e) {
			throw new Error(e);
		}
	}

}
