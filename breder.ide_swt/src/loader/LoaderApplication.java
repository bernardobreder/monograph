package loader;

import java.io.File;
import java.util.Vector;

import resource.IDEConfigurator;
import util.Task;
import frame.desktop.Desktop;

public class LoaderApplication extends Task {

	protected final Vector<LoaderWorkspace> apps = new Vector<LoaderWorkspace>();

	protected final Desktop desktop;

	public LoaderApplication(Desktop desktop) {
		this.desktop = desktop;
	}

	@Override
	public void action() {
		File dir = new File("app");
		if (dir.exists() && dir.isDirectory()) {
			for (File appDir : dir.listFiles()) {
				if (appDir.isDirectory()) {
					loadByDirectory(appDir);
				}
			}
		}
	}

	private void loadByDirectory(File dir) {
		File binDir = new File(dir, IDEConfigurator.BIN);
		if (binDir.exists() && binDir.isDirectory()) {
			File confDir = new File(binDir, "configurator");
			if (confDir.exists() && confDir.isDirectory()) {
				File file = new File(confDir, IDEConfigurator.MY_WORKSPACE
						+ ".class");
				if (file.exists() && file.isFile()) {
					LoaderWorkspace app = new LoaderWorkspace(this, dir);
					apps.add(app);
					app.start();
				}
			}
		}
	}

	public Vector<LoaderWorkspace> getApps() {
		return apps;
	}

	public Desktop getDesktop() {
		return desktop;
	}

}
