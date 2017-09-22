package breder.ide.debug;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import breder.ide.lang.c.GdbProcess;

public class DebugManager {
	
	private static final DebugManager instance = new DebugManager();
	
	private final List<GdbProcess> processes = new ArrayList<GdbProcess>();
	
	private DebugManager() {
	}
	
	public void addProcess(GdbProcess process) {
		this.processes.add(process);
	}
	
	public void removeProcess(GdbProcess process) {
		this.processes.remove(process);
	}
	
	public List<GdbProcess> getProcesses() {
		return Collections.unmodifiableList(this.processes);
	}
	
	public GdbProcess getProcess() {
		return this.processes.get(0);
	}
	
	public static DebugManager getInstance() {
		return instance;
	}
	
}
