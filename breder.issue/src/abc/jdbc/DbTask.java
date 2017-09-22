package abc.jdbc;

import java.rmi.RemoteException;

public abstract class DbTask<E> {

	public abstract E dbPerform(JDBC jdbc) throws RemoteException;

	public E start() throws RemoteException {
		synchronized (DbTask.class) {
			return this.dbPerform(JDBC.getInstance());
		}
	}

}
