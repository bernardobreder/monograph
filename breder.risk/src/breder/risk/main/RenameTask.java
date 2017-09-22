package breder.risk.main;

import javax.swing.JOptionPane;

import breder.risk.model.IRisk;
import breder.risk.model.ModelLocator;
import breder.util.task.LocalTask;

public class RenameTask extends LocalTask {

	@Override
	public void updateUI() {
		IRisk[] risks = MainFrame.getInstance().getTreeRisks();
		if (risks.length > 0) {
			String oldName = risks[0].getName();
			for (IRisk risk : risks) {
				if (!risk.getName().equals(risks[0].getName())) {
					oldName = "";
					break;
				}
			}
			String name = JOptionPane.showInputDialog(MainFrame.getInstance(),
					"Which name do you want to Rename ?", oldName);
			if (name != null) {
				for (IRisk test : risks) {
					test.setName(name);
				}
				ModelLocator.getInstance().sort();
				ModelLocator.getInstance().save();
				MainFrame.getInstance().refresh();
			}
		}
	}

}
