package breder.compiler.exception;

import breder.compiler.compiler.Context;
import breder.compiler.parser.javacc.Token;

public class BClassClastException extends BrederJRuntimeException {

	public BClassClastException(Context context, Token token) {
		super(context, token, "can not cast");
	}

}
