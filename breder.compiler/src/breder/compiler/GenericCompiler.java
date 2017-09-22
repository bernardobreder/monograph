
package breder.compiler;

import breder.compiler.compiler.AbstractCompiler;
import breder.compiler.exception.BParseException;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.TokenMgrError;

public abstract class GenericCompiler {

	public static void main(AbstractCompiler compiler, String classname) throws Throwable {
		compiler.addLibrary("breder_lang");
		try {
			compiler.start(classname);
		} catch (TokenMgrError e) {
			throw new BParseException(e, compiler.getContext());
		} catch (ParseException e) {
			throw new BParseException(e, compiler.getContext());
		}
	}

}
