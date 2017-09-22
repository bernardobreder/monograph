package breder.risk.create;

import breder.risk.main.MainFrame;
import breder.util.task.LocalTask;

public class CreateTask extends LocalTask {

	@Override
	public void updateUI() {
		new CreateFrame(MainFrame.getInstance()).setVisible(true);
	}

}
