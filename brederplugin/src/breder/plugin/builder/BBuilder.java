package breder.plugin.builder;

import java.io.File;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;

import breder.plugin.BrederProjectConstant;
import breder.plugin.console.BConsole;
import breder.plugin.console.BConsoleManager;
import breder.plugin.monitor.BProgressMonitor;
import breder.plugin.util.FileUtil;
import breder.plugin.util.SoUtil;
import breder.plugin.wizard.build.bproject.BProjectNewTask;

public class BBuilder extends IncrementalProjectBuilder {

	public static final String BUILDER_ID = "brederplugin.sampleBuilder";

	public static final String MARKER_TYPE = "brederplugin.xmlProblem";

	@Override
	protected void clean(IProgressMonitor monitor) throws CoreException {
		IFolder binaryFolder = this.getProject().getFolder(
				BrederProjectConstant.BINARY_FOLDER);
		File[] files = FileUtil.list(binaryFolder.getLocation().toFile(),
				SoUtil.getLibraryExtensionWithoutDot());
		for (File file : files) {
			binaryFolder.getFile(file.getName()).delete(true,
					BProgressMonitor.DEFAULT);
		}
	}

	protected IProject[] build(int kind, Map args, IProgressMonitor monitor)
			throws CoreException {
		IProject project = this.getProject();
		if (!this.hasBeenBuilt(project)) {
			IFolder sourceFolder = (IFolder) project
					.findMember(BrederProjectConstant.SOURCE_FOLDER);
			if (sourceFolder == null) {
				return null;
			}
			IFile mainFile = (IFile) sourceFolder.findMember("Main.breder");
			if (mainFile == null) {
				return null;
			}
			this.buildLaunchConfiguration(project, monitor);
		} else {
			IResourceDelta deltas = getDelta(getProject());
			for (IResourceDelta delta : deltas.getAffectedChildren()) {
				if (delta.getResource().getName().equals(
						BrederProjectConstant.SOURCE_FOLDER)) {
					this.buildLaunchConfiguration(project, monitor);
				}
			}
		}
		return null;
	}

	private IProject[] buildLaunchConfiguration(IProject project,
			IProgressMonitor monitor) throws CoreException {
		for (ILaunchConfiguration conf : DebugPlugin.getDefault()
				.getLaunchManager().getLaunchConfigurations()) {
			String projectName = conf.getAttribute(
					BrederProjectConstant.PROJECT_NAME, "");
			if (projectName.equals(project.getName())) {
				String classname = conf.getAttribute(
						BrederProjectConstant.MAIN_CLASS_NAME, "");
				IFile file = FileUtil.getFile(project, classname);
				if (file.exists()) {
					BConsole console = BConsoleManager.getInstance()
							.buildConsole("Breder Compiler", true);
					new BuildNativeTask(project, console).run();
					new BuildBrederTask(file, console).run();
					project.getFolder(BrederProjectConstant.BINARY_FOLDER)
							.refreshLocal(IResource.DEPTH_INFINITE,
									BProgressMonitor.DEFAULT);
					return new IProject[] { project };
				} else {
					return new IProject[] { project };
				}
			}
		}
		BProjectNewTask.addLaunchConfig(project);
		return this.buildLaunchConfiguration(project, monitor);
	}

	public void checkResource(IResource resource, IProgressMonitor monitor) {
		if (resource instanceof IFile
				&& resource.getName().endsWith(
						BrederProjectConstant.BREDER_EXTENSION)) {
			IFile file = (IFile) resource;
			BConsole console = BConsoleManager.getInstance().buildConsole(
					"Breder Compiler", true);
			new BuildBrederTask(file, console).start();
		}
	}

	protected void fullBuild(final IProgressMonitor monitor)
			throws CoreException {
		try {
			getProject().accept(new SampleResourceVisitor(monitor));
		} catch (CoreException e) {
		}
	}

	protected void incrementalBuild(IResourceDelta delta,
			IProgressMonitor monitor) throws CoreException {
		delta.accept(new SampleDeltaVisitor(monitor));
	}

	private class SampleDeltaVisitor implements IResourceDeltaVisitor {

		private IProgressMonitor monitor;

		public SampleDeltaVisitor(IProgressMonitor monitor) {
			this.monitor = monitor;
		}

		public boolean visit(IResourceDelta delta) throws CoreException {
			IResource resource = delta.getResource();
			switch (delta.getKind()) {
			case IResourceDelta.ADDED:
				checkResource(resource, this.monitor);
				break;
			case IResourceDelta.REMOVED:
				checkResource(resource, this.monitor);
				break;
			case IResourceDelta.CHANGED:
				checkResource(resource, this.monitor);
				break;
			}
			return true;
		}
	}

	private class SampleResourceVisitor implements IResourceVisitor {

		private IProgressMonitor monitor;

		public SampleResourceVisitor(IProgressMonitor monitor) {
			this.monitor = monitor;
		}

		public boolean visit(IResource resource) {
			checkResource(resource, this.monitor);
			return true;
		}

	}

}
