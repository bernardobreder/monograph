package breder.org.lang.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import breder.util.swing.BFrame;

public class MainFrame extends BFrame {

	private static final MainFrame instance = new MainFrame();

	public MainFrame() {
		super(null);
		this.setTitle("Breder Language");
		this.build();
		this.setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	private void build() {
		this.setLayout(new BorderLayout());
		this.buildNorth();
		this.add(this.buildLeft(), BorderLayout.WEST);
		this.add(this.buildRight(), BorderLayout.EAST);
		this.buildCenter();
		this.buildSouth();
	}

	private Component buildLeft() {
		DocumentTree tree = new DocumentTree();
		JScrollPane scroll = new JScrollPane(tree);
		scroll.setPreferredSize(new Dimension(300,0));
		return scroll;
	}

	private Component buildRight() {
		JPanel panel = new JPanel();
		return panel;
	}

	private void buildNorth() {
	}

	private void buildCenter() {
		JTabbedPane pane = new JTabbedPane();
		pane.addTab("Preview", this.buildEditorPreview());
		pane.addTab("Code", this.buildEditorCode());
		this.add(pane, BorderLayout.CENTER);
	}

	private JPanel buildEditorPreview() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(this.buildEditorTool(), BorderLayout.NORTH);
		panel.add(new JScrollPane(this.buildEditor()), BorderLayout.CENTER);
		return panel;
	}

	private JPanel buildEditorCode() {
		JPanel panel = new JPanel(new BorderLayout());
		JTextArea area = new JTextArea();
		panel.add(new JScrollPane(area), BorderLayout.CENTER);
		return panel;
	}

	private Component buildEditor() {
		JEditorPane pane = new JEditorPane();
		return pane;
	}

	private Component buildEditorTool() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		return panel;
	}

	private void buildSouth() {
	}

	public static MainFrame getInstance() {
		return instance;
	}

}
