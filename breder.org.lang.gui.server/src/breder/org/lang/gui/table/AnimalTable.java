package breder.org.lang.gui.table;

import java.lang.reflect.Field;
import java.util.Date;

import breder.jsql.Table;
import breder.jsql.annotation.ATable;

@ATable("inpa$animal")
public class AnimalTable extends Table {
	
	public static Field ID;
	
	public final Number id;
	
	public static Field NAME;
	
	public final String name;
	
	public static Field BURN;
	
	public final Date burn;
	
	public AnimalTable() {
		this(null, null, null);
	}
	
	public AnimalTable(Number id, String name, Date burn) {
		super();
		this.id = id;
		this.name = name;
		this.burn = burn;
	}
	
}
