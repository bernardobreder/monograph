package breder.ide.lang.c;

import java.io.File;

import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.GenericDirectoryTreeNode;

public class IncludeDirectoryTreeNode extends GenericDirectoryTreeNode {
	
	public IncludeDirectoryTreeNode(AbstractTreeNode parent, File dir) {
		super(parent, dir);
	}
	
}
