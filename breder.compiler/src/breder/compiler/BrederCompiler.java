
package breder.compiler;

import breder.compiler.exception.BrederJRuntimeException;

public abstract class BrederCompiler {

	public static void main(String[] args) {
		try {
			BrederJCompiler.main(args);
		} catch (BrederJRuntimeException e) {
			System.err.println(e.getMessage());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
