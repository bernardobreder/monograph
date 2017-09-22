package breder.test;

import java.io.File;

import javax.swing.SwingUtilities;

import breder.test.main.MainFrame;
import breder.test.model.TestModel;
import breder.util.lookandfeel.LookAndFeel;
import breder.util.util.FileUtil;

public class Main {

	public static void main(String[] args) {
		for (File dir : new File(".").listFiles()) {
			if (dir.isDirectory() && dir.getName().startsWith("tmp-")) {
				FileUtil.remove(dir);
			}
		}
		TestModel.getInstance();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				LookAndFeel.getInstance().installNimbus();
				MainFrame.getInstance().setVisible(true);
			}
		});
	}

}
