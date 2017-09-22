package breder.plugin.launch;

import java.util.HashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.RuntimeProcess;
import org.eclipse.debug.internal.ui.views.console.ProcessConsole;
import org.eclipse.debug.ui.console.ConsoleColorProvider;
import org.eclipse.ui.internal.Workbench;

import breder.plugin.BrederProjectConstant;
import breder.plugin.EclipseOptionPane;
import breder.plugin.properties.ProjectPropertyPage;
import breder.plugin.util.BTask;
import breder.plugin.util.EditorUtil;

public class RunTask extends BTask {

	private final IProject project;

	private final ILaunch launch;

	private IProgressMonitor monitor;

	public RunTask(IProject project, ILaunch launch, IProgressMonitor monitor) {
		super();
		this.project = project;
		this.launch = launch;
		this.monitor = monitor;
	}

	@Override
	public void action() throws Exception {
		if (EditorUtil.needSave()) {
			Workbench.getInstance().saveAllEditors(false);
			project.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, null);
		}
		IFolder binaryFolder = project
				.getFolder(BrederProjectConstant.BINARY_FOLDER);
		binaryFolder.refreshLocal(IResource.DEPTH_INFINITE, this.monitor);
		IFile binaryFile = binaryFolder.getFile("binary.b");
		if (!binaryFile.exists()) {
			EclipseOptionPane.showMessageInLaunch(
					"Binary of the BProject '%s' not found", project.getName());
			return;
		}
		try {
			BrederProcess bprocess = new BrederProcess(project);
			bprocess.setOutput(binaryFile.getLocation().toOSString());
			{
				String prop = this.project
						.getPersistentProperty(new QualifiedName("",
								ProjectPropertyPage.LINKER_PATHS));
				if (prop != null && prop.trim().length() > 0) {
					String[] splits = prop.split(",");
					for (String split : splits) {
						bprocess.addLinkerPath(split);
					}
				}
			}
			bprocess.start(project.getLocation().toFile());
			IProcess eprocess = new RuntimeProcess(launch, bprocess
					.getProcess(), "b", new HashMap());
			ProcessConsole process = new ProcessConsole(eprocess,
					new ConsoleColorProvider());
		} catch (final Exception e) {
			EclipseOptionPane.showMessageInLaunch(e.getMessage());
		}
	}

}
