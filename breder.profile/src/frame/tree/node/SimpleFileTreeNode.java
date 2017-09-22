package frame.tree.node;

import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.FileTreeNode;

public class SimpleFileTreeNode extends FileTreeNode {

	private final Object value;

	public SimpleFileTreeNode(AbstractTreeNode parent, Object value) {
		super(parent);
		this.value = value;
	}

	@Override
	public String toString() {
		return value.toString();
	}

}
