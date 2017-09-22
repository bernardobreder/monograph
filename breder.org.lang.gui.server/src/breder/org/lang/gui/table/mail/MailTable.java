package breder.org.lang.gui.table.mail;

import java.lang.reflect.Field;
import java.util.Date;

import breder.jsql.Table;
import breder.jsql.annotation.ATable;

@ATable("infomarka$mail")
public class MailTable extends Table {

	public static Field ID;

	public final Number id;

	public static Field GROUP_ID;

	public final Number group_id;

	public static Field DATE;

	public final Date date;

	public static Field SUBJECT;

	public final String subject;

	public static Field TEXT;

	public final String text;

	public MailTable() {
		this(null, null, null, null, null);
	}

	public MailTable(Number id, Number group_id, Date date, String subject,
			String text) {
		super();
		this.id = id;
		this.group_id = group_id;
		this.date = date;
		this.subject = subject;
		this.text = text;
	}

}
