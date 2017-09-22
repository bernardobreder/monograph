package breder.test.create;

import javax.swing.JOptionPane;

import breder.test.main.MainFrame;
import breder.test.main.task.CompileTask;
import breder.test.model.ITest;
import breder.test.model.TestModel;
import breder.util.task.RemoteTask;

public class BuildTestTask extends RemoteTask {

	private CreateFrame frame;

	private ITest test;

	public BuildTestTask(CreateFrame frame) {
		this.frame = frame;
	}

	@Override
	public boolean preAction() {
		this.test = frame.getBuildTest();
		if (this.test == null) {
			return false;
		}
		if (this.test.getName().length() == 0) {
			JOptionPane.showMessageDialog(frame, "name is empty");
			return false;
		}
		return true;
	}

	@Override
	public void perform() throws Throwable {
	}

	@Override
	public void updateUI() {
		TestModel.getInstance().addTest(this.test);
		MainFrame.getInstance().refresh();
		MainFrame.getInstance().openTest(this.test);
		new CompileTask(this.test).start();
		frame.close();
	}

}
