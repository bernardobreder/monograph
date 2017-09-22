package breder.plugin.wizard.build.bproject;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;

import breder.plugin.BrederProjectConstant;
import breder.plugin.builder.BNature;
import breder.plugin.element.ElementManager;
import breder.plugin.monitor.BProgressMonitor;
import breder.plugin.util.BTask;
import breder.plugin.view.project.ProjectView;
import breder.plugin.wizard.build.bproject.template.AbstractTemplate;
import breder.plugin.wizard.build.bproject.template.TemplateProject;

public class BProjectNewTask extends BTask {

	private final String projectname;

	private int templateIndex;

	public BProjectNewTask(String projectname, int templateIndex) {
		this.projectname = projectname;
		this.templateIndex = templateIndex;
		if (this.templateIndex < 0) {
			this.templateIndex = 0;
		}
	}

	@Override
	public void action() throws CoreException {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(
				projectname);
		if (!project.exists()) {
			project.create(BProgressMonitor.DEFAULT);
		}
		if (!project.isOpen()) {
			project.open(BProgressMonitor.DEFAULT);
		}
		{
			IProjectDescription desc = project.getDescription();
			desc.setNatureIds(new String[] { BNature.NATURE_ID });
			project.setDescription(desc, BProgressMonitor.DEFAULT);
		}
		{
			{
				IFolder folder = project
						.getFolder(BrederProjectConstant.BINARY_FOLDER);
				if (!folder.exists()) {
					folder.create(true, true, BProgressMonitor.DEFAULT);
				}
			}
			{
				IFolder folder = project
						.getFolder(BrederProjectConstant.SOURCE_FOLDER);
				if (!folder.exists()) {
					folder.create(true, true, BProgressMonitor.DEFAULT);
				}
			}
			{
				IFolder folder = project
						.getFolder(BrederProjectConstant.SOURCE_NATIVE_FOLDER);
				if (!folder.exists()) {
					folder.create(true, true, BProgressMonitor.DEFAULT);
				}
			}
			{
				IFolder folder = project
						.getFolder(BrederProjectConstant.BAR_FOLDER);
				if (!folder.exists()) {
					folder.create(true, true, BProgressMonitor.DEFAULT);
				}
			}
			{
				IFolder folder = project
						.getFolder(BrederProjectConstant.TEST_FOLDER);
				if (!folder.exists()) {
					folder.create(true, true, BProgressMonitor.DEFAULT);
				}
			}
		}
		{
			String comment = project.getDescription().getComment();
			comment = comment
					+ String.format("source : %s\n",
							BrederProjectConstant.SOURCE_FOLDER);
			IProjectDescription desc = project.getDescription();
			desc.setComment(comment);
			project.setDescription(desc, null);
		}
		{
			AbstractTemplate template = TemplateProject.getInstance().get(
					this.templateIndex);
			template.build(project);
		}
		{
			ILaunchConfiguration lc = addLaunchConfig(project);
			lc.launch("run", BProgressMonitor.DEFAULT);
			ElementManager.getInstance().getRoot().refresh(1);
		}
	}

	public static ILaunchConfiguration addLaunchConfig(IProject project)
			throws CoreException {
		ILaunchManager launchManager = DebugPlugin.getDefault()
				.getLaunchManager();
		ILaunchConfigurationType launchConfigurationType = launchManager
				.getLaunchConfigurationType(BrederProjectConstant.LAUNCH_CONFIGURATION_ID);
		ILaunchConfigurationWorkingCopy lcwc = launchConfigurationType
				.newInstance(null, project.getName());
		lcwc
				.setAttribute(BrederProjectConstant.PROJECT_NAME, project
						.getName());
		lcwc.setAttribute(BrederProjectConstant.MAIN_CLASS_NAME, "Main");
		ILaunchConfiguration lc = lcwc.doSave();
		project.build(IncrementalProjectBuilder.FULL_BUILD,
				BProgressMonitor.DEFAULT);
		return lc;
	}

	@Override
	public void updateUI() {
		ProjectView.getInstance().getViewer().refresh();
	}

}
