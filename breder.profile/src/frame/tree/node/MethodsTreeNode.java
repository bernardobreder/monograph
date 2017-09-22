package frame.tree.node;

import logic.struct.JPMethod;
import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.FileTreeNode;

public class MethodsTreeNode extends FileTreeNode {
	
	private final JPMethod method;
	
	public MethodsTreeNode(AbstractTreeNode parent, JPMethod method) {
		super(parent);
		this.method = method;
	}
	
	@Override
	public String toString() {
		return method.getName();
	}
	
}
