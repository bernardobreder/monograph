package breder.ide.task;

import breder.ide.MainFrame;
import breder.ide.lang.IRun;
import breder.ide.lang.Project;
import breder.ide.projects.ProjectNode;
import breder.util.swing.tree.AbstractTreeNode;
import breder.util.task.LocalTask;

public class RunTask extends LocalTask {

	@Override
	public void updateUI() throws Throwable {
		int id = MainFrame.getInstance().buildConsole("name");
		new BuildTask(id).start();
	}

	private static class BuildTask extends GenericTask {

		private int consoleId;

		public BuildTask(int consoleId) {
			super("Carregando o Projeto");
			this.consoleId = consoleId;
		}

		@Override
		public void perform() throws Throwable {
			AbstractTreeNode node = MainFrame.getInstance().getTree()
					.getSelectNode();
			if (node != null) {
				ProjectNode projectNode = node.getParent(ProjectNode.class);
				Project project = projectNode.getProject();
				if (project instanceof IRun) {
					if (project.getTargets().size() > 0) {
						((IRun) project).run(this.consoleId);
					}
				}
			}
		}

		@Override
		public void updateUI() throws Throwable {
		}

	}
}
