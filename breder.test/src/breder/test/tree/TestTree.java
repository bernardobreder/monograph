package breder.test.tree;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

import breder.util.swing.tree.BTree;
import breder.util.swing.tree.ITreeNode;

public class TestTree extends BTree {

	public TestTree() {
		super(new TreeTestModel());
		{
			final TreeCellRenderer renderer = this.getCellRenderer();
			this.setCellRenderer(new DefaultTreeCellRenderer() {
				@Override
				public Component getTreeCellRendererComponent(JTree tree,
						Object value, boolean sel, boolean expanded,
						boolean leaf, int row, boolean hasFocus) {
					JLabel c = (JLabel) renderer.getTreeCellRendererComponent(
							tree, value, sel, expanded, leaf, row, hasFocus);
					if (value instanceof ITreeNode) {
						ITreeNode node = (ITreeNode) value;
						Icon icon = node.getIcon();
						if (icon != null) {
							c.setIcon(icon);
						}
					}
					return c;
				}
			});
		}
		this.getSelectionModel().addTreeSelectionListener(
				new TreeSelectionListener() {
					@Override
					public void valueChanged(TreeSelectionEvent e) {
						TestTreeNode node = (TestTreeNode) e.getPath()
								.getLastPathComponent();
						new TestOpenTask(node.getTest()).start();
					}
				});
	}
}
