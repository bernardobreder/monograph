package breder.org.lang.gui.gui.doc;

import javax.swing.JOptionPane;

import breder.org.lang.gui.service.ServiceLocator;
import breder.org.lang.gui.service.document.IDocument;
import breder.util.task.WriterRemoteTask;

public class NewTask extends WriterRemoteTask {

	private IDocument doc;
	private String name;

	@Override
	public boolean preAction() {
		if (DocumentFrame.getInstance().getTree().getSelectionPath() != null) {
			DocumentTreeNode node = (DocumentTreeNode) DocumentFrame
					.getInstance().getTree().getSelectionPath()
					.getLastPathComponent();
			doc = node.getDoc();
			this.name = JOptionPane.showInputDialog(
					DocumentFrame.getInstance(), "Name of Document");
			return true;
		}
		return false;
	}

	@Override
	public void lockPerform() throws Throwable {
		if (this.name != null) {
			ServiceLocator.getInstance().getDocService()
					.newDocument(doc.getId(), name, new byte[] { '\n' });
		}
	}

	@Override
	public void updateUI() {
		DocumentFrame.getInstance().getTree().getModel().refresh();
	}

}
