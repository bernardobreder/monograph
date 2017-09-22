package breder.compiler.exception;

import breder.compiler.compiler.Context;
import breder.compiler.parser.javacc.Token;

public class MultiReturnException extends BrederJRuntimeException {

	public MultiReturnException(Context context, Token token) {
		super(context, token, "value with more then one return");
	}

}
