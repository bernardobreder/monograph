package frame.tree.node;

import java.util.ArrayList;
import java.util.List;

import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.DirectoryTreeNode;

public class ConstantTreeNode extends DirectoryTreeNode {
	
	public ConstantTreeNode(AbstractTreeNode parent) {
		super(parent);
	}
	
	@Override
	public AbstractTreeNode[] getChildren() {
		List<AbstractTreeNode> list = new ArrayList<AbstractTreeNode>();
		List<String> consts = RootTreeNode.getInstance().getBinary().getVm().getConstants();
		for (int n = 0; n < consts.size(); n++) {
			String constant = consts.get(n);
			final int index = n;
			list.add(new SimpleFileTreeNode(this, constant) {
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
		return "constant";
	}
	
}
