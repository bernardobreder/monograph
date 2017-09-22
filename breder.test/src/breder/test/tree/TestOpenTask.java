package breder.test.tree;

import breder.test.main.MainFrame;
import breder.test.model.ITest;
import breder.util.task.LocalTask;

public class TestOpenTask extends LocalTask {

	private ITest test;

	public TestOpenTask(ITest test) {
		this.test = test;
	}

	@Override
	public void updateUI() {
		MainFrame.getInstance().openTest(test);
	}

}
