package frame.tree.node;

import java.util.ArrayList;
import java.util.List;

import logic.struct.JPClass;
import logic.struct.JPMethod;
import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.DirectoryTreeNode;

public class ClassTreeNode extends DirectoryTreeNode {

	private final JPClass clazz;

	public ClassTreeNode(AbstractTreeNode parent, JPClass clazz) {
		super(parent);
		this.clazz = clazz;
	}

	@Override
	public AbstractTreeNode[] getChildren() {
		List<AbstractTreeNode> list = new ArrayList<AbstractTreeNode>();
		for (JPMethod method : clazz.getMethods()) {
			list.add(new MethodTreeNode(this, method));
		}
		return list.toArray(new AbstractTreeNode[0]);
	}

	@Override
	public String toString() {
		return clazz.getName();
	}

}
