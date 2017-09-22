package breder.ide.task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import breder.ide.MainFrame;
import breder.ide.lang.IBuilder;
import breder.ide.lang.Project;
import breder.ide.projects.ProjectNode;
import breder.util.swing.tree.AbstractTreeNode;
import breder.util.util.FileUtil;

public class BuildTask extends GenericTask {
	
	private List<Project> projects = new ArrayList<Project>();
	
	public BuildTask() {
		super(String.format("Compilando o Projeto"));
		
	}
	
	public BuildTask(Project project) {
		super(String.format("Compilando o Projeto"));
		this.projects.add(project);
	}
	
	@Override
	public void perform() throws Throwable {
		for (AbstractTreeNode node : MainFrame.getInstance().getTree().getSelectNodes()) {
			ProjectNode projectNode = node.getParent(ProjectNode.class);
			Project project = projectNode.getProject();
			if (project instanceof IBuilder) {
				IBuilder builder = (IBuilder) project;
				if (project.getTargets().size() > 0) {
					FileUtil.remove(new File(project.getTargets().get(0)));
					builder.build();
				}
			}
		}
	}
	
	@Override
	public void updateUI() throws Throwable {
	}
	
}
