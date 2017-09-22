package breder.info.gui.api;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import breder.info.logic.ClassDef;
import breder.info.logic.StructLocator;
import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.DirectoryTreeNode;

public class ApiModel extends DirectoryTreeNode {

	public ApiModel() {
		super(null);
	}

	@Override
	public AbstractTreeNode[] getChildren() {
		List<ClassDef> list = StructLocator.getInstance().getApi().getClasses();
		AbstractTreeNode[] l = new AbstractTreeNode[list.size()];
		for (int n = 0; n < l.length; n++) {
			l[n] = new ClassApiNode(this, list.get(n));
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
		return "root";
	}

}
