package breder.info.gui.api;

import breder.util.swing.tree.BTree;

public class ApiTree extends BTree {

	public ApiTree() {
		super(new ApiModel());
		this.setCellRenderer(new ApiTreeRenderer());
	}
}
