package breder.plugin;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class EclipseOptionPane {

	public static void showMessageInWizard(String format, Object... objects) {
		showMessage("Breder Launch", format, objects);
	}

	public static void showMessageInLaunch(String format, Object... objects) {
		showMessage("Breder Launch", format, objects);
	}

	public static void showMessage(final String title, final String format,
			final Object... objects) {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				Shell shell = PlatformUI.getWorkbench().getDisplay()
						.getActiveShell();
				MessageDialog.openInformation(shell, title, String.format(
						format, objects));
			}
		});
	}
}
