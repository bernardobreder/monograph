package breder.processor.node;

import breder.processor.parser.Token;

public class BIdentify extends BRValue {

	private final Token token;

	public BIdentify(Token token) {
		super();
		this.token = token;
	}

	@Override
	public Token getToken() {
		return token;
	}

}
