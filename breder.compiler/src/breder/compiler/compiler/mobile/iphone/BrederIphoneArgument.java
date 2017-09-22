package breder.compiler.compiler.mobile.iphone;

import breder.compiler.BrederArgument;
import breder.compiler.compiler.AbstractCompiler;
import breder.compiler.exception.NoArgumentInitializeException;

public class BrederIphoneArgument extends BrederArgument {

	public static final String NO_DEFAULT_LIBRARY = "no_default_library";
	public static final String OUTPUT = "op";
	public static final String CLASSPATH = "cp";
	public static final String BAR = "bar";
	public static final String LINKER = "l";
	public static final String NBH = "no_breder_home";

	public BrederIphoneArgument(String[] args) throws NoArgumentInitializeException {
		super(args);
	}

	@Override
	public String[] getParameters() {
		return new String[] { OUTPUT, CLASSPATH, LINKER, NO_DEFAULT_LIBRARY, NBH, BAR };
	}

	@Override
	public String configure(AbstractCompiler compiler) {
		IPhoneCompiler iphoneCompiler = (IPhoneCompiler) compiler;
		if (this.map.get(OUTPUT) != null) {
			iphoneCompiler.setOutputPath(this.map.get(OUTPUT).get(0));
		}
		return super.configure(compiler);
	}

}
