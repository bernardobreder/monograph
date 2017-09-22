package breder.ide.task;

import breder.ide.MainFrame;
import breder.util.task.LocalTask;

public class VariableRefreshTask extends LocalTask {
	
	@Override
	public void updateUI() throws Throwable {
		MainFrame.getInstance().getVariableTree().getModel().refresh();
	}
	
}
