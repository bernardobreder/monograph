package breder.org.lang.gui.table;

import java.lang.reflect.Field;

import breder.jsql.Table;
import breder.jsql.annotation.ATable;

@ATable("inpa$user")
public class UserTable extends Table {
	
	public static Field ID;
	
	public final Number id;
	
	public static Field USERNAME;
	
	public final String username;
	
	public static Field PASSWORD;
	
	public final String password;
	
	public UserTable() {
		this(null, null, null);
	}
	
	public UserTable(Number id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
}
