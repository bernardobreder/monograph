package breder.org.lang.gui.gui.doc;

import java.util.ArrayList;
import java.util.List;

import breder.org.lang.gui.service.ServiceLocator;
import breder.org.lang.gui.service.document.IDocument;
import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.RemoteTreeNode;

public class DocumentTreeNode extends RemoteTreeNode {

	private IDocument doc;

	public DocumentTreeNode(AbstractTreeNode parent, IDocument doc) {
		super(parent);
		this.doc = doc;
	}

	@Override
	public AbstractTreeNode[] getRemoteChildren() throws Exception {
		IDocument[] paths;
		if (this.doc == null) {
			paths = ServiceLocator.getInstance().getDocService().list(null);
		} else {
			paths = ServiceLocator.getInstance().getDocService()
					.list(this.doc.getId());
		}
		List<AbstractTreeNode> list = new ArrayList<AbstractTreeNode>();
		for (IDocument path : paths) {
			list.add(new DocumentTreeNode(this, path));
		}
		return list.toArray(new AbstractTreeNode[0]);
	}

	public IDocument getDoc() {
		return doc;
	}

	@Override
	public String toString() {
		if (doc == null) {
			return "";
		} else {
			return doc.getName();
		}
	}

}
