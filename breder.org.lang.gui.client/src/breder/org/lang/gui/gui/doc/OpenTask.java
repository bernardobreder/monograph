package breder.org.lang.gui.gui.doc;

import javax.swing.tree.TreePath;

import breder.org.lang.gui.service.ServiceLocator;
import breder.org.lang.gui.service.document.IDocument;
import breder.util.task.ReaderRemoteTask;

public class OpenTask extends ReaderRemoteTask {

	private IDocument doc;

	private byte[] bytes;

	@Override
	public boolean preAction() {
		TreePath path = DocumentFrame.getInstance().getTree()
				.getSelectionPath();
		if (path != null) {
			DocumentTreeNode node = (DocumentTreeNode) path
					.getLastPathComponent();
			this.doc = node.getDoc();
			return true;
		}
		return false;
	}

	@Override
	public void lockPerform() throws Throwable {
		this.bytes = ServiceLocator.getInstance().getDocService()
				.getBytes(this.doc.getId());
	}

	@Override
	public void updateUI() {
		if (this.bytes == null) {
			this.bytes = new byte[0];
		}
		DocumentFrame.getInstance().getArea().setText(new String(this.bytes));
	}

}
