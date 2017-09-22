package breder.webservice.jdbc;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import breder.jsql.ITransaction;

public abstract class DbTask<E> {

	private static ThreadLocal<ITransaction> connections = new ThreadLocal<ITransaction>();

	protected abstract E dbPerform() throws Throwable;

	public E start() throws Throwable {
		ITransaction c = DbConnection.getInstance().get("").start();
		connections.set(c);
		try {
			try {
				E e = this.dbPerform();
				c.commit();
				return e;
			} catch (InvocationTargetException e) {
				Throwable t = e.getCause();
				while (t instanceof InvocationTargetException) {
					t = ((InvocationTargetException) t).getCause();
				}
				throw t;
			}
		} catch (Throwable e) {
			c.rollback();
			throw e;
		} finally {
			connections.set(null);
		}
	}

	public ITransaction getMainTransaction() {
		return connections.get();
	}

	protected Object[][] query(String query, Object... objects)
			throws SQLException {
		return connections.get().nativeSql(query, objects);
	}

}
