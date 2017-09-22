package breder.org.lang.gui.table;

import java.lang.reflect.Field;

import breder.jsql.Table;
import breder.jsql.annotation.AColumn;
import breder.jsql.annotation.ATable;

@ATable("Servicos")
public class ServiceTable extends Table {

	@AColumn("id_servico")
	public final Number id;
	public static Field ID;

	@AColumn("cod_servico")
	public final String codigo;
	public static Field CODIGO;

	@AColumn("servico")
	public final String servico;
	public static Field SERVICO;

	@AColumn("Valor_Socio")
	public final Number socio;
	public static Field SOCIO;

	@AColumn("Valor_Particular")
	public final Number particular;
	public static Field PARTICULAR;

	@AColumn("Descontinuado")
	public final String descontinuado;
	public static Field DESCONTINUADO;

	@AColumn("valor_socio_prata")
	public final Number prata;
	public static Field PRATA;

	public ServiceTable() {
		this(null, null, null, null, null, null, null);
	}

	public ServiceTable(Number id, String codigo, String servico, Number socio,
			Number particular, String descontinuado, Number prata) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.servico = servico;
		this.socio = socio;
		this.particular = particular;
		this.descontinuado = descontinuado;
		this.prata = prata;
	}

}
