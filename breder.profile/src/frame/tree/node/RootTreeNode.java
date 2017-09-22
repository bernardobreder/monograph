package frame.tree.node;

import logic.Binary;
import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.DirectoryTreeNode;

public class RootTreeNode extends DirectoryTreeNode {
	
	private static final RootTreeNode instance = new RootTreeNode();
	
	private Binary binary;
	
	private RootTreeNode() {
		super(null);
	}
	
	@Override
	public AbstractTreeNode[] getChildren() {
		if (RootTreeNode.getInstance().getBinary() == null) {
			return new AbstractTreeNode[0];
		}
		return new AbstractTreeNode[] { new ConstantTreeNode(this), new ClassRootTreeNode(this),
				new MethodRootTreeNode(this), ProcessRootTreeNode.getInstance() };
	}
	
	public static RootTreeNode getInstance() {
		return instance;
	}
	
	public Binary getBinary() {
		return binary;
	}
	
	public void setBinary(Binary binary) {
		this.binary = binary;
	}
	
	@Override
	public String toString() {
		return "root";
	}
	
}
