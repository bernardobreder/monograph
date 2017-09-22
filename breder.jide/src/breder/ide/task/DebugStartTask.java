package breder.ide.task;

import breder.ide.MainFrame;
import breder.ide.debug.DebugManager;
import breder.ide.lang.IDebug;
import breder.ide.lang.Project;
import breder.ide.lang.c.GdbProcess;
import breder.ide.projects.ProjectNode;
import breder.util.swing.tree.AbstractTreeNode;

public class DebugStartTask extends GenericTask {
	
	public DebugStartTask() {
		super("Starting the Debug");
	}
	
	@Override
	public void perform() throws Throwable {
		for (AbstractTreeNode node : MainFrame.getInstance().getTree().getSelectNodes()) {
			Project project = node.getParent(ProjectNode.class).getProject();
			if (project instanceof IDebug) {
				if (project.getTargets().size() > 0) {
					((IDebug) project).debug();
					GdbProcess process = DebugManager.getInstance().getProcess();
					{
						OpenTask task = new OpenTask(process.getFile());
						task.setLine(process.getLine());
						task.start();
					}
					new VariableRefreshTask().start();
				}
			}
		}
	}
	
	@Override
	public void updateUI() throws Throwable {
	}
	
}
