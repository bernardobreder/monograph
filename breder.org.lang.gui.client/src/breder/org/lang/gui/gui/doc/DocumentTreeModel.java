package breder.org.lang.gui.gui.doc;

import breder.util.swing.tree.BTreeModel;

public class DocumentTreeModel extends BTreeModel {

	public DocumentTreeModel() {
		super(new DocumentTreeNode(null, null));
	}

}
