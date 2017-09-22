package breder.plugin.builder;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

import breder.plugin.BrederProjectConstant;
import breder.plugin.console.BConsole;
import breder.plugin.monitor.BProgressMonitor;
import breder.plugin.util.BTask;

public class BuildBrederTask extends BTask {

	private final IFile file;

	private BConsole console;

	public BuildBrederTask(IFile file, BConsole console) {
		super();
		this.file = file;
		this.console = console;
	}

	@Override
	public void action() throws Exception {
		IProject project = file.getProject();
		deleteMarkers(project);
		file.deleteMarkers(BBuilder.MARKER_TYPE, false, IResource.DEPTH_ZERO);
		project.getFolder(BrederProjectConstant.BINARY_FOLDER).getFile(
				"binary.b").delete(true, BProgressMonitor.DEFAULT);
		BCompiler compiler = new BCompiler(project, file, console);
		compiler.launch(".");
	}

	private void deleteMarkers(IResource file) {
		try {
			file.deleteMarkers(BBuilder.MARKER_TYPE, false,
					IResource.DEPTH_INFINITE);
		} catch (CoreException ce) {
		}
	}

	@Override
	public synchronized void start() {
		super.start();
	}

}
