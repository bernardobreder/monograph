package breder.ide.projects;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import breder.ide.NodeUtil;
import breder.ide.lang.Project;
import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.DirectoryTreeNode;
import breder.util.swing.tree.GenericDirectoryTreeNode;
import breder.util.swing.tree.GenericFileTreeNode;

public class ProjectNode extends DirectoryTreeNode {
	
	private Project project;
	
	public ProjectNode(AbstractTreeNode parent, Project project) {
		super(parent);
		this.project = project;
	}
	
	@Override
	public AbstractTreeNode[] getChildren() {
		List<AbstractTreeNode> nodes = new ArrayList<AbstractTreeNode>();
		for (File file : this.getProject().getDir().listFiles()) {
			if (NodeUtil.accept(file)) {
				if (file.isDirectory()) {
					nodes.add(new GenericDirectoryTreeNode(this, file));
				} else {
					nodes.add(new GenericFileTreeNode(this, file));
				}
			}
		}
		return nodes.toArray(new AbstractTreeNode[0]);
	}
	
	@Override
	public String toString() {
		return project.getName();
	}
	
	public File getDir() {
		return project.getDir();
	}
	
	public Project getProject() {
		return project;
	}
	
	protected void setProject(Project project) {
		this.project = project;
	}
	
}
