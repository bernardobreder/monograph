package breder.plugin.util;

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.internal.WorkbenchPage;

public class EditorUtil {

	public static boolean needSave() {
		IWorkbenchWindow windows[] = Workbench.getInstance()
				.getWorkbenchWindows();
		for (int i = 0; i < windows.length; i++) {
			IWorkbenchPage pages[] = windows[i].getPages();
			for (int j = 0; j < pages.length; j++) {
				WorkbenchPage page = (WorkbenchPage) pages[j];
				ISaveablePart[] parts = page.getDirtyParts();
				for (int k = 0; k < parts.length; k++) {
					ISaveablePart part = parts[k];

					if (part.isSaveOnCloseNeeded()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static void openFile(final IFile file) {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				IWorkbenchPage page = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage();
				try {
					IDE.openEditor(page, file);
				} catch (PartInitException e) {
				}
			}
		});
	}

}
