package breder.org.lang.gui.gui.trayicon;

import java.awt.Frame;

import javax.swing.JFrame;

import breder.org.lang.gui.gui.doc.DocumentFrame;
import breder.util.task.LocalTask;

public class DocumentTask extends LocalTask {

	@Override
	public void updateUI() {
		for (Frame frame : JFrame.getFrames()) {
			if (frame.getClass() == DocumentFrame.class) {
				frame.requestFocusInWindow();
				frame.requestFocus();
				return;
			}
		}
		new DocumentFrame().setVisible(true);
	}

}
