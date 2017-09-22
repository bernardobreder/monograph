package breder.ide.task;

import java.util.List;

import breder.ide.debug.DebugManager;
import breder.ide.lang.c.GdbProcess;

public class DebugNextTask extends GenericTask {
	
	private Integer timer;
	
	public DebugNextTask() {
		this(1);
	}
	
	public DebugNextTask(Integer timer) {
		super("Next the Debug");
		this.timer = timer;
	}
	
	@Override
	public void perform() throws Throwable {
		List<GdbProcess> processes = DebugManager.getInstance().getProcesses();
		if (processes.size() == 0) {
			return;
		}
		GdbProcess process = processes.get(0);
		for (int n = 0; n < this.timer; n++) {
			process.next();
		}
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
