package breder.info.task;

import javax.swing.JOptionPane;

import breder.info.gui.MainFrame;
import breder.info.logic.StructLocator;
import breder.util.lookandfeel.LookAndFeel;
import breder.util.task.RemoteTask;

public class StartTask extends RemoteTask {

	private boolean updated;

	@Override
	public void perform() throws Throwable {
		StructLocator.getInstance();
		{
			UpdaterTask task = new UpdaterSoftwareTask();
			task.start();
			// this.updated = task.isUpdated();
		}
		{
			UpdaterTask task = new UpdaterDatabaseTask();
			task.start();
		}
	}

	@Override
	public void updateUI() throws Throwable {
		LookAndFeel.getInstance().installNimbus();
		if (this.updated) {
			JOptionPane.showMessageDialog(null, "Software updated!\nIt better to restart the application.");
		}
		MainFrame.getInstance().setVisible(true);
	}

}
