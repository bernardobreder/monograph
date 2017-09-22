package breder.risk.main;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;

import breder.risk.create.CreateTask;
import breder.util.so.SoUtil;
import breder.util.swing.BMenuItem;
import breder.util.task.FinishFrameTask;

public class MainFrameMenu extends JMenuBar {

	public MainFrameMenu(MainFrame frame) {
		this.add(this.buildProgram(frame));
		this.add(this.buildSource());
	}

	private JMenu buildProgram(MainFrame frame) {
		JMenu menu = new JMenu("Program");
		menu.add(new BMenuItem("Open", new OpenTask(frame), KeyStroke
				.getKeyStroke('O', SoUtil.CTRL_MASK)));
		menu.addSeparator();
		menu.add(new BMenuItem("Exit", new FinishFrameTask(frame), KeyStroke
				.getKeyStroke('Q', SoUtil.CTRL_MASK)));
		return menu;
	}

	private JMenu buildSource() {
		JMenu menu = new JMenu("Test");
		menu.add(new BMenuItem("New", new CreateTask(), KeyStroke.getKeyStroke(
				'N', SoUtil.CTRL_MASK)));
		menu.add(new BMenuItem("Rename", new RenameTask(), KeyStroke
				.getKeyStroke('R', SoUtil.CTRL_MASK)));
		menu.add(new BMenuItem("Delete", new DeleteTask(), KeyStroke
				.getKeyStroke('D', SoUtil.CTRL_MASK)));
		return menu;
	}

}
