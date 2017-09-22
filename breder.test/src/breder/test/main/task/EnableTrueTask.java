package breder.test.main.task;

import breder.test.main.MainFrame;
import breder.test.model.ITest;
import breder.test.model.TestModel;
import breder.util.task.LocalTask;

public class EnableTrueTask extends LocalTask {

	private ITest[] tests;

	@Override
	public boolean preAction() {
		this.tests = MainFrame.getInstance().getTreeTests();
		return this.tests.length > 0;
	}

	@Override
	public void updateUI() {
		for (ITest test : tests) {
			test.setEnabled(true);
			test.setOk(null);
		}
		TestModel.getInstance().save();
		for (ITest test : tests) {
			new CompileTask(test).start();
		}
	}

}
