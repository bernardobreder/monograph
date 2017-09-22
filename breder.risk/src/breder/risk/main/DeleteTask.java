package breder.risk.main;

import javax.swing.JOptionPane;

import breder.risk.model.IRisk;
import breder.risk.model.ModelLocator;
import breder.util.task.LocalTask;

public class DeleteTask extends LocalTask {

	@Override
	public void updateUI() {
		IRisk[] risks = MainFrame.getInstance().getTreeRisks();
		if (risks != null) {
			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(
					MainFrame.getInstance(),
					"Do you want to remove the risks ?", "Delete Task",
					JOptionPane.YES_NO_OPTION)) {
				ModelLocator.getInstance().removeRisks(risks);
				MainFrame.getInstance().refresh();
			}
		}
	}

}
