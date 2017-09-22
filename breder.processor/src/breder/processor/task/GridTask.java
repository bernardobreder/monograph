package breder.processor.task;

import breder.processor.gui.MainFrame;
import breder.util.task.LocalTask;

public class GridTask extends LocalTask {

	private final boolean visible;

	public GridTask(boolean visible) {
		super();
		this.visible = visible;
	}

	@Override
	public void updateUI() {
		MainFrame.getInstance().getArchPanel().setGridVisible(visible);
		MainFrame.getInstance().getArchPanel().repaint();
	}

}
