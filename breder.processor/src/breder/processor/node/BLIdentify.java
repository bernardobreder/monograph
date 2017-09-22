package breder.processor.node;

import breder.processor.parser.Token;

public class BLIdentify extends BLValue {

	private final Token token;

	public BLIdentify(Token token) {
		super();
		this.token = token;
	}

	@Override
	public Token getToken() {
		return token;
	}

}
