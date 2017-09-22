package breder.ide.task;

import java.util.List;

import breder.ide.debug.DebugManager;
import breder.ide.lang.c.GdbProcess;

public class DebugStepTask extends GenericTask {
	
	public DebugStepTask() {
		super("Step the Debug");
	}
	
	@Override
	public void perform() throws Throwable {
		List<GdbProcess> processes = DebugManager.getInstance().getProcesses();
		if (processes.size() == 0) {
			return;
		}
		GdbProcess process = processes.get(0);
		process.nextIn();
		{
			OpenTask task = new OpenTask(process.getFile());
			task.setLine(process.getLine());
			task.start();
		}
		new VariableRefreshTask().start();
	}
	
	@Override
	public void updateUI() throws Throwable {
	}
	
}
