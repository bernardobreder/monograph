
package breder.compiler;

import breder.compiler.compiler.AbstractCompiler;
import breder.compiler.compiler.Compiler;
import breder.compiler.compiler.DescriberCompiler;

public abstract class BrederJCompiler {

	public static void main(String[] args) throws Throwable {
		BrederArgument arguments = new BrederArgument(args);
		AbstractCompiler compiler;
		if (arguments.isDescriber()) {
			compiler = new DescriberCompiler();
		} else {
			compiler = new Compiler();
		}
		String classname = arguments.configure(compiler);
		GenericCompiler.main(compiler, classname);
	}

}
