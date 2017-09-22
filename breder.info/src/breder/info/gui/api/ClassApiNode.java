package breder.info.gui.api;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import breder.info.logic.ClassDef;
import breder.info.logic.MethodDef;
import breder.util.swing.tree.AbstractTreeNode;

public class ClassApiNode extends ApiNode<ClassDef> {

	public ClassApiNode(AbstractTreeNode parent, ClassDef def) {
		super(parent);
		this.setElement(def);
	}

	@Override
	public AbstractTreeNode[] getChildren() {
		List<MethodDef> list = this.getElement().getMethods();
		AbstractTreeNode[] l = new AbstractTreeNode[list.size()];
		for (int n = 0; n < l.length; n++) {
			l[n] = new MethodApiNode(this, list.get(n));
		}
		Arrays.sort(l, new Comparator<AbstractTreeNode>() {
			@Override
			public int compare(AbstractTreeNode o1, AbstractTreeNode o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		return l;
	}

	@Override
	public String toString() {
		return getElement().getName();
	}

}
