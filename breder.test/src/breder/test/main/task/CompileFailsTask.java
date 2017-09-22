package breder.test.main.task;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import breder.test.main.MainFrame;
import breder.test.model.ITest;
import breder.test.model.TestModel;

public class CompileFailsTask extends CompileTask {

	@Override
	public boolean preAction() {
		return true;
	}

	@Override
	public void perform() throws Throwable {
		List<ITest> tests = new ArrayList<ITest>();
		for (ITest test : TestModel.getInstance().getTests()) {
			if (test.getOk() != null && test.getOk() == false) {
				test.setOk(null);
				tests.add(test);
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						MainFrame.getInstance().refresh();
					}
				});
			}
		}
		for (ITest test : tests) {
			this.test = test;
			super.perform();
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					MainFrame.getInstance().refresh();
				}
			});
		}
	}

}
