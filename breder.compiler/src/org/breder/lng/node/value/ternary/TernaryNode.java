package org.breder.lng.node.value.ternary;

import java.io.IOException;

import org.breder.lng.node.value.ValueNode;
import org.breder.lng.node.value.binary.BinaryNode;
import org.breder.lng.util.AbstractDataOutputStream;

public abstract class TernaryNode extends BinaryNode {

	protected ValueNode center;

	public TernaryNode(ValueNode left, ValueNode right, ValueNode center) {
		super(left, right);
		this.center = center;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(AbstractDataOutputStream output) throws IOException {
		super.write(output);
		this.center.write(output);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + center.hashCode();
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
		TernaryNode other = (TernaryNode) obj;
		if (!center.equals(other.center))
			return false;
		return true;
	}

}
