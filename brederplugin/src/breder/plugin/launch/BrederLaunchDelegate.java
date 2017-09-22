package breder.plugin.launch;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;

import breder.plugin.BrederProjectConstant;
import breder.plugin.EclipseOptionPane;

public class BrederLaunchDelegate implements ILaunchConfigurationDelegate {

	@Override
	public void launch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {
		String projectname = configuration.getAttribute(
				BrederProjectConstant.PROJECT_NAME, (String) null);
		if (projectname == null) {
			EclipseOptionPane
					.showMessageInLaunch(
							"Launch Configuration '%s' do not have a projet associated",
							configuration.getName());
			return;
		}
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(
				projectname);
		if (project == null) {
			EclipseOptionPane.showMessageInLaunch(
					"Not found the BProject with name '%s'", projectname);
			return;
		}
		new RunTask(project, launch, monitor).run();
	}

}
