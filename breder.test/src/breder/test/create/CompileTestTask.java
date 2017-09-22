package breder.test.create;

import breder.test.main.task.CompileTask;

public class CompileTestTask extends CompileTask {

	protected CreateFrame frame;

	public CompileTestTask(CreateFrame frame) {
		this.frame = frame;
	}

	@Override
	public boolean preAction() {
		this.test = frame.getBuildTest();
		frame.setConsoleText("");
		return this.test != null;
	}

	@Override
	public void updateUI() {
		this.frame.cleanConsole();
		this.frame.appendConsole(text);
	}

}
