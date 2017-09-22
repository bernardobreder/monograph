package ui.tree;

import org.eclipse.swt.events.TreeEvent;
import org.eclipse.swt.events.TreeListener;
import org.eclipse.swt.widgets.TreeItem;

public class TreeExpandListener extends TreeGenericListener implements
		TreeListener {

	public TreeExpandListener(TreeModel model) {
		super(model);
		this.getModel().tree.addTreeListener(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void treeExpanded(TreeEvent e) {
		TreeItem item = (TreeItem) e.item;
		TreeData data = (TreeData) item.getData();
		data.setExpandTask(new TreeExpandTask(this.getModel(), item));
		data.getExpandTask().start();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void treeCollapsed(TreeEvent e) {
		TreeItem item = (TreeItem) e.item;
		TreeData data = (TreeData) item.getData();
		data.getExpandTask().interrupt();
	}

}
