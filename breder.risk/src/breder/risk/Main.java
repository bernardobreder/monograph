package breder.risk;

import java.io.File;

import javax.swing.SwingUtilities;

import breder.risk.main.MainFrame;
import breder.risk.model.ModelLocator;
import breder.util.lookandfeel.LookAndFeel;
import breder.util.util.FileUtil;

public class Main {

	public static void main(String[] args) {
		for (File dir : new File(".").listFiles()) {
			if (dir.isDirectory() && dir.getName().startsWith("tmp-")) {
				FileUtil.remove(dir);
			}
		}
		ModelLocator.getInstance();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				LookAndFeel.getInstance().installNimbus();
				MainFrame.getInstance().setVisible(true);
			}
		});
	}

}
