package org.breder.lng.node.value.unary;

import java.io.IOException;

import org.breder.lng.node.AbstractNode;
import org.breder.lng.node.value.ValueNode;
import org.breder.lng.util.AbstractDataInputStream;
import org.breder.lng.util.AbstractDataOutputStream;

public class PosDecNode extends UnaryNode {

	public PosDecNode(ValueNode left) {
		super(left);
	}

	@SuppressWarnings("unchecked")
	public static <E extends AbstractNode> E read(AbstractDataInputStream input)
			throws IOException {
		return (E) new PosDecNode(AbstractNode.<ValueNode> read(input));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(AbstractDataOutputStream output) throws IOException {
		output.writeIndex(POS_DEC_VALUE);
		super.write(output);
	}

}
