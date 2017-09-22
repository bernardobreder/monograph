package breder.ide.projects;

import java.util.ArrayList;
import java.util.List;

import breder.ide.lang.Project;
import breder.ide.lang.ProjectManager;
import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.DirectoryTreeNode;

public class RootProjectNode extends DirectoryTreeNode {
	
	private static final RootProjectNode instance = new RootProjectNode();
	
	private RootProjectNode() {
		super(null);
	}
	
	@Override
	public AbstractTreeNode[] getChildren() {
		List<AbstractTreeNode> nodes = new ArrayList<AbstractTreeNode>();
		for (Project project : ProjectManager.getInstance().getProjects()) {
			nodes.add(new ProjectNode(this, project));
		}
		return nodes.toArray(new AbstractTreeNode[0]);
	}
	
	@Override
	public String toString() {
		return "root";
	}
	
	public static RootProjectNode getInstance() {
		return instance;
	}
	
}
