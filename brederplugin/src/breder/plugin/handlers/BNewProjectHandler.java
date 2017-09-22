package breder.plugin.handlers;

import org.eclipse.jface.wizard.IWizard;

import breder.plugin.wizard.build.bproject.BProjectNewWizard;

public class BNewProjectHandler extends ShortcutHandler {

	@Override
	public IWizard newInstance() {
		return new BProjectNewWizard();
	}

}
