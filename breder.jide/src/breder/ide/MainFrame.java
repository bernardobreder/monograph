package breder.ide;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;

import breder.ide.debug.DebugModel;
import breder.ide.debug.VariableTreeModel;
import breder.ide.projects.BTreeProject;
import breder.ide.task.BuildTask;
import breder.ide.task.DebugNextTask;
import breder.ide.task.DebugStartTask;
import breder.ide.task.DebugStepTask;
import breder.ide.task.DebugStopTask;
import breder.ide.task.RunTask;
import breder.ide.task.SaveTask;
import breder.util.swing.BAction;
import breder.util.swing.BFrame;
import breder.util.swing.BMenuItem;
import breder.util.swing.GBC;
import breder.util.swing.tree.BTree;

public class MainFrame extends BFrame {

	private static final MainFrame instance = new MainFrame();

	private JTabbedPane tabbedPane;

	private JTextArea consoleText;

	private BTree variables;

	private JTabbedPane consoleTab;

	private JTabbedPane rightDownTab;

	private BTreeProject tree;

	private MainFrame() {
		super(null);
		this.setTitle("IDE");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.buildMenu();
		this.build();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	private void buildMenu() {
		JMenuBar menu = new JMenuBar();
		menu.add(this.buildFileMenu());
		this.setJMenuBar(menu);
	}

	private JMenu buildFileMenu() {
		JMenu menu = new JMenu("File");
		menu.add(new BMenuItem("Save", new SaveTask(), KeyStroke.getKeyStroke(
				'S', InputEvent.META_MASK)));
		menu.add(new BMenuItem("Build", new BuildTask(), KeyStroke
				.getKeyStroke('B', InputEvent.META_MASK)));
		menu.add(new BMenuItem("Run", new RunTask(), KeyStroke.getKeyStroke(
				'R', InputEvent.META_MASK)));
		menu.add(new BMenuItem("Debug", new DebugStartTask(), KeyStroke
				.getKeyStroke('D', InputEvent.META_MASK)));
		menu.add(new BMenuItem("Step", new DebugStepTask(), KeyStroke
				.getKeyStroke(KeyEvent.VK_F5, InputEvent.META_MASK)));
		menu.add(new BMenuItem("Next", new DebugNextTask(), KeyStroke
				.getKeyStroke(KeyEvent.VK_F6, InputEvent.META_MASK)));
		menu.add(new BMenuItem("Next2X", new DebugNextTask(2), KeyStroke
				.getKeyStroke(KeyEvent.VK_F6, InputEvent.SHIFT_MASK)));
		menu.add(new BMenuItem("Next4X", new DebugNextTask(4), KeyStroke
				.getKeyStroke(KeyEvent.VK_F6, InputEvent.SHIFT_MASK
						| InputEvent.META_MASK)));
		menu.add(new BMenuItem("Next8X", new DebugNextTask(8), KeyStroke
				.getKeyStroke(KeyEvent.VK_F6, InputEvent.SHIFT_MASK
						| InputEvent.META_MASK | InputEvent.ALT_MASK)));
		menu
				.add(new BMenuItem("Stop Debug", new DebugStopTask(), KeyStroke
						.getKeyStroke('D', InputEvent.META_MASK
								| InputEvent.ALT_MASK)));
		return menu;
	}

	private void build() {
		this.setLayout(new BorderLayout());
		this.add(this.buildLeft(), BorderLayout.WEST);
		this.add(this.buildCenter(), BorderLayout.CENTER);
		this.add(this.buildRight(), BorderLayout.EAST);
	}

	private Component buildRight() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(new TitledBorder("Right"));
		panel.setPreferredSize(new Dimension(300, 0));
		panel.add(this.buildVariable(), new GBC(0, 0).both());
		panel.add(this.buildRightDown(), new GBC(0, 1).both());
		return panel;
	}

	private Component buildRightDown() {
		this.rightDownTab = new JTabbedPane();
		this.rightDownTab.addTab("Debug", this.buildDebug());
		return this.rightDownTab;
	}

	private Component buildDebug() {
		BTree tree = new BTree(new DebugModel());
		return new JScrollPane(tree);
	}

	private Component buildVariable() {
		JTabbedPane tab = new JTabbedPane();
		this.variables = new BTree(new VariableTreeModel());
		tab.addTab("Variable", new JScrollPane(this.variables));
		return tab;
	}

	public int buildConsole(String title) {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel tool = new JPanel(new FlowLayout(FlowLayout.LEFT));
		{
			panel.add(tool, BorderLayout.NORTH);
		}
		{
			JTextArea text = new JTextArea();
			JScrollPane pane = new JScrollPane(text);
			panel.add(pane, BorderLayout.CENTER);
			tool.add(new JButton(new BAction("Close", new CloseTabTask(
					this.rightDownTab, panel))));
		}
		synchronized (this) {
			this.rightDownTab.addTab(title, panel);
			return this.rightDownTab.getTabCount() - 1;
		}
	}

	private Component buildLeft() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new TitledBorder("Projects"));
		panel.setPreferredSize(new Dimension(300, 0));
		this.tree = new BTreeProject();
		JScrollPane scroll = new JScrollPane(tree);
		panel.add(scroll, BorderLayout.CENTER);
		return panel;
	}

	private Component buildCenter() {
		this.tabbedPane = new JTabbedPane();
		this.tabbedPane.setBorder(new TitledBorder("Files"));
		return tabbedPane;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public IdeEditorPane getTextField() {
		int index = this.tabbedPane.getSelectedIndex();
		if (index < 0)
			return null;
		return this.getTextField(index);
	}

	public IdeEditorPane getTextField(int n) {
		return (IdeEditorPane) this.tabbedPane.getComponent(n);
	}

	public void printConsole(String line) {
		this.consoleText.append(line);
	}

	public void printConsoleLine(String line) {
		this.printConsole(line + "\n");
	}

	public static MainFrame getInstance() {
		return instance;
	}

	public BTreeProject getTree() {
		return tree;
	}

	public BTree getVariableTree() {
		return this.variables;
	}

	public JTabbedPane getConsoleTab() {
		return consoleTab;
	}

	public JTextComponent getConsole(Integer index) {
		JPanel panel = (JPanel) this.rightDownTab.getComponent(index);
		for (Component component : panel.getComponents()) {
			if (component instanceof JScrollPane) {
				return (JTextComponent) ((JScrollPane) component).getViewport()
						.getView();
			}
		}
		return null;
	}

	public void removeConsole(Integer index) {
		this.rightDownTab.remove(index);
	}

}
