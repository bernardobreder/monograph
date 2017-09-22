package breder.test.main.task;

import javax.swing.JOptionPane;

import breder.test.main.MainFrame;
import breder.test.model.ITest;
import breder.test.model.TestModel;
import breder.util.task.LocalTask;

public class DeleteTask extends LocalTask {

	@Override
	public void updateUI() {
		ITest[] tests = MainFrame.getInstance().getTreeTests();
		if (tests.length > 0) {
			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(
					MainFrame.getInstance(), "Do you want to remove tests ?",
					"Delete Task", JOptionPane.YES_NO_OPTION)) {
				for (ITest test : tests) {
					TestModel.getInstance().removeTest(test);
				}
				MainFrame.getInstance().refresh();
			}
		}
	}

}
