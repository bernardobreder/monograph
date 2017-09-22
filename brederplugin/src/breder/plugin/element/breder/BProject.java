package breder.plugin.element.breder;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

import breder.plugin.BrederProjectConstant;
import breder.plugin.element.BFolder;
import breder.plugin.element.Binary;
import breder.plugin.element.Element;
import breder.plugin.element.IBProject;
import breder.plugin.element.Workspace;
import breder.plugin.monitor.BProgressMonitor;
import breder.plugin.util.ArrayUtil;

public class BProject extends BFolder implements IBProject {

	private final IProject project;

	public BProject(Workspace parent, IProject project) {
		super(parent, project);
		this.project = project;
	}

	@Override
	protected Element[] doRefresh() {
		Element[] elements = super.doRefresh();
		try {
			IFolder[] sources = getSources(project);
			for (int n = 0; n < elements.length; n++) {
				Element element = elements[n];
				if (element instanceof BFolder) {
					BFolder bdirectory = (BFolder) element;
					if (ArrayUtil.find(sources, bdirectory.getResource()) != -1) {
						elements[n] = new BSource(this, bdirectory
								.getResource());
					} else if (bdirectory.getName().equals("bin")) {
						elements[n] = new Binary(this, bdirectory.getResource());
					}
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return elements;
	}

	@Override
	public IProject getResource() {
		return project;
	}

	public static IFolder[] getBars(IProject project) throws CoreException {
		return new IFolder[] { project.getFolder("bar") };
	}

	public static IFolder[] getSources(IProject project) throws CoreException {
		List<IFolder> sources = new ArrayList<IFolder>();
		String[] lines;
		lines = project.getDescription().getComment().split("\n");
		String prefix = "source : ";
		for (String line : lines) {
			if (line.startsWith(prefix)) {
				String name = line.substring(prefix.length());
				for (IResource resource : project.members()) {
					if (resource instanceof IFolder
							&& resource.getName().equals(name)) {
						sources.add((IFolder) resource);
						break;
					}
				}
			}
		}
		for (IResource resource : project.members()) {
			if (resource instanceof IFolder) {
				IFolder folder = (IFolder) resource;
				if (folder.getName().startsWith("src")
						&& !folder.getName().equals("src")) {
					sources.add(folder);
				}
			}
		}
		return sources.toArray(new IFolder[0]);
	}

	public static IFolder[] getSourceNativeFolders(IProject project)
			throws CoreException {
		IFolder nativeFolder = project
				.getFolder(BrederProjectConstant.SOURCE_NATIVE_FOLDER);
		if (!nativeFolder.exists()) {
			nativeFolder.create(true, true, BProgressMonitor.DEFAULT);
		}
		IResource[] resources = nativeFolder.members();
		List<IFolder> folders = new ArrayList<IFolder>();
		for (IResource resource : resources) {
			if (resource instanceof IFolder) {
				IFolder folder = (IFolder) resource;
				if (!folder.isHidden()) {
					folders.add((IFolder) resource);
				}
			}
		}
		return folders.toArray(new IFolder[folders.size()]);
	}

}
