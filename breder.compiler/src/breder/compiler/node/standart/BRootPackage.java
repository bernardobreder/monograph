
package breder.compiler.node.standart;

import java.util.List;

import breder.compiler.filesystem.BrederFile;
import breder.compiler.filesystem.IBrederFileManager;
import breder.compiler.filesystem.IDirectory;
import breder.compiler.filesystem.IResource;
import breder.compiler.util.LightArrayList;

public class BRootPackage extends BPackage {

	public BRootPackage() {
		super(null, null);
	}

	public void refresh() {
		this.packages = null;
	}

	@Override
	public List<BrederFile> getFiles() {
		if (this.classes == null) {
			classes = new LightArrayList<BrederFile>();
			IResource[] resources = IBrederFileManager.DEFAULT.list();
			for (IResource resource : resources) {
				if (resource instanceof BrederFile) {
					classes.add((BrederFile) resource);
				}
			}
		}
		return classes;
	}

	@Override
	public List<BPackage> getPackages() {
		if (this.packages == null) {
			packages = new LightArrayList<BPackage>();
			IResource[] resources = IBrederFileManager.DEFAULT.list();
			for (IResource resource : resources) {
				if (resource instanceof IDirectory) {
					IDirectory dir = (IDirectory) resource;
					packages.add(new BPackage(dir, null));
				}
			}
		}
		return packages;
	}

}
