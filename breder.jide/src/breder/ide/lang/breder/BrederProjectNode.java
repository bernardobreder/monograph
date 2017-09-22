package breder.ide.lang.breder;

import java.io.File;
import java.text.ParseException;
import java.util.List;

import breder.ide.lang.ILanguage;
import breder.ide.lang.LanguageManager;
import breder.ide.lang.Project;
import breder.ide.lang.ProjectManager;
import breder.ide.lang.c.SourceDirectoryTreeNode;
import breder.ide.projects.ProjectNode;
import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.GenericDirectoryTreeNode;
import breder.xml.ITag;

public class BrederProjectNode extends ProjectNode {
	
	public BrederProjectNode(AbstractTreeNode parent, Project project, ITag projectTag)
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
			}
		}
		return nodes;
	}
	
	public BrederProject getProject() {
		return (BrederProject) super.getProject();
	}
	
}
