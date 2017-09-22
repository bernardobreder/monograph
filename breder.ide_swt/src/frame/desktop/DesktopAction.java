package frame.desktop;

import loader.LoaderApplication;
import engine.IDE;

public class DesktopAction extends DesktopInterface {

	@Override
	public void onNewFileAction() {
	}

	@Override
	public void onQuitAction() {
		IDE.getInstance().getDesktop().getShell().dispose();
	}

	@Override
	public void onInitWindowAction() {
		new LoaderApplication(IDE.getInstance().getDesktop()).start();
	}

}
