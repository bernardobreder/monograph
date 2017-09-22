package breder.risk.view;

import breder.risk.main.MainFrame;
import breder.risk.model.IRisk;
import breder.util.task.LocalTask;

public class ViewTask extends LocalTask {

	private IRisk risk;

	public ViewTask(IRisk risk) {
		super();
		this.risk = risk;
	}

	@Override
	public void updateUI() {
		new ViewFrame(MainFrame.getInstance(), risk).setVisible(true);
	}

}
