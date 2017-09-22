package breder.ide.lang.c;

import java.io.File;

import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.GenericDirectoryTreeNode;

public class SourceDirectoryTreeNode extends GenericDirectoryTreeNode {
	
	public SourceDirectoryTreeNode(AbstractTreeNode parent, File dir) {
		super(parent, dir);
	}
	
}
