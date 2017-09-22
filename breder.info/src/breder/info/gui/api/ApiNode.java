package breder.info.gui.api;

import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.DirectoryTreeNode;

public abstract class ApiNode<E> extends DirectoryTreeNode {

	private E element;

	public ApiNode(AbstractTreeNode parent) {
		super(parent);
	}

	public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

}
