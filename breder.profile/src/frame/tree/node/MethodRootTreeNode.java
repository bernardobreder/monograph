package frame.tree.node;

import java.util.ArrayList;
import java.util.List;

import logic.struct.JPMethod;
import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.DirectoryTreeNode;

public class MethodRootTreeNode extends DirectoryTreeNode {
	
	public MethodRootTreeNode(AbstractTreeNode parent) {
		super(parent);
	}
	
	@Override
	public AbstractTreeNode[] getChildren() {
		List<AbstractTreeNode> list = new ArrayList<AbstractTreeNode>();
		List<JPMethod> methods = RootTreeNode.getInstance().getBinary().getVm().getMethods();
		for (int n = 0; n < methods.size(); n++) {
			JPMethod method = methods.get(n);
			final int index = n;
			list.add(new MethodsTreeNode(this, method) {
				
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
		return "methods";
	}
	
}
