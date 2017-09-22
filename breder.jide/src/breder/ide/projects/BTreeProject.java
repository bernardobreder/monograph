package breder.ide.projects;

import javax.swing.JPopupMenu;

import breder.ide.task.OpenTask;
import breder.util.swing.table.IOpenCellListener;
import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.BTree;
import breder.util.swing.tree.GenericFileTreeNode;

public class BTreeProject extends BTree {
	
	public BTreeProject() {
		super(RootProjectNode.getInstance());
		this.add(new IOpenCellListener<AbstractTreeNode>() {
			
			@Override
			public JPopupMenu getPopupMenu(int row, AbstractTreeNode cell) {
				return null;
			}
			
			@Override
			public void actionPerformed(int row, AbstractTreeNode cell) {
				if (cell instanceof GenericFileTreeNode) {
					GenericFileTreeNode node = (GenericFileTreeNode) cell;
					new OpenTask(node).start();
				}
			}
		});
	}
	
}
