package breder.plugin.wizard.build.bpackage;

import java.util.StringTokenizer;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;

import breder.plugin.BrederProjectConstant;
import breder.plugin.EclipseOptionPane;
import breder.plugin.monitor.BProgressMonitor;
import breder.plugin.util.BTask;

public class BPackageNewTask extends BTask {

	private final String sourcename;

	private final String packagename;

	public BPackageNewTask(String sourcename, String packagename) {
		super();
		this.sourcename = sourcename;
		this.packagename = packagename;
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
	}

}
