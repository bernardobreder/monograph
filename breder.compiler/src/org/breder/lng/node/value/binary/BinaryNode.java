package org.breder.lng.node.value.binary;

import java.io.IOException;

import org.breder.lng.node.value.ValueNode;
import org.breder.lng.node.value.unary.UnaryNode;
import org.breder.lng.util.AbstractDataOutputStream;

public abstract class BinaryNode extends UnaryNode {

	protected ValueNode right;

	public BinaryNode(ValueNode left, ValueNode right) {
		super(left);
		this.right = right;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(AbstractDataOutputStream output) throws IOException {
		super.write(output);
		this.right.write(output);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + right.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BinaryNode other = (BinaryNode) obj;
		if (!right.equals(other.right))
			return false;
		return true;
	}

}
