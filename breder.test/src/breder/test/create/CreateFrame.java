
package breder.test.create;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import breder.test.editor.EditorText;
import breder.test.main.ConsoleText;
import breder.test.model.AbstractTest;
import breder.test.model.ITest;
import breder.test.model.Source;
import breder.util.swing.BAction;
import breder.util.swing.BFrame;
import breder.util.swing.BLabelTextField;
import breder.util.swing.layout.VerticalLayout;
import breder.util.task.FinishFrameTask;

public class CreateFrame extends BFrame {

	private static final int SOURCE_COUNT = 5;

	private JButton compileButton;

	private JPanel panel;

	private BLabelTextField nameField;

	private List<EditorText> sources = new ArrayList<EditorText>();

	private JTextArea expected;

	private ConsoleText consoleText;

	public CreateFrame(BFrame parent, ITest content) {
		super(parent);
		this.setTitle("Breder Test - Create Test");
		this.build(content);
		this.pack();
		this.setMinimumSize(this.getSize());
		this.setSize(800, 600);
		this.setLocationRelativeTo(parent);
		if (content != null) {
			new CompileTestTask(this).start();
		}
	}

	private void build(ITest content) {
		this.setLayout(new BorderLayout());
		this.add(this.buildNorth(), BorderLayout.NORTH);
		this.add(this.buildCenter(content), BorderLayout.CENTER);
		this.add(this.buildSouth(), BorderLayout.SOUTH);
	}

	private Component buildNorth() {
		JPanel panel = new JPanel();
		return panel;
	}

	private Component buildCenter(ITest test) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(this.buildExpected(test), BorderLayout.NORTH);
		panel.add(this.buildSources(test), BorderLayout.CENTER);
		panel.add(this.buildConsole(), BorderLayout.SOUTH);
		return panel;
	}

	private Component buildExpected(ITest test) {
		JPanel panel = new JPanel(new VerticalLayout());
		String name = test == null ? "" : test.getName();
		panel.add(this.nameField = new BLabelTextField("Name :", name));
		{
			JScrollPane scroll = new JScrollPane(this.expected = new JTextArea());
			scroll.setBorder(new TitledBorder("Expected"));
			scroll.setPreferredSize(new Dimension(100, 100));
			if (test != null) {
				for (int n = 0; n < test.getExpected().size(); n++) {
					this.expected.setText(this.expected.getText() + test.getExpected().get(n));
					if (n != test.getExpected().size() - 1) {
						this.expected.setText(this.expected.getText() + "\n");
					}
				}
			}
			panel.add(scroll);
		}
		return panel;
	}

	private Component buildConsole() {
		JScrollPane scroll = new JScrollPane(this.consoleText = new ConsoleText());
		scroll.setBorder(new TitledBorder("Console"));
		scroll.setPreferredSize(new Dimension(100, 100));
		return scroll;
	}

	private Component buildSources(ITest test) {
		this.panel = new JPanel(new VerticalLayout());
		JScrollPane scroll = new JScrollPane(panel);
		scroll.setPreferredSize(new Dimension(500, 300));
		scroll.setBorder(new TitledBorder("Source"));
		for (int n = 0; n < SOURCE_COUNT; n++) {
			EditorText text = this.buildSource("breder.Test" + (n == 0 ? "" : n));
			if (test != null) {
				if (n < test.getSources().size()) {
					String content = test.getSources().get(n).getCode();
					text.setText(content);
				}
			}
			this.sources.add(text);
		}
		return scroll;
	}

	private EditorText buildSource(String classname) {
		EditorText text = new EditorText();
		text.setEditable(true);
		JScrollPane scroll = new JScrollPane(text);
		scroll.setBorder(new TitledBorder(classname));
		scroll.setPreferredSize(new Dimension(100, 300));
		this.panel.add(scroll);
		this.panel.getParent().getParent().validate();
		return text;
	}

	private Component buildSouth() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.add(new JButton(new BAction("Expected", new ExpectedTestTask(this))));
		panel.add(this.compileButton = new JButton(new BAction("Compile", new CompileTestTask(this))));
		panel.add(new JButton(new BAction("Build", new BuildTestTask(this))));
		panel.add(new JButton(new BAction("Replace", new ReplaceTestTask(this))));
		panel.add(new JButton(new BAction("Close", new FinishFrameTask(this))));
		this.getRootPane().setDefaultButton(this.compileButton);
		this.compileButton.setMnemonic('C');
		return panel;
	}

	public ITest getBuildTest() {
		AbstractTest test = new AbstractTest();
		String name = this.nameField.getField().getText().trim();
		test.setName(name);
		test.setMainClass("breder.Test");
		{
			List<Source> list = new ArrayList<Source>();
			for (int n = 0; n < SOURCE_COUNT; n++) {
				String text = this.sources.get(n).getText().trim();
				if (text.length() > 0) {
					list.add(new Source("breder.Test" + (n == 0 ? "" : n), text));
				}
			}
			test.setSources(list);
		}
		{
			String[] lines = expected.getText().split("\n");
			test.setExpected(Arrays.asList(lines));
		}
		return test;
	}

	public void setConsoleText(String text) {
		this.consoleText.setText(text);
	}

	public void copyToExpected() {
		this.expected.setText(this.consoleText.getText());
	}

	public void cleanConsole() {
		this.consoleText.setText("");
	}

	public void appendConsole(String text) {
		this.consoleText.setText(this.consoleText.getText() + text);
	}

}
