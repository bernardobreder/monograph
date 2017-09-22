package breder.org.lang.gui.table.mail;

import java.lang.reflect.Field;
import java.util.Date;

import breder.jsql.Table;
import breder.jsql.annotation.ATable;

@ATable("infomarka$group_mail")
public class GroupMailTable extends Table {

	public static Field ID;

	public final Number id;

	public static Field PARENT_ID;

	public final Number parent_id;

	public static Field NAME;

	public final String name;

	public GroupMailTable() {
		this(null, null, null);
	}

	public GroupMailTable(Number id, Number parent_id, String name) {
		super();
		this.id = id;
		this.parent_id = parent_id;
		this.name = name;
	}

}
