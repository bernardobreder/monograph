package breder.org.lang.gui.table;

import java.lang.reflect.Field;

import breder.jsql.Table;
import breder.jsql.annotation.ATable;

@ATable("inpa$log")
public class LogTable extends Table {

	public static Field ID;

	public final Number id;

	public static Field USER_ID;

	public final Number user_id;

	public static Field MESSAGE;

	public final String message;

	public LogTable() {
		this(null, null, null);
	}

	public LogTable(String message) {
		this(null, message);
	}

	public LogTable(Number user, String message) {
		this(null, user, message);
	}

	public LogTable(Number id, Number user_id, String message) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.message = message;
	}

}
