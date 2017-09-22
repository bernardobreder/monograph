package breder.plugin.launch;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.FileEditorInput;

import breder.plugin.BrederProjectConstant;
import breder.plugin.EclipseOptionPane;
import breder.plugin.builder.BNature;
import breder.plugin.monitor.BProgressMonitor;

public class BrederLaunchShortcut implements ILaunchShortcut {

	@Override
	public void launch(ISelection selection, String mode) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection s = (IStructuredSelection) selection;
			if (s.getFirstElement() instanceof IResource) {
				IResource file = (IResource) s.getFirstElement();
				this.launch(file.getProject(), mode);
			}
		}
	}

	@Override
	public void launch(IEditorPart editor, String mode) {
		IEditorInput input = editor.getEditorInput();
		if (input instanceof FileEditorInput) {
			FileEditorInput finput = (FileEditorInput) input;
			this.launch(finput.getFile().getProject(), mode);
		}
	}

	private void launch(IProject project, String mode) {
		try {
			if (project.getNature(BNature.NATURE_ID) == null) {
				return;
			}
			ILaunchConfiguration[] configs = DebugPlugin.getDefault()
					.getLaunchManager().getLaunchConfigurations();
			for (ILaunchConfiguration config : configs) {
				if (config.getAttribute(BrederProjectConstant.PROJECT_NAME, "")
						.equals(project.getName())) {
					config.launch(mode, BProgressMonitor.DEFAULT);
					return;
				}
			}
			EclipseOptionPane
					.showMessageInLaunch("Not found Launch Configuration");
		} catch (Exception e) {
			EclipseOptionPane.showMessageInLaunch(e.getMessage());
		}
	}

}
