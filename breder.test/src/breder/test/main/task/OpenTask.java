package breder.test.main.task;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import breder.test.BrederTestConfigurator;
import breder.test.main.MainFrame;
import breder.test.model.TestModel;
import breder.util.io.HomeFile;
import breder.util.task.RemoteTask;

public class OpenTask extends RemoteTask {

	private final JFrame frame;
	private File file;

	public OpenTask(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public boolean preAction() {
		JFileChooser chooser = new JFileChooser(new HomeFile());
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(this.frame)) {
			this.file = chooser.getSelectedFile();
			return true;
		}
		return false;
	}

	@Override
	public void perform() throws Throwable {
		TestModel.getInstance().load(file);
		BrederTestConfigurator.getInstance()
				.setFilename(file.getAbsolutePath());
		BrederTestConfigurator.getInstance().save();
	}

	@Override
	public void updateUI() {
		MainFrame.getInstance().refresh();
	}

}
