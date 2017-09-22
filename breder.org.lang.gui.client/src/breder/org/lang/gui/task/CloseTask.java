package breder.org.lang.gui.task;

import breder.util.task.IWindow;
import breder.util.task.LocalTask;

public class CloseTask extends LocalTask {

	private final IWindow window;

	public CloseTask(IWindow window) {
		super();
		this.window = window;
	}

	@Override
	public void updateUI() {
		this.window.close();
	}

}
