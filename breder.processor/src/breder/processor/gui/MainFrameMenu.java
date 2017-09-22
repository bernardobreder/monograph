package breder.processor.gui;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import breder.processor.task.GridTask;
import breder.util.swing.BAction;
import breder.util.task.LocalTask;

public class MainFrameMenu extends JMenuBar {

	private JCheckBoxMenuItem gridMenu;

	public MainFrameMenu() {
		// this.add(this.buildView());
	}

	private JMenu buildView() {
		JMenu menu = new JMenu("View");
		menu.add(this.gridMenu = new JCheckBoxMenuItem(new BAction("Grid",
				new LocalTask() {
					@Override
					public void updateUI() {
						new GridTask(gridMenu.getState()).start();
						gridMenu.setState(!gridMenu.getState());
					}
				})));
		return menu;
	}
}
