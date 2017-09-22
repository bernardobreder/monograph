package breder.org.lang.gui.gui.doc;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import breder.org.lang.gui.service.document.IDocument;
import breder.util.swing.tree.BTree;

public class DocumentTree extends BTree {

	private IDocument oldDoc;

	public DocumentTree() {
		super(new DocumentTreeModel());
		this.getSelectionModel().addTreeSelectionListener(
				new TreeSelectionListener() {
					@Override
					public void valueChanged(TreeSelectionEvent e) {
						onSelectionChange();
					}
				});
	}

	@Override
	public DocumentTreeNode getSelectNode() {
		return (DocumentTreeNode) super.getSelectNode();
	}

	protected void onSelectionChange() {
		if (this.oldDoc != null
				&& DocumentFrame.getInstance().getArea().isChange()) {
			String text = DocumentFrame.getInstance().getArea().getText();
			new SaveTask(this.oldDoc, text).start();
		}
		this.oldDoc = DocumentFrame.getInstance().getTree().getSelectNode()
				.getDoc();
		new OpenTask().start();
	}
}
