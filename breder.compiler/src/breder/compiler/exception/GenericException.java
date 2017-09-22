package breder.compiler.exception;

import breder.compiler.compiler.Context;
import breder.compiler.parser.javacc.Token;

public class GenericException extends BrederJRuntimeException {

	public GenericException(Context context, Token token) {
		super(context, token, "number of generic is diferent");
	}

}
