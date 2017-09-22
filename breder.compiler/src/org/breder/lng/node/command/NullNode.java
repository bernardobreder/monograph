package org.breder.lng.node.command;

import java.io.IOException;

import org.breder.lng.util.AbstractDataOutputStream;
import org.breder.lng.util.AbstractOpcodeOutputStream;

public class NullNode extends CommandNode {

	public static final NullNode INSTANCE = new NullNode();

	private NullNode() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(AbstractOpcodeOutputStream output) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(AbstractDataOutputStream output) throws IOException {
		output.writeIndex(NULL_CMD);
	}

	@Override
	public String toString() {
		return ";";
	}

}
