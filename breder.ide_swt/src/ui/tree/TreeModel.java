package ui.tree;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class TreeModel {
	
	protected final Tree tree;
	
	protected final TreeFolder root;
	
	public TreeModel(Tree tree, TreeFolder root) {
		super();
		this.tree = tree;
		this.root = root;
		new TreeExpandListener(this);
		new TreeKeyListener(this);
		for (TreeData data : root.getChildren()) {
			TreeItem item = new TreeItem(tree, SWT.NONE);
			item.setData(data);
			item.setText(data.toString());
			new TreeExpandTask(this, item).start();
		}
	}
	
	public void update(Tree tree, TreeFolder root) {
		for (TreeData data : root.getChildren()) {
			TreeItem item = new TreeItem(tree, SWT.NONE);
			item.setData(data);
			item.setText(data.toString());
			if (data instanceof TreeFolder) {
				item.setExpanded(true);
				TreeItem aux = new TreeItem(item, SWT.NONE);
				aux.setText("Building");
			}
		}
	}
	
	public Tree getTree() {
		return tree;
	}
	
	public TreeFolder getRoot() {
		return root;
	}
	
}
