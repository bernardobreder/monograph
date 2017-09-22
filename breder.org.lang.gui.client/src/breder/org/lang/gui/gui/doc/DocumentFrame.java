package breder.org.lang.gui.gui.doc;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import breder.org.lang.gui.Main;
import breder.util.so.SoUtil;
import breder.util.swing.BAction;
import breder.util.swing.BFrame;
import breder.util.swing.BMenuItem;
import breder.util.task.FinishFrameTask;

public class DocumentFrame extends BFrame {

	private static DocumentFrame instance;

	private DocumentTree tree;

	private DocumentArea area;

	public DocumentFrame() {
		super(null);
		this.setTitle("Breder Language - Documentation");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.build();
		this.buildMenu();
		this.pack();
		this.setMinimumSize(this.getSize());
		this.setSize(800, 600);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		instance = this;
	}

	private void buildMenu() {
		JMenuBar bar = new JMenuBar();
		bar.add(this.buildGeneralMenu());
		this.setJMenuBar(bar);
	}

	private JMenu buildGeneralMenu() {
		JMenu menu = new JMenu("General");
		menu.add(new BMenuItem(new BAction("New", new NewTask()), KeyStroke
				.getKeyStroke('N', SoUtil.CTRL_MASK)));
		menu.add(new JSeparator());
//		menu.add(new JMenuItem(new BAction("Save", new SaveTask())));
//		menu.add(new JSeparator());
		menu.add(new JMenuItem(new BAction("Exit", new FinishFrameTask(this))));
		return menu;
	}

	private void build() {
		this.setLayout(new BorderLayout());
		this.add(this.buildCenter(), BorderLayout.CENTER);
		this.add(this.buildLeft(), BorderLayout.WEST);
	}

	private Component buildCenter() {
		this.area = new DocumentArea();
		JScrollPane scroll = new JScrollPane(area);
		return scroll;
	}

	private Component buildLeft() {
		this.tree = new DocumentTree();
		JScrollPane scroll = new JScrollPane(this.tree);
		scroll.setPreferredSize(new Dimension(200, 50));
		return scroll;
	}

	public DocumentTree getTree() {
		return tree;
	}

	public DocumentArea getArea() {
		return area;
	}

	public static DocumentFrame getInstance() {
		return instance;
	}

}
