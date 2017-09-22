package breder.test.main.task;

import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JOptionPane;

import breder.test.main.MainFrame;
import breder.test.model.ITest;
import breder.test.model.Source;
import breder.util.task.RemoteTask;

public class DeployTask extends RemoteTask {

	private ITest test;
	private File classpath;

	@Override
	public boolean preAction() {
		this.test = MainFrame.getInstance().getTest();
		this.classpath = new File("../breder.compiler/tst/breder/test");
		if (!this.classpath.exists()) {
			return false;
		}
		return this.test != null;
	}

	@Override
	public void perform() throws Throwable {
		File[] files = this.classpath.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isFile() && !file.isHidden()) {
					file.delete();
				}
			}
		}
		for (Source source : this.test.getSources()) {
			String name = source.getName().substring(
					source.getName().lastIndexOf('.') + 1)
					+ ".breder";
			FileOutputStream output = new FileOutputStream(new File(
					this.classpath, name));
			String code = source.getCode().trim();
			if (code.startsWith("package") && code.indexOf(";") != -1) {
				int index = code.indexOf(";");
				code = "package breder.test;" + code.substring(index + 1);
			}
			output.write(code.getBytes());
			output.close();
		}
	}

	@Override
	public void updateUI() {
		JOptionPane.showMessageDialog(MainFrame.getInstance(),
				"Deploy concluded");
	}

}
