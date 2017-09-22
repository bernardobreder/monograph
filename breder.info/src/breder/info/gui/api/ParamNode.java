package breder.info.gui.api;

import breder.info.logic.ParamDef;
import breder.util.swing.tree.AbstractTreeNode;

public class ParamNode extends ApiNode<ParamDef> {

	public ParamNode(AbstractTreeNode parent, ParamDef def) {
		super(parent);
		this.setElement(def);
	}

	@Override
	public AbstractTreeNode[] getChildren() {
		// if (this.getElement().getDescriber() != null) {
		// return new AbstractTreeNode[] { new SimpleAbstractTreeNode(this,
		// this.getElement().getDescriber()) };
		// } else {
		return new AbstractTreeNode[0];
		// }
	}

	@Override
	public String toString() {
		return this.getElement().getClassname() + " " + this.getElement().getName();
	}

}
