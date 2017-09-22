package breder.test.main.task;

import javax.swing.JOptionPane;

import breder.test.main.MainFrame;
import breder.test.model.ITest;
import breder.test.model.TestModel;
import breder.util.task.LocalTask;

public class RenameTask extends LocalTask {

	@Override
	public void updateUI() {
		ITest[] tests = MainFrame.getInstance().getTreeTests();
		if (tests.length > 0) {
			String oldName = tests[0].getName();
			for (ITest test : tests) {
				if (!test.getName().equals(tests[0].getName())) {
					oldName = "";
					break;
				}
			}
			String name = JOptionPane.showInputDialog(
					"Which name do you want to Rename ?", oldName);
			if (name != null) {
				for (ITest test : tests) {
					test.setName(name);
				}
				TestModel.getInstance().sort();
				TestModel.getInstance().save();
				MainFrame.getInstance().refresh();
			}
		}
	}

}
