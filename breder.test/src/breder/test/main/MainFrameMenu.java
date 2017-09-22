package breder.test.main;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import breder.test.create.CreateTask;
import breder.test.main.task.CompileAllTask;
import breder.test.main.task.CompileFailsTask;
import breder.test.main.task.CompileTask;
import breder.test.main.task.DeleteTask;
import breder.test.main.task.DeployTask;
import breder.test.main.task.EnableFalseTask;
import breder.test.main.task.EnableTrueTask;
import breder.test.main.task.ExecuteTask;
import breder.test.main.task.OpenTask;
import breder.test.main.task.RenameTask;
import breder.util.so.SoUtil;
import breder.util.swing.BMenuItem;
import breder.util.task.FinishFrameTask;

public class MainFrameMenu extends JMenuBar {

	private JCheckBoxMenuItem memoryTestOption;

	public MainFrameMenu(MainFrame frame) {
		this.add(this.buildProgram(frame));
		this.add(this.buildTest());
		this.add(this.buildOption());
		this.add(this.buildHelp());
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

	private JMenu buildOption() {
		JMenu menu = new JMenu("Option");
		menu.add(this.memoryTestOption = new JCheckBoxMenuItem("Test Memory"));
		return menu;
	}

	private JMenu buildTest() {
		JMenu menu = new JMenu("Test");
		menu.add(new BMenuItem("New", new CreateTask(), KeyStroke.getKeyStroke(
				'N', SoUtil.CTRL_MASK)));
		menu.add(new BMenuItem("Rename", new RenameTask(), KeyStroke
				.getKeyStroke('R', SoUtil.CTRL_MASK)));
		menu.add(new BMenuItem("Delete", new DeleteTask(), KeyStroke
				.getKeyStroke('D', SoUtil.CTRL_MASK)));
		menu.addSeparator();
		menu.add(new BMenuItem("Compile", new CompileTask(), KeyStroke
				.getKeyStroke('C', SoUtil.CTRL_MASK)));
		menu.add(new BMenuItem("Compile All", new CompileAllTask(), KeyStroke
				.getKeyStroke('B', SoUtil.CTRL_MASK)));
		menu.add(new BMenuItem("Compile Fails", new CompileFailsTask(),
				KeyStroke.getKeyStroke('B', SoUtil.CTRL_MASK
						| SoUtil.SHIFT_MASK)));
		menu.add(new BMenuItem("Execute", new ExecuteTask(), KeyStroke
				.getKeyStroke('E', SoUtil.CTRL_MASK)));
		menu.addSeparator();
		menu.add(new BMenuItem("Enable True", new EnableTrueTask(), KeyStroke
				.getKeyStroke(']', SoUtil.CTRL_MASK)));
		menu.add(new BMenuItem("Enable False", new EnableFalseTask(), KeyStroke
				.getKeyStroke('[', SoUtil.CTRL_MASK)));
		menu.addSeparator();
		menu.add(new BMenuItem("Deploy", new DeployTask(), KeyStroke
				.getKeyStroke('P', SoUtil.CTRL_MASK)));
		return menu;
	}

	private JMenu buildHelp() {
		JMenu menu = new JMenu("Help");
		return menu;
	}

	public JCheckBoxMenuItem getMemoryTestOption() {
		return memoryTestOption;
	}

}
