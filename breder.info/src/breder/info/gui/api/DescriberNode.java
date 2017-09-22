package breder.info.gui.api;

import breder.util.swing.tree.AbstractTreeNode;

public class DescriberNode extends ApiNode<String> {

	public DescriberNode(AbstractTreeNode parent, String text) {
		super(parent);
		this.setElement(text);
	}

	@Override
	public AbstractTreeNode[] getChildren() {
		return new AbstractTreeNode[0];
	}

	@Override
	public String toString() {
		return "desc";
	}

}
