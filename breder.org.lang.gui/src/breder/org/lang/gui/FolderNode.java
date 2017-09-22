package breder.org.lang.gui;

import java.util.ArrayList;
import java.util.List;

import breder.org.disk.IFile;
import breder.org.disk.IFolder;
import breder.org.disk.IResource;
import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.DirectoryTreeNode;

public class FolderNode extends DirectoryTreeNode {

	private IFolder folder;

	public FolderNode(AbstractTreeNode parent, IFolder folder) {
		super(parent);
		this.folder = folder;
	}

	@Override
	public AbstractTreeNode[] getChildren() {
		List<AbstractTreeNode> list = new ArrayList<AbstractTreeNode>();
		for (IResource resource : this.folder.listResources()) {
			if (resource instanceof IFile) {
				IFile file = (IFile) resource;
				list.add(new DocumentNode(null, file));
			} else {
				IFolder folder = (IFolder) resource;
				list.add(new FolderNode(null, folder));
			}
		}
		return list.toArray(new AbstractTreeNode[0]);
	}

	@Override
	public String toString() {
		return folder.getName();
	}

}
