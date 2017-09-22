
package breder.org.lang.gui.table;

import java.lang.reflect.Field;

import breder.jsql.Table;
import breder.jsql.annotation.ATable;

@ATable("inpa$person")
public class PersonTable extends Table {

	public static Field ID;

	public final Number id;

	public static Field TYPE;

	public final Number type;

	public static Field NAME;

	public final String name;

	public PersonTable() {
		this(null, null, null);
	}

	public PersonTable(Number id, Number type, String name) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
	}

}
