package breder.plugin.console;

import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IOConsole;

import breder.plugin.BResource;

public class BConsoleManager {

	private static final BConsoleManager instance = new BConsoleManager();

	private BConsoleManager() {
	}

	public BConsole buildConsole(String title, boolean replace) {
		IOConsole lastConsole = null;
		if (replace) {
			IConsole[] consoles = ConsolePlugin.getDefault()
					.getConsoleManager().getConsoles();
			for (IConsole console : consoles) {
				if (console.getImageDescriptor() == BResource.getInstance()
						.getPerspectiveDescriptor()) {
					if (console.getName().equals(title)) {
						if (console instanceof IOConsole) {
							lastConsole = (IOConsole) console;
							((IOConsole) console).clearConsole();
							break;
						}
					}
				}
			}
		}
		if (lastConsole == null) {
			lastConsole = new IOConsole(title, BResource.getInstance()
					.getPerspectiveDescriptor());
		}
		ConsolePlugin.getDefault().getConsoleManager().addConsoles(
				new IConsole[] { lastConsole });
		return new BConsole(lastConsole);
	}

	public static BConsoleManager getInstance() {
		return instance;
	}

}
