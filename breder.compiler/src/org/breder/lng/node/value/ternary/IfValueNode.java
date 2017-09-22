package org.breder.lng.node.value.ternary;

import java.io.IOException;

import org.breder.lng.node.AbstractNode;
import org.breder.lng.node.value.ValueNode;
import org.breder.lng.util.AbstractDataInputStream;
import org.breder.lng.util.AbstractDataOutputStream;
import org.breder.lng.util.AbstractOpcodeOutputStream;

public class IfValueNode extends TernaryNode {

	public IfValueNode(ValueNode left, ValueNode right, ValueNode center) {
		super(left, right, center);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(AbstractOpcodeOutputStream output) throws IOException {
		this.left.build(output);
		this.center.build(output);
		this.right.build(output);
		output.opStackTernary();
	}

	@SuppressWarnings("unchecked")
	public static <E extends AbstractNode> E read(AbstractDataInputStream input)
			throws IOException {
		return (E) new IfValueNode(AbstractNode.<ValueNode> read(input),
				AbstractNode.<ValueNode> read(input),
				AbstractNode.<ValueNode> read(input));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(AbstractDataOutputStream output) throws IOException {
		output.writeIndex(IF_VALUE);
		super.write(output);
	}

}
