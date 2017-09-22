package breder.compiler.exception;

import breder.compiler.compiler.Context;
import breder.compiler.parser.javacc.Token;

public class OperatorUndefinedException extends BrederJRuntimeException {

	public OperatorUndefinedException(Context context, Token token) {
		super(context, token, "operator undefined");
	}

}
