package views.project;

import java.util.ArrayList;
import java.util.List;

import ui.tree.StaticTreeParent;
import ui.tree.TreeData;
import ui.tree.TreeFile;
import ui.tree.TreeFolder;
import breder.io.BDirectory;
import breder.io.BFile;
import breder.io.BResource;

public abstract class GenericTreeParent extends StaticTreeParent {
	
	protected final BDirectory file;
	
	public GenericTreeParent(BDirectory file) {
		super();
		this.file = file;
	}
	
	public abstract TreeFolder getFolderNode(BDirectory dir);
	
	public abstract TreeFile getFileNode(BFile file);
	
	@Override
	public TreeData[] getChildren() {
		List<TreeData> list = new ArrayList<TreeData>();
		for (BResource resource : this.getResource().listFiles()) {
			if (!resource.isHidden()) {
				if (resource.isDirectory()) {
					BDirectory dir = (BDirectory) resource;
					TreeData data = this.getFolderNode(dir);
					if (data != null) {
						list.add(data);
					}
				} else {
					BFile file = (BFile) resource;
					TreeData data = this.getFileNode(file);
					if (data != null) {
						list.add(data);
					}
				}
			}
		}
		return list.toArray(new TreeData[0]);
	}
	
	public BDirectory getResource() {
		return file;
	}
	
	@Override
	public String toString() {
		return file.getName();
	}
	
}
