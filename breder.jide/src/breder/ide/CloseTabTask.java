package breder.ide;

import java.awt.Component;
import java.awt.Container;

import breder.util.task.LocalTask;

public class CloseTabTask extends LocalTask {
	
	private final Component component;
	
	private final Container pane;
	
	public CloseTabTask(Container pane, Component component) {
		super();
		this.pane = pane;
		this.component = component;
	}
	
	@Override
	public void updateUI() throws Throwable {
		this.pane.remove(component);
	}
	
}
