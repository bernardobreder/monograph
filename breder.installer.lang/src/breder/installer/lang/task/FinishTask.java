package breder.installer.lang.task;

import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

import breder.installer.logic.IInstallTask;

public class FinishTask implements IInstallTask {

	@Override
	public void perform(JTextComponent label) throws Exception {
		{
			JOptionPane
					.showMessageDialog(
							null,
							"You have to restart the Operation System for complete the installing.\nThe Breder Language only work if you REBOOT the Operation System.",
							"Reboot", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@Override
	public String getName() {
		return "Finishing";
	}

}
