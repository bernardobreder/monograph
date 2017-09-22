
package breder.test.main.task;

import java.io.File;

import breder.test.execute.BVMProcess;
import breder.test.execute.ExecuteFrame;
import breder.test.main.MainFrame;
import breder.test.main.TestUtil;
import breder.test.model.ITest;
import breder.util.task.RemoteTask;
import breder.util.util.FileUtil;

public class ExecuteTask extends RemoteTask {

	protected ITest test;

	private File dir;

	private BVMProcess process;

	public ExecuteTask() {
		this(null);
	}

	public ExecuteTask(ITest test) {
		super(2);
		this.test = test;
		this.setShowProcess(true);
	}

	@Override
	public boolean preAction() {
		if (test == null) {
			this.test = MainFrame.getInstance().getTest();
		}
		if (this.test == null) {
			return false;
		}
		return true;
	}

	@Override
	public void perform() throws Throwable {
		this.dir = new File("tmp-" + this.hashCode());
		try {
			TestUtil.compile(dir, test);
			File brederProfileFile = TestUtil.compileBrederVM(dir);
			this.process = TestUtil.execute(brederProfileFile, new File(dir, "binary.b"));
		} finally {
			FileUtil.remove(dir);
		}
	}

	@Override
	public void updateUI() {
		new ExecuteFrame(MainFrame.getInstance(), process).setVisible(true);
	}

	public void doFinally() {
		this.test = null;
	}

}
