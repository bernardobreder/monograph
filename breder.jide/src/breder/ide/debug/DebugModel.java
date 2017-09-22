package breder.ide.debug;

import java.util.ArrayList;
import java.util.List;

import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.DirectoryTreeNode;

public class DebugModel extends DirectoryTreeNode {
	
	public DebugModel() {
		super(null);
	}
	
	@Override
	public AbstractTreeNode[] getChildren() {
		List<AbstractTreeNode> list = new ArrayList<AbstractTreeNode>();
		return list.toArray(new AbstractTreeNode[0]);
	}
	
	@Override
	public String toString() {
		return "";
	}
	
}
