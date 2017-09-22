package frame.tree.node;

import java.util.ArrayList;
import java.util.List;

import logic.struct.JPClass;
import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.DirectoryTreeNode;

public class ClassRootTreeNode extends DirectoryTreeNode {

	public ClassRootTreeNode(AbstractTreeNode parent) {
		super(parent);
	}

	@Override
	public AbstractTreeNode[] getChildren() {
		List<AbstractTreeNode> list = new ArrayList<AbstractTreeNode>();
		List<JPClass> clazzs = RootTreeNode.getInstance().getBinary().getVm()
				.getClasses();
		for (int n = 0; n < clazzs.size(); n++) {
			JPClass clazz = clazzs.get(n);
			final int index = n;
			list.add(new ClassTreeNode(this, clazz) {
				@Override
				public String toString() {
					return String.format("[%d] %s", index, super.toString());
				}
			});
		}
		return list.toArray(new AbstractTreeNode[0]);
	}

	@Override
	public String toString() {
		return "class";
	}

}
