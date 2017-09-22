package breder.info.gui.api;

import java.util.ArrayList;
import java.util.List;

import breder.info.logic.MethodDef;
import breder.util.swing.tree.AbstractTreeNode;

public class MethodApiNode extends ApiNode<MethodDef> {

	public MethodApiNode(AbstractTreeNode parent, MethodDef def) {
		super(parent);
		this.setElement(def);
	}

	@Override
	public AbstractTreeNode[] getChildren() {
		List<AbstractTreeNode> l = new ArrayList<AbstractTreeNode>();
		if (this.getElement().getDescriber() != null) {
			l.add(new DescriberNode(this, this.getElement().getDescriber()));
		}
		if (this.getElement().getParams().size() > 0) {
			l.add(new ParamsNode(this, this.getElement()));
		}
		return l.toArray(new AbstractTreeNode[0]);
	}

	@Override
	public String toString() {
		return this.getElement().getName();
	}

}
