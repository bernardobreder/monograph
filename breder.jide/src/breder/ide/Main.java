package breder.ide;

import javax.swing.SwingUtilities;

import breder.ide.lang.ProjectManager;
import breder.util.lookandfeel.LookAndFeel;

public class Main {

	public static void main(String[] args) {
		ProjectManager.getInstance().start();
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				LookAndFeel.getInstance().installNimbus();
				MainFrame.getInstance().setVisible(true);
			}
		});
	}

}
