package breder.org.lang.gui.table;

import java.lang.reflect.Field;

import breder.jsql.Table;
import breder.jsql.annotation.AColumn;
import breder.jsql.annotation.ATable;

@ATable("Usuarios")
public class UsuariosTable extends Table {

	public static Field ID;

	@AColumn("id_user")
	public final Number id;

	public static Field GRP_USER;

	@AColumn("grp_user")
	public final String group;

	public static Field USER;

	@AColumn("user")
	public final String username;

	public static Field SENHA;

	@AColumn("senha")
	public final String password;

	public UsuariosTable() {
		this(null, null, null, null);
	}

	public UsuariosTable(Number id, String group, String username,
			String password) {
		super();
		this.id = id;
		this.group = group;
		this.username = username;
		this.password = password;
	}

}
