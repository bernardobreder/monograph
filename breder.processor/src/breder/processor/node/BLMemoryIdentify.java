package breder.processor.node;

import breder.processor.parser.Token;

public class BLMemoryIdentify extends BLIdentify {

	private final BRValue index;

	public BLMemoryIdentify(Token token, BRValue index) {
		super(token);
		this.index = index;
	}

}
