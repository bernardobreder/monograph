package breder.org.lang.gui.gui.trayicon;

import java.awt.SystemTray;
import java.awt.Window;

import javax.swing.JFrame;

import breder.util.task.LocalTask;

public class QuitTask extends LocalTask {

	@Override
	public void updateUI() {
		Window[] windows = JFrame.getWindows();
		for (Window window : windows) {
			window.dispose();
		}
		SystemTray.getSystemTray().remove(
				SystemTray.getSystemTray().getTrayIcons()[0]);
	}

}
