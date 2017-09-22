package breder.org.lang.gui.table;

import java.lang.reflect.Field;

import breder.jsql.Table;
import breder.jsql.annotation.ATable;

@ATable("inpa$person_animal")
public class PersonAnimalRelTable extends Table {
	
	public static Field PERSON_ID;
	
	public final Number person_id;
	
	public static Field ANIMAL_ID;
	
	public final Number animal_id;
	
	public PersonAnimalRelTable() {
		this(null, null);
	}
	
	public PersonAnimalRelTable(Number person_id, Number animal_id) {
		super();
		this.person_id = person_id;
		this.animal_id = animal_id;
	}
	
}
