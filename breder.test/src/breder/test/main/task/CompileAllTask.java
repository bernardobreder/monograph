package breder.test.main.task;

import javax.swing.SwingUtilities;

import breder.test.main.MainFrame;
import breder.test.model.ITest;
import breder.test.model.TestModel;

public class CompileAllTask extends CompileTask {

	@Override
	public boolean preAction() {
		this.memoryTest = MainFrame.getInstance().getJMenuBar()
				.getMemoryTestOption().isSelected();
		return true;
	}

	@Override
	public void perform() throws Throwable {
		for (ITest test : TestModel.getInstance().getTests()) {
			test.setOk(null);
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainFrame.getInstance().refresh();
			}
		});
		for (ITest test : TestModel.getInstance().getTests()) {
			this.test = test;
			if (this.test.isEnabled()) {
				super.perform();
			}
		}
	}

}
