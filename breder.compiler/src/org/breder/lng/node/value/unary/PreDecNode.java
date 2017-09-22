package org.breder.lng.node.value.unary;

import java.io.IOException;

import org.breder.lng.node.AbstractNode;
import org.breder.lng.node.value.ValueNode;
import org.breder.lng.util.AbstractDataInputStream;
import org.breder.lng.util.AbstractDataOutputStream;
import org.breder.lng.util.AbstractOpcodeOutputStream;

public class PreDecNode extends UnaryNode {

	public PreDecNode(ValueNode left) {
		super(left);
	}

	@SuppressWarnings("unchecked")
	public static <E extends AbstractNode> E read(AbstractDataInputStream input)
			throws IOException {
		return (E) new PreDecNode(AbstractNode.<ValueNode> read(input));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(AbstractOpcodeOutputStream output) {
		throw new RuntimeException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(AbstractDataOutputStream output) throws IOException {
		output.writeIndex(PRE_DEC_VALUE);
		super.write(output);
	}

}
