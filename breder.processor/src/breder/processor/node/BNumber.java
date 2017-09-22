package breder.processor.node;

import breder.processor.parser.Token;

public class BNumber extends BRValue {

	private final Token token;

	public BNumber(Token token) {
		super();
		this.token = token;
	}

	@Override
	public Token getToken() {
		return this.token;
	}

}
