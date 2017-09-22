package breder.org.lang.gui;

import breder.org.disk.IFileSystem;

public class RootTreeNode extends FolderNode {

	private static final RootTreeNode instance = new RootTreeNode();

	private RootTreeNode() {
		super(null, IFileSystem.DEFAULT.getFolder("lang"));
	}

	@Override
	public String toString() {
		return "";
	}

	public static RootTreeNode getInstance() {
		return instance;
	}

}
