package org.breder.lng.node.value.unary;

import java.io.IOException;

import org.breder.lng.node.value.ValueNode;
import org.breder.lng.util.AbstractDataOutputStream;

public abstract class UnaryNode extends ValueNode {

	protected ValueNode left;

	public UnaryNode(ValueNode left) {
		this.left = left;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(AbstractDataOutputStream output) throws IOException {
		this.left.write(output);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnaryNode other = (UnaryNode) obj;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.left.toString();
	}

}
