package views.project;

import ui.tree.TreeFile;
import ui.tree.TreeFolder;
import breder.io.BDirectory;
import breder.io.BFile;

public class FolderTreeParent extends GenericTreeParent {
	
	public FolderTreeParent(BDirectory file) {
		super(file);
	}
	
	@Override
	public TreeFile getFileNode(BFile file) {
		if (file.getName().endsWith(".java")) {
			return new JavaTreeFile(file);
		} else {
			return new FileTreeFile(file);
		}
	}
	
	@Override
	public TreeFolder getFolderNode(BDirectory dir) {
		return new FolderTreeParent(dir);
	}
	
}
