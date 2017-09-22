package breder.webservice.jdbc;

import java.util.Hashtable;
import java.util.Map;

import breder.jsql.IConnection;

public class DbConnection {
	
	private static final DbConnection instance = new DbConnection();
	
	private final Map<String, IConnection> connections = new Hashtable<String, IConnection>();
	
	private DbConnection() {
	}
	
	public void put(String name, IConnection connection) {
		this.connections.put(name, connection);
	}
	
	public IConnection get(String name) {
		return this.connections.get(name);
	}
	
	public IConnection get() {
		return this.get("");
	}
	
	public static DbConnection getInstance() {
		return instance;
	}
	
}
