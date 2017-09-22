package breder.org.lang.gui;

import breder.org.disk.IFile;
import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.FileTreeNode;

public class DocumentNode extends FileTreeNode {

	private IFile file;

	public DocumentNode(AbstractTreeNode parent, IFile file) {
		super(parent);
		this.file = file;
	}

	@Override
	public String toString() {
		return file.getName();
	}

}
