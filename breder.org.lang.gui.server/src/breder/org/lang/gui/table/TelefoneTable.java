package breder.org.lang.gui.table;

import java.lang.reflect.Field;

import breder.jsql.Table;
import breder.jsql.annotation.AColumn;
import breder.jsql.annotation.ATable;

@ATable("Caderno_telefone")
public class TelefoneTable extends Table {

	public static Field ID;

	@AColumn("id_lancamento")
	public final Number id;

	public static Field NAME;

	@AColumn("Nome")
	public final String name;

	public static Field TELEFONE;

	@AColumn("telefone")
	public final String telefone;

	public static Field DESCRIBER;

	@AColumn("obs")
	public final String describer;

	public TelefoneTable() {
		this(null, null, null, null);
	}

	public TelefoneTable(Number id, String group, String username,
			String password) {
		super();
		this.id = id;
		this.name = group;
		this.telefone = username;
		this.describer = password;
	}

}
