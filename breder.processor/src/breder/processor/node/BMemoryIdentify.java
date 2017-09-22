package breder.processor.node;

import breder.processor.parser.Token;

public class BMemoryIdentify extends BIdentify {

	private final BRValue index;

	public BMemoryIdentify(Token token, BRValue index) {
		super(token);
		this.index = index;
	}

}
