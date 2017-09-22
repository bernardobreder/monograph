package breder.processor.gui;

import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import breder.processor.task.GridTask;
import breder.util.swing.BAction;
import breder.util.task.LocalTask;

public class MainFrameTool extends JToolBar {

	private JToggleButton gridButton;

	public MainFrameTool() {
		super();
		this.add(this.gridButton = new JToggleButton(new BAction("Grid",
				new LocalTask() {
					@Override
					public void updateUI() {
						onGridAction();
					}
				})));
		this.add(new Separator());
		this.add(new JButton(new BAction("Stop", new LocalTask() {
			@Override
			public void updateUI() {
				onStopAction();
			}
		})));
		this.add(new JButton(new BAction("Run", new LocalTask() {
			@Override
			public void updateUI() {
				onRunAction();
			}
		})));
		this.add(new JButton(new BAction("Debug", new LocalTask() {
			@Override
			public void updateUI() {
				onDebugAction();
			}
		})));
		this.add(new JButton(new BAction("Next", new LocalTask() {
			@Override
			public void updateUI() {
				onNextAction();
			}
		})));
	}

	protected void onStopAction() {
		// TODO Auto-generated method stub

	}

	protected void onRunAction() {
		// TODO Auto-generated method stub

	}

	protected void onDebugAction() {
		// TODO Auto-generated method stub

	}

	protected void onNextAction() {
		// TODO Auto-generated method stub

	}

	protected void onGridAction() {
		new GridTask(this.gridButton.getModel().isSelected()).start();
	}

}
