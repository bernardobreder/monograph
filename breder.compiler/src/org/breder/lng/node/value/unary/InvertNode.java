package org.breder.lng.node.value.unary;

import java.io.IOException;

import org.breder.lng.node.AbstractNode;
import org.breder.lng.node.value.ValueNode;
import org.breder.lng.util.AbstractDataInputStream;
import org.breder.lng.util.AbstractDataOutputStream;
import org.breder.lng.util.AbstractOpcodeOutputStream;

public class InvertNode extends UnaryNode {

	public InvertNode(ValueNode left) {
		super(left);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(AbstractOpcodeOutputStream output) throws IOException {
		this.left.build(output);
		output.opBooleanNot();
	}

	@SuppressWarnings("unchecked")
	public static <E extends AbstractNode> E read(AbstractDataInputStream input)
			throws IOException {
		return (E) new InvertNode(AbstractNode.<ValueNode> read(input));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(AbstractDataOutputStream output) throws IOException {
		output.writeIndex(INVERT_VALUE);
		super.write(output);
	}

}
