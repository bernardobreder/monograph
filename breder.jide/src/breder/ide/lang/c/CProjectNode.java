package breder.ide.lang.c;

import java.io.File;
import java.text.ParseException;
import java.util.List;

import breder.ide.lang.Project;
import breder.ide.lang.ProjectManager;
import breder.ide.projects.ProjectNode;
import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.GenericDirectoryTreeNode;
import breder.util.util.FileUtil;
import breder.xml.ITag;

public class CProjectNode extends ProjectNode {
	
	public CProjectNode(AbstractTreeNode parent, Project project, ITag projectTag)
			throws ParseException {
		super(parent, project);
	}
	
	@Override
	public AbstractTreeNode[] getChildren() {
		AbstractTreeNode[] nodes = super.getChildren();
		for (int n = 0; n < nodes.length; n++) {
			AbstractTreeNode node = nodes[n];
			if (node instanceof GenericDirectoryTreeNode) {
				GenericDirectoryTreeNode dnode = (GenericDirectoryTreeNode) node;
				for (File source : this.getProject().getSources()) {
					if (dnode.getDir().equals(source)) {
						nodes[n] = new SourceDirectoryTreeNode(dnode.getParent(), dnode.getDir());
					}
				}
				for (File include : this.getProject().getIncludes()) {
					if (dnode.getDir().equals(include)) {
						nodes[n] = new IncludeDirectoryTreeNode(dnode.getParent(), dnode.getDir());
					}
				}
			}
		}
		return nodes;
	}
	
	
	public CProject getProject() {
		return (CProject) super.getProject();
	}
	
}
