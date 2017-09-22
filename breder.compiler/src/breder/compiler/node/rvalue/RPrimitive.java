
package breder.compiler.node.rvalue;

import breder.compiler.compiler.Context;
import breder.compiler.parser.javacc.Token;

public abstract class RPrimitive extends RValue {

	private final Token token;

	public RPrimitive(Token token) {
		super();
		this.token = token;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void check(Context context) {
	}

	public Token getToken() {
		return token;
	}

}
