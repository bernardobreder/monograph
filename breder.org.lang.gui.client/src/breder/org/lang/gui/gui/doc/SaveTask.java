package breder.org.lang.gui.gui.doc;

import breder.org.lang.gui.service.ServiceLocator;
import breder.org.lang.gui.service.document.IDocument;
import breder.util.task.WriterRemoteTask;

public class SaveTask extends WriterRemoteTask {

	private byte[] bytes;

	private IDocument doc;

	public SaveTask(IDocument doc, String text) {
		this.bytes = text.getBytes();
		this.doc = doc;
	}

	@Override
	public void lockPerform() throws Throwable {
		ServiceLocator.getInstance().getDocService()
		.setByte(doc.getId(), this.bytes);
	}

	@Override
	public void updateUI() {
	}

}
