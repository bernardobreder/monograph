package frame.tree.node;

import java.util.ArrayList;
import java.util.List;

import logic.struct.JPMethod;
import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.DirectoryTreeNode;

public class MethodTreeNode extends DirectoryTreeNode {

	private final JPMethod method;

	public MethodTreeNode(AbstractTreeNode parent, JPMethod method) {
		super(parent);
		this.method = method;
	}

	@Override
	public AbstractTreeNode[] getChildren() {
		List<AbstractTreeNode> list = new ArrayList<AbstractTreeNode>();
		list.add(new InstructionTreeNode(this, method));
		return list.toArray(new AbstractTreeNode[0]);
	}

	@Override
	public String toString() {
		return method.getName();
	}

}
