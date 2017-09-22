package breder.compiler.exception;

import breder.compiler.compiler.Context;
import breder.compiler.node.standart.BSource;
import breder.compiler.parser.javacc.Token;

public class BrederJRuntimeException extends RuntimeException {

	public BrederJRuntimeException(String message) {
		super(message);
	}

	public BrederJRuntimeException(Context context, Token token,
			String message, Object... objects) {
		super(token != null ? String.format("['%s','%d','%d','%s'] : %s",
				context.getClassname(), token.beginLine, token.beginColumn,
				token.image, String.format(message, objects)) : String.format(
				"['%s'] : %s", context.getClassname(),
				String.format(message, objects)));
	}

	public BrederJRuntimeException(Context context, BSource source,
			Token token, String message, Object... objects) {
		super(token != null ? String.format("['%s','%d','%d','%s'] : %s",
				source.getClassname(), token.beginLine, token.beginColumn,
				token.image, String.format(message, objects)) : String.format(
				"['%s'] : %s", source.getClassname(),
				String.format(message, objects)));
	}

}
