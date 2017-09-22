package breder.plugin.wizard.build.bclass;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import breder.plugin.BActivator;
import breder.plugin.BrederProjectConstant;
import breder.plugin.EclipseOptionPane;
import breder.plugin.element.BFile;
import breder.plugin.monitor.BProgressMonitor;
import breder.plugin.util.BTask;
import breder.plugin.util.EditorUtil;

public class BClassNewTask extends BTask {

	private BFile file;

	private final String sourcename;

	private final String packagename;

	private final String classname;

	public BClassNewTask(String sourcename, String packagename, String classname) {
		super();
		this.sourcename = sourcename;
		this.packagename = packagename;
		this.classname = classname;
	}

	@Override
	public void action() throws Exception {
		StringTokenizer token = new StringTokenizer(sourcename, "/");
		String projectName = token.nextToken();
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(
				projectName);
		if (!project.exists()) {
			EclipseOptionPane.showMessageInWizard(
					"Not found the BProject with name '%s'", projectName);
		}
		IFolder sourceFolder = project
				.getFolder(BrederProjectConstant.SOURCE_FOLDER);
		if (!sourceFolder.exists()) {
			EclipseOptionPane.showMessageInWizard(
					"Not found the BSource with name '%s' in the BProject",
					BrederProjectConstant.SOURCE_FOLDER, projectName);
		}
		IFolder packageFolder = sourceFolder;
		if (packagename.length() != 0) {
			for (String item : packagename.split("[.]")) {
				packageFolder = packageFolder.getFolder(item);
				if (!packageFolder.exists()) {
					packageFolder.create(true, true, BProgressMonitor.DEFAULT);
				}
			}
		}
		IFile classFile = packageFolder.getFile(classname
				+ BrederProjectConstant.BREDER_EXTENSION);
		if (classFile.exists()) {
			EclipseOptionPane.showMessageInWizard(
					"Class '%s' already exist in the BProject with name '%s'",
					classFile.getFullPath().toOSString(), projectName);
		}
		{
			Map<String, String> map = new HashMap<String, String>();
			map.put("class", classname);
			map.put("package", packagename);
			InputStream input = BActivator.getTemplate(
					BrederProjectConstant.TEMPLATE, map);
			classFile.create(input, true, BProgressMonitor.DEFAULT);
		}
		{
			EditorUtil.openFile(classFile);
		}
	}

	@Override
	public void updateUI() {
		IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		try {
			IDE.openEditor(page, file.getResource());
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

}
