package ui.tree;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TreeItem;

import util.Task;

public class TreeExpandTask extends Task {

	protected final TreeModel model;

	protected final TreeItem itemParent;

	protected final TreeFolder data;

	protected TreeData[] datas;

	public TreeExpandTask(TreeModel model, TreeItem item) {
		this.model = model;
		this.itemParent = item;
		data = (TreeFolder) item.getData();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void action() throws Throwable {
		datas = data.getChildren();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateUI() {
		if (!itemParent.isDisposed()) {
			itemParent.removeAll();
			for (TreeData data : datas) {
				TreeItem item = new TreeItem(itemParent, SWT.NONE);
				item.setData(data);
				item.setText(data.toString());
				if (data instanceof TreeFolder) {
					item.setExpanded(true);
					TreeItem aux = new TreeItem(item, SWT.NONE);
					aux.setText("Building");
				}
			}
		}
	}

}
