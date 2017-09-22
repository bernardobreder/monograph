
package breder.test.create;

import breder.test.main.MainFrame;
import breder.test.model.ITest;
import breder.util.task.LocalTask;

public class CreateTask extends LocalTask {

	@Override
	public void updateUI() {
		ITest test = MainFrame.getInstance().getTest();
		new CreateFrame(MainFrame.getInstance(), test).setVisible(true);
	}

}
