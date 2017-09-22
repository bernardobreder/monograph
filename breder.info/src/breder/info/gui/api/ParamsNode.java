package breder.info.gui.api;

import java.util.List;

import breder.info.logic.MethodDef;
import breder.info.logic.ParamDef;
import breder.util.swing.tree.AbstractTreeNode;

public class ParamsNode extends ApiNode<MethodDef> {

	public ParamsNode(AbstractTreeNode parent, MethodDef def) {
		super(parent);
		this.setElement(def);
	}

	@Override
	public AbstractTreeNode[] getChildren() {
		List<ParamDef> list = this.getElement().getParams();
		AbstractTreeNode[] l = new AbstractTreeNode[list.size()];
		for (int n = 0; n < l.length; n++) {
			l[n] = new ParamNode(this, list.get(n));
		}
		return l;
	}

	@Override
	public String toString() {
		return "params";
	}

}
