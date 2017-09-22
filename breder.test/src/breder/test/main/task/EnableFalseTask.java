package breder.test.main.task;

import breder.test.main.MainFrame;
import breder.test.model.ITest;
import breder.util.task.LocalTask;

public class EnableFalseTask extends LocalTask {

	private ITest[] tests;

	@Override
	public boolean preAction() {
		this.tests = MainFrame.getInstance().getTreeTests();
		return this.tests.length > 0;
	}

	@Override
	public void updateUI() {
		for (ITest test : tests) {
			test.setEnabled(false);
			new CompileTask(test).start();
		}
	}

}
