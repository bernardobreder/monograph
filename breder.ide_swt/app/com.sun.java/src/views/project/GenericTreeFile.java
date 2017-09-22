package views.project;

import ui.tree.TreeFile;
import breder.io.BResource;

public abstract class GenericTreeFile extends TreeFile {
	
	protected final BResource file;
	
	public GenericTreeFile(BResource file) {
		super();
		this.file = file;
	}
	
	public BResource getFile() {
		return file;
	}
	
	@Override
	public String toString() {
		return file.getName();
	}
}
