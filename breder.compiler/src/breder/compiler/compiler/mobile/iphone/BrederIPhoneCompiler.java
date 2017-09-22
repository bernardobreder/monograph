package breder.compiler.compiler.mobile.iphone;

import breder.compiler.BrederArgument;
import breder.compiler.GenericCompiler;
import breder.compiler.compiler.Compiler;

public class BrederIPhoneCompiler {

	public static void main(String[] args) throws Throwable {
		BrederArgument arguments = new BrederIphoneArgument(args);
		Compiler compiler = new IPhoneCompiler();
		String classname = arguments.configure(compiler);
		GenericCompiler.main(compiler,classname);
	}

}
