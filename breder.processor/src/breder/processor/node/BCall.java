package breder.processor.node;

import breder.processor.parser.Token;

public class BCall extends BRValue {

	private final Token token;

	public BCall(Token token) {
		super();
		this.token = token;
	}

	@Override
	public Token getToken() {
		return token;
	}

}
