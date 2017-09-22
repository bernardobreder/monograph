package breder.ide.task;

import java.util.List;

import breder.ide.debug.DebugManager;
import breder.ide.lang.c.GdbProcess;

public class DebugStopTask extends GenericTask {
	
	public DebugStopTask() {
		super("Stoping the Debug");
	}
	
	@Override
	public void perform() throws Throwable {
		List<GdbProcess> processes = DebugManager.getInstance().getProcesses();
		if (processes.size() == 0) {
			return;
		}
		GdbProcess process = processes.get(0);
		DebugManager.getInstance().removeProcess(process);
		process.exit();
		new VariableRefreshTask().start();
	}
	
	@Override
	public void updateUI() throws Throwable {
	}
	
}
