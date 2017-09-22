package abc.jdbc;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bbreder
 */
public class JDBC {

	private static final JDBC instance = new JDBC();

	private Connection connection;

	private Object[][] queryImpl(String query, Object... objects)
			throws SQLException {
		List<Object[]> list = new ArrayList<Object[]>();
		PreparedStatement ps = this.getConnection().prepareStatement(query);
		for (int n = 0; n < objects.length; n++) {
			ps.setObject(n + 1, objects[n]);
		}
		boolean result;
		result = ps.execute();
		if (result) {
			ResultSet rs = ps.getResultSet();
			int cols = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				Object[] objs = new Object[cols];
				for (int n = 0; n < cols; n++) {
					objs[n] = rs.getObject(n + 1);
				}
				list.add(objs);
			}
		}
		ps.close();
		if (!result)
			return null;
		Object[][] r = new Object[list.size()][];
		for (int n = 0; n < list.size(); n++) {
			r[n] = list.get(n);
		}
		return r;
	}

	public Object[][] query(String query, Object... objects)
			throws RemoteException {
		if (this.connection == null) {
			this.reconnect();
		}
		try {
			return this.queryImpl(query, objects);
		} catch (Exception e) {
			this.reconnect();
			try {
				return this.queryImpl(query, objects);
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
		}
	}

	/**
	 * Realiza a carga do connector com o banco de dados
	 * 
	 * @param driver
	 * @param url
	 * @param username
	 * @param password
	 */
	private void load(String driver, String url, String username,
			String password) {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			throw new Error(e);
		}
	}

	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * @return the instance
	 */
	public static JDBC getInstance() {
		if (instance.connection == null) {
			instance.reconnect();
		}
		return instance;
	}

	/**
	 * Realiza a ação de reconectar
	 */
	private void reconnect() {
		this.load("com.mysql.jdbc.Driver",
				"jdbc:mysql://mysql.breder.org/bernardobreder",
				"bernardobreder", "tepeguei");
	}

	public static void main(String[] args) throws IOException {
		Object[][] objects = JDBC.getInstance().query("SELECT * FROM PERSON");
	}

}
