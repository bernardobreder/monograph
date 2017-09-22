package breder.installer.lang;

import breder.installer.lang.gui.WelcomeStepFrame;
import breder.installer.logic.InstallerConfig;

public class Installer extends InstallerConfig {

	public Installer() {
		this.addStepFrame(new WelcomeStepFrame());
	}

	@Override
	public String getName() {
		return "Breder Language";
	}

	@Override
	public boolean canFinish(int step) {
		return true;
	}

}
