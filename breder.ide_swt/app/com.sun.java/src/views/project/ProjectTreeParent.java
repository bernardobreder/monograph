package views.project;

import ui.tree.TreeFile;
import ui.tree.TreeFolder;
import breder.io.BDirectory;
import breder.io.BFile;

public class ProjectTreeParent extends GenericTreeParent {
	
	public ProjectTreeParent(BDirectory file) {
		super(file);
	}
	
	@Override
	public TreeFile getFileNode(BFile file) {
		return null;
	}
	
	@Override
	public TreeFolder getFolderNode(BDirectory dir) {
		if (dir.getName().equals("src")) {
			return new SourceTreeParent(dir);
		}
		return null;
	}
}
