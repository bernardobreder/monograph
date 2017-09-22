package breder.compiler.filesystem;

import java.io.File;

public class Directory extends Resource implements IDirectory {

	private IResource[] resources;

	public Directory(File path) {
		super(path);
		File[] files = super.file.listFiles();
		this.resources = new IResource[files.length];
		for (int n = 0; n < files.length; n++) {
			File file = files[n];
			if (file.isDirectory()) {
				resources[n] = new Directory(file);
			} else {
				if (file.getName().endsWith(".breder")) {
					resources[n] = new BrederFile(file);
				} else {
					resources[n] = new Resource(file);
				}
			}
		}
	}

	@Override
	public IResource[] list() {
		return this.resources;
	}

}
