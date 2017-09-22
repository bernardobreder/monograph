package breder.test.main.task;

import java.io.File;

import javax.swing.SwingUtilities;

import breder.test.main.MainFrame;
import breder.test.main.TestUtil;
import breder.test.model.ITest;
import breder.util.task.RemoteTask;
import breder.util.util.FileUtil;

public class CompileTask extends RemoteTask {

	protected ITest test;

	protected String text;

	protected boolean memoryTest;

	public CompileTask() {
		this(null);
	}

	public CompileTask(ITest test) {
		super(5);
		this.test = test;

	}

	@Override
	public boolean preAction() {
		if (test == null) {
			ITest[] tests = MainFrame.getInstance().getTreeTests();
			for (ITest test : tests) {
				new CompileTask(test).start();
			}
			return false;
		}
		if (test == null) {
			this.test = MainFrame.getInstance().getTest();
		}
		if (this.test == null) {
			return false;
		} else {
			this.test.setOk(null);
			this.memoryTest = MainFrame.getInstance().getJMenuBar()
					.getMemoryTestOption().isSelected();
			MainFrame.getInstance().refresh();
			return this.test.isEnabled();
		}
	}

	@Override
	public void perform() throws Throwable {
		File dir = new File("tmp-" + this.hashCode());
		try {
			try {
				TestUtil.compile(dir, test);
				this.text = TestUtil.execute(dir, test);
				if (this.memoryTest && this.test.getOk() != null
						&& this.test.getOk() == true) {
					long memory = 0;
					int step = 32;
					do {
						TestUtil.execute(dir, test, memory);
						test.setMemory(memory / 1024);
						MainFrame.getInstance().refresh();
						memory += step * 1024;
					} while (!this.test.getOk());
					memory -= 2 * step * 1024;
					do {
						TestUtil.execute(dir, test, memory);
						test.setMemory(memory / 1024);
						MainFrame.getInstance().refresh();
						memory += 1024;
					} while (!this.test.getOk());
				}
			} finally {
				FileUtil.remove(dir);
			}
		} catch (final Exception e) {
			String text = e.getMessage();
			this.text = text;
			this.test.setOk(TestUtil.expected(test, text));
			return;
		} finally {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					MainFrame.getInstance().refresh();
				}
			});
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainFrame.getInstance().cleanConsole();
			}
		});
	}

	@Override
	public void updateUI() {
		MainFrame.getInstance().cleanConsole();
		MainFrame.getInstance().appendConsole(text);
		MainFrame.getInstance().refresh();
	}

	@Override
	public void doFinally() {
		this.test = null;
	}

}
