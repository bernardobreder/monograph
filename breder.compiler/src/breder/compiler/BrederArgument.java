
package breder.compiler;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import breder.compiler.compiler.AbstractCompiler;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.exception.NoArgumentInitializeException;
import breder.compiler.util.LightArrayList;

public class BrederArgument {

	private String classname;

	protected final Map<String, List<String>> map = new HashMap<String, List<String>>();

	public static final String NO_DEFAULT_LIBRARY = "no_default_library";

	public static final String OUTPUT = "o";

	public static final String CLASSPATH = "cp";

	public static final String LINKER = "l";

	public static final String BAR = "bar";

	public static final String NBH = "no_breder_home";

	public static final String DESCRIBER = "describer";

	private String[] PARAMETERS = new String[] { OUTPUT, CLASSPATH, LINKER, NO_DEFAULT_LIBRARY, NBH, BAR, DESCRIBER };

	public BrederArgument(String[] args) throws NoArgumentInitializeException {
		this.build(args);
	}

	public String[] getParameters() {
		return PARAMETERS;
	}

	protected void build(String[] args) {
		if (args.length == 0) {
			throw new NoArgumentInitializeException();
		}
		if (args[0].startsWith("-")) {
			throw new NoArgumentInitializeException();
		}
		this.classname = args[0];
		for (int n = 1; n < args.length; n++) {
			if (args[n].startsWith("-")) {
				String key = args[n].substring(1);
				if (!Arrays.asList(this.getParameters()).contains(key)) {
					throw new NoArgumentInitializeException();
				}
				List<String> list = this.map.get(key);
				if (list == null) {
					list = new LightArrayList<String>();
					this.map.put(key, list);
				}
				if (n != args.length - 1) {
					if (!args[n + 1].startsWith("-")) {
						list.add(args[++n]);
					} else {
						list.add(null);
					}
				}
			} else {
				throw new NoArgumentInitializeException();
			}
		}
		if (this.map.get("cp") == null) {
			this.map.put("cp", Arrays.asList("."));
		}
		if (this.map.get("bar") == null) {
			this.map.put("bar", Arrays.asList("."));
		}
	}

	public void check() throws NoArgumentInitializeException {
		if (this.map.get("o") != null && this.map.get("o").size() > 1) {
			throw new NoArgumentInitializeException();
		}

	}

	public String configure(AbstractCompiler compiler) {
		if (this.map.get(NBH) == null) {
			String brederhomeEnv = System.getenv("BREDER_HOME");
			if (brederhomeEnv == null) {
				throw new BrederJRuntimeException("not found the BREDER_HOME environment variable");
			}
			File brederhome = new File(brederhomeEnv, "src");
			compiler.addClasspath(brederhome.getAbsolutePath());
		}
		if (this.map.get(CLASSPATH) != null) {
			for (String value : this.map.get(CLASSPATH)) {
				if (value == null) {
					throw new NoArgumentInitializeException();
				}
				compiler.addClasspath(value);
			}
		}
		if (this.map.get(LINKER) != null) {
			for (String value : this.map.get(LINKER)) {
				if (value == null) {
					throw new NoArgumentInitializeException();
				}
				compiler.addLibrary(value);
			}
		}
		if (this.map.get(NO_DEFAULT_LIBRARY) == null) {
			compiler.addLibrary("breder_util");
			compiler.addLibrary("breder_math");
			compiler.addLibrary("breder_io");
			//compiler.addLibrary("breder_compiler");
			//compiler.addLibrary("breder_gui_opengl");
			//compiler.addLibrary("breder_gui_sdl");
		}
		if (this.map.get(BAR) != null) {
			for (String value : this.map.get(BAR)) {
				if (value == null) {
					throw new NoArgumentInitializeException();
				}
				compiler.addBar(value);
			}
		}
		if (this.map.get(OUTPUT) != null) {
			compiler.setOutput(this.map.get(OUTPUT).get(0));
		}
		return this.classname;
	}

	public boolean isDescriber() {
		return this.map.get(DESCRIBER) != null;
	}

}
