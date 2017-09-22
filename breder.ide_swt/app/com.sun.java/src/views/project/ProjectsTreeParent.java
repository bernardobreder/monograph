package views.project;

import ui.tree.TreeFile;
import ui.tree.TreeFolder;
import breder.io.BDirectory;
import breder.io.BFile;

public class ProjectsTreeParent extends GenericTreeParent {
	
	public ProjectsTreeParent(BDirectory file) {
		super(file);
	}
	
	@Override
	public TreeFile getFileNode(BFile file) {
		return null;
	}
	
	@Override
	public TreeFolder getFolderNode(BDirectory dir) {
		return new ProjectTreeParent(dir);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Projects";
	}
	
}
