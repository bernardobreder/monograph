package ui.tree;

import java.util.ArrayList;
import java.util.List;

public abstract class DynamicTreeParent<E> extends TreeFolder {

	/** Lista com os itens */
	private final List<TreeData> children = new ArrayList<TreeData>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addChild(TreeData child) {
		children.add(child);
		child.setParent(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeChild(TreeData child) {
		children.remove(child);
		child.setParent(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TreeData[] getChildren() {
		return children.toArray(new TreeData[children.size()]);
	}

}
