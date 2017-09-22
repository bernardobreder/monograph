package breder.test.main;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import breder.test.editor.EditorText;
import breder.test.model.ITest;
import breder.test.model.Source;
import breder.util.swing.layout.VerticalLayout;
import breder.util.util.StringUtil;

public class TestPanel extends JPanel {

	private ITest test;

	public TestPanel(ITest test) {
		this.test = test;
		this.setLayout(new VerticalLayout());
		this.build();
	}

	private void build() {
		{
			EditorText pane = new EditorText();
			String expected = StringUtil.joinLine(test.getExpected());
			pane.setText(expected);
			JScrollPane scroll = new JScrollPane(pane);
			scroll.setBorder(new TitledBorder("Expected"));
			if (scroll.getPreferredSize().height > 200) {
				scroll.setPreferredSize(new Dimension(0, 200));
			}
			this.add(scroll);
		}
		List<Source> sources = test.getSources();
		for (Source source : sources) {
			EditorText pane = new EditorText();
			pane.setText(source.getCode());
			JScrollPane scroll = new JScrollPane(pane);
			scroll.setBorder(new TitledBorder(source.getName()));
			this.add(scroll);
		}
		{
			JPanel panel = new JPanel(new VerticalLayout());
			panel.setBorder(new TitledBorder("Execute Context"));
			panel.add(new JLabel(String.format("Timer : %d miliseconds",
					test.getTimer())));
			if (test.getMemory() != null) {
				panel.add(new JLabel(String.format("Memory : %d KBytes",
						test.getMemory())));
			}
			this.add(panel);
		}
	}

}
