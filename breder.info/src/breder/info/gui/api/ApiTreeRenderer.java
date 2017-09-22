package breder.info.gui.api;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;

public class ApiTreeRenderer extends JPanel implements TreeCellRenderer {

	public ApiTreeRenderer() {
		this.setLayout(new BorderLayout());
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		ApiNode<?> node = (ApiNode<?>) value;
		this.removeAll();
		if (node.getElement() instanceof String) {
			this.add(new JScrollPane(new JTextArea(node.getElement().toString())));
		} else {
			this.add(new JLabel(node.toString()));
		}
		return this;
	}
}
